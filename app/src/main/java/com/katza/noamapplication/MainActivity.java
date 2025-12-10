package com.katza.noamapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
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
    SeekBar seekBar2;
    Switch switch1;
    TextView tv; // ← מהתמונה

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

        // ===== מהתמונה =====
        tv = findViewById(R.id.tv);
        registerForContextMenu(tv);
        // ===================

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alpha = progress / (float) seekBar.getMax();
                imageView.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "התחלת להזיז את הסליידר", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Toast.makeText(MainActivity.this, "עצרת על ערך: " + progress, Toast.LENGTH_SHORT).show();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                imageView.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    // ===== OPTIONS MENU =====
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.activity_main) {
            Toast.makeText(this, "אתה כבר במסך זה", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.activity_main2) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ===== CONTEXT MENU =====
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.firstline) {
            Toast.makeText(this, "You selected first line", Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.secondline) {
            Toast.makeText(this, "You selected second line", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // ===== BUTTONS =====
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            imageView.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.button2) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
