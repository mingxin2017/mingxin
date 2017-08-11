package com.mx.ssh.dao.impl;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mx.publicMethos.ISqlUtil;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxUsersReporterScore;
import com.mx.ssh.bean.MxUsersReporterSignUp;
import com.mx.ssh.bean.MxUsersReporterTeam;
import com.mx.ssh.dao.IReporterBusinessDAO;
import com.mx.weixin.util.MxKeyValue;


public class ReporterBusinessDAOImpl extends HibernateDaoSupport implements IReporterBusinessDAO{
	private ISqlUtil sqlUtil;//����ע��ִ���Զ���sql����ͨ����
	
	public ISqlUtil getSqlUtil() {
		return sqlUtil;
	}
	public void setSqlUtil(ISqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}
	//��ȡС�����Ŷ�
	@SuppressWarnings("unchecked")
	public List<MxUsersReporterTeam> getReporterTeams() {
		return getHibernateTemplate().find("from com.bean.MxUsersReporterTeam");
	}
	//С��������
	public void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp) {
		try {
			getHibernateTemplate().save(mxUsersReporterSignUp);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	//�û�id��ȡ�Ŷ���Ϣ
	public MxUsersReporterTeam getTeamByUserId(Integer userId) {	
		String sql="select au.* from [MXDB].[dbo].[MX_users_reporter_team] au" 
				+",[MXDB].[dbo].[MX_users_reporter_signUp] s where au.team_id = s.reporter_teamID " +
				"and s.state = 1 and s.user_id ='"+userId+"'";
		return sqlUtil.queryHqlListBySession(sql,new MxUsersReporterTeam()).get(0);
	}
	//�û�id��ȡ������Ϣ
	public MxUsersReporterScore getScoreByUserId(Integer userId) {
		String sql="select s.* from [MXDB].[dbo].[MX_users_reporter_score] s where s.user_id ='"+userId+"'";
		if(sqlUtil.queryHqlListBySession(sql,new MxUsersReporterScore()).size()==0){
			return null;
		}
		return sqlUtil.queryHqlListBySession(sql,new MxUsersReporterScore()).get(0);
	}
	//���û�id��ȡ����
	public List<MxNewsData> getNewsDataByUserId(Integer userId) {
		String sql="select s.* from [MXDB].[dbo].[MX_news_data] s where s.news_writerID ='"+userId+"'";
		return sqlUtil.queryHqlListBySession(sql,new MxNewsData());
	}
	//��������id��ȡ����
	public MxNewsData getNewsDataByNewsId(Integer newsId) {
		String sql="select s.* from [MXDB].[dbo].[MX_news_data] s where s.news_id ='"+newsId+"'";
		return sqlUtil.queryHqlListBySession(sql,new MxNewsData()).get(0);
	}


    
	
    

}
