package org.techtown.capture.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmotionActivity extends AppCompatActivity {

    EditText etRan;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        etRan = findViewById(R.id.etRan);
        btn1 = findViewById(R.id.btn1);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi jsonPlaceHolderApi = retrofit.create(RestApi.class);
        Call<List<Postdeep>> call = jsonPlaceHolderApi.getPostdeeps();
        call.enqueue(new Callback<List<Postdeep>>() {
                         @Override
                         public void onResponse(Call<List<Postdeep>> call, Response<List<Postdeep>> response) {
                             if (!response.isSuccessful()) {
                                 etRan.setText("Code: " + response.code());
                                 return;
                             }
                             List<Postdeep> posts = response.body();
                             for (Postdeep post_deep : posts) {
                                 etRan.setText(post_deep.getemo());

                             }
                         }

                         @Override
                         public void onFailure(Call<List<Postdeep>> call, Throwable t) {
                             etRan.setText(t.getMessage());
                         }

                     });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EmotionActivity.this, MusicActivity.class);
                startActivityForResult(intent, 1005);
            }
        });


    }
}