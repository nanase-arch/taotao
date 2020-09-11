package com.taotao.exception;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomExceptionResolver implements HandlerExceptionResolver{
    Logger logger = Logger.getLogger(CustomExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        logger.error(ex.getMessage());
        /**
         *  只要报错就会来到全局异常处理器这里
         *  但是如果是同步请求页面跳转完全没有问题
         *      ModelAndView
         *  如果是异步请求 页面跳转就会有问题
         */

        //获取请求头
        String header = request.getHeader("X-Requested-With");
        //代表我们的请求是 ajax请求
        if (header != null && header.equals("XMLHttpRequest")){
            // response json
            try {
                //设置响应状态 为200
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write("{\"code\":\"ErrorResponse\"}");
                writer.flush();
                //如果没有这一句 上面的代码都没想过
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        return modelAndView;
    }
}
