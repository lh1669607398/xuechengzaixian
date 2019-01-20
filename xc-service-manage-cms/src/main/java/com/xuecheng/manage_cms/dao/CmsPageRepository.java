package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 继承MongoRepository调用内置方法查询mongodb数据库
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
    /**
     * 根据页面名称 ,站点id ,访问地址查询页面
     *
     * @param pageName    页面名称
     * @param SiteId      站点id
     * @param pageWebPath 访问地址
     * @return
     */
    public CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String SiteId, String pageWebPath);
}
