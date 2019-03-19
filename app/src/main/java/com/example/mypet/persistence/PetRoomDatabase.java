package com.example.mypet.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PetDao mDao;

        PopulateDbAsync(PetRoomDatabase db) {
            mDao = db.petDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            Pet pet = new Pet();
            pet.setId(1);
            mDao.add(pet);
            pet = new Pet();
            pet.setId(2);
            mDao.add(pet);
            return null;
        }
    }
}
