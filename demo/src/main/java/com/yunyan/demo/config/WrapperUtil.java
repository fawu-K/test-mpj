package com.yunyan.demo.config;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.fawu.common.BaseEntity;
import com.fawu.common.queryCondition.BaseQueryCriteria;
import com.fawu.common.queryCondition.QueryConditionFieldDTO;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wrapper的工具类
 *
 * @author <a href="https://github.com/fawu-K">fawu.K</a>
 * @since 2024-08-03 17:27
 **/

public class WrapperUtil {
    /**
     * 可序列化
     */
    private static final int FLAG_SERIALIZABLE = 1;
    private static final Map<String, SFunction> FUNCTION_MAP = new HashMap<>();

    public static <T> PageInfo<T> listToPage(List<T> list, Integer page, Integer size) {
        PageInfo<T> pageInfo = new PageInfo<>();
        if (list == null || list.isEmpty()) {
            // 判空处理
            pageInfo.setList(new ArrayList<>());
            return pageInfo;
        }

        pageInfo.setTotal(list.size());

        // 分页
        int offset = size * (page - 1);
        if (offset >= list.size()) {
            offset = list.size() -1;
        }
        int offend = size * page;
        if (offend >= list.size()) {
            offend = list.size();
        }
        list = list.subList(offset, offend);
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 编辑查询条件，将查询条件编辑进wrapper中
     * @param conditionDto 查询条件
     * @param wrapper wrapper
     * @return wrapper
     */
    public static <T, R> MPJLambdaWrapper<T> setQueryConditionToWrapper(List<QueryConditionFieldDTO> conditionDto, MPJLambdaWrapper<T> wrapper, Class<?> entityClass) {
        String tableName = "";
        // 检查字段是否有 @Excel 注解
        if (entityClass.isAnnotationPresent(TableName.class)) {
            // 获取 @TableName 注解
            TableName annotation = entityClass.getAnnotation(TableName.class);
            // 将表头名称和字段名称放入 Map 中
            tableName = annotation.value();
        }

        for (QueryConditionFieldDTO fieldDTO : conditionDto) {

            // 如果传入的实体类中有表名注解且查询条件与实体类的表名不相符则跳过该查询条件
            if (!"".equals(tableName) && !tableName.equals(fieldDTO.getTableName())) {
                continue;
            }

            SFunction<R, T> column = getSFunction(entityClass, fieldDTO.getFieldCode());

            switch (fieldDTO.getOperation()) {
                case eq -> wrapper.eq(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case ne -> wrapper.ne(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case like -> wrapper.like(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case gt -> wrapper.gt(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case ge -> wrapper.ge(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case lt -> wrapper.lt(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case le -> wrapper.le(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case in -> wrapper.in(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case notIn -> wrapper.notIn(ObjUtil.isNotEmpty(fieldDTO.getFieldValue()), column, fieldDTO.getFieldValue());
                case isNull -> wrapper.isNull((Boolean) fieldDTO.getFieldValue(), column);
                case isNotNull -> wrapper.isNotNull((Boolean) fieldDTO.getFieldValue(), column);
                default -> {
                }
            }
        }
        return wrapper;
    }

    /**
     * 构建通用的查询条件参数
     * @param queryCriteria 查询条件
     * @param entityClass 要查询的实体类
     * @return 构建完成的查询条件
     * @param <T> 实体类
     */
    public static <T extends BaseEntity> MPJLambdaWrapper<T> buildWrapperByQueryCriteria(BaseQueryCriteria queryCriteria, Class<T> entityClass) {
        MPJLambdaWrapper<T> wrapper = MPJWrappers.lambdaJoin(entityClass)
                .eq(ObjUtil.isNotEmpty(queryCriteria.getId()), getSFunction(entityClass, "id"), queryCriteria.getId())
                // 创建人
                .eq(ObjUtil.isNotEmpty(queryCriteria.getCreator()), getSFunction(entityClass, "creator"), queryCriteria.getCreator())
                // 最后一次修改人
                .eq(ObjUtil.isNotEmpty(queryCriteria.getUpdator()), getSFunction(entityClass, "updator"), queryCriteria.getUpdator());
        // 创建时间区间
        buildSection(wrapper, getSFunction(entityClass, "created"), queryCriteria.getCreated());
        // 修改时间区间
        buildSection(wrapper, getSFunction(entityClass, "updated"), queryCriteria.getUpdated());

        return wrapper;
    }

    /**
     * 构建区间查询条件
     * @param wrapper 查询条件构造器
     * @param func 区间查询的字段
     * @param values 条件
     * @return 查询条件构造器
     * @param <T> 实体类
     * @param <R> 字段
     */
    public static <T, R> MPJLambdaWrapper<T> buildSection(MPJLambdaWrapper<T> wrapper, SFunction<R, ?> func, Object[] values) {
        return buildSection(wrapper, null, func, values);
    }

    /**
     * 构建区间查询条件
     * @param wrapper 查询条件构造器
     * @param alias 别名
     * @param func 区间查询的字段
     * @param values 条件
     * @param <T> 实体类
     * @param <R> 字段
     * @return 查询条件构造器
     */
    public static <T, R> MPJLambdaWrapper<T> buildSection(MPJLambdaWrapper<T> wrapper, String alias, SFunction<R, ?> func, Object[] values) {
        if (ObjUtil.isNotEmpty(values)) {
            wrapper.ge(ObjUtil.isNotEmpty(values[0]), alias, func, values[0])
                    .le(ObjUtil.isNotEmpty(values[1]), alias, func, values[1]);
        }
        return wrapper;
    }



    /**
     * 获取方法的sfunction
     * @param entityClass 实体类
     * @param fieldName 字段名
     * @return sfunction
     */
    public static <T, R> SFunction<T, R> getSFunction(Class<?> entityClass, String fieldName) {

        // 下划线转成驼峰
        fieldName = underscoreToCamelCase(fieldName);

        if (FUNCTION_MAP.containsKey(entityClass.getName() + fieldName)) {
            return FUNCTION_MAP.get(entityClass.getName() + fieldName);
        }

        Field field = getDeclaredField(entityClass, fieldName);
        if(field == null){
            throw ExceptionUtils.mpe("This class %s is not have field %s ", entityClass.getName(), fieldName);
        }
        SFunction<T, R> func = null;
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(field.getType(), entityClass);
        final CallSite site;
        String getFunName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            site = LambdaMetafactory.altMetafactory(lookup,
                    "invoke",
                    MethodType.methodType(SFunction.class),
                    methodType,
                    lookup.findVirtual(entityClass, getFunName, MethodType.methodType(field.getType())),
                    methodType, FLAG_SERIALIZABLE);
            func = (SFunction<T, R>) site.getTarget().invokeExact();
            FUNCTION_MAP.put(entityClass.getName() + field, func);
            return func;
        } catch (Throwable e) {
            throw ExceptionUtils.mpe("This class %s is not have method %s ", entityClass.getName(), getFunName);
        }
    }

    /**
     * 获取字段
     * @param clazz 类
     * @param fieldName 字段名
     * @return 字段
     */
    private static Field getDeclaredField(Class<?> clazz, String fieldName) {
        Field field = null;

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return null;
    }

    /**
     *  下划线转驼峰写法
     *
     * @param input
     * @return
     */
    public static String underscoreToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        // 标记是否处于下划线后，用于判断是否需要转换为大写
        boolean nextUpperCase = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpperCase = false;
                } else {
                    result.append(currentChar);
                }
            }
        }

        return result.toString();
    }

}
