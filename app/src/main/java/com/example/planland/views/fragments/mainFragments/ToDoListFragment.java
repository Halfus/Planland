package com.example.planland.views.fragments.mainFragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.planland.R;
import com.example.planland.databinding.FragmentSettingsBinding;
import com.example.planland.databinding.FragmentToDoListBinding;
import com.example.planland.views.activities.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoListFragment extends Fragment
{
    public static final int TEXT_REQUEST = 1;
    public static int positionToBeChanged = 0;
    public static boolean isAddingNewTodo = false;
    private ListView listView;
    private FragmentToDoListBinding binding;
    private FloatingActionButton buttonNewToDo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentToDoListBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_to_do_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                positionToBeChanged = position;
//                //Todo: todos are to do
//            }
//        });
//
//        buttonNewToDo.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(new NewToDoFragment())
//                        .navigate(R.id.action_TodoList_to_NewToDo);
//            }
//        });
//        registerForContextMenu(listView);
    }
}