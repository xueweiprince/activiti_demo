import com.st.demo.web.dao.IRedisDAO;
import com.st.demo.web.pojo.RedisTestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuewei on 2017/10/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-activiti.xml"})
public class BaseJunit4Test {

    @Autowired
    IRedisDAO redisDAO;

    @Test   //标明是测试方法
//    @Transactional   //标明此方法需使用事务
//    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void sendMessage(){

        String msg = "Hello, Redis!";
        redisDAO.sendMessage("java", msg); //发布字符串消息


        RedisTestBean bean = new RedisTestBean("123456");
        bean.setName("Redis");
        bean.setOld((byte)2);
        bean.setSeliry((short)40);
        bean.setManbers(new String[]{"234567", "3456789"});
        redisDAO.sendMessage("java", bean); //发布一个普通的javabean消息


        Integer[] values = new Integer[]{21341,123123,12323};
        redisDAO.sendMessage("java", values);  //发布一个数组消息

    }

}
