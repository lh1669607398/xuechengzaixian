package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 接收到页面查询条件实体类
 */
@Data
public class QueryPageRequest extends RequestData {
    //站点id     
    @ApiModelProperty("站点id")
    private String siteId;
    //页面ID     
    @ApiModelProperty("页面ID")
    @Id
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模版id
    private String templateId;
}
