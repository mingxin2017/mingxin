package com.mx.ssh.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mx.publicMethos.ISqlUtil;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxUsersReporterScore;
import com.mx.ssh.bean.MxUsersReporterSignUp;
import com.mx.ssh.bean.MxUsersReporterTeam;
import com.mx.ssh.dao.IReporterBusinessDAO;

@Repository("reporterBusinessDAO")
public class ReporterBusinessDAOImpl extends HibernateDaoSupport implements IReporterBusinessDAO{
	@Autowired
	private ISqlUtil sqlUtilDAO;//����ע��ִ���Զ���sql����ͨ����
	
	//��ȡС�����Ŷ�
	@SuppressWarnings("unchecked")
	public List<MxUsersReporterTeam> getReporterTeams() {
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxUsersReporterTeam");
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
		return sqlUtilDAO.queryHqlListBySession(sql,new MxUsersReporterTeam()).get(0);
	}
	//�û�id��ȡ������Ϣ
	public MxUsersReporterScore getScoreByUserId(Integer userId) {
		String sql="select s.* from [MXDB].[dbo].[MX_users_reporter_score] s where s.user_id ='"+userId+"'";
		if(sqlUtilDAO.queryHqlListBySession(sql,new MxUsersReporterScore()).size()==0){
			return null;
		}
		return sqlUtilDAO.queryHqlListBySession(sql,new MxUsersReporterScore()).get(0);
	}
	//���û�id��ȡ����
	public List<MxNewsData> getNewsDataByUserId(Integer userId) {
		String sql="select s.* from [MXDB].[dbo].[MX_news_data] s where s.news_writerID ='"+userId+"'";
		return sqlUtilDAO.queryHqlListBySession(sql,new MxNewsData());
	}
	//��������id��ȡ����
	public MxNewsData getNewsDataByNewsId(Integer newsId) {
		String sql="select s.* from [MXDB].[dbo].[MX_news_data] s where s.news_id ='"+newsId+"'";
		return sqlUtilDAO.queryHqlListBySession(sql,new MxNewsData()).get(0);
	}


    
	
    

}
