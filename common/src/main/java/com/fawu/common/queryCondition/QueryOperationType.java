package com.fawu.common.queryCondition;

import lombok.Getter;

/**
 * 查询方式
 *
 * @author <a href="https://github.com/fawu-K">fawu.K</a>
 * @since 2024-08-02 11:26
 **/

@Getter
public enum QueryOperationType {
    eq("等于", "="),
    ne("不等于", "<>"),
    like("包含", "like"),
    notLike("不包含", "not like"),
    gt("大于", ">"),
    ge("大于等于", ">="),
    lt("小于", "<"),
    le("小于等于", "<="),
    in("在...内", "in"),
    notIn("不在...内", "not in"),
    isNull("为空", "is null"),
    isNotNull("不为空", "is not null")
    ;

    private final String label;
    private final String value;

    private QueryOperationType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    ;
}
