package com.fawu.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础entity类
 *
 * @author K.faWu
 * @program LowCode
 * @date 2023-01-06 14:00
 **/
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 修改者
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String created;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updated;


}
