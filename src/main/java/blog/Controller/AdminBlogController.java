package blog.Controller;

import blog.config.BlogIndex;
import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/blog")
public class AdminBlogController {

    @Autowired
    TBlogMapper tBlogMapper;
    @Autowired
    TBlogtypeMapper tBlogtypeMapper;
    @Autowired
    BlogIndex blogIndex;

    /*************取得所有博客数据******************************/
    @RequestMapping(value = "/getAllBlog",method = RequestMethod.GET )
    //直接返回JSON数据
    @ResponseBody
    public Result getBlogByPage(@RequestParam(value = "page",defaultValue = "1")int page,
                                    @RequestParam(value = "limit",defaultValue = "5") int limit){
        PageHelper.startPage(page,limit,"id asc");
        List<TBlog> list=tBlogMapper.selectAll();
        //通过这个PageInfo对象可以获取到分页后的结果和所有结果数量等等参数
        PageInfo<TBlog> datameta = new PageInfo<>(list);
        List<TBlog> data=datameta.getList();
        for(TBlog tBlog:data){
            tBlog.setTypename(tBlogtypeMapper.selectByPrimaryKey(tBlog.getTypeId()).getTypename());
        }
        //layui的表格分页所需要参数 Code(默认为0吧)、表格数据Data、表格总数count
        Result result=new Result();
        result.setCode(0);
        result.setData(data);
        result.setCount((int)datameta.getTotal());
        //layui开启了分页，所以这里默认会传入page和limit参数
        return result;
    }

    /*************删除数据******************************/
    @RequestMapping(value="/delBlog",method = RequestMethod.GET)
    @ResponseBody
    public Result delBlog(@RequestParam(value = "id")int id){
        //根据主键删除数据
        TBlog tBlog=tBlogMapper.selectByPrimaryKey(id);
        Result result=new Result();
        try{
            tBlogMapper.delete(tBlog);
            result.setMsg("200");
        }catch(Exception e){
            result.setMsg("500");
        }
        return result;
    }


    //新增一篇博客,或者修改一篇博客
    @RequestMapping(value = "/addBlog",method = RequestMethod.POST)
    @ResponseBody
    public Result addBlog(@RequestBody TBlog tBlog){
       try{
           //更新
           if(tBlog.getId()!=null){
               //后面带selective意思是为null的字段不更新
               int i=tBlogMapper.updateByPrimaryKeySelective(tBlog);
               this.blogIndex.updateIndex(tBlog);
               if(i>0){
                   return new Result("修改成功",200);
               }else{
                   return new Result("修改失败",400);
               }
           }
           //新增
           Date date=new Date();
           tBlog.setReleasedate(date);

           String text=tBlog.getText();
           if(text.length()>70){
               tBlog.setSummary(text.substring(0,70)+"...");
           }else{
               tBlog.setSummary(text);
           }
           tBlog.setClickhit(0);
           tBlog.setReplyhit(0);
           int i=tBlogMapper.insert(tBlog);
           this.blogIndex.addIndex(tBlog);
           if(i!=1){
               return new Result("新增失败",400);
           }else{
               return new Result("新增成功",200);
           }
       }catch(Exception e){
           e.printStackTrace();
           return new Result("新增失败",400);
       }
    }


    //上传图片,暂未开发
    /*@RequestMapping(value = "/blog/imageupload")
    public Result imageUpload(MultipartFile file){
        return new Result();
    }*/


}
