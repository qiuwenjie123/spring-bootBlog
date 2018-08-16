package blog.Controller.foreground;

import blog.dao.TBlogMapper;
import blog.pojo.TBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/blogdetail")
public class BlogController {

    @Autowired
    TBlogMapper tBlogMapper;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/getblog/{id}",method = RequestMethod.GET)
    public String getblog(@PathVariable(value = "id")Integer id, HttpServletResponse response){

        TBlog tBlog=tBlogMapper.selectByPrimaryKey(id);
        request.setAttribute("blog",tBlog);

        //"Access-Control-Allow-Origin", "http://write.blog.csdn.net"

        return "foreground/blog";
    }
}
