import blog.dao.TBlogMapper;
import blog.pojo.TBlog;
import blog.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Proxy;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@MapperScan(basePackages = "blog.dao" )
public class MybatisTest {

    @Autowired
    private TBlogMapper tBlogMapper;

    @Test
    public void getblog(){
        List<TBlog> list=tBlogMapper.selectAll();
        for(TBlog tBlog:list){
            System.out.println(tBlog.getId());
            System.out.println(tBlog.getSummary());
            //Proxy.getProxyClass()
        }
    }
}
