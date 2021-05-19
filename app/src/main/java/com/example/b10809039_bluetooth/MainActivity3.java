package com.example.b10809039_bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private TextView text;private Button botton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        text=(TextView) this.findViewById(R.id.textView3);
        botton=(Button)findViewById(R.id.button4);
        Bundle bundle = getIntent().getExtras();
        String n = bundle.getString("name" );
        String a = bundle.getString("add");
        text.append("裝置名稱"+n+"\n"+"MAC位置"+a);
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity3.this , MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}