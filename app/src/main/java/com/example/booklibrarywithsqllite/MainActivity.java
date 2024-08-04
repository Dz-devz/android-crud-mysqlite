package com.example.booklibrarywithsqllite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editId, editName, editEmail;
    private Button addButton, deleteButton, showButton, showAllButton, deleteAllButton, updateButton;
    private DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);

        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        showButton = findViewById(R.id.showButton);
        showAllButton = findViewById(R.id.showAllButton);
        deleteAllButton = findViewById(R.id.deleteAllButton);
        updateButton = findViewById(R.id.updateButton);

        myDB = new DatabaseHelper(this);

        addData();
        showData();
        updateData();
        deleteData();
        getAllData();
    }

    public void addData() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertData(editName.getText().toString(), editEmail.getText().toString());

                if (isInserted){
                    Toast.makeText(
                            MainActivity.this,
                            "Data Inserted...",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Something went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showData(){
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editId.setError("Please Enter Id");
                    return;
                }
                    Cursor cursor = myDB.getData(id);
                    String data = null;

                    if(cursor.moveToNext()){
                        data = "ID : " + cursor.getString(0) + "\n" +
                        "NAME: " + cursor.getString(1) + "\n" +
                        "EMIL: " + cursor.getString(2) + "\n";
                        showMessageData("DATA", data);
                    } else{
                        showMessageData("DATA", "There is no Data");
                    }

            }
        });
    }

    public Cursor getAllData() {
        showAllButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Cursor cursor = myDB.getAllData();
                StringBuffer buffer = new StringBuffer();
                if(cursor.getCount() == 0){
                    showMessageData("Data", "Nothing Found");
                    return;
                } while (cursor.moveToNext()){
                    buffer.append("ID : " + cursor.getString(0) + "\n");
                    buffer.append("NAME : " + cursor.getString(1) + "\n");
                    buffer.append("EMAIl : " + cursor.getString(2) + "\n\n");
                }
                showMessageData("DATA", buffer.toString());
            }
        });
    }

    public void updateData(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDB.updateData(editId.getText().toString(),
                        editName.getText().toString(),
                        editEmail.getText().toString());

                if(isUpdated){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showMessageData(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    public void deleteData(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editId.setError("Please Enter Id");
                }
                Integer var = myDB.deleteData(id);

                if(var > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "Deletion Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}