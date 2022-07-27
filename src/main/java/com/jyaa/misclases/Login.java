package com.jyaa.misclases;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Login {

@SerializedName("userName")
@Expose
private String userName;
@SerializedName("userPassword")
@Expose
private String userPassword;

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getUserPassword() {
return userPassword;
}

public void setUserPassword(String userPassword) {
this.userPassword = userPassword;
}

}