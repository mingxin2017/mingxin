package com.dao;

import java.util.List;

import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceUsers;

public interface IActivitiesMySpaceDAO {

	boolean saveActivitiesMySpaceComment(MxActivitiesMySpaceComment activitiesMySpaceComment);

	List<MxActivitiesMySpaceUsers> getMySpaceListByUserId(Integer userId);

	List<MxActivitiesMySpaceData> getMySpaceListBySpceIds(List<MxActivitiesMySpaceUsers> userMySpaceList);

}
