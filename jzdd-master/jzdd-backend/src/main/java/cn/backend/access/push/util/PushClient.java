package cn.backend.access.push.util;

import cn.backend.access.push.config.UMengConfig;
import cn.backend.access.push.model.Notification;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class PushClient {

    protected final Log logger = LogFactory.getLog(getClass());

    // The user agent
    protected final String USER_AGENT = "Mozilla/5.0";

    // This object is used for sending the post request to Umeng
    protected HttpClient client = new DefaultHttpClient();

    // The host
    protected static final String host = "http://msg.umeng.com";

    // The upload path
    protected static final String uploadPath = "/upload";

    // The post path
    protected static final String postPath = "/api/send";

    @Autowired
    private UMengConfig uMengConfig;

    public boolean sendToAndroid(Notification msg) throws JSONException, IOException {
        String url = host + postPath;
        msg.setAppkey(uMengConfig.getAppkey_android());
        String postBody = msg.getPostBody();
        String sign = DigestUtils.md5Hex(("POST" + url + postBody + uMengConfig.getAppMasterSecret_android()).getBytes("utf8"));
        url = url + "?sign=" + sign;
        return send(url, postBody);
    }

    public boolean sendToIOS(Notification msg) throws JSONException, IOException {
        String url = host + postPath;
        msg.setAppkey(uMengConfig.getAppkey_ios());
        String postBody = msg.getPostBody();
        String sign = DigestUtils.md5Hex(("POST" + url + postBody + uMengConfig.getAppMasterSecret_ios()).getBytes("utf8"));
        url = url + "?sign=" + sign;
        return send(url, postBody);
    }

    public boolean send(String url, String postBody) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);
        StringEntity se = new StringEntity(postBody, "UTF-8");
        post.setEntity(se);
        // Send the post request and get the response
        HttpResponse response = client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        logger.debug("Response Code : " + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        logger.info("友盟消息推送:"+result.toString());
        if (status == 200) {
            logger.debug("Notification sent successfully.");
        } else {
            logger.error("Failed to send the notification!");
        }
        return true;
    }

    public boolean push(Notification msg) {

        return true;
    }
}