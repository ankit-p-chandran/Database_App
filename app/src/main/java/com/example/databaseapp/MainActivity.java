package com.example.databaseapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.databaseapp.adapter.StudentAdapter;
import com.example.databaseapp.database.AppDatabase;
import com.example.databaseapp.database.entity.Student;
import com.example.databaseapp.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextClass = findViewById(R.id.editTextClass);
        EditText editTextAge = findViewById(R.id.editTextAge);
        recyclerView = findViewById(R.id.recyclerStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        new GetStudentTask().execute();

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(v -> {
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String age = editTextAge.getText().toString();
            String clazz = editTextClass.getText().toString();

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setAge(age);
            student.setClazz(clazz);

            new Thread(() -> {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, Constants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();

                db.studentDao().insertAll(student);

            }).start();


            Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();

            editTextFirstName.setText("");
            editTextLastName.setText("");
            editTextAge.setText("");
            editTextClass.setText("");
        });
    }

    class GetStudentTask extends AsyncTask<Void, Void, List<Student>> {

        @Override
        protected List<Student> doInBackground(Void... voids) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
            return db.studentDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            StudentAdapter adapter = new StudentAdapter(students);
            recyclerView.setAdapter(adapter);
        }
    }
}