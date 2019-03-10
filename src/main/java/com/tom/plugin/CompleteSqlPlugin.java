package com.tom.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

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
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
    }
}
