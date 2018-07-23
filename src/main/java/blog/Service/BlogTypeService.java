package blog.Service;

import common.Result;

public interface BlogTypeService {
    public Result selectByPage(int page,int limit);
}
