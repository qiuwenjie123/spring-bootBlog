import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlogtype;
import com.alibaba.fastjson.JSON;
import blog.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@MapperScan(basePackages = "blog.dao" )
public class FastjsonTest {

    @Autowired
    private TBlogtypeMapper tBlogtypeMapper;

    @Test
    public void t(){
        List<TBlogtype> list=tBlogtypeMapper.selectAll();
        String s= JSON.toJSONString(list);
        System.out.println(s);
    }


}
