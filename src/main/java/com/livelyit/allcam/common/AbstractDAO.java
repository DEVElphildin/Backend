package com.livelyit.allcam.common;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AbstractDAO {
    protected Log log = LogFactory.getLog(AbstractDAO.class);
    
    @Autowired
    @Qualifier("allcamSqlSessionTemplate")
    private SqlSession allcamSqlSession;
    
    //@Autowired
    //@Qualifier("smsSqlSessionTemplate")
    //private SqlSession smsSqlSession;
    
    protected SqlSession getSqlSession(int type) {
    	switch (type) {
			default:
	    	case Utils.SQL_ALLCAM :
	    		return allcamSqlSession;
//			case Utils.SQL_SMS :
//				return smsSqlSession;
		}
    }
    
    protected void printQueryId(String queryId) {    	
        if(log.isDebugEnabled()){
        	log.debug("\t QueryId  \t:  " + queryId);
        }
    }
     
    public Object insert(int type, String queryId, Object params){
        printQueryId(queryId);
        return getSqlSession(type).insert(queryId, params);
    }
     
    public Object update(int type, String queryId, Object params){
        printQueryId(queryId);
        return getSqlSession(type).update(queryId, params);
    }
     
    public Object delete(int type, String queryId, Object params){
        printQueryId(queryId);
        return getSqlSession(type).delete(queryId, params);
    }
     
    public Object selectOne(int type, String queryId){
        printQueryId(queryId);
        return getSqlSession(type).selectOne(queryId);
    }
     
    public Object selectOne(int type, String queryId, Object params){
        printQueryId(queryId);
        return getSqlSession(type).selectOne(queryId, params);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(int type, String queryId){
        printQueryId(queryId);
        return getSqlSession(type).selectList(queryId);
    }

    @SuppressWarnings("rawtypes")
    public List selectList(int type, String queryId, Object params){
        printQueryId(queryId);
        return getSqlSession(type).selectList(queryId,params);
    }
}