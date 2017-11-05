package com.mx.ssh.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceComment;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxActivitiesMySpaceInviteCode;
import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;
import com.mx.ssh.bean.MxActivitiesMySpaceUsers;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMine;
import com.mx.ssh.dao.IActivitiesMySpaceDAO;


@Repository("activitiesMySpaceDAO")
public class ActivitiesMySpaceDAOImpl extends HibernateDaoSupport implements IActivitiesMySpaceDAO{

	
	@Autowired 
    public void setSessionFactoryOverride(SessionFactory sessionFactory)
    { 
      super.setSessionFactory(sessionFactory); 
    } 
	
	/**
	 * 保存空间讨论内容
	 * 
	 */
	public boolean saveActivitiesMySpaceComment(MxActivitiesMySpaceComment activitiesMySpaceComment) {
		// TODO Auto-generated method stub
		try {
			getHibernateTemplate().save(activitiesMySpaceComment);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<MxActivitiesMySpaceUsers> getMySpaceListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<MxActivitiesMySpaceUsers> userMySpaceList = getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.mxUsersData.userId = "+ userId);
		return userMySpaceList;
	}

	public List<MxActivitiesMySpaceData> getMySpaceListBySpceIds(List<MxActivitiesMySpaceUsers> userMySpaceList) {
		// TODO Auto-generated method stub
		List<MxActivitiesMySpaceData> userSpaceList=new ArrayList();
		for(int i=0;i<userMySpaceList.size();i++){
			MxActivitiesMySpaceData spaceData=(MxActivitiesMySpaceData) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ userMySpaceList.get(i).getMyspaceId()).get(0);
			userSpaceList.add(spaceData);
		}
		return userSpaceList;
	}

