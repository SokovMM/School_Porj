package com.example.school_porj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Second2 extends AppCompatActivity {
    private ListView listGetClass;
    private ArrayList<StudentClass> postArrayList;
    public JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        listGetClass = findViewById(R.id.GetClassSecond2Act2);
// ПОДКЛЮЧЕНИЕ
        postArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.62:8081/students/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonApi = retrofit.create(JsonApi.class);
// ПОДКЛЮЧЕНИЕ
        Call<ArrayList<StudentClass>> arrayListCallGetClass = jsonApi.callPost2();
        arrayListCallGetClass.enqueue(new Callback<ArrayList<StudentClass>>() {


            @Override
            public void onResponse(Call<ArrayList<StudentClass>> call, Response<ArrayList<StudentClass>> response) {
                postArrayList = response.body();

                for (int i = 0; i < postArrayList.size(); i++) {

                    //postArrayList.add()
                    Custom custom = new Custom(postArrayList, Second2.this, R.layout.student_item);

                    listGetClass.setAdapter(custom);


                    Intent intent = new Intent(Second2.this, Third3.class);
                    listGetClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            intent.putExtra("GetNameClass", postArrayList.get(position).getName());
                            intent.putExtra("GetIdClass", postArrayList.get(position).getId());
                            startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentClass>> call, Throwable t) {
                System.out.println("ОШИБКА: " + t.toString());
            }
        });


    }
}