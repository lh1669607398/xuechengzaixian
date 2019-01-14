package com.xuecheng.mange_cms.dao;


import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.ManageCmsApplication;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindPage() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    /**
     * 添加
     */
    @Test
    public void testSave() {
        //定义实体类
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
    }

    /**
     * 删除
     */
    @Test
    public void testDelete(){
        //先查询
       cmsPageRepository.deleteById("5c3c65ccfa0dee4714730b0b");
    }

    /**
     * 修改
     */
    @Test
    public void  testUpdat(){
        //先查询
        Optional<CmsPage> optional = cmsPageRepository.findById("5c3c65ccfa0dee4714730b0b");
        //判断
        if (optional.isPresent()){
            //获取对象
            CmsPage cmsPage = optional.get();
            cmsPage.setSiteId("t01");
            cmsPage.setPageName("测试页面01");
            //从新存储
            cmsPageRepository.save(cmsPage);
        }
    }

}

