package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;
import com.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.bean.sysBean.ActivitiesUserMySpaceMine;
import com.dao.IActivitiesMySpaceDAO;

public class ActivitiesMySpaceDAOImpl extends HibernateDaoSupport implements IActivitiesMySpaceDAO{

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
		List<MxActivitiesMySpaceUsers> userMySpaceList = getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceUsers au where au.mxUsersData.userId = "+ userId);
		return userMySpaceList;
	}

	public List<MxActivitiesMySpaceData> getMySpaceListBySpceIds(List<MxActivitiesMySpaceUsers> userMySpaceList) {
		// TODO Auto-generated method stub
		List<MxActivitiesMySpaceData> userSpaceList=new ArrayList();
		for(int i=0;i<userMySpaceList.size();i++){
			MxActivitiesMySpaceData spaceData=(MxActivitiesMySpaceData) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ userMySpaceList.get(i).getMyspaceId()).get(0);
			userSpaceList.add(spaceData);
		}
		return userSpaceList;
	}

	public MxActivitiesMySpaceData getMySpaceBySpaceId(int myspaceId) {
		// TODO Auto-generated method stub
		return (MxActivitiesMySpaceData) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceData au where au.myspaceId = "+ myspaceId).get(0);
	}

	public MxActivitiesMySpaceUsers getMySpaceUserByUserId_SpaceId(
			Integer userId, int myspaceId) {
		// TODO Auto-generated method stub
		return (MxActivitiesMySpaceUsers) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId+"and au.userId="+userId).get(0);
	}

	public List<MxActivitiesMySpaceComment> getUserMySpaceCommontList(
			int myspaceId) {
		// TODO Auto-generated method stub
		//List<ActivitiesUserMySpaceComment> reutnList=new ArrayList();
		List<MxActivitiesMySpaceComment> myspaceCommentList=getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceComment au order by au.createDate desc where au.myspaceId = "+ myspaceId);
		
		return myspaceCommentList;
	}

	public List<ActivitiesUserMySpaceMaterial> getUserMySpaceMaterialList(
			int myspaceId) {
		// TODO Auto-generated method stub
		List<ActivitiesUserMySpaceMaterial> returnBuff=new ArrayList();
		List<MxActivitiesMySpaceUsers> spaceUsers=getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId);
		for(int i=0;i<spaceUsers.size();i++){
			ActivitiesUserMySpaceMaterial itemBuff=new ActivitiesUserMySpaceMaterial();
			List<MxActivitiesMySpaceMaterial> materialsBuff=getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceMaterial au where au.submitUserId = "+ spaceUsers.get(i).getMxUsersData().getUserId()+" order by au.createDate desc ");
			//MxUsersData user=(MxUsersData) getHibernateTemplate().find("from com.bean.MxUsersData au where au.userId = "+ spaceUsers.get(i).getUserId()).get(0);
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
		return getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceUsers au where au.myspaceId = "+ myspaceId);
	}

	public ActivitiesUserMySpaceMine getMySpaceUserMine(Integer userId,
			int myspaceId) {
		// TODO Auto-generated method stub
		ActivitiesUserMySpaceMine returnList=new ActivitiesUserMySpaceMine();
		List<MxActivitiesMySpaceMaterial> materialsBuff=getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceMaterial au where au.submitUserId = "+ userId+" and au.myspaceId="+myspaceId+" order by au.createDate desc ");
		List<MxActivitiesMySpaceComment> myspaceCommentList=getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceComment au where au.myspaceId = "+ myspaceId +" and au.mxUsersData.userId="+userId+"order by au.createDate desc");
		returnList.setActivitiesMySpaceCommentMineList(myspaceCommentList);
		returnList.setActivitiesMySpaceMaterialMineList(materialsBuff);
		return returnList;
	}

	public boolean commentClickPraise(int commentId, int userId) {
		// TODO Auto-generated method stub
		MxActivitiesMySpaceComment comment=(MxActivitiesMySpaceComment) getHibernateTemplate().find("from com.bean.MxActivitiesMySpaceComment au where au.commentId = "+ commentId).get(0);
	
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

	public boolean myspaceCommentDelete(int commentId) {
		try{
		getHibernateTemplate().delete(getHibernateTemplate().get(MxActivitiesMySpaceComment.class , commentId));
		getHibernateTemplate().flush();
		return true;
		}catch(Exception e){
			return false;
		}
	}


}
