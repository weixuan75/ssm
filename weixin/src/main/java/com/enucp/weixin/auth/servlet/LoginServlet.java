package com.enucp.weixin.auth.servlet;

import com.enucp.weixin.auth.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/wxlogin")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectURI = "http://ycttcn.com/weixin/callback";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + AuthUtil.APPID+
                "&redirect_uri=" + URLEncoder.encode(redirectURI,"utf-8")+
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        resp.sendRedirect(url);
    }
}
