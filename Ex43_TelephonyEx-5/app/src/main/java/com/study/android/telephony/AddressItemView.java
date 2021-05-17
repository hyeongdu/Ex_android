package com.study.android.telephony;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AddressItemView extends LinearLayout {

    TextView txtName;
    Button btnTelNum;
    ImageView imageView1;

    public AddressItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.address_item_view, this, true);

        txtName = findViewById(R.id.txtName);
        btnTelNum = findViewById(R.id.btnTelNum);
        imageView1 = findViewById(R.id.imageView1);
    }

    public void setName(String name) {
        txtName.setText(name);
    }

    public void setTel(String telnum) {
        btnTelNum.setText(telnum);
    }

    public void setImage(Bitmap photo) {
        imageView1.setImageBitmap(photo);
    }
}
