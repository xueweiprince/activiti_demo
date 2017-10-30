package test;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Created by xuewei on 2017/4/27.
 */
public class Test {

    public static void main(String arg0[]){
//        Md5PasswordEncoder md5=new Md5PasswordEncoder();
//        String encryptionpassword=md5.encodePassword("123456", "administrator");
//        System.out.println(encryptionpassword);
        while (true) {
            try {
                Jedis jedis = new Jedis("localhost", 6379);

                UUID taskid = UUID.randomUUID();
                //将任务插入任务队列：task-queue
                jedis.lpush("task-queue", taskid.toString());
                System.out.println("插入了一个新的任务： " + taskid);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}
