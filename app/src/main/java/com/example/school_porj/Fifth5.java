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

public class Fifth5 extends AppCompatActivity {
    public JsonApi jsonApi;
    private ListView listGet5;
    private ArrayList<StudentClass> postArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth5);

        listGet5 = findViewById(R.id.GetStudent5Prich);
        Bundle arguments = getIntent().getExtras();


        String nameClass = arguments.get("GetNameClass").toString();
        Integer id_class = (Integer) arguments.get("GetIdClass");

        String nameStudent = arguments.get("GetNameStudent").toString();
        Integer idStudent = (Integer) arguments.get("GetIdStudent");




        postArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.62:8081/students/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonApi = retrofit.create(JsonApi.class);

        Call<ArrayList<StudentClass>> arrayListCallGetClass = jsonApi.callPost3();
        arrayListCallGetClass.enqueue(new Callback<ArrayList<StudentClass>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentClass>> call, Response<ArrayList<StudentClass>> response) {
                postArrayList = response.body();
                //       for (int i = 0; i < postArrayList.size(); i++) ;
                {
                    //postArrayList.add()
                    Custom custom = new Custom(postArrayList, Fifth5.this, R.layout.student_item);
                    listGet5.setAdapter(custom);
                }

                Intent intent = new Intent(Fifth5.this, fourth4.class);
                listGet5.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                        intent.putExtra("GetNamePrichin",postArrayList.get(position).getName());
                        intent.putExtra("GetIdPrichin", postArrayList.get(position).getId());


                        intent.putExtra("GetNameClass",nameClass);
                        intent.putExtra("GetIdClass",id_class);


                        intent.putExtra("GetNameStudent", nameStudent);
                        intent.putExtra("GetIdStudent", idStudent);
                        startActivity(intent);

                    }
                });


            }

            @Override
            public void onFailure(Call<ArrayList<StudentClass>> call, Throwable t) {

            }
        });
    }
}