package com.example.myapplication.DataModel;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(
        entities = ClosetItem.class,
        version = 1,
        exportSchema = false
)
public abstract class UserDatabase extends RoomDatabase {

        private static UserDatabase userDB = null;

        public abstract ItemDAO itemDAO();

        public static synchronized UserDatabase getDBInstance(Context context) {
            if(userDB == null) {
                userDB = Room.databaseBuilder(
                        context.getApplicationContext(),
                        UserDatabase.class,
                        "user19b2"
                ).allowMainThreadQueries().build();
            }
            return userDB;
        }
}

