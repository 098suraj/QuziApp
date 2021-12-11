package com.example.firebase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firebase.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {
    private MutableLiveData<FirebaseUser>firebaseUserMutableLiveData;
    private FirebaseUser currentUser;
    private AuthRepository authRepository;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }



    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepository=new AuthRepository(application);
        currentUser=authRepository.getFirebaseAuth().getCurrentUser();
        firebaseUserMutableLiveData=authRepository.getFirebaseUserMutableLiveData();
    }
    public  void  signUp(String email,String passowd){authRepository.signUp(email, passowd);}
    public void signIn(String email,String password){authRepository.signIn(email, password);}
    void singOut(){authRepository.singout();}
}
