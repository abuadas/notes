package edu.birzeit.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TASKS = "TASKS";
    private Button btnAdd;
    private ListView listTasks;
    private List<Task> list;
    private ArrayAdapter<Task> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        listTasks = findViewById(R.id.listTasks);
        list = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listTasks.setAdapter(taskAdapter);
        loadTasks();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTaskActivity();
            }
        });
    }

    private void openAddTaskActivity() {
        Intent intent = new Intent(this, AddTask.class);
        startActivityForResult(intent, ADD_TASK_REQUEST);
    }

    private static final int ADD_TASK_REQUEST = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            Task task = data.getParcelableExtra("task");
            list.add(task);
            taskAdapter.notifyDataSetChanged();
        }
    }

    private void saveTasks() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String strJsonTasks = gson.toJson(list);
        editor.putString(TASKS, strJsonTasks);
        editor.commit();
    }

    private void loadTasks() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String strJsonTasks = prefs.getString(TASKS, "");

        if (!strJsonTasks.isEmpty()) {
            Task[] tasks = gson.fromJson(strJsonTasks, Task[].class);
            list.clear();
            list.addAll(Arrays.asList(tasks));
            taskAdapter.notifyDataSetChanged();
        }
    }
}
