package com.github.taven.web;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class QQImgCheck {
    public static void main(String[] args) throws IOException {
        String postUrl = "https://api.q.qq.com/api/json/security/ImgSecCheck?access_token="
                + "RRTzS2bUdPQi-DOvbyDRvqq_f1YdzxvR_hyHZGeJb7l0M2vTRxKWo5NxggTyRuLayBuT"
                + "&appid=" + 1109578711;
        File file = new File("D:\\haha.jpeg");

        HttpClient httpClient = HttpClients.createDefault();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder
                .addBinaryBody("media", file, ContentType.MULTIPART_FORM_DATA, null)
                .setMode(HttpMultipartMode.RFC6532);
        HttpPost httpPost = new HttpPost(postUrl);
        httpPost.setEntity(multipartEntityBuilder.build());
//        httpPost.setHeader("Content-Type", );
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity =  httpResponse.getEntity();
        String content = EntityUtils.toString(httpEntity);
        System.out.println(content);
    }
}
