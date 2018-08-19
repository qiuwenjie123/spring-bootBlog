package blog.Controller.foreground;

import blog.dao.TBlogMapper;
import blog.dao.TCommentMapper;
import blog.pojo.TBlog;
import blog.pojo.TComment;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    TBlogMapper tBlogMapper;
    @Autowired
    TCommentMapper tCommentMapper;


    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ResponseBody
    public Result commitsubmit(String nickname, String str,int blogId){

        //获取评论者ip
        String userIp = request.getRemoteAddr();
        String user = userIp+","+nickname;

        TComment comment=new TComment();
        comment.setUserIp(user);
        comment.setContent(str);
        comment.setCommentDate(new Date());
        comment.setBlogId(blogId);

        Result result=new Result();

        int i = tCommentMapper.insert(comment);//添加评论
        if(i!=1){
            result.setMsg("添加失败，联系管理员");
            return result;
        }
        TBlog tBlog = tBlogMapper.selectByPrimaryKey(comment.getBlogId()); //更新一下博客的评论次数
        tBlog.setReplyhit(tBlog.getReplyhit() + 1);
        tBlogMapper.updateByPrimaryKey(tBlog);
        result.setMsg("添加成功");
        return result;

    }


}
