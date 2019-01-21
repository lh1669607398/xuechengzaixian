package com.xuecheng.mange_cms;

import com.xuecheng.manage_cms.ManageCmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
public class RestTmplateTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel?id=5a791725dd573c3574ee333f", Map.class);
        Map map = entity.getBody();
        System.out.println(map);

    }
}
