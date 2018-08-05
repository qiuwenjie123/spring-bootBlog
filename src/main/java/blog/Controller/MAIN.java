package blog.Controller;

import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MAIN {

    @Autowired
    TBlogtypeMapper tBlogtypeMapper;

    @Autowired
    TBlogMapper tBlogMapper;

    @RequestMapping("/hello")
    public String mmm(){
        return "main";
    }


    //根据名字打开特定的Tab
    @RequestMapping(value = "/gethtml",method = RequestMethod.GET)
    public String getHtml(Model model,@RequestParam(value="pageName")String Name,
                          @RequestParam(value = "param")String param){

        //打开新增博客或者修改博客页面
        if(Name.equals("modifyBlog")){
            if(!param.equals("undefined")){
                Integer id=Integer.parseInt(param);
                TBlog blog=tBlogMapper.selectByPrimaryKey(id);
                model.addAttribute("blog",blog);
            }
            List<TBlogtype> list=tBlogtypeMapper.selectAll();
            model.addAttribute("blogtypes",list);
        }
        return Name;
    }

}
