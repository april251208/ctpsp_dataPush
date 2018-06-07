package com.ctpsp.common.page;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * jsp页面的分页
 *
 * @author sunhaijian
 * @date 2016-05-09
 */
public class PageParamHandlerInterceptor implements HandlerInterceptor {
    public static final String REQUEST_PAGE_PARAM = "_page_param";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> pageParms = new HashMap<>();
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Map parameterMap = request.getParameterMap();
        if (parameterMap != null) {
            Set keySet = parameterMap.keySet();
            for (Object key : keySet) {
                Object[] value = (Object[]) parameterMap.get(key);
                pageParms.put(key.toString(), value[0].toString());
            }
        }
        request.setAttribute(REQUEST_PAGE_PARAM, pageParms);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
