package cn.backend.access.push;

import cn.backend.access.push.config.DisplayType;
import cn.backend.access.push.config.PushType;
import cn.backend.access.push.model.Aps;
import cn.backend.access.push.model.Body;
import cn.backend.access.push.model.Notification;
import cn.backend.access.push.model.Payload;
import cn.backend.access.push.util.PushClient;
import cn.backend.config.message.CustomMessage;
import cn.zdwl.common.exception.AppException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessagePushAPI {

    @Autowired
    private PushClient pushClient;

    @Value("${mipush.activity}")
    private String miActivity;

    /**
     * 初始化推送模板
     * @return
     * @throws JSONException
     */
    private Notification initNotification() throws JSONException {
        Notification notification = new Notification();
        notification.setType(PushType.unicast);
        notification.setTimestamp(System.currentTimeMillis() / 1000);
        // 测试模式
        notification.setProductionMode(false);
        notification.setDescription("平湖电力消息推送");
        return notification;
    }

    /**
     *
     * @param notification
     * @return
     * @throws JSONException
     */
    private Payload initPayload(Notification notification) throws JSONException {
        Payload payload = notification.getPayload();
        payload.setDisplayType(DisplayType.notification);
        return payload;
    }

    /**
     * 初始化消息体
     * @param payload
     * @param title
     * @param text
     * @throws JSONException
     */
    private void initBody(Payload payload, String title, String text) throws JSONException {
        Body body = payload.getBody();
        body.setTicker(title);
        body.setTitle(title);
        body.setText(text);
    }


    /**
     * 推送初始化
     * @param title
     * @param text
     * @return
     * @throws JSONException
     */
    private Notification init(String title, String text) throws JSONException {
        Notification notification = initNotification();
        Payload payload = initPayload(notification);
        initBody(payload, title, text);
        initAps(payload, title, text);
        return notification;
    }

    /**
     * IOS推送需要
     * @param payload
     * @param title
     * @param text
     * @throws JSONException
     */
    private void initAps(Payload payload, String title, String text) throws JSONException {
        Aps aps = payload.getAps();
        aps.setAlert(title, text);
    }

    /**
     * 单个用户发送消息
     *
     * @param deviceNo
     * @param title
     * @param text
     */
    @Transactional
    public void singlePush(String deviceNo, String title, String text) {
        try {
            Notification notification = init(title, text);
            notification.setType(PushType.unicast);
            notification.setDeviceTokens(new HashSet<String>(Arrays.asList(deviceNo)));
            if (deviceNo.length() == 44) {
                //小米推送
                notification.setMiPush(true, miActivity);
                pushClient.sendToAndroid(notification);
            }
            if (deviceNo.length() == 64) {
                pushClient.sendToIOS(notification);
            }
        } catch (JSONException | IOException e) {
            throw new AppException(CustomMessage.PUSH_MESSAGE_FAIL);
        }
    }


    /**
     * 给多个用户推送消息(不超过500个)
     *
     * @param deviceNos
     * @param title
     * @param text
     */
    @Transactional
    public void listPush(Set<String> deviceNos, String title, String text) {
        try {
            Notification notification = init(title, text);
            notification.setType(PushType.listcast);
            Set<String> android_tokens = deviceNos.stream().filter(e -> e.length() == 44).collect(Collectors.toSet());
            if (android_tokens.size() >= 1) {
                //小米推送
                notification.setMiPush(true, miActivity);
                notification.setDeviceTokens(android_tokens);
                pushClient.sendToAndroid(notification);
            }
            Set<String> ios_tokens = deviceNos.stream().filter(e -> e.length() == 64).collect(Collectors.toSet());
            if (ios_tokens.size() >= 1) {
                notification.setDeviceTokens(ios_tokens);
                pushClient.sendToIOS(notification);
            }
        } catch (JSONException | IOException e) {
            throw new AppException(CustomMessage.PUSH_MESSAGE_FAIL);
        }

    }

    /**
     * 全推(基于群推实现)
     *
     * @param deviceNos
     * @param title
     * @param text
     */
    public void allPush(Set<String> deviceNos, String title, String text) {
        int max = 500;
        if (deviceNos.size() <= max) {
            listPush(deviceNos, title, text);
        } else {
            int size = deviceNos.size() / max;
            if (size >= 1) {
                for (int i = 0; i <= size; i++) {
                    Set<String> toPush = deviceNos.stream().skip(i * max).limit(max).collect(Collectors.toSet());
                    if (toPush.size() > 0) {
                        listPush(toPush, title, text);
                    }
                }
            }
        }
    }

}
