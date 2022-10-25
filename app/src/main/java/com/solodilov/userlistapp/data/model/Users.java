package com.solodilov.userlistapp.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Users{

	@SerializedName("ListUsers")
	private List<ListUsersItem> listUsers;

	@SerializedName("CurrentUid")
	private String currentUid;

	public List<ListUsersItem> getListUsers(){
		return listUsers;
	}

	public String getCurrentUid(){
		return currentUid;
	}
}