package com.zzc.core.dataSourceManager;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

/**
 * 动态切换SqlSessionFactory的SqlSessionDaoSupport
 *
 * @see org.mybatis.spring.support.SqlSessionDaoSupport
 */
public class DynamicSqlSessionDaoSupport extends DaoSupport {
	private static Logger log = Logger.getRootLogger();
    private Map<Object, SqlSessionFactory> targetSqlSessionFactorys;

    private SqlSessionFactory              defaultTargetSqlSessionFactory;

    private SqlSession                     sqlSession;

    private boolean                        externalSqlSession;

    @Autowired(required = false)
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalSqlSession) {
            this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    @Autowired(required = false)
    public final void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSession = sqlSessionTemplate;
        this.externalSqlSession = true;
    }

    /**
     * Users should use this method to get a SqlSession to call its statement
     * methods This is SqlSession is managed by spring. Users should not
     * commit/rollback/close it because it will be automatically done.
     * 
     * @return Spring managed thread safe SqlSession
     */
    public final SqlSession getSqlSession() {
    	log.info("---------当前sqlSession为-------->"+ContextHolder.getContext());
    	//begin 以下是修改部分 用来动态获取sqlsessionfactory
        SqlSessionFactory targetSqlSessionFactory = targetSqlSessionFactorys.get(ContextHolder
                .getContext());
        //end 
        if (targetSqlSessionFactory != null) {
            setSqlSessionFactory(targetSqlSessionFactory);
        } else if (defaultTargetSqlSessionFactory != null) {
            setSqlSessionFactory(defaultTargetSqlSessionFactory);
        }
        return this.sqlSession;
    }

    /**
     * {@inheritDoc}
     */
    protected void checkDaoConfig() {
        Assert.notNull(this.sqlSession,
                "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
    }

    public Map<Object, SqlSessionFactory> getTargetSqlSessionFactorys() {
        return targetSqlSessionFactorys;
    }

    /**
     * Specify the map of target SqlSessionFactory, with the lookup key as key.
     * @param targetSqlSessionFactorys
     */
    public void setTargetSqlSessionFactorys(Map<Object, SqlSessionFactory> targetSqlSessionFactorys) {
        this.targetSqlSessionFactorys = targetSqlSessionFactorys;
    }

    public SqlSessionFactory getDefaultTargetSqlSessionFactory() {
        return defaultTargetSqlSessionFactory;
    }

    /**
     * Specify the default target SqlSessionFactory, if any.
     * @param defaultTargetSqlSessionFactory
     */
    public void setDefaultTargetSqlSessionFactory(SqlSessionFactory defaultTargetSqlSessionFactory) {
        this.defaultTargetSqlSessionFactory = defaultTargetSqlSessionFactory;
    }

}
