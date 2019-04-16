package com.hikealert.myapplication;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.lang.reflect.Method;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothLeService bluetoothService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    UUID kUartTxCharacteristicUUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    UUID kUartRxCharacteristicUUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    UUID kUartServiceUUID = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");

    int connected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void goToEmergencyOptions(View view) {
        connected = 0;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //Check if the phone is capable of bluetooth
        if (bluetoothAdapter == null) {
            //If the user doesn't have bluetooth, things won't go well for them
            Context context = getApplicationContext();
            CharSequence text = "Phone does not support Bluetooth. Start digging a hole.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //don't proceed on, because there is no hope for them
            return;
        }
        //Since the user does have bluetooth, see if it's enabled
        if (!bluetoothAdapter.isEnabled()) {
            Context context = getApplicationContext();
            CharSequence text = "Bluetooth: DISABLED";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //If not enabled, ask to enable it
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {

            Context context = getApplicationContext();
            CharSequence text = "Bluetooth: ENABLED";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            handleBluetooth();
        }
    }

    public void goTouserMap(View view) {
        Intent intent = new Intent(this, Map_User.class);
        startActivity(intent);
    }

    public void goToAboutPage(View view) {
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void handleBluetooth() {
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        bluetoothService = new BluetoothLeService();
        bluetoothService.initialize((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE));

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);

        bluetoothAdapter.startDiscovery();

        Intent intent = new Intent(this, emergencyOptions.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The Enabled Bluetooth
                handleBluetooth();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Emergency services REQUIRE Bluetooth";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismiss progress dialog
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                Log.d("Debugging", "Found a device");
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getName() != null && device.getName().toLowerCase().contains("blue")) {
                    Log.d("Debugging", "This is good");
                    Context myContext = getApplicationContext();
                    CharSequence text = "Found device: " + device.getName();

                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(myContext, text, duration);
                    toast.show();
                    // Characteristics



                    //myService.addService(bluetoothService.getSupportedGattServices().get(0));
                    Log.d("Bluetooth stuff", "Address: " + device.getAddress());
                    if(bluetoothService.connect(device) == true){
                        Log.d("Connection Verified", "BLUETOOTH Works, now just for a goddamn message");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //bluetoothService.discoverStuff();
                        displayGattServices(bluetoothService.getSupportedGattServices());

                        String testText = "Hello, World!";
                        byte[] value = testText.getBytes();

                        BluetoothGattCharacteristic sendBT =  mGattCharacteristics.get(4).get(1);

                        BluetoothGatt myGatt = bluetoothService.getGatt();
                        sendBT.setValue(value);
                        final boolean success = myGatt.writeCharacteristic(sendBT);

                        Log.d("Did we do it?", "Maybe? ->  " + success);
                        BluetoothGattCharacteristic recvBT =  mGattCharacteristics.get(4).get(0);
                    }
                }
            }
        }
    };
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException{
        try{
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BTMODULEUUID);
        }
        catch (Exception ex){
            Log.e("debug", "could not create Insecure RFCOMM Connection", ex);
        }
        return device.createInsecureRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mReceiver);
    }
}
