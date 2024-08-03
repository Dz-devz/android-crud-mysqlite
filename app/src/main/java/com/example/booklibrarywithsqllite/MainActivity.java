package com.example.booklibrarywithsqllite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editId, editName, editEmail;
    private Button addButton, deleteButton, showButton, showAllButton, deleteAllButton, updateButton;

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
    }


}