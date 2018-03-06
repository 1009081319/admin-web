package com.ly.fn.admin.common.interceptor;

import com.ly.fn.admin.common.util.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @package：com.ly.fn.admin.common.interceptor
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/11/30-14:39
 */
public class SQLHelper {

    public static int getCount(final String sql, final Connection connection, final MappedStatement mappedStatement, final Object parameterObject, final BoundSql boundSql, Log log) throws SQLException {
        final String countSql = "select count(1) from (" + sql + ") temp_count";
        Connection conn = connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("count sql:"+ StringUtils.replaceEach(countSql,new String[]{"\n","\t"},new String[]{" "," "}));
            }
            if (null == connection) {
                conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            }
            preparedStatement = conn.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), parameterObject);
            setParameters(preparedStatement,mappedStatement,countBS,parameterObject);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } finally {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != preparedStatement) {
                preparedStatement.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
    }

    private static void setParameters(PreparedStatement preparedStatement, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null :
                    configuration.newMetaObject(parameterObject);//RowBounds.NO_ROW_LIMIT
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
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                        if ("start".equalsIgnoreCase(propertyName)) {
                            value = 0;
                        } else if ("length".equalsIgnoreCase(propertyName)) {
                            value = RowBounds.NO_ROW_LIMIT;
                        }
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(preparedStatement, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }
}
