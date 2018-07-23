package blog.dao;

import blog.common.base.IBaseMapper;
import blog.pojo.TBlogtype;

import java.util.List;

public interface TBlogtypeMapper extends IBaseMapper<TBlogtype> {
    List<TBlogtype> selectByPage(int page, int limit) ;
}