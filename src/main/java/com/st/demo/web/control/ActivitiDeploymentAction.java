package com.st.demo.web.control;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.st.demo.web.util.FilenameUtils;


@Controller
@RequestMapping(value = "/activiti/deployment")
public class ActivitiDeploymentAction {

    @Autowired
    RepositoryService repositoryService;

    @RequestMapping(value = "deploy")
    public ModelAndView deploy(@RequestParam MultipartFile file) {

        try {
            //获取所要部暑的文件流
            InputStream inStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String suffix = FilenameUtils.getFileExtention(fileName);

            //创建流程部署所需的构建器
            DeploymentBuilder deployMentBuilder = repositoryService.createDeployment();

            if (suffix.equals("bar") || suffix.equals("zip")) {
                ZipInputStream zip = new ZipInputStream(inStream);
                deployMentBuilder.addZipInputStream(zip);
            } else {
                deployMentBuilder.addInputStream(fileName, inStream);
            }
            deployMentBuilder.deploy();
        } catch (Exception e) {
            System.err.println(e);
        }

        return new ModelAndView("activiti/activitiList");
    }

}