	public MxActivitiesMySpaceData getMySpaceBySpaceId(int myspaceId) {
		// TODO Auto-generated method stub
		return (MxActivitiesMySpaceData) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ myspaceId).get(0);
	}

	public MxActivitiesMySpaceUsers getMySpaceUserByUserId_SpaceId(
			Integer userId, int myspaceId) {
		// TODO Auto-generated method stub
		return (MxActivitiesMySpaceUsers) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId+"and au.userId="+userId).get(0);
	}

	public List<MxActivitiesMySpaceComment> getUserMySpaceCommontList(
			int myspaceId) {
		// TODO Auto-generated method stub
		//List<ActivitiesUserMySpaceComment> reutnList=new ArrayList();
		//List<MxActivitiesMySpaceComment> myspaceCommentList=getHibernateTemplate().find(" from com.mx.ssh.bean.MxActivitiesMySpaceComment c order by c.createDate desc where c.myspaceId = "+ myspaceId);
		List<MxActivitiesMySpaceComment> myspaceCommentList=getHibernateTemplate().find("select c from MxActivitiesMySpaceComment c,MxActivitiesMySpaceUsers u where c.mxUsersData.userId=u.mxUsersData.userId and c.myspaceId = "+ myspaceId +" order by c.createDate desc");
		
		return myspaceCommentList;
	}

	@SuppressWarnings("unchecked")
	public List<ActivitiesUserMySpaceMaterial> getUserMySpaceMaterialList(
			int myspaceId) {
		// TODO Auto-generated method stub
		List<ActivitiesUserMySpaceMaterial> returnBuff=new ArrayList();
		List<MxActivitiesMySpaceUsers> spaceUsers=getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId);
		for(int i=0;i<spaceUsers.size();i++){
			ActivitiesUserMySpaceMaterial itemBuff=new ActivitiesUserMySpaceMaterial();
			List<MxActivitiesMySpaceMaterial> materialsBuff=getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceMaterial au where au.submitUserId = "+ spaceUsers.get(i).getMxUsersData().getUserId()+" order by au.createDate desc ");
			itemBuff.setUserData(spaceUsers.get(i).getMxUsersData());
			itemBuff.setUserMySpaceMaterialList(materialsBuff);
			returnBuff.add(itemBuff);
		}
		
		return returnBuff;
	}

	public boolean saveActivitiesMySpaceMaterial(MxActivitiesMySpaceMaterial material) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().save(material);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}

	public List<MxActivitiesMySpaceUsers> getMySpaceUsersList(int myspaceId) {
		// TODO Auto-generated method stub/MX_System/src/com/bean/MxActivitiesMySpaceUsers.java
		return getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId);
	}

	public ActivitiesUserMySpaceMine getMySpaceUserMine(Integer userId,
			int myspaceId) {
		// TODO Auto-generated method stub
		ActivitiesUserMySpaceMine returnList=new ActivitiesUserMySpaceMine();


		List<MxActivitiesMySpaceMaterial> materialsBuff=getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceMaterial au where au.submitUserId = "+ userId+" and au.myspaceId="+myspaceId+" order by au.createDate desc ");

		List<MxActivitiesMySpaceComment> myspaceCommentList=getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceComment au where au.myspaceId = "+ myspaceId +" and au.mxUsersData.userId="+userId+" and au.parentCommentId=-1 order by au.createDate desc");
		returnList.setActivitiesMySpaceCommentMineList(myspaceCommentList);
		returnList.setActivitiesMySpaceMaterialMineList(materialsBuff);
		return returnList;
	}

	public boolean commentClickPraise(int commentId, int userId) {
		// TODO Auto-generated method stub
		MxActivitiesMySpaceComment comment=(MxActivitiesMySpaceComment) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceComment au where au.commentId = "+ commentId).get(0);
	
		if (comment == null) {
			return false;
		} else {
			String clickPraiseUserIds = comment.getPraiseUserIds();
			if(clickPraiseUserIds.length()>0){
				String str[] = clickPraiseUserIds.split(",");
				for (int i = 0; i < str.length; i++) {
					System.out.println(str[i]+"===");
					if(Integer.parseInt(str[i])==userId){
						return false;
					}
				}
			}
			String newBuff=clickPraiseUserIds+userId+",";
			comment.setPraiseUserIds(newBuff);
			comment.setPraiseClickNum(comment.getPraiseClickNum()+1);
			getHibernateTemplate().update(comment);
			return true;
		}
		
	}

	public MxActivitiesData getActivityByMyspaceId(int parseInt) {
		// TODO Auto-generated method stub
		
		return (MxActivitiesData) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesData au where au.activitiesId = "+ parseInt).get(0);
	}

	public boolean addActivityInviteCode(
			MxActivitiesMySpaceInviteCode inviteCode) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().save(inviteCode);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	
	/*
	 * 验证活动验证码
	 */
	public int validateInviteCode(String inviteCode,int userId) {
		MxActivitiesMySpaceInviteCode code=(MxActivitiesMySpaceInviteCode) getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceInviteCode au where au.inviteCode ='"+ inviteCode+"'").get(0);
		if(code==null){//验证码不存在
			return -10;
		}else{
			//对比时间是否过期
			if(code.getState()==-1){//验证码已过期失效
				return -11;
			}
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
				Date codeDate=sdf.parse(code.getCreateDate().toString());
				Date now=sdf.parse(new Timestamp(System.currentTimeMillis()).toString());
				long cha = now.getTime() - codeDate.getTime(); 
		        double result = cha * 1.0 / (1000 * 60 * 60); 
		        if(result>24){ 
		             //System.out.println("验证码已过期");
		        	code.setState(-1);
		        	getHibernateTemplate().update(code);//更新验证码code将状态设置为已过期
		            return -11; 
		        }
		        
		        
				List buff= getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.mxUsersData.userId ="+ userId+" and au.myspaceId="+code.getMyspaceId());
				System.out.println(buff);
				if (buff.size()==0) {//为空说明还未加入活动
					MxUsersData user = new MxUsersData();
					user.setUserId(userId);
					MxActivitiesMySpaceUsers spaceUser = new MxActivitiesMySpaceUsers(
							user, code.getMyspaceId(), -111, new Timestamp(System.currentTimeMillis()), 
							new Timestamp(System.currentTimeMillis()), 0, 0, 
							new Timestamp(System.currentTimeMillis()),
							new Timestamp(System.currentTimeMillis()));
					getHibernateTemplate().save(spaceUser);
					return code.getMyspaceId();//成功则返回当前活动空间id
				} else {//不为空说明已经是活动参加人员
					return -12;
				}
		        
		        
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			
		}
		
	}

	public boolean validateMySpaceUser(int myspaceId, int userId) {
		// TODO Auto-generated method stub
		try{
			
			List buff=getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.mxUsersData.userId ="+ userId+" and au.myspaceId="+myspaceId);
			if (buff.size()==0) {
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 移除活动空间用户
	 */
	public boolean deleteMyspaceUser(int myspaceId, int userId) {
		// TODO Auto-generated method stub
		List users=getHibernateTemplate().find("from com.mx.ssh.bean.MxActivitiesMySpaceUsers au where au.mxUsersData.userId ="+ userId+" and au.myspaceId="+myspaceId);
		if(users.size()>0){
			MxActivitiesMySpaceUsers user= (MxActivitiesMySpaceUsers) users.get(0);
			getHibernateTemplate().delete(user);
			return true;
		}else{
			return false;
		}
	}

	
	/*保存帖子的评论
	 * (non-Javadoc)
	 * @see com.mx.ssh.dao.IActivitiesMySpaceDAO#saveMyspaceComment_comment(int, int, java.lang.String)
	 */
	public int saveMyspaceComment_comment(int myspaceId,int commentId, int userId,
			String commentTxt) {
		
		MxUsersData user=new MxUsersData();
		user.setUserId(userId);
		
		MxActivitiesMySpaceComment mc=new MxActivitiesMySpaceComment(user,myspaceId,0,commentTxt,"",0,new Timestamp(System.currentTimeMillis()),commentId);

		try{
			getHibernateTemplate().save(mc);
			return mc.getCommentId();
			//return true;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
	}

	/*删除帖子评论
	 * (non-Javadoc)
	 * @see com.mx.ssh.dao.IActivitiesMySpaceDAO#deleteMyspaceComment_comment(int)
	 */
	public boolean deleteMyspaceComment_comment(int commentId) {
		
		MxUsersData user=new MxUsersData();
		user.setUserId(0);
		MxActivitiesMySpaceComment comment=new MxActivitiesMySpaceComment(user,0,0,"","",0,new Timestamp(System.currentTimeMillis()),0);
		comment.setCommentId(commentId);
		try{
			getHibernateTemplate().delete(comment);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteMyspaceMaterial(int materialId) {
		// TODO Auto-generated method stub
		List ms=getHibernateTemplate().find("from MxActivitiesMySpaceMaterial au where au.materialId ="+ materialId);
		if(ms.size()>0){
			MxActivitiesMySpaceMaterial m= (MxActivitiesMySpaceMaterial) ms.get(0);
			getHibernateTemplate().delete(m);
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteMyspaceComment(int commentId) {
		// TODO Auto-generated method stub
		try{
		Session session= this.getSession();
	    Query q= session.createQuery("delete from MxActivitiesMySpaceComment au where au.commentId="+commentId+" or au.parentCommentId="+commentId);
	    q.executeUpdate();
	    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


}
