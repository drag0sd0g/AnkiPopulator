package com.dragos.anki.api;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

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
            StringEntity entity = new StringEntity(ankiCommandAsJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");
            try (CloseableHttpResponse httpresponse = httpclient.execute(httpPost)) {
                result = EntityUtils.toString(httpresponse.getEntity());
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
