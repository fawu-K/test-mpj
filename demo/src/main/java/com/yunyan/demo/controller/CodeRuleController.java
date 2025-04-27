package com.yunyan.demo.controller;

import com.fawu.common.Result;
import com.fawu.common.queryCondition.QueryConditionFieldDTO;
import com.github.pagehelper.PageInfo;
import com.yunyan.demo.domain.CodeRule;
import com.yunyan.demo.service.CodeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="https://github.com/fawu-K">fawu.K</a>
 * @since 2025-04-25 11:09
 **/
@Tag(name = "销售信息", description = "销售信息")
@RestController
@RequestMapping("codeRule")
public class CodeRuleController {

    @Resource
    private CodeRuleService baseService;


    @Operation(summary = "如果util在当前项目中就能执行成功")
    @PostMapping("querySuccess")
    public Result<PageInfo<CodeRule>> querySuccess(@RequestBody List<QueryConditionFieldDTO> queryCriteria, Integer page, Integer size) {
        return Result.success(baseService.querySuccess(queryCriteria, page, size));
    }

    @Operation(summary = "如果util在引入的包中就出现异常")
    @PostMapping("queryError")
    public Result<PageInfo<CodeRule>> queryError(@RequestBody List<QueryConditionFieldDTO> queryCriteria, Integer page, Integer size) {
        return Result.success(baseService.queryError(queryCriteria, page, size));
    }
}
