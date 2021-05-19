package com.example.b10809039_bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private TextView text;
    String n,a;
    private Button botton,nextPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
         text=(TextView) this.findViewById(R.id.textView2);
         nextPageBtn = (Button)findViewById(R.id.button2);
         botton=(Button)findViewById(R.id.button3);
        IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);
        IntentFilter filter2=new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver,filter2);

        botton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                if(!mBluetoothAdapter.isEnabled())
                {
                    mBluetoothAdapter.enable();

                }

                mBluetoothAdapter.startDiscovery();


            }


        });





        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity2.this , MainActivity3.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",n);
                bundle.putString("add",a);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void onDestroy() {

        super.onDestroy();
        //解除註冊
        unregisterReceiver(mReceiver);
        Log.e("destory","解除註冊");
    }

    private BroadcastReceiver mReceiver=new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            Log.e("ywq", action);
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (device.getBondState() == BluetoothDevice.BOND_BONDED || device.getBondState() != BluetoothDevice.BOND_BONDED) {    //顯示已配對裝置
                    text.append("\n" + device.getName()+ "\n");
                    n=device.getName();
                    a=device.getAddress();
                    botton.setText("關閉掃描");

                } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {

                    text.setText("搜尋完成...");
                    botton.setText("開啟掃描");


                }

            }


        }

    };

    }
