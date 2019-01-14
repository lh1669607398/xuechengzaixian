package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 继承MongoRepository调用内置方法查询mongodb数据库
 * */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
}
