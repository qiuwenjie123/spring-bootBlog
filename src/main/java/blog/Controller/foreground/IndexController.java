package blog.Controller.foreground;

import blog.dao.TBlogMapper;
import blog.pojo.TBlog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    TBlogMapper tBlogMapper;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String toIndex(@RequestParam(value = "page",defaultValue = "1")int page){

        //开启分页，默认一页7篇
        PageHelper.startPage(page,7,"id asc");
        List<TBlog> list=tBlogMapper.selectAll();
        PageInfo<TBlog> data=new PageInfo<>(list);
        List<TBlog> result=data.getList();
        request.setAttribute("articles",result);
        return "foreground/index";

    }
}
