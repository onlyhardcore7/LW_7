package com.example.julia.lw_7;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sm;
    Sensor s;
    TextView t1,t2;
    Button b1;
    double x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(reset);
    }

    View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            t1.setText("");
            t2.setText("");
            x_max=y_max=z_max=0;
        }
    };
    double x_max = 0,y_max = 0,z_max = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Many sensors return 3 values, one for each axis.
        x = event.values[0]; y = event.values[1]; z = event.values[2];
        // Do something with this sensor value.
        if(x_max==0 && y_max==0 && z_max==0) {
            x_max = x;
            y_max = y;
            z_max = z;
        }
        if(x_max<x) {
            x_max = x;
        }
        if(y_max<y) {
            y_max = y;
        }
        if(z_max<z) {
            z_max = z;
        }
        t1.setText( x + " " + y + " " + z + " ");
        t2.setText( x_max + " " + y_max + " " + z_max);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }
}