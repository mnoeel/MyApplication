package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DataModel.ClosetItem;
import com.example.myapplication.DataModel.ItemDAO;
import com.example.myapplication.DataModel.UserDatabase;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private UserDatabase closetDatabase;
    private static final int pic_id = 123;
    Button camera_open_id;

    ImageView imageView;
    Bitmap bmpImage;
    EditText color, season;
    ItemDAO itemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.itemImage);
        camera_open_id = findViewById(R.id.camera_button);

        color = findViewById(R.id.colorOfItem);
        season = findViewById(R.id.seasonOfItem);
        itemDAO = UserDatabase.getDBInstance(this).itemDAO();
        closetDatabase = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "closet-database").build();
        camera_open_id.setOnClickListener(v -> {
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id);
        });


    }

    // final int CAMERA_INTENT = 51;
    public void showItems(View view) {
        Intent intent = new Intent(this, ShowUsersActivity.class);
        startActivity(intent);
    }

   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            imageView.setImageBitmap(photo);
        } }

        public void saveItem (View view){
            if (color.getText().toString().isEmpty()
                    || season.getText().toString().isEmpty()
                   ) {
                Toast.makeText(
                        this,
                        "User Data is Missing",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                ClosetItem closetItem = new ClosetItem();
                closetItem.setColorOfItem(color.getText().toString());
                closetItem.setSeasonOfItem(season.getText().toString());
                itemDAO.insertClosetItem(closetItem);
                Toast.makeText(
                        this,
                        "Insertion successful",
                        Toast.LENGTH_SHORT
                ).show();
            } } }






