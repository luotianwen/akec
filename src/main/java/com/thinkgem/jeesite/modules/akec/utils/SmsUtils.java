package com.thinkgem.jeesite.modules.akec.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.thinkgem.jeesite.common.config.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SmsUtils {
      static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
    private static String ACCOUNT=Global.getConfig("sms.account");
    private static String PASSWORD=Global.getConfig("sms.password");
    private static String URL=Global.getConfig("sms.url");
    public static boolean send(String  phone,String pwd){
        Map map = new HashMap();
        map.put("account",ACCOUNT);//API账号
        map.put("password",PASSWORD);//API密码
        map.put("msg","您好，您的验证码是"+pwd);//短信内容
        map.put("phone",phone);//手机号
        map.put("report","false");//是否需要状态报告
        JSONObject djs = (JSONObject) JSONObject.toJSON(map);
        logger.info(djs.toString());
       String data= sendSmsByPost(URL,djs.toString());
        JSONObject js= (JSONObject) JSONObject.parse(data);
        if(js.getString("code").equals("0")){
            logger.info(phone+"发送验证码成功"+pwd);
            return true;
        }
        logger.error(phone+"发送验证码失败");
        return false;
    }
    public static void main(String[] args) {
        //短信下发
        String sendUrl = URL;
        Map map = new HashMap();
        map.put("account",ACCOUNT);//API账号
        map.put("password",PASSWORD);//API密码
        map.put("msg","您好，您的验证码是P123456");//短信内容
        map.put("phone","18500430578");//手机号
        map.put("report","false");//是否需要状态报告
        JSONObject js = (JSONObject) JSONObject.toJSON(map);
        System.out.println(sendSmsByPost(URL,js.toString()));

    }
    public static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            OutputStream os=httpURLConnection.getOutputStream();
            os.write(postContent.getBytes("UTF-8"));
            os.flush();
            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
