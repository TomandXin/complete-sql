package com.tom.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 打印完整SQL的mybatis插件
 *
 * @author tomxin
 * @date 2019-03-10
 * @since v1.0.0
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class CompleteSqlPlugin implements Interceptor {


    /**
     * 自定义打印日志逻辑
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        try {
            // 打印日志逻辑


        } catch (Exception e) {

        }
        return invocation.proceed();
    }

    /**
     * 将自定义插件封装为代理类
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 自定义参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 打印完整SQL的方法
     *
     * @param invocation
     */
    private void printCompleteSql(Invocation invocation) {
        // 获取对应的参数值
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];
        // 获取SQL
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String originalSql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // 判空
        if (null == parameterMappings || parameterMappings.size() <= 0) {
            return;
        }
        for (int i = 0; i < parameterMappings.size(); ++i) {
            ParameterMapping parameterMapping = parameterMappings.get(0);
            // 判断参数的类型是否是输入的类型
            if (parameterMapping.getMode() == ParameterMode.OUT) {
                continue;
            }
            String propertyName = parameterMapping.getProperty();
            Object value;
            if (boundSql.hasAdditionalParameter(propertyName)) {
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (null == parameter) {
                value = null;
            } else {
                MetaObject metaObject = mappedStatement.getConfiguration().newMetaObject(parameter);
                value = metaObject.getValue(propertyName);
            }
            // 获取数据类型
            if (parameterMapping.getJavaType().equals(Date.class)) {
                value = PluginDateUtils.formatToString((Date) value);
            }
            originalSql.replaceFirst("\\?", value.toString());
        }
        System.out.println("=======================complete sql start=============================");
        System.out.println("tom plugin print complete sql [ " + originalSql + " ]");
        System.out.println("=======================complete sql end===============================");
    }
}
