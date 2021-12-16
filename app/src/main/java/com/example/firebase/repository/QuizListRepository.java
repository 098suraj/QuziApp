package com.example.firebase.repository;

import androidx.annotation.NonNull;

import com.example.firebase.Model.QuizListModel;
import com.example.firebase.ViewModel.QuizListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.List;

public class QuizListRepository {

    private onFirestoreTaskComplete fireStoreTaskComplete;
    private FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    private CollectionReference reference= firestore.collection("Quiz");

    public QuizListRepository(onFirestoreTaskComplete fireStoreTaskComplete) {
        this.fireStoreTaskComplete = fireStoreTaskComplete;
    }
    public void getQuizData(){
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    fireStoreTaskComplete.quizDataLoaded(task.getResult().toObjects(QuizListModel.class));
                }else{
                    fireStoreTaskComplete.onError(task.getException());
                }
            }
        });
    }
    public interface onFirestoreTaskComplete{
        void quizDataLoaded(List<QuizListModel> quizListModels);
        void onError(Exception e);
    }

}
