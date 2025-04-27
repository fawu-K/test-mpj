package com.yunyan.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fawu.common.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author fawu.k
 * @since 2024-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("code_rule")
@Schema(name = "CodeRule对象", description = "")
public class CodeRule extends BaseEntity {

    @Schema(description = "编码适用类型：员工")
    private String codeType;

    @Schema(description = "编码公式")
    private String codeFormula;

    @Schema(description = "编码计数")
    private Integer codeCount;

    @Schema(description = "步长")
    private Integer stepSize;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "是否停用")
    private Boolean isDeactivate;
}
