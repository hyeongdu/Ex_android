package com.study.android.ex55_firebasechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "lecture";



    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private String CHAT_NAME;
    private String USER_NAME;

    private EditText chat_edit;
    private ListView chat_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chat_view = findViewById(R.id.chat_view);
        chat_edit = findViewById(R.id.chat_edit);

        //로그인 화면에서 받아온 채팅방 이름 유저 이름 저장

        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chatName");
        USER_NAME = intent.getStringExtra("userName");

        openChat(CHAT_NAME);
    }
    public void onBtnClicked(View v){
        if(chat_edit.getText().toString().equals(""))
            return;
        ChatDTO chat = new ChatDTO(USER_NAME, chat_edit.getText().toString());

        databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat);

        chat_edit.setText("");
    }

    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter){
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
        adapter.add(chatDTO.getUserName() + " : " + chatDTO.getMessage());
    }

    private void removeMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter){
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
        adapter.remove(chatDTO.getUserName() + " : " + chatDTO.getMessage());
    }


    private void openChat(String chat_name) {

        final ArrayAdapter<String> adapter
                        = new ArrayAdapter<>(this,
                                                            android.R.layout.simple_list_item_1,
                                                            android.R.id.text1);
        chat_view.setAdapter(adapter);

        databaseReference.child("chat").child(chat_name)
                                        .addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                addMessage(dataSnapshot, adapter);
                                                Log.d(TAG,"s:" + s);
                                            }

                                            @Override
                                            public void onChildChanged( DataSnapshot dataSnapshot,String s) {

                                            }

                                            @Override
                                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                                            }

                                            @Override
                                            public void onChildMoved( DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
    }
}