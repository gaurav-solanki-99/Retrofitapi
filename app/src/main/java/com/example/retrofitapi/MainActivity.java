package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitapi.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =binding.etUsername.getText().toString();
                int age =Integer.parseInt(binding.etAge.getText().toString());
                int salary=Integer.parseInt(binding.etsalary.getText().toString());

                UserApiInterface userApiInterface=UserApi.getUserApiInstance();
               Call<User> call= userApiInterface.userSave(name,age,salary);
               call.enqueue(new Callback<User>() {
                   @Override
                   public void onResponse(Call<User> call, Response<User> response) {
                       System.out.println(">>>>>>>>>>>Response Code >>>"+response.code());
                       Log.e(" Code >>>",""+response.code());
                       if(response.code()==200)
                       {
                           Toast.makeText(MainActivity.this, "Data insert Successfully", Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this, "Data not insert", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<User> call, Throwable t) {
                       Toast.makeText(MainActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });


        binding.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(binding.etId.getText().toString());

                UserApiInterface userApiInterface = UserApi.getUserApiInstance();
                Call<User> call =userApiInterface.deleteData(id);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        System.out.println(">>>>>>>>>>>Response Code >>>"+response.code());
                        Log.e(" Code >>>",""+response.code());

                        if(response.code()==200)
                        {
                            Toast.makeText(MainActivity.this, "Success Fully delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}