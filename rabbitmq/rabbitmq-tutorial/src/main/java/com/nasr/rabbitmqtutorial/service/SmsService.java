package com.nasr.rabbitmqtutorial.service;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsService implements NotificationService {
    public static final String USERNAME = "xxx";
    public static final String PASSWORD = "xxx";

    public void send(Notification smsRequest) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "username=" + USERNAME + "&password=" + PASSWORD + "&to="
                + smsRequest.getTo() + "&text=" + smsRequest.getContent() + "&isflash=" + true);

        Request request = new Request.Builder()
                /*don't get url and username and password for security problem*/
                .url("https://rest.payamak-panel.com/api/SendSMS/SendSMS")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "c26ca3b0-9f44-3cdf-9da3-60c86a9f75b3")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        client.newCall(request).execute();
    }
}
