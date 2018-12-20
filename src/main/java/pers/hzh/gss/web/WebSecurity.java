package pers.hzh.gss.web;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebSecurity extends HandlerInterceptorAdapter {
    private static Logger logger = Logger.getLogger(WebSecurity.class);

    public final static String SESSION_KEY = "manager_id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info(request.getRequestURL());
        String[] arr=request.getRequestURL().toString().split("/");
        HttpSession session = request.getSession();

        if(session.getAttribute(SESSION_KEY) != null) {//如果登陆了，那么是index页面的下级页面的通过，不是的跳转到index
            if (arr.length>=4) {
                if (arr[3].equals("index")) {
                    return true;
                }
            }
            String url = "/index";
            response.sendRedirect(url);
            return false;
        }

        if(arr.length==4){//如果没有 session ，那么通过login
            if(arr[3].equals("login")){
                return true;
            }
        }
        // 跳转登录
        String url = "/login";
        response.sendRedirect(url);
        return false;
    }
}