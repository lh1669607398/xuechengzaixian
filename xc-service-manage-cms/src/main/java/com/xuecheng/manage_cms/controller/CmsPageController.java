package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private PageService pageService;

    /**
     * 分页查询模板页面
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
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

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    @PostMapping("/add")
    @Override
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    @GetMapping("/findOen/{id}")
    /**
     * 根据id查询页面
     * @param id
     * @return
     */
    @Override
    public CmsPage findOen(@PathVariable("id") String id) {
        return pageService.findById(id);
    }

    @PutMapping("/update/{id}")
    /**
     * 修改页面
     * @param id
     * @param cmsPage
     * @return
     */
    @Override
    public CmsPageResult update(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        return pageService.update(id,cmsPage);
    }
    @GetMapping("/delete/{id}")
    /**
     * 根据id删除页面
     * @param id
     * @return
     */
    @Override
    public ResponseResult delete(@PathVariable("id") String id) {
        return pageService.delete(id);
    }

}
