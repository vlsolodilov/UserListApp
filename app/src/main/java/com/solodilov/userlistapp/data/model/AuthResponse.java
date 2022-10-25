package com.solodilov.userlistapp.data.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse{

	@SerializedName("Code")
	private int code;

	public int getCode(){
		return code;
	}
}