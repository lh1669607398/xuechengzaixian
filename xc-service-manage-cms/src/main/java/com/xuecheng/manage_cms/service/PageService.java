package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    private CmsPageRepository  cmsPageRepository;

    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        //进行判断
        if (page<=0){
            page=1;
        }
        page=page-1 ;//为了适应mongodb的接口将页码减1

        if (size<=0){
            size=20;
        }
        //进行分页
        Pageable pageable= PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        //设置参数
        QueryResult queryResult=new QueryResult();
        queryResult.setList(  all.getContent());
        queryResult.setTotal(all.getTotalPages());
        //返回
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

}
