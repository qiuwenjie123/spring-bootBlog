package blog.Service.impl;

import blog.Service.BlogTypeService;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlogtype;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {


    /**
     * 分类查询博客分类
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Result selectByPage(int page,int limit) {
        Result result=new Result();
        return result;
    }
}
