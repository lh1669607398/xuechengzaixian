package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模板
 */
@Service
public class TemplateService {
    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    /**
     * 查询所有模板
     * @return
     */
    public QueryResponseResult findAll(){
        List<CmsTemplate> templateList = cmsTemplateRepository.findAll();
        QueryResult queryResult=new QueryResult();
        queryResult.setList(templateList);
        return  new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
}
