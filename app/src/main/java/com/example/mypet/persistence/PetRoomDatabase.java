package com.example.mypet.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Pet.class}, version = 1)
public abstract class PetRoomDatabase extends RoomDatabase {
    public abstract PetDao petDao();

    private static volatile PetRoomDatabase INSTANCE;

    static PetRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PetRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PetRoomDatabase.class, "pet_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
