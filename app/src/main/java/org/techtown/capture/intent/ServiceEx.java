package org.techtown.capture.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.VideoView;

public class ServiceEx extends AppCompatActivity {

    TextView text;
    Animation anim;
    boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ex);
        text = findViewById(R.id.text);

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.inside_music));
        videoView.start();
        videoView.setOnPreparedListener(onPreparedListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            // 다음 화면으로 넘어갈 클래스 지정한다.
            Intent intent = new Intent(getApplicationContext(), show.class);

            startActivity(intent);  // 다음 화면으로 넘어간다.
        }

        return super.onTouchEvent(event);
    }

    MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.setLooping(true);
        }
    };
}