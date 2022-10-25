package com.solodilov.userlistapp.data.model;

import com.google.gson.annotations.SerializedName;

public class ListUsersItem{

	@SerializedName("Uid")
	private String uid;

	@SerializedName("User")
	private String user;

	@SerializedName("Language")
	private String language;

	public String getUid(){
		return uid;
	}

	public String getUser(){
		return user;
	}

	public String getLanguage(){
		return language;
	}
}