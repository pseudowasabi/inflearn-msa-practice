package com.pseudowasabi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);

    @Override
    public String filterType() {
        return "pre"; // pre-filter
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true; // use this filter(=true) or not(=false)
    }

    @Override
    public Object run() throws ZuulException {

        logger.info("******* printing logs: ");

        RequestContext ctx = RequestContext.getCurrentContext(); // obtain root context of web project
        HttpServletRequest httpServletRequest = ctx.getRequest();
        logger.info("******* {}", httpServletRequest.getRequestURI());

        return null;
    }
}
