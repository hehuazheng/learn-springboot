package com.hzz.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: hezz
 */
@Component
public class TraceFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("traceId", UUID.randomUUID().toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
