package com.example.firebase.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebase.Model.QuizListModel;
import com.example.firebase.repository.QuizListRepository;

import java.util.List;

public class QuizListViewModel extends ViewModel implements QuizListRepository.onFirestoreTaskComplete {
    private MutableLiveData<List<QuizListModel>> quizListLiveData=new MutableLiveData<>();
    private QuizListRepository repository=new QuizListRepository(this);

    public QuizListViewModel(MutableLiveData<List<QuizListModel>> quizListLiveData) {
        this.quizListLiveData = quizListLiveData;
    }

    @Override
    public void quizDataLoaded(List<QuizListModel> quizListModels) {
    quizListLiveData.setValue(quizListModels);
    }

    public MutableLiveData<List<QuizListModel>> getQuizListLiveData() {
        return quizListLiveData;
    }

    @Override
    public void onError(Exception e) {

            Log.d("QuizERROR","onError"+ e.getMessage());

    }
}
