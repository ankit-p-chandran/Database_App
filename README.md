# Database_App
**Local Databse App In Android.**



<img src="app/src/main/res/mipmap-xhdpi/ic_launcher_round.png" align="left"
width="100"
    hspace="10" vspace="10">

This is a simple local databse used app it will use the system memory to store values. In this there is student table is there the input is student details. 
<br>

## Preview
<img src="/Screenshot/2.jpg" width="300"  align="left">
<img src="/Screenshot/1.jpg" width="300" align="left">
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

## Implementation
Now databse action on this line of codes.

```private void initLayout() {
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
```


## Follow me
<h4>Hey, while you're here why don't you think of following me for awesome projects like this, ah? <a href="https://github.com/ankit-p-chandran">Follow Me on my Profile!</a></h4>

<br>
Lets grab code with chai.

