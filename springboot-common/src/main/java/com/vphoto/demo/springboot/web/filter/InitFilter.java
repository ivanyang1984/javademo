/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午7:59
 * LastModified: 18-5-8 下午7:59
 */

package com.vphoto.demo.springboot.web.filter;

import com.vphoto.demo.springboot.web.context.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Order(0)
@Component
public class InitFilter extends OncePerRequestFilter {

    protected static final Logger logger = LoggerFactory.getLogger(InitFilter.class);

    private ThreadLocal<Date> startTimeThread = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        // 记录请求开始时间
        startTimeThread.set(new Date(System.currentTimeMillis()));

        HttpContext.start(request, response);

        filterChain.doFilter(request, response);
    }


}

