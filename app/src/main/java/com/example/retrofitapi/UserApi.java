package com.example.retrofitapi;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class UserApi
{
   private static UserApiInterface userApiInterface;
   public static UserApiInterface getUserApiInstance()
   {
       if(userApiInterface==null)
       {
           Retrofit retrofit = new Retrofit.Builder()
                               .baseUrl(UserAddress.BASE_URL)
                               .addConverterFactory(GsonConverterFactory.create())
                                .build();
           userApiInterface=retrofit.create(UserApiInterface.class);


       }

       return userApiInterface;
   }

}
 interface UserApiInterface
{

    @FormUrlEncoded
    @POST("/api/v1/create")
    public Call<User> userSave(@Field("name")String name,
                               @Field("age")int age,
                               @Field("salary")int salary);


    @DELETE("/api/v1/delete/{id}")
    Call<User> deleteData(
            @Path("id") int id

    );

    // @DELETE("/users/{x}/data/{y}/{id}")




}
