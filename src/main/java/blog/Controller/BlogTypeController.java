package blog.Controller;

import blog.dao.TBlogtypeMapper;
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


import java.util.List;

@Controller
public class BlogTypeController {

    @Autowired
    TBlogtypeMapper tBlogtypeMapper;


    /*************取得数据(需要分页)******************************/
    @RequestMapping(value = "/admin/getAllBlogTypeBypage",method = RequestMethod.GET )
    //直接返回JSON数据
    @ResponseBody
    public Result getBlogTypeByPage(@RequestParam(value = "page",defaultValue = "1")int page,
                                    @RequestParam(value = "limit",defaultValue = "5") int limit){
        PageHelper.startPage(page,limit,"id asc");
        List<TBlogtype> list=tBlogtypeMapper.selectAll();
        //通过这个PageInfo对象可以获取到分页后的结果和所有结果数量等等参数
        PageInfo<TBlogtype> datameta = new PageInfo<>(list);
        List<TBlogtype> data=datameta.getList();
        //layui的表格分页所需要参数 Code(默认为0吧)、表格数据Data、表格总数count
        Result result=new Result();
        result.setCode(0);
        result.setData(data);
        result.setCount((int)datameta.getTotal());
        //layui开启了分页，所以这里默认会传入page和limit参数
        return result;
    }

    /*************取得所有数据******************************/
    @RequestMapping(value = "/admin/getAllBlogType",method = RequestMethod.GET )
    @ResponseBody
    public List<TBlogtype> getAllBlogType(){
        List<TBlogtype> list=tBlogtypeMapper.selectAll();
        return list;
    }

    /*************删除数据******************************/
    @RequestMapping(value="/admin/delBlogType",method = RequestMethod.GET)
    @ResponseBody
    public Result delBlogType(@RequestParam(value = "id")int id){
        //根据主键删除数据
        TBlogtype tBlogtype=tBlogtypeMapper.selectByPrimaryKey(id);
        Result result=new Result();
        try{
            tBlogtypeMapper.delete(tBlogtype);
            result.setMsg("200");
        }catch(Exception e){
            result.setMsg("500");
        }
        return result;
    }

    /*************增加数据******************************/
    @RequestMapping(value="/admin/addBlogType",method = RequestMethod.GET)
    @ResponseBody
    public Result addBlogType(@RequestParam(value = "typename")String typename) {
        TBlogtype  tBlogtype=new TBlogtype();
        tBlogtype.setTypename(typename);
        tBlogtype.setOrdernum(0);
        Result result=new Result();
        try{
            tBlogtypeMapper.insert(tBlogtype);
            result.setMsg("200");
        }catch(Exception e){
            result.setMsg("500");
        }
        return result;
    }

    /*************编辑数据******************************/
    @RequestMapping(value="/admin/editBlogType",method = RequestMethod.GET)
    @ResponseBody
    public Result editBlogType(@RequestParam(value = "id")int id,
                               @RequestParam(value = "typename")String typename) {
        TBlogtype tBlogtype=tBlogtypeMapper.selectByPrimaryKey(id);
        tBlogtype.setTypename(typename);
        Result result=new Result();
        try{
            tBlogtypeMapper.updateByPrimaryKey(tBlogtype);
            result.setMsg("200");
        }catch(Exception e){
            result.setMsg("500");
        }
        return result;
    }
}
