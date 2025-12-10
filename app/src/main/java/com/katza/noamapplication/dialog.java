package com.katza.noamapplication;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dialog extends AppCompatActivity {

    EditText name;
    EditText password;
    Button saveButton;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);

        // Padding עבור מערכת ה־Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // קישור רכיבים מה־layout
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        saveButton = findViewById(R.id.saveButton);
        info = findViewById(R.id.info);

        // לחיצה על כפתור Save
        saveButton.setOnClickListener(v -> {
            String n = name.getText().toString();
            String p = password.getText().toString();
            info.setText("Saved! Name: " + n + ", Password: " + p);
        });

        // הגדרת גודל הדיאלוג (לא למלא את המסך)
        getWindow().setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }
}
