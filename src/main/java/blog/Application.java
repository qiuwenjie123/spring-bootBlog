package blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "blog.dao" )  //Map 的路径
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
