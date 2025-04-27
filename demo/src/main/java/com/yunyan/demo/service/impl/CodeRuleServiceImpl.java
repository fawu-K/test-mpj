package com.yunyan.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fawu.common.queryCondition.QueryConditionFieldDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.yunyan.demo.domain.CodeRule;
import com.yunyan.demo.mapper.CodeRuleMapper;
import com.yunyan.demo.service.CodeRuleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author qawzf
* @description 针对表【sale_order(销售订单)】的数据库操作Service实现
* @createDate 2025-04-25 11:06:05
*/
@Service
public class CodeRuleServiceImpl extends ServiceImpl<CodeRuleMapper, CodeRule>
    implements CodeRuleService {

    @Resource
    private CodeRuleMapper codeRuleMapper;

    @Override
    public PageInfo<CodeRule> querySuccess(List<QueryConditionFieldDTO> queryCriteria, Integer page, Integer size) {
        MPJLambdaWrapper<CodeRule> wrapper = MPJWrappers.lambdaJoin(CodeRule.class);

        // 如果util在当前项目中就能执行成功
        com.yunyan.demo.config.WrapperUtil.setQueryConditionToWrapper(queryCriteria, wrapper, CodeRule.class);
        PageHelper.startPage(page, size);
        List<CodeRule> codeRules = codeRuleMapper.selectJoinList(CodeRule.class, wrapper);
        return new PageInfo<>(codeRules);
    }

    @Override
    public PageInfo<CodeRule> queryError(List<QueryConditionFieldDTO> queryCriteria, Integer page, Integer size) {
        MPJLambdaWrapper<CodeRule> wrapper = MPJWrappers.lambdaJoin(CodeRule.class);

        // 如果util在引入的包中就出现异常
        com.fawu.common.queryCondition.WrapperUtil.setQueryConditionToWrapper(queryCriteria, wrapper, CodeRule.class);
        PageHelper.startPage(page, size);
        List<CodeRule> codeRules = codeRuleMapper.selectJoinList(CodeRule.class, wrapper);
        return new PageInfo<>(codeRules);
    }
}




