package com.example.user.yvd120901;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DevicesActivity extends AppCompatActivity {
    final int REQUEST_ENABLE_BT = 321;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter( );
        if( !mBtAdapter.isEnabled() ) {
            Intent enableIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
            startActivityForResult( enableIntent, REQUEST_ENABLE_BT ); }

        mBtAdapter.startDiscovery();

    }
}
