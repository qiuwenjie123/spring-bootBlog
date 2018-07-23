package blog.Controller;

import blog.dao.TBlogtypeMapper;
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

    @RequestMapping("/hello")
    public String mmm(){
        return "main";
    }


    //根据名字打开特定的Tab
    @RequestMapping(value = "/gethtml",method = RequestMethod.GET)
    public String getHtml(Model model,@RequestParam(value="pageName")String Name){

        if(Name.equals("modifyBlog")){
            List<TBlogtype> list=tBlogtypeMapper.selectAll();
            model.addAttribute("blogtypes",list);
        }
        return Name;
    }

}
