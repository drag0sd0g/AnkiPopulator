package com.dragos.anki.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AnkiHttpClient {

    private static final String LOCAL_ANKI_URL = "http://localhost:8765";

    private final CloseableHttpClient httpclient;

    public AnkiHttpClient() {
        this.httpclient = HttpClients.createDefault();
    }

    public String postCommandToAnki(String ankiCommandAsJson) {
        HttpPost httpPost = new HttpPost(LOCAL_ANKI_URL);
        String result = "";
        try {
            StringEntity entity = new StringEntity(ankiCommandAsJson, "UTF-8");
            entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpresponse = httpclient.execute(httpPost);
            HttpEntity responseEntity = httpresponse.getEntity();
            result = EntityUtils.toString(responseEntity, "UTF-8");
            EntityUtils.consume(responseEntity);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
