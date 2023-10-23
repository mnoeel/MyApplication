package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.DataModel.ItemDAO;
import com.example.myapplication.DataModel.UserDatabase;

public class ShowUsersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemDAO itemDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        recyclerView =findViewById(R.id.userRecyclerView);
        itemDAO = UserDatabase.getDBInstance(this).itemDAO();
        UserRecycler userRecycler = new UserRecycler(itemDAO.getAllClosetItems());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userRecycler);
    }
}