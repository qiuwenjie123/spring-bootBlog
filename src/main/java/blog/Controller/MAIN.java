package blog.Controller;

import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogger;
import blog.pojo.TBlogtype;
import blog.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MAIN {

    @Autowired
    TBlogtypeMapper tBlogtypeMapper;

    @Autowired
    TBlogMapper tBlogMapper;

    @RequestMapping("/admin/hello")
    public String mmm(){
        return "main";
    }


    @RequestMapping(value = "/tologin", method = RequestMethod.POST)
    public String login(TBlogger tBlogger, HttpServletRequest request){
        //获取登录实体
        Subject subject = SecurityUtils.getSubject();
        //获取加密后密码
        String password = MD5Util.md5(tBlogger.getPassword(), "xp");//后面那个是“盐”参数
        System.out.println(password);
        //获取用户密码登录token
        UsernamePasswordToken token = new UsernamePasswordToken(tBlogger.getUsername(), password);
        try {
            //根据token登录 会调用MyRealm中的doGetAuthenticationInfo方法进行身份认证
            subject.login(token);
            return "redirect:/admin/hello";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("bloger", tBlogger);
            //提示信息
            request.setAttribute("errorInfo", "用户名或密码错误");
            return "/login";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String tologin(){
        return "login";
    }



    //根据名字打开特定的Tab
    @RequestMapping(value = "/admin/gethtml",method = RequestMethod.GET)
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
