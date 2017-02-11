package ns.dam.isi.frsf.utn.edu.ar.lab09;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private TextView ejeXTextView;
    private TextView ejeYTextView;
    private TextView ejeZTextView;
    private TextView ejeXFechaTextView;
    private TextView ejeYFechaTextView;
    private TextView ejeZFechaTextView;
    private Button resetButton;

    private float maxAcceleracionX;
    private float maxAcceleracionY;
    private float maxAcceleracionZ;


    private SensorManager mSensorManager;
    private Sensor mSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejeXTextView = (TextView) findViewById(R.id.ejeXTextView);
        ejeYTextView = (TextView) findViewById(R.id.ejeYTextView);
        ejeZTextView = (TextView) findViewById(R.id.ejeZTextView);
        ejeXFechaTextView = (TextView) findViewById(R.id.ejeXFechaTextView);
        ejeYFechaTextView = (TextView) findViewById(R.id.ejeYFechaTextView);
        ejeZFechaTextView = (TextView) findViewById(R.id.ejeZFechaTextView);

        resetButton = (Button) findViewById(R.id.ressetButton);
        resetButton.setOnClickListener(this);

        maxAcceleracionX = 0;
        maxAcceleracionY = 0;
        maxAcceleracionZ = 0;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float accX = event.values[0];
        float accY = event.values[1];
        float accZ = event.values[2];
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

        if(maxAcceleracionX < accX) {
            maxAcceleracionX = accX;
            ejeXFechaTextView.setText("Hora: " + df.format(d));
            ejeXTextView.setText("Magnitud: " + maxAcceleracionX);
        }
        if(maxAcceleracionY < accY) {
            maxAcceleracionY = accY;
            ejeYFechaTextView.setText("Hora: " + df.format(d));
            ejeYTextView.setText("Magnitud: " + maxAcceleracionY);
        }
        if(maxAcceleracionZ < accZ) {
            maxAcceleracionZ = accZ;
            ejeZFechaTextView.setText("Hora: " + df.format(d));
            ejeZTextView.setText("Magnitud: " + maxAcceleracionZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ressetButton) {
            maxAcceleracionX = 0;
            maxAcceleracionY = 0;
            maxAcceleracionZ = 0;

            ejeXFechaTextView.setText("Hora: ");
            ejeXTextView.setText("Magnitud: ");
            ejeYFechaTextView.setText("Hora: ");
            ejeYTextView.setText("Magnitud: ");
            ejeZFechaTextView.setText("Hora: ");
            ejeZTextView.setText("Magnitud: ");
        }
    }
}
