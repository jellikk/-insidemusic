package org.techtown.capture.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MusicList extends AppCompatActivity {

    ArrayList<musiclistVO> data;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);



        data = new ArrayList<>();
        lv = findViewById(R.id.lv);

        data.add(new musiclistVO("김필","어느날"));
        data.add(new musiclistVO("임재범","너를 위해"));
        data.add(new musiclistVO("김범수","보고싶다"));
        data.add(new musiclistVO("정자연","행복"));
        data.add(new musiclistVO("최윤석","기쁨"));

        musiclistAdapter adapter = new musiclistAdapter(
                MusicList.this,
                R.layout.emotion_list,
                data);

        lv.setAdapter(adapter);




    }
}