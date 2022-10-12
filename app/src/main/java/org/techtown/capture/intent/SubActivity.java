package org.techtown.capture.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    EditText etEmo;
    Button btnOne, btnTwo, btnThr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        etEmo = findViewById(R.id.etEmo);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThr = findViewById(R.id.btnThr);

        etEmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etEmo.getText().toString().equals("슬픔")) {
                    btnOne.setText("슬플때 더 슬픈 음악");
                    btnTwo.setText("슬플때 더 기쁜 음악");
                    btnThr.setText("슬플때 더 분위기전환 음악");
                } else if (etEmo.getText().toString().equals("기쁨")) {
                    btnOne.setText("기쁠때 더 슬픈 음악");
                    btnTwo.setText("기쁠때 더 기쁜 음악");
                    btnThr.setText("기쁠때 더 분위기전환 음악");
                } else if (etEmo.getText().toString().equals("화남")) {
                    btnOne.setText("화날때 더 슬픈 음악");
                    btnTwo.setText("화날때 더 기쁜 음악");
                    btnThr.setText("화날때 더 분위기전환 음악");

                }
            }
        });

    }
}