package com.enucp.weixin.auth.servlet;

import com.enucp.weixin.auth.util.AuthUtil;
import net.sf.json.JSONObject;
import org.apache.http.auth.AUTH;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by weixuan on 2017-07-08.
 */
@WebServlet("/callback")
public class CallBackServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + AuthUtil.APPID+
                "&secret=" +AuthUtil.APPSECRET+
                "&code=" +code+
                "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String openId = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/cgi-bin/user/info" +
                "?access_token=" +token+
                "&openid=" +openId+
                "&lang=zh_CN ";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print(userInfo.toString());
        pw.close();
    }
}
