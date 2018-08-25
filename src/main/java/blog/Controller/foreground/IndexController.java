package blog.Controller.foreground;

import blog.Service.DateProcess;
import blog.Service.PageProcess;
import blog.dao.TBlogMapper;
import blog.dao.TBlogtypeMapper;
import blog.pojo.TBlog;
import blog.pojo.TBlogtype;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.Result;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
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

    /**
     * 打开首页
     * @param page
     * @return
     */
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

        /*
        //有图片的话就取出第一张的图片
        Document doc=Jsoup.parse(tBlog.getContent());
        Elements elements=doc.select("img");
        if(elements.size()>0){
            String imgsrc=elements.get(0).attr("src");
            request.setAttribute("imgsrc",imgsrc);
        }
        */

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


    /**
     * 打开分类文章
     * @param type
     * @param page
     * @return
     */
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


    /**
     * 获取每日一说
     * @return
     */
    @RequestMapping("/getone")
    @ResponseBody
    public Result getOne(){

        StringBuilder temp=new StringBuilder();
        int i=0;
        Result result=new Result();
        CloseableHttpClient httpClient= HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        HttpGet httpGet=new HttpGet("http://open.iciba.com/dsapi/");
        try{
            httpResponse=httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if(entity!=null){
                InputStream inputStream=entity.getContent();
                byte[] b=new byte[2048];
                while((i=inputStream.read(b))!=-1){
                    //去掉空格，然后初始化
                    temp.append(new String(b).trim());
                    b=new byte[2048];
                }
                JSONObject jsonObject= JSON.parseObject(temp.toString());
                inputStream.close();
                result.setData(jsonObject.getString("note"));
            }else{
                httpResponse.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
