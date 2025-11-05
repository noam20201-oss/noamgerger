package com.katza.noamapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1;
    Button button2;
    ImageView imageView;
    SeekBar seekBar2 ;
    Switch switch1;
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

        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        switch1 = findViewById(R.id.switch1);
        seekBar2 = findViewById(R.id.seekBar2);
        
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // ככל שהסליידר ימינה יותר, התמונה תופיע יותר (שקיפות נמוכה יותר)
                float alpha = progress / (float) seekBar.getMax(); // הופך את הערך ליחס בין 0 ל-1
                imageView.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // מציג הודעה כשהמשתמש מתחיל להזיז את הסליידר
                Toast.makeText(MainActivity.this, "התחלת להזיז את הסליידר", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // מציג הודעה כשהמשתמש מפסיק להזיז את הסליידר
                int progress = seekBar.getProgress();
                Toast.makeText(MainActivity.this, "עצרת על ערך: " + progress, Toast.LENGTH_SHORT).show();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // אם המתג פועל (ON) – הצגת התמונה
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    // אם המתג כבוי (OFF) – הסתרת התמונה
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            // הצגת התמונה
            imageView.setVisibility(View.VISIBLE);
        } else if (v.getId() ==R.id.button2) {
            // הסתרת התמונה
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
