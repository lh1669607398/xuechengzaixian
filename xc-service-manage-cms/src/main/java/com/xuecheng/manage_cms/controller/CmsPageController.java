package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private PageService pageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findlist(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        //测试
      /*  QueryResult queryResult=new QueryResult();
        CmsPage cmsPage=new CmsPage();
        cmsPage.setPageName("测试页面");
        List<CmsPage> list= new ArrayList<>();
        list.add(cmsPage);
        queryResult.setList(list);
        queryResult.setTotal(2);
        QueryResponseResult queryResponseResult= new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;  */
        return   pageService.findList(page,size,queryPageRequest);
    }
}
