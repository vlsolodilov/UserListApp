package com.solodilov.userlistapp.data.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("Users")
	private Users users;

	public Users getUsers(){
		return users;
	}
}