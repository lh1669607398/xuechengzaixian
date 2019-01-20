package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    //分页查询页面
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        //如果条件不存在,则创建一个
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        //条件值
        CmsPage cmsPage = new CmsPage();
        //站点id
        if (StringUtils.isNoneEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //页面别名
        if (StringUtils.isNoneEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //条件匹配器
        //页面别名模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatche = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建条件实例
        Example<CmsPage> example = Example.of(cmsPage, exampleMatche);
        //进行判断
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;//为了适应mongodb的接口将页码减1

        if (size <= 0) {
            size = 20;
        }
        //进行分页
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        //设置参数
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        //返回
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult add(CmsPage cmsPage) {
        //判断传入的对象是否存在
        if (cmsPage == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //根据页面名称  站点id 和访问路径获取页面
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (cmsPage1 != null) {
            //抛出异常
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        //页面不存在 ,存入数据库
        cmsPage.setPageId(null);  //将主键id制为null,使它自增
        cmsPageRepository.save(cmsPage);
        //返回结果
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    /**
     * 根据id查询页面
     *
     * @param id
     * @return
     */
    public CmsPage findById(String id) {
        //根据id查询
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        //判断optional对象是否存在
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    /**
     * 修改页面
     *
     * @param id
     * @return
     */
    public CmsPageResult update(String id, CmsPage cmsPage) {
        //获取页面
        CmsPage cmsPage1 = findById(id);
        //修改属性
        if (cmsPage1 != null) {
            //更新模板id
            cmsPage1.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsPage1.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsPage1.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsPage1.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsPage1.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsPage1.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //执行跟新
            CmsPage save = cmsPageRepository.save(cmsPage1);
            if (save != null) {
                //修改成功
                return new CmsPageResult(CommonCode.SUCCESS, cmsPage1);
            }
        }
        //修改失败
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    /**
     * 根据id删除页面
     *
     * @param id
     * @return
     */
    public ResponseResult delete(String id) {
        CmsPage cmsPage = findById(id);
        if (cmsPage == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        cmsPageRepository.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

}
