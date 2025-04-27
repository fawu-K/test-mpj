package com.fawu.common.queryCondition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询条件基类
 *
 * @author <a href="https://github.com/fawu-K">fawu.K</a>
 * @since 2024-11-18 09:14
 **/
@Data
public class BaseQueryCriteria implements Serializable {

    @Schema(title = "唯一标识")
    private Long id;

    @Schema(title = "创建人Id")
    private String creator;

    @Schema(title = "最后一次修改人id")
    private String updator;

    @Schema(title = "创建时间区间")
    private String[] created;

    @Schema(title = "最后一次修改时间区间")
    private String[] updated;
}
