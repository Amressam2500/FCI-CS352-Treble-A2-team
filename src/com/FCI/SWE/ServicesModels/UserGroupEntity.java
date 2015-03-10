package com.FCI.SWE.ServicesModels;

import java.util.List;
public class UserGroupEntity {
	private String userId;
	private String groupId;
	
	public void set_userId(String userId){
		this.userId= userId;
	}
	public void set_groupID(String groupId){
		this.groupId= groupId;
	}
	public String get_userId(){
		return userId;
	}
	public String get_groupId(){
		return groupId;
	}

}
