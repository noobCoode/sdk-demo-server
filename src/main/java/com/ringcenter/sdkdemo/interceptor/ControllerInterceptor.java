package com.ringcenter.sdkdemo.interceptor;

import com.ringcenter.sdkdemo.utils.CookieUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Xu Dongdong
 * @date 2020-5-4
 */
@Component
public class ControllerInterceptor implements HandlerInterceptor {
    private static final String SESSION_ID = "Session_id";
    private static final String URL = "URL";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (CookieUtil.getCookie(request, SESSION_ID) == null) {
            CookieUtil.writeCookie(response, SESSION_ID, UUID.randomUUID().toString());
            CookieUtil.writeCookie(response,URL,request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
