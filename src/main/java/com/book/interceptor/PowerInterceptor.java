package com.book.interceptor;

import com.book.constants.RoleConstant;
import com.book.pojo.po.User;
import com.book.util.CurrentUserInfo;
import io.swagger.models.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 李嘉劲
 */
public class PowerInterceptor implements HandlerInterceptor {
    // 不进行拦截的路径
    private static String[] loginPaths = new String[] {
            "/user/login",
            "/user/register",
    };

    // 普通读者可以访问的path
    String[] readerPaths = new String[] {
            "/book/getAllAlbums",
            "/book/borrow",
            "/borrowrecord/getBorrowRecords"
    };

    // 管理员可以访问的path
    String[] managerPaths = new String[] {
            "/book/*",
            "/borrowrecord/*",
    };
    // 超级管理员可以访问的path
    String[] superPaths = new String[] {
            "/*",
    };
    // 实现预处理方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果是OPTIONS请求则直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        // 获取当前用户和请求路径
        User user = CurrentUserInfo.get();
        String path = request.getRequestURI();

        boolean pass = false;
        if (user.getCondi() == RoleConstant.READER) {
            pass = isPathAllowed(path, readerPaths);
        } else if (user.getCondi() == RoleConstant.MANAGER) {
            pass = isPathAllowed(path, managerPaths) || isPathAllowed(path, readerPaths);
        } else if (user.getCondi() == RoleConstant.SUPER_MANAGER) {
            // 超级管理员可以访问所有路径
            pass = true;
        }

        // 如果用户角色不满足上述条件，默认拦截请求
        if (!pass) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return pass;
    }

    // 检查请求路径是否允许访问的辅助方法
    private boolean isPathAllowed(String path, String[] allowedPaths) {
        for (String allowedPath : allowedPaths) {
            // 简单的匹配逻辑
            if (pathMatches(path, allowedPath)) {
                return true;
            }
        }
        return false;
    }

    // 路径匹配的辅助方法，这里只是一个非常基础的实现，可以根据需要增加正则匹配等
    private boolean pathMatches(String path, String allowedPath) {
        // 简单的通配符替换，对于更复杂的匹配需求可以使用正则表达式
        allowedPath = allowedPath.replace("*", ".*");
        return path.matches(allowedPath);
    }

}
