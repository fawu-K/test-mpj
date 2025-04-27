package com.yunyan.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fawu.common.queryCondition.QueryConditionFieldDTO;
import com.github.pagehelper.PageInfo;
import com.yunyan.demo.domain.CodeRule;

import java.util.List;

/**
* @author qawzf
* @description 针对表【sale_order(销售订单)】的数据库操作Service
* @createDate 2025-04-25 11:06:06
*/
public interface CodeRuleService extends IService<CodeRule> {

    PageInfo<CodeRule> querySuccess(List<QueryConditionFieldDTO> queryCriteria, Integer page, Integer size);

    PageInfo<CodeRule> queryError(List<QueryConditionFieldDTO> queryCriteria, Integer page, Integer size);
}
