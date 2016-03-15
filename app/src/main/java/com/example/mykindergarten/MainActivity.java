package com.example.mykindergarten;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        TextView tv=(TextView)findViewById(R.id.textViewprivate);
//        tv.setText("הגן של ");
        ImageButton bAdd = (ImageButton)findViewById(R.id.bAddChild);
        ImageButton bDelete = (ImageButton)findViewById(R.id.bDeleteChild);
        ImageButton bPresence = (ImageButton)findViewById(R.id.bPresence);
        ImageButton bGraphs = (ImageButton)findViewById(R.id.bGraphs);

        bAdd.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bPresence.setOnClickListener(this);
        bGraphs.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        Intent i;

        switch (v.getId())
        {
            case R.id.bAddChild:
                i = new Intent(MainActivity.this, AddChild.class);
                startActivity(i);
                break;
            case R.id.bDeleteChild:
                i = new Intent(MainActivity.this, DeleteChild.class);
                startActivity(i);
                break;
            case R.id.bPresence:
                i = new Intent(MainActivity.this, Presence_new.class);
                startActivity(i);
                break;
        }

    }
}
