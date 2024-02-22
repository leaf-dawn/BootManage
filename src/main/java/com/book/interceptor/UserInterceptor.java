package com.book.interceptor;

import com.book.util.CurrentUserInfo;
import com.book.util.JwtUtil;
import io.swagger.models.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李嘉劲
 * 用户拦截器，获取用户token并添加到threadlocal中
 */
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是OPTIONS请求则直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        String token = request.getHeader("token");
        // 考虑到可能存在无token的情况
        if (token != null && !token.isEmpty()) {
            CurrentUserInfo.set(JwtUtil.getCurrentUser(token));
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
        CurrentUserInfo.remove();
    }

}
