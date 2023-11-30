package edu.birzeit.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    public static final String TASKS = "TASKS";
    private EditText edtTitle;
    private Button btnSave;
    private EditText edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edtTitle = findViewById(R.id.edtTitle);
        btnSave = findViewById(R.id.btnSave);
        edtContent = findViewById(R.id.edtContent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String taskTitle = edtTitle.getText().toString();
        String taskText = edtContent.getText().toString();

        Task task = new Task();
        task.setTaskTitle(taskTitle);
        task.setTaskTxt(taskText);
        task.setTaskStatus(false);

        List<Task> taskList = getTasksFromSharedPreferences();
        taskList.add(task);
        saveTasksToSharedPreferences(taskList);
        finish();
    }

    private List<Task> getTasksFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE);
        String tasksJson = sharedPreferences.getString("taskList", "");
        if (tasksJson.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(tasksJson, new TypeToken<List<Task>>() {}.getType());
        }
    }

    private void saveTasksToSharedPreferences(List<Task> taskList) {
        SharedPreferences sharedPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String tasksJson = new Gson().toJson(taskList);
        editor.putString("taskList", tasksJson);
        editor.commit();
    }

    public void addItem(View view) {
    }
}