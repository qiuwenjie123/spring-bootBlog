package blog.Service.impl;

import blog.Service.PageProcess;
import org.springframework.stereotype.Service;

@Service
public class PageProcessImpl implements PageProcess {
    @Override
    public int pageprocess(int currentpage) {

        int startpage;
        if(currentpage%5==0){
            startpage=currentpage-4;
        }else{
            startpage=currentpage%5==1?currentpage:(currentpage+1-currentpage%5);
        }
        return startpage;
    }

}
