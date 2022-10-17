package com.prasoon.firealarm;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Vibrator;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView fireStatus, gas, temperature;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireStatus = findViewById(R.id.fire_status);
        gas = findViewById(R.id.gas);
        temperature = findViewById(R.id.temperature);
        // Database Access
        myRef = FirebaseDatabase.getInstance().getReference().child("status");

        // Vibration Service
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 500, 500};

        myRef.child("fire_status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean fireDetected = dataSnapshot.getValue(Boolean.class);
                if(Boolean.TRUE.equals(fireDetected)){
                    fireStatus.setText("Fire Detected!!");
                    fireStatus.setTextColor(Color.parseColor("#FF0000"));
                    v.vibrate(pattern, 0);
                } else {
                    fireStatus.setText("No Fire Detected");
                    fireStatus.setTextColor(Color.parseColor("#AAFF00"));
                    v.cancel();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIRE", "Failed to read value.", error.toException());
            }
        });
        myRef.child("gas_value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gas_value = snapshot.getValue(String.class);
                Log.i("temp", gas_value);
                gas.setText(gas_value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temperature_value = snapshot.getValue(String.class);
                Log.i("temp", temperature_value);
                temperature.setText(temperature_value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}