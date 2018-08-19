package blog.config;

import blog.Interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

//@EnableWebMvc
//@Configuration
public class MyMvcConfigurationSupport extends WebMvcConfigurerAdapter {

    @Autowired
    CommonInterceptor commonInterceptor;

    //不拦截静态资源
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**").excludePathPatterns("/bootstrap/**")
                .excludePathPatterns("/css/**").excludePathPatterns("/images/**").
                excludePathPatterns("/js/**").excludePathPatterns("/layui/**")
                .excludePathPatterns("/wangEditor-3.1.1/**");
        super.addInterceptors(registry);
    }

   /* @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/static/laydate/");
        registry.addResourceHandler("/layui/**").addResourceLocations("classpath:/static/layui/");
        registry.addResourceHandler("/wangEditor-3.1.1/**").addResourceLocations("classpath:/static/wangEditor-3.1.1/");
        super.addResourceHandlers(registry);
    }
    */
}
