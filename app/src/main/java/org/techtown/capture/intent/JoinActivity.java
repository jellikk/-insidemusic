package org.techtown.capture.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    Button btnComp;
    RequestQueue requestQueue;
    EditText etID2, etPW2, etNICK, etMail, etPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btnComp = findViewById(R.id.btnComp);
        etID2 = findViewById(R.id.etID2);
        etPW2 = findViewById(R.id.etPW2);
        etNICK = findViewById(R.id.etNICK);
        etMail = findViewById(R.id.etMail);
        etPhone = findViewById(R.id.etPhone);

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mb_id = etID2.getText().toString();
                String mb_pw = etPW2.getText().toString();
                String mb_nick = etNICK.getText().toString();
                String mb_email = etMail.getText().toString();
                String mb_phone = etPhone.getText().toString();

                String url = "http://220.93.229.207:8083/gios/JoinCon";

                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(JoinActivity.this, "회원가입 연결 성공", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(JoinActivity.this, "회원가입 서버 연결 실패", Toast.LENGTH_SHORT).show();

                            }
                        }

                )

                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<>();
                        params.put("mb_id",mb_id);
                        params.put("mb_pw",mb_pw);
                        params.put("mb_nick",mb_nick);
                        params.put("mb_email",mb_email);
                        params.put("mb_phone",mb_phone);

                        return params;
                    }
                };
                request.setShouldCache(false);
                requestQueue.add(request);

                // Intent intent = new Intent();
                finish();


            }
        });
    }
}



