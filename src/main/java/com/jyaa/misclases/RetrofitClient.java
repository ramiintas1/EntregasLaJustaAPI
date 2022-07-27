package com.jyaa.misclases;

import com.jyaa.misDTO.LoginDto;
import com.jyaa.misclases.Login;
import com.jyaa.misInterfaces.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitClient {
	
	Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://ec2-35-173-57-183.compute-1.amazonaws.com:8080/api/").addConverterFactory(GsonConverterFactory.create());
	
	Retrofit retrofit = builder.build(); 
	
	UserClient userClient = retrofit.create(UserClient.class);
									
	private static String token;
	
	public void login() {
		Login login = new Login();
		login.setUserName("ramiro2@ramiro.com");
		login.setUserPassword("123456");
		Call<UsuarioJusta> call= userClient.login(login);
		
		call.enqueue(new Callback<UsuarioJusta>() {
			
			public void onResponse(Call<UsuarioJusta> call, Response<UsuarioJusta> response) {
				if(response.isSuccessful()) {
					//Toast.makeText(RetrofitClient.this,response.body().getApellido(), Toast.LENGTH_SHORT).show();
					token = response.body().getValue(); 
					System.out.println(token);
				}else {
					System.out.println("Token incorrecto");
				}
			}
			
			public void onFailure(Call<UsuarioJusta> call, Throwable t) {
				System.out.println("Token incorrecto");
			}
		});
		
	}
}
