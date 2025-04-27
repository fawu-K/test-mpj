package com.fawu.common.queryCondition;

import lombok.Data;

/**
 * 查询条件字段
 *
 * @author <a href="https://github.com/fawu-K">fawu.K</a>
 * @since 2024-08-02 11:23
 **/

@Data
public class QueryConditionFieldDTO {

    /**
     * 字段所属表
     */
    private String tableName;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段代码
     */
    private String fieldCode;

    /**
     * 字段值
     */
    private Object fieldValue;

    /**
     * 查询方式
     */
    private QueryOperationType operation;

}
