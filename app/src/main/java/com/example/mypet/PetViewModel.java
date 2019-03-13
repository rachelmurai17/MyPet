package com.example.mypet;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mypet.persistence.Pet;
import com.example.mypet.persistence.PetRepository;

import java.util.List;

public class PetViewModel extends AndroidViewModel {

    private PetRepository mRepository;
    private LiveData<List<Pet>> mAllPets;

    public PetViewModel (Application application) {
        super(application);
        mRepository = new PetRepository(application);
        mAllPets = mRepository.listPets();
    }

    public LiveData<List<Pet>> listPets() { return mAllPets; }

    public void insert(Pet pet) { mRepository.add(pet); }
}
