package com.jyaa.misInterfaces;

import com.jyaa.misDTO.LoginDto;
import com.jyaa.misclases.Login;
import com.jyaa.misclases.UsuarioJusta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {
	
	@POST("/token/generate-token")
	Call<UsuarioJusta> login(@Body Login login);

}
