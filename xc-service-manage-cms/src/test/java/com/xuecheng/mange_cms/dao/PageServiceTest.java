package com.xuecheng.mange_cms.dao;

import com.xuecheng.manage_cms.ManageCmsApplication;
import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
public class PageServiceTest {
    @Autowired
    private PageService pageService;

    @Test
    public void test() {
        String pageId = "5a795ac7dd573c04508f3a56";
        String html = pageService.getPageHtml(pageId);
        System.out.println(html);
    }
}
