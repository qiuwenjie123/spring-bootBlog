package blog.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    /**
     * 跳转到错误页面
     * @return
     */
    @RequestMapping
    public String error(){
        return getErrorPath();
    }


}
