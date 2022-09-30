import com.tao.config.Config;
import com.tao.pojo.Hello;
import com.tao.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Make {

    @Test
    public void test(){
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //getBean : 参数即为spring配置文件中bean的id .
        Hello hello = (Hello) context.getBean("hello");
        hello.show();
    }

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        User getUser = (User) context.getBean("getUser");
        User user = (User) context.getBean("user");
        System.out.println(getUser.getName());
        System.out.println(user.getName());
        System.out.println(user.hashCode());
        System.out.println(getUser.hashCode());
        System.out.println(user == getUser);
    }
}
