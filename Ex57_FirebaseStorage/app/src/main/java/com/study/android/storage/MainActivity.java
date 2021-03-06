package com.study.android.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    private final String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    private static final int MULTIPLE_PERMISSIONS = 101;

    private RadioGroup radioGroup;
    private ImageView imageView;
    
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        checkPermissions();
        initProgram();
    }



    private boolean checkPermissions() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for (String pm : permissions) {
            result = ContextCompat.checkSelfPermission(this, pm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,
//                    permissionList.toArray(new String[permissionList.size()]),
                    permissionList.toArray(new String[0]),
                    MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals(this.permissions[0])) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            showNoPermissionToastAndFinish();
                        }
                    } else if (permissions[i].equals(this.permissions[1])) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            showNoPermissionToastAndFinish();

                        }
                    } else if (permissions[i].equals(this.permissions[2])) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            showNoPermissionToastAndFinish();

                        }
                    }
                }
            } else {
                showNoPermissionToastAndFinish();
            }
        }
    }

    private void showNoPermissionToastAndFinish() {
        Toast.makeText(this, "?????? ????????? ?????? ???????????? ?????? ???????????????.",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    public void initProgram() {
        radioGroup = findViewById(R.id.radioGroup);
        imageView = findViewById(R.id.imageView);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int imageNum;
                switch (checkedId) {
                    default:
                    case R.id.radio0:
                        imageNum = 1;
                        break;
                    case R.id.radio1:
                        imageNum = 2;
                        break;
                    case R.id.radio2:
                        imageNum = 3;
                        break;
                    case R.id.radio3:
                        imageNum = 4;
                        break;
                }

                printImageList();
                downloadImage(imageNum);
            }
        });
    }


    private void printImageList() {
    }
    public void downloadImage(int imageNum) {
        String folderName = "images";
        String imageName = String.format("Image%d.png", imageNum);

        // Storage ????????? ???????????? ??????
        String storagePath = folderName + "/" + imageName;

        StorageReference imageRef = mStorageRef.child(storagePath);

        try {
            // Storage ?????? ???????????? ???????????? ????????????
            final File imageFile = File.createTempFile("images", "jpg");
            imageRef.getFile(imageFile).addOnSuccessListener(
                    new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Success Case
                            Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getPath());
                            imageView.setImageBitmap(bitmapImage);
                            Toast.makeText(getApplicationContext(), "Success !!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail Case
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Fail !!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onButton1Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
        finish();

    }



}