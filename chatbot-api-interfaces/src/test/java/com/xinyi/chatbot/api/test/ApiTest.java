package com.xinyi.chatbot.api.test;

import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * API 单元测试
 *
 * @author xinyi
 * @date 2023/2/11
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/51112288545484/topics?scope=unanswered_questions&count=20");
        httpGet.addHeader("cookie", "UM_distinctid=1863e3c64d2b1f-0754f09b0b3c4f-26021051-144000-1863e3c64d31002; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221863e3c66f8b1f-0fd971254b38418-26021051-1327104-1863e3c66f9d9a%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2M2UzYzY2ZjhiMWYtMGZkOTcxMjU0YjM4NDE4LTI2MDIxMDUxLTEzMjcxMDQtMTg2M2UzYzY2ZjlkOWEifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%221863e3c66f8b1f-0fd971254b38418-26021051-1327104-1863e3c66f9d9a%22%7D; sajssdk_2015_cross_new_user=1; zsxq_access_token=564A05A0-373E-A8E8-DC2A-A059E3E83E9E_2DDC4020075DE4A8; zsxqsessionid=eecde526327254b6f98536d46ae79d0f; abtest_env=product");
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(res);
        } else {
            System.out.println(httpResponse.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/51112288545484/answer");
        httpPost.addHeader("cookie", "知识星球个人cookie信息");
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(res);
        } else {
            System.out.println(httpResponse.getStatusLine().getStatusCode());
        }
    }

}
