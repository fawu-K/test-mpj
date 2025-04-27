post http://127.0.0.1:19292/codeRule/queryError?page=1&size=10
post http://127.0.0.1:19292/codeRule/querySuccess?page=1&size=10

body:
```json
[
    {
        "tableName": "code_rule",
        "fieldCode": "codeFormula",
        "operation": "like",
        "fieldValue": "HR",
        "sort": 1
    }
]
```
在idea中启动demo项目以上两个接口可以正常访问，但在将demo项目打成jar包后，接口queryError会抛出异常。
```log
### Error querying database.  Cause: org.apache.ibatis.builder.BuilderException: Error evaluating expression 'ew.sqlSegment != null and ew.sqlSegment != '' and ew.nonEmptyOfWhere'. Cause: org.apache.ibatis.ognl.OgnlException: sqlSegment [java.lang.NoClassDefFoundError: com/yunyan/demo/domain/CodeRule]
### Cause: org.apache.ibatis.builder.BuilderException: Error evaluating expression 'ew.sqlSegment != null and ew.sqlSegment != '' and ew.nonEmptyOfWhere'. Cause: org.apache.ibatis.ognl.OgnlException: sqlSegment [java.lang.NoClassDefFoundError: com/yunyan/demo/domain/CodeRule]] with root cause
java.lang.ClassNotFoundException: com.yunyan.demo.domain.CodeRule
```
