package com.example.firebase.repository;

import androidx.annotation.NonNull;

import com.example.firebase.Model.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class QuestionRepository {
    private FirebaseFirestore firestore;
    private String quizId;
    private HashMap<String,Long>resultMap=new HashMap<>();
    private OnQuestionLoad onQuestionLoad;
    private OnResultAdded onResultAdded;
    private String currentUserID= FirebaseAuth.getInstance().getCurrentUser().getUid();
    private OnResultLoad onresultLoad;
    public void getResults(){
        firestore.collection("Quiz").document(quizId).collection("results")
                .document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    resultMap.put("correct",task.getResult().getLong("correct"));
                    resultMap.put("wrong",task.getResult().getLong("wrong"));
                    resultMap.put("notAnswered",task.getResult().getLong("notAnswered"));
                    onresultLoad.onResultLoad(resultMap);
                }else{
                    onresultLoad.onError(task.getException());
                }
            }
        });

    }
    public interface OnResultLoad{
        void onResultLoad(HashMap<String,Long>resultMap);
        void onError(Exception e);
    }
    public interface OnQuestionLoad{
        void onLoad(List<QuestionModel> questionModels);
        void onError(Exception e);
    }
    public interface OnResultAdded{
        boolean onSubmit();
        void onError(Exception e);
    }
}
