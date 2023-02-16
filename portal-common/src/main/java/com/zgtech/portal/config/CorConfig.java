package com.zgtech.portal.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author winner
 * 前端访问跨域
 */
@Configuration
public class CorConfig implements WebMvcConfigurer {
    @Value("${path.filepath}")
    private String filePath;
    @Value("${path.file}")
    private String file;
    @Value("${path.watermarkpath}")
    private String waterMarkPath;
    @Value("${path.watermarkiconpath}")
    private String waterMarkIconPath;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                //添加映射路径
                registry.addMapping("/**")
                        //放行哪些原始域
                        .allowedOriginPatterns("*")
                        //是否发送Cookie信息
                        .allowCredentials(true)
                        //放行哪些原始域(请求方式)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        //放行哪些原始域(头部信息)
                        .allowedHeaders("*");
            }
        };
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String[] file ={"file:/home/user/portal/file/"};
        String picture = file+filePath;
        String waterMark = file+ waterMarkPath;
        String waterMarkIcon = file+waterMarkIconPath;
//        String[] file ={"file:D:/file/picture/","file:D:/file/watermark/","file:D:/file/watermarkicon/"};
        String[] file ={picture,waterMark,waterMarkIcon};
        registry.addResourceHandler("/file/**").addResourceLocations(file);
    }

}
