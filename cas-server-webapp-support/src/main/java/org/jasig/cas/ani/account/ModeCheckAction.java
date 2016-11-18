package org.jasig.cas.ani.account;

import javax.servlet.http.HttpServletRequest;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Created by hey on 16-11-4.
 */

public class ModeCheckAction{
    public static final String NORMAL = "normal";
    public static final String RLOGIN = "rlogin";
    public static final String APP = "app";

    public ModeCheckAction() { }

    public Event check(final RequestContext context) {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        // 根据mode判断请求模式
        // mode=rlogin，是AJAX远程登录模式，
        // mode=app是app登录模式，
        // else 原模式，认证中心本地登录
        String mode = request.getParameter("mode");
        if (mode != null && mode.equals("rlogin")) {
            context.getFlowScope().put("mode", mode);
            return new Event(this, RLOGIN);
        }
        if (mode != null && mode.equals("app")) {
            context.getFlowScope().put("mode", mode);
            return new Event(this, APP);
        }
        context.getFlowScope().put("mode", mode);
        return new Event(this, NORMAL);
    }
}



