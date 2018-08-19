package blog.Controller.foreground;

import blog.Service.DateProcess;
import blog.Service.PageProcess;
import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 首页，或者分类获取
 */
@Controller
public class IndexController {

    @Autowired
    TBlogMapper tBlogMapper;

    @Autowired
    TBlogtypeMapper tBlogtypeMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    DateProcess dateProcess;

    @Autowired
    PageProcess pageProcess;

    //首页每页展示的文章数目
    @Value("${myindex.artitleSum}")
    String sum;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String toIndex(@RequestParam(value = "page",defaultValue = "1")int page){

        int artitlesum=Integer.parseInt(sum);

        //开启分页，默认一页5篇
        PageHelper.startPage(page,artitlesum,"id desc");
        List<TBlog> list=tBlogMapper.selectAll();

        //文章总数
        int allsum=tBlogMapper.selectCount(new TBlog());
        int allpages=allsum%artitlesum==0?(allsum/artitlesum):(allsum/artitlesum+1);

        PageInfo<TBlog> data=new PageInfo<>(list);
        List<TBlog> result=data.getList();

        //处理显示的日期，和查询分类名称
        for(TBlog tBlog:result){
            Date date=tBlog.getReleasedate();
            if(date!=null){
                tBlog.setDate(dateProcess.cutToShort(tBlog.getReleasedate(),1));
            }
            TBlogtype tBlogtype=tBlogtypeMapper.selectByPrimaryKey(tBlog.getTypeId());
            tBlog.setTypename(tBlogtype.getTypename());
        }
        //处理分页显示
        int startpage=pageProcess.pageprocess(page);

        //查询所有分类
        List<TBlogtype> tBlogtypes=tBlogtypeMapper.selectAll();

        request.setAttribute("blogtypes",tBlogtypes);
        request.setAttribute("startpage",startpage);
        request.setAttribute("allpages",allpages);
        request.setAttribute("articles",result);
        request.setAttribute("currentpage",page);

        return "foreground/index";
    }


    @RequestMapping(value = "/getblogbytype/{type}",method = RequestMethod.GET)
    public String getblogbytype(@PathVariable(value = "type") int type,
                                @RequestParam(value = "page",defaultValue = "1")int page){

        int artitlesum=Integer.parseInt(sum);
        //开启分页，默认一页5篇
        PageHelper.startPage(page,artitlesum,"id desc");
        TBlog tBlogexample=new TBlog();


        //查询分类下文章总数以及所有文章
        tBlogexample.setTypeId(type);
        List<TBlog> list=tBlogMapper.select(tBlogexample);
        int allsum=tBlogMapper.selectCount(tBlogexample);
        int allpages=allsum%artitlesum==0?(allsum/artitlesum):(allsum/artitlesum+1);

        PageInfo<TBlog> data=new PageInfo<>(list);
        List<TBlog> result=data.getList();

        //查询该分类的分类名称
        TBlogtype tBlogtype=tBlogtypeMapper.selectByPrimaryKey(type);
        String typename=tBlogtype.getTypename();

        //处理显示的日期
        for(TBlog tBlog:result){
            Date date=tBlog.getReleasedate();
            if(date!=null){
                tBlog.setDate(dateProcess.cutToShort(tBlog.getReleasedate(),1));
            }
            tBlog.setTypename(typename);
        }

        //处理分页显示
        int startpage=pageProcess.pageprocess(page);

        //查询所有分类
        List<TBlogtype> tBlogtypes=tBlogtypeMapper.selectAll();

        request.setAttribute("blogtypes",tBlogtypes);
        request.setAttribute("typename",typename);
        request.setAttribute("startpage",startpage);
        request.setAttribute("allpages",allpages);
        request.setAttribute("articles",result);
        request.setAttribute("currentpage",page);

        return "foreground/index";
    }

}
