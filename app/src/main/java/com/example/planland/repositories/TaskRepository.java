package com.example.planland.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.planland.contracts.TaskService;
import com.example.planland.entity.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class TaskRepository implements TaskService {


    private static volatile TaskRepository instance;
    private final static ReentrantLock lock=new ReentrantLock();

    private final DatabaseReference taskRef;

    private TaskRepository(){
        taskRef = FirebaseDatabase.getInstance().getReference("tasks"); //TODO Different change according to your firebase
    }
    //Singleton
    public static TaskRepository getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance==null) {
                    instance = new TaskRepository();
                }
            }
        }
        return instance;
    }

    //TODO: Test the functionality
    @Override
    public LiveData<ArrayList<Task>> GetAllTasks() {

        MutableLiveData<ArrayList<Task>> tempLive = new MutableLiveData<>();

        taskRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Task> temp = new ArrayList<>();

                for (DataSnapshot s : snapshot.getChildren()){
                    temp.add(s.getValue(Task.class));
                }

                tempLive.setValue(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}

        });

        return tempLive;

    }

    //TODO: Implement
    @Override
    public Task GetTaskById(String ID) {
        return null;
    }

    @Override
    public void AddTask(Task task) {
        taskRef.child(task.getId()).setValue(task);
    }

    @Override
    public void UpdateTask(Task task) {
        taskRef.child(task.getId()).setValue(task);
    }
    @Override
    public void DeleteTask(String  id) {
        taskRef.child(id).setValue(null);
    }


}
