package com.example.mypet.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Class definition for a Pet.
 */
@Entity(tableName = "pet_table")
public class Pet {

    public Pet() {
        this.created = System.currentTimeMillis()/1000;
    }

    // Fields

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "breed")
    private String breed;

    @ColumnInfo(name = "age")
    private int age;

    // Getters

    public String getName() {
        return this.name;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getAge() {
        return this.age;
    }

    public int getId() {
        return this.id;
    }

    public long getCreated() {
        return this.created;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}