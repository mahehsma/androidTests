package com.example.pathfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String pathToThisElement = "element"; //rename to sdcard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_startPathFinder = findViewById(R.id.button_startPathFinder);
        button_startPathFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPath();
            }
        });
    }

    private void searchPath(){
        Toast.makeText(getApplicationContext(),R.string.startingSearch, Toast.LENGTH_SHORT).show();
        PathFinder pathFinder = new PathFinder();
        try {
            String path = pathFinder.getPath(pathToThisElement);
            setText(path);
        } catch (NoExternalStorageFoundException ex){
            setText(R.string.noExternalStorageFound+"");
        }
    }

    private void setText(String path){
        TextView textView_showPath = findViewById(R.id.textView_path);
        if(path == null) textView_showPath.setText(R.string.pathNotFound);
        else textView_showPath.setText(path);
    }
}