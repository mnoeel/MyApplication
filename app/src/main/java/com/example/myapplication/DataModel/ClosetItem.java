package com.example.myapplication.DataModel;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Items")
public class ClosetItem {
    @PrimaryKey(autoGenerate = true)
    int uid;
    @ColumnInfo(name = "Color")
    String colorOfItem;
    @ColumnInfo(name = "Season")
    String seasonOfItem;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte [] image;

    public String getColorOfItem() {
        return colorOfItem;
    }
    public void setColorOfItem(String colorOfItem) {
        this.colorOfItem = colorOfItem;
    }


    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getSeasonOfItem() {
        return seasonOfItem;
    }
    public void setSeasonOfItem(String userName) {
        this.seasonOfItem = seasonOfItem;
    }


    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
}
