package com.wuyongfa.yifa.financialsystem.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * 分页插件，拦截sql,重新生成sql   
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月26日
 */
@Intercepts({ @Signature(type = StatementHandler.class,method = "prepare", args = { Connection.class,Integer.class }) })
public class PageInterceptor implements Interceptor {

	private String dialect = "";
	private String pageSqlId = "";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
	    if (invocation.getTarget() instanceof RoutingStatementHandler) {
	        BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtil
	                .getValueByFieldName(
	                        (RoutingStatementHandler) invocation.getTarget(),
	                        "delegate");
	        MappedStatement mappedStatement = (MappedStatement) ReflectUtil
	                .getValueByFieldName(delegate,
	                        "mappedStatement");
	
	        Page page = Page.threadLocal.get();
	        if (page == null) {
	            page = new Page();
	            Page.threadLocal.set(page);
	        }
	
	        if (mappedStatement.getId().matches(".*(" + this.pageSqlId + ")$") && page.getPageSize() > 0) {
	            BoundSql boundSql = delegate.getBoundSql();
	            Object parameterObject = boundSql.getParameterObject();
	
	            String sql = boundSql.getSql();
	            String countSqlId = mappedStatement.getId().replaceAll(pageSqlId, "Count");
	            MappedStatement countMappedStatement = null;
	            if (mappedStatement.getConfiguration().hasStatement(countSqlId)) {
	                countMappedStatement = mappedStatement.getConfiguration().getMappedStatement(countSqlId);
	            }
	            String countSql = null;
	            if (countMappedStatement != null) {
	                countSql = countMappedStatement.getBoundSql(parameterObject).getSql();
	            } else {
	                countSql = "SELECT COUNT(1) FROM (" + sql + ") T_COUNT";
	            }
	            
	            int totalCount = 0;
	            PreparedStatement countStmt = null;
	            ResultSet resultSet = null;
	            try {
	                Connection connection = (Connection) invocation.getArgs()[0];
	                countStmt = connection.prepareStatement(countSql);
	                BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
	                
	                setParameters(countStmt, mappedStatement, countBoundSql, parameterObject);
	                
	                resultSet = countStmt.executeQuery();
	                if(resultSet.next()) {
	                    totalCount = resultSet.getInt(1);
	                }
	            } catch (Exception e) {
	                throw e;
	            } finally {
	                try {
	                    if (resultSet != null) {
	                        resultSet.close();
	                    }
	                } finally {
	                    if (countStmt != null) {
	                        countStmt.close();
	                    }
	                }
	            }
	            
	            page.setTotalCount(totalCount);
	            
	            ReflectUtil.setValueByFieldName(boundSql, "sql", generatePageSql(sql,page));
	        }
	    }
	
	    return invocation.proceed();
	}
	
	
	/** 
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler 
	 * @param ps 
	 * @param mappedStatement 
	 * @param boundSql 
	 * @param parameterObject 
	 * @throws SQLException 
	 */  
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {  
	    ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());  
	    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
	    if (parameterMappings != null) {  
	        Configuration configuration = mappedStatement.getConfiguration();  
	        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
	        MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);  
	        for (int i = 0; i < parameterMappings.size(); i++) {  
	            ParameterMapping parameterMapping = parameterMappings.get(i);  
	            if (parameterMapping.getMode() != ParameterMode.OUT) {  
	                Object value;  
	                String propertyName = parameterMapping.getProperty();  
	                PropertyTokenizer prop = new PropertyTokenizer(propertyName);  
	                if (parameterObject == null) {  
	                    value = null;  
	                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
	                    value = parameterObject;  
	                } else if (boundSql.hasAdditionalParameter(propertyName)) {  
	                    value = boundSql.getAdditionalParameter(propertyName);  
	                } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {  
	                    value = boundSql.getAdditionalParameter(prop.getName());  
	                    if (value != null) {  
	                        value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));  
	                    }  
	                } else {
	                    value = metaObject == null ? null : metaObject.getValue(propertyName);  
	                }
	                TypeHandler typeHandler = parameterMapping.getTypeHandler();  
	                if (typeHandler == null) {  
	                    throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());  
	                }  
	                typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());  
	            }  
	        }  
	    }  
	}  
	
	/** 
	 * 根据数据库方言，生成特定的分页sql 
	 * @param sql 
	 * @param page 
	 * @return 
	 */  
	private String generatePageSql(String sql,Page page){  
	    if(page!=null && StringUtils.isNotBlank(dialect)){  
	        StringBuffer pageSql = new StringBuffer();  
	        if("mysql".equals(dialect)){  
	            pageSql.append(sql);  
	            pageSql.append(" LIMIT "+page.getCurrentResult()+","+page.getPageSize());  
	        }else if("oracle".equals(dialect)){  
	            pageSql.append("SELECT * FROM (SELECT TMP_TB.*,ROWNUM ROW_ID FROM (");  
	            pageSql.append(sql);  
	            pageSql.append(")  TMP_TB WHERE ROWNUM <= "); 
	            pageSql.append(page.getCurrentResult()+page.getPageSize());  
	            pageSql.append(") WHERE ROW_ID > ");  
	            pageSql.append(page.getCurrentResult());  
	        }  
	        return pageSql.toString();  
	    }else{  
	        return sql;  
	    }  
	} 
	
	@Override
	public Object plugin(Object target) {
	    return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties properties) {
	    try {
	        if (StringUtils.isEmpty(this.dialect = properties
	                .getProperty("dialect"))) {
	            throw new PropertyException("dialect property is not found!");
	        }
	        if (StringUtils.isEmpty(this.pageSqlId = properties
	                .getProperty("pageSqlId"))) {
	            throw new PropertyException("pageSqlId property is not found!");
	        }
	    } catch (PropertyException e) {
	        e.printStackTrace();
	    }
	}
	
}
