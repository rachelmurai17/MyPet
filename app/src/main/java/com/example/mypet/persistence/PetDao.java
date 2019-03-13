package com.example.mypet.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Pet pet);

    @Delete
    void delete(Pet pet);

    @Query("SELECT * FROM pet_table ORDER BY created ASC")
    LiveData<List<Pet>> listPets();
}