package org.techtown.capture.intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class MyCamera extends AppCompatActivity {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView;
    private Button captureBtn, AnaBtn, RecapBtn;
    private ProcessCameraProvider cameraProvider;
    private ImageCapture imageCapture;


    //start
    Preview preview;
    boolean check = true;

    //end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        previewView = findViewById(R.id.previewView);
        captureBtn = findViewById(R.id.captureBtn);
        AnaBtn = findViewById(R.id.AnaBtn);
        RecapBtn = findViewById(R.id.RecapBtn);

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timestamp = System.currentTimeMillis();

                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

                ImageCapture.OutputFileOptions outputFileOptions =
                        new ImageCapture.OutputFileOptions.Builder(getContentResolver(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues).build();

                imageCapture.takePicture(outputFileOptions,ContextCompat.getMainExecutor(MyCamera.this),
                        new ImageCapture.OnImageSavedCallback() {
                            @Override
                            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                                // insert your code here.
                                Log.d("onImageSavedgetSavedUri", String.valueOf(outputFileResults.getSavedUri()));
                                Toast.makeText(getApplicationContext(), "찍혔어요",Toast.LENGTH_LONG).show();
                                //여기 추가
                                cameraProvider.unbind(preview);


                            }
                            @Override
                            public void onError(ImageCaptureException error) {
                                // insert your code here.
                                Log.d(" Camerror", String.valueOf(error));
                                Toast.makeText(getApplicationContext(), "에러났어요",Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        });

        AnaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCamera.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // start



        // end

        test();


    }

    void test2(){
        try {
            cameraProvider = cameraProviderFuture.get();
            bindPreview(cameraProvider);

//                if(check){
//                    bindPreview(cameraProvider);
//                }else{
//                    cameraProvider.unbind(preview);
//                }

        } catch (ExecutionException | InterruptedException e) {
            // No errors need to be handled for this Future.
            // This should never be reached.
        }
    }

    void test(){
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);

//                if(check){
//                    bindPreview(cameraProvider);
//                }else{
//                    cameraProvider.unbind(preview);
//                }

            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));
    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());


        imageCapture =
                new ImageCapture.Builder()
                        .setTargetRotation(previewView.getDisplay().getRotation())
                        .build();

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageCapture, preview);



        RecapBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                test2();

//                Intent intent = new Intent(MyCamera.this, MyCamera.class);
//                startActivity(intent);
            }
        });
    }




}
