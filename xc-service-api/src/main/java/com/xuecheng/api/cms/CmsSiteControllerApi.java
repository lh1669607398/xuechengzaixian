package com.xuecheng.api.cms;

import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * 站点
 */
public interface CmsSiteControllerApi  {
    /**
     * 查询所有站点
     * @return
     */
    public QueryResponseResult  findList();
}
