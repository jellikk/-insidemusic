package org.techtown.capture.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText etId, etPw;
    Button btnJoin, btnLogin;
    RequestQueue requestQueue;
    boolean check = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPw);
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);



        String url = "http://220.93.229.207:8083/gios/loginCon";
//                url +="?id=";
//                url +=id;
//                url +="?pw=";
//                url +=pw;

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String mb_id = etId.getText().toString();
                        String mb_pw = etPw.getText().toString();

                        StringRequest request = new StringRequest(
                                Request.Method.POST,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//                                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();

//                                        한글 인코딩
                                        String rs = null;
                                        try {
                                            rs = URLDecoder.decode(response, "UTF-8");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        if(!response.equals("fail")){
                                            Intent intent = new Intent(LoginActivity.this, EmotionActivity.class);
                                            Toast.makeText(LoginActivity.this, rs+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "아이디, 패스워드를 확인하세요.", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(LoginActivity.this, "연결실패",Toast.LENGTH_SHORT).show();
                                    }
                                }

                        ){
                            // getParams라는 메소드 오버라이딩 : ctrl + o
                            // alt + insert

                             // 안드로이드 한글
                            @Override
                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                                return super.parseNetworkResponse(response);
                                try {
                                    String utf8String = new String(response.data, "UTF-8");
                                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                                } catch (UnsupportedEncodingException e) {
                                    // log error
                                    return Response.error(new ParseError(e));
                                } catch (Exception e) {
                                    // log error
                                    return Response.error(new ParseError(e));
                                }
                            }


                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("mb_id", mb_id);
                                params.put("mb_pw", mb_pw);

                                return params;
                            }
                        };

                        request.setShouldCache(false);
                        requestQueue.add(request);
//                        Intent intent = new Intent(LoginActivity.this, EmotionActivity.class);
//                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
                    }
                });

                //btnJoin
        btnJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                        startActivity(intent);
                    }

                });
            }
    }

