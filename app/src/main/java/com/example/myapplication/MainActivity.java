package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DataModel.ClosetItem;
import com.example.myapplication.DataModel.ItemDAO;
import com.example.myapplication.DataModel.UserDatabase;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.itemImage);
        bmpImage = null;

        color = findViewById(R.id.colorOfItem);
        season = findViewById(R.id.seasonOfItem);
        itemDAO = UserDatabase.getDBInstance(this).itemDAO();
    }

    ImageView imageView;
    Bitmap bmpImage;
    EditText color, season;
    ItemDAO itemDAO;
    final int CAMERA_INTENT = 51;
    public void showItems(View view) {
        Intent intent = new Intent(this, ShowUsersActivity.class);
        startActivity(intent);
    }
    public void saveItem(View view) {
        if(color.getText().toString().isEmpty()
                || season.getText().toString().isEmpty()
                || bmpImage ==null) {
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
        }
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(intent, CAMERA_INTENT);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_INTENT:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the image from the intent
                        Uri imageUri = data.getData();

                        try {
                            // Load the image from the URI into a bitmap
                            bmpImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                            // Set the bitmap to the ImageView
                            imageView.setImageBitmap(bmpImage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Log.e("MyApp", "Intent data is NULL");
                        Toast.makeText(this, "Intent data is NULL", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("MyApp", "Result not OK: " + resultCode);
                    Toast.makeText(this, "Result not OK", Toast.LENGTH_SHORT).show();
                }
                break;
        } } }

