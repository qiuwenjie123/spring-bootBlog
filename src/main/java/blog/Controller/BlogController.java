package blog.Controller;

import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    TBlogMapper tBlogMapper;
    @Autowired
    TBlogtypeMapper tBlogtypeMapper;

    /*************取得数据******************************/
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

}
