package com.example.pruebasensorluz_234963

import android.content.Context
import android.hardware.SensorManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

abstract class AndroidSensor (
    private val context: Context,
    private val sensorFeature: String,
    sensorType: Int
): MeasurableSensor(sensorType), SensorEventListener {
    override val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun startListening() {
        if(!doesSensorExist) {
            return
        }
        if(!::sensorManager.isInitialized && sensor==null){
            sensorManager = context.getSystemService(sensorManager::class.java) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorType)
        }
        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun stopListening() {
        if(!doesSensorExist || !::sensorManager.isInitialized) {
            return
        }
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(!doesSensorExist) {
            return
        }
        if(event?.sensor?.type == sensorType){
            onSensorValuesChanged?.invoke(event.values.toList())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

}