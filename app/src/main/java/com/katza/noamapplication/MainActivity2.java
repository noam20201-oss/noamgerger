package com.katza.noamapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    Button button;
    SharedPreferences sp;
    EditText name;
    EditText password;
    TextView dis;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp = getSharedPreferences("noamfile",MODE_PRIVATE);

        button = findViewById((R.id.button));
        name = findViewById((R.id.name));
        password = findViewById((R.id.password));
        dis = findViewById((R.id.dis));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name1",name.getText().toString());
                editor.putString("password1",password.getText().toString());
                editor.commit();


            }
        });


        String strname = sp.getString("name1", null);
        String strpassword = sp.getString("password1", null);
        if(strname!= null&& strpassword!= null)
            dis.setText("hi"+ strname + "your password is"+strpassword);



    }
}