package blog.Controller.foreground;

import blog.Service.DateProcess;
import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.dao.TCommentMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import blog.pojo.TComment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 获取某篇博客
 */
@Controller
@RequestMapping("/blogdetail")
public class BlogController {

    @Autowired
    TBlogMapper tBlogMapper;
    @Autowired
    TCommentMapper tCommentMapper;
    @Autowired
    TBlogtypeMapper tBlogtypeMapper;
    @Autowired
    HttpServletRequest request;
    @Autowired
    DateProcess dateProcess;

    @RequestMapping(value = "/getblog/{id}",method = RequestMethod.GET)
    public String getblog(@PathVariable(value = "id")Integer id, HttpServletResponse response){

        TBlog tBlog=tBlogMapper.selectByPrimaryKey(id);
        Date date=tBlog.getReleasedate();
        if(date!=null) {
            tBlog.setDate(dateProcess.cutToShort(tBlog.getReleasedate(),0));
        }
        //更新访问次数
        tBlog.setClickhit(tBlog.getClickhit()+1);
        tBlogMapper.updateByPrimaryKey(tBlog);

        //查询该文章的最新的5篇博客评论
        TComment tComment=new TComment();
        tComment.setBlogId(id);
        PageHelper.startPage(1,5,"id desc");
        List<TComment> lists=tCommentMapper.select(tComment);
        PageInfo<TComment> data=new PageInfo<>(lists);
        List<TComment> comments=data.getList();

        for(TComment comment:comments){
            String[] s=comment.getUserIp().split(",");
            comment.setNickname(s[1]);
        }

        TBlogtype tBlogtype=tBlogtypeMapper.selectByPrimaryKey(tBlog.getTypeId());
        String typename=tBlogtype.getTypename();

        request.setAttribute("blog",tBlog);
        request.setAttribute("comments",comments);
        request.setAttribute("typename",typename);

        return "foreground/blog";
    }
}
