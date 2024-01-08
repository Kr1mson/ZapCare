package com.example.medizap;

import static android.content.Context.SENSOR_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Tracker extends Fragment implements SensorEventListener {

    View view;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView textViewStepCounter;
    protected SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private boolean isCounterSensorPresent;
    private int stepCount = 0;
    private int progressValue = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tracker, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);
        textViewStepCounter = view.findViewById(R.id.textViewStepCounter);

        // Access the activity's window to keep the screen on
        if (getActivity() != null) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        sensorManager = (SensorManager) requireContext().getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = (stepCounterSensor != null);
        } else {
            isCounterSensorPresent = false;
        }


        startProgressUpdates();
        return view;
    }

    private void startProgressUpdates() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressValue <= 100) {
                    updateProgress();
                    handler.postDelayed(this, 200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);
    }

    private void updateProgress() {
        progressText.setText(String.valueOf(progressValue));
        progressBar.setProgress(progressValue);
        progressValue++;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == stepCounterSensor) {
            stepCount = (int) sensorEvent.values[0];
            updateStepCount();
        }
    }

    private void updateStepCount() {
        textViewStepCounter.setText(String.valueOf(stepCount));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isCounterSensorPresent) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isCounterSensorPresent) {
            sensorManager.unregisterListener(this, stepCounterSensor);
        }
    }
}
