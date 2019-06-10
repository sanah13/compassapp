package uk.ac.solent.compassapp

import android.hardware.Sensor
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.hardware.Sensor.TYPE_ORIENTATION
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.hardware.SensorEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image: ImageView
        val currentDegree = 0f
        val mSensorManager: SensorManager
        val textview: TextView
        image = (ImageView) findViewById(R.id.img1);
        textview = (TextView) findViewById(R.id.tv1);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }
    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME)
    }
    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }
    fun onSensorChanged(event: SensorEvent) {
        val degree = Math.round(event.values[0]).toFloat()
        tvHeading.setText("Heading: " + java.lang.Float.toString(degree) + " degrees")
        val ra = RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f)
        ra.duration = 210
        ra.fillAfter = true
        image.startAnimation(ra)
        currentDegree = -degree
    }
    fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }
}
