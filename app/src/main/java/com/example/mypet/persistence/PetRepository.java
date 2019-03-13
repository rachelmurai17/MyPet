package com.example.mypet.persistence;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PetRepository {

    private PetDao mPetDao;
    private LiveData<List<Pet>> mAllPets;

    public PetRepository(Application application) {
        PetRoomDatabase db = PetRoomDatabase.getDatabase(application);
        mPetDao = db.petDao();
        mAllPets = mPetDao.listPets();
    }

    public LiveData<List<Pet>> listPets() {
        return mAllPets;
    }

    public void add(Pet pet) {
        new insertAsyncTask(mPetDao).execute(pet);
    }

    private static class insertAsyncTask extends AsyncTask<Pet, Void, Void> {

        private PetDao mAsyncTaskDao;

        insertAsyncTask(PetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pet... params) {
            mAsyncTaskDao.add(params[0]);
            return null;
        }
    }
}
