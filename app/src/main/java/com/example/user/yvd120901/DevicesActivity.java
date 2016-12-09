package com.example.user.yvd120901;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DevicesActivity extends AppCompatActivity {
    final int REQUEST_ENABLE_BT = 321;
    MyReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        mReceiver = new MyReceiver();
        BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter( );
        if( !mBtAdapter.isEnabled() ) {
            Intent enableIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
            startActivityForResult( enableIntent, REQUEST_ENABLE_BT ); }

        mBtAdapter.startDiscovery();
        // 搜尋到藍牙裝置時，呼叫我們的函式
        IntentFilter filter = new IntentFilter( BluetoothDevice.ACTION_FOUND );
        this.registerReceiver( mReceiver, filter );

        // 搜尋的過程結束後，也呼叫我們的函式
        filter = new IntentFilter( BluetoothAdapter.ACTION_DISCOVERY_FINISHED );
        this.registerReceiver( mReceiver, filter );

    }
    class MyReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BT", "Found!!");
            Toast.makeText(DevicesActivity.this, "Bluetooth found!", Toast.LENGTH_SHORT).show();

            String action = intent.getAction();
            //找到設備
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Log.d("BT", "find device:" + device.getName()
                            + device.getAddress());
                    Toast.makeText(DevicesActivity.this, "device:" + device.getName()
                            + device.getAddress(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}


