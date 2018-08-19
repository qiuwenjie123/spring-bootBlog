package blog.Interceptor;

import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Autowired
    TBlogtypeMapper tBlogtypeMapper;

    @Autowired
    TBlogMapper tBlogMapper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //查出所有的博客分类
        List<TBlogtype> list=tBlogtypeMapper.selectAll();

        //查出最近写的8篇文章
        PageHelper.startPage(1,8,"id desc");
        List<TBlog> data=tBlogMapper.selectAll(); //latestBlogs
        PageInfo<TBlog> latestBlogs=new PageInfo<>(data);

        request.setAttribute("blogtypesList",list);
        request.setAttribute("latestBlogs",latestBlogs);
        return true;
    }
}
