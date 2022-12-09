package com.example.school_porj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Third3 extends AppCompatActivity {
    public JsonApi jsonApi;
    private ListView listGet3;
    private ArrayList<StudentClass> postArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third3);

       TextView TextgetFromIntent3 = findViewById(R.id.GetClass3);


        Bundle arguments = getIntent().getExtras();


        String nameClass = arguments.get("GetNameClass").toString();
        Integer id_class = (Integer) arguments.get("GetIdClass");



        System.out.println(id_class);
        TextgetFromIntent3.setText(id_class.toString());
        System.out.println(id_class);



       listGet3 = findViewById(R.id.GetStudent3);

// ПОДКЛЮЧЕНИЕ
        postArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.62:8081/students/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonApi = retrofit.create(JsonApi.class);
// ПОДКЛЮЧЕНИЕ

        Call<ArrayList<StudentClass>> arrayListCallGetClass = jsonApi.getClassById(id_class);
        arrayListCallGetClass.enqueue(new Callback<ArrayList<StudentClass>>() {

            @Override
            public void onResponse(Call<ArrayList<StudentClass>> call, Response<ArrayList<StudentClass>> response) {
                System.out.println("Работает");

                postArrayList = response.body();
                //       for (int i = 0; i < postArrayList.size(); i++) ;
                {
                    //postArrayList.add()
                    Custom custom = new Custom(postArrayList, Third3.this, R.layout.student_item);
                    listGet3.setAdapter(custom);
                }

                    Intent intent = new Intent(Third3.this, Fifth5.class);
                    listGet3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            intent.putExtra("GetNameClass",nameClass);
                            intent.putExtra("GetIdClass",id_class);


                            intent.putExtra("GetNameStudent", postArrayList.get(position).getName());
                            intent.putExtra("GetIdStudent", postArrayList.get(position).getId());
                            startActivity(intent);

                        }
                    });
                }


            @Override
            public void onFailure(Call<ArrayList<StudentClass>> call, Throwable t) {
                System.out.println(t.toString());

            }
        });



    }
}