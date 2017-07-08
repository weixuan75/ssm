package com.enucp.weixin.auth.util;


import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by weixuan on 2017-07-08.
 */
public class AuthUtil {
    //常量
    public static final String APPID ="wx569203383d649d5d";
    public static final String APPSECRET ="9587bb553625517d998c4c46bf83812a";

    public static JSONObject doGetJson(String url){
        JSONObject jsonObject=null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //获取返回的实体
            HttpEntity entity = response.getEntity();
            if (entity!=null){
                String result = EntityUtils.toString(entity,"UTF-8");
                jsonObject=JSONObject.fromObject(result);
            }
            //释放连接
            httpGet.releaseConnection();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
