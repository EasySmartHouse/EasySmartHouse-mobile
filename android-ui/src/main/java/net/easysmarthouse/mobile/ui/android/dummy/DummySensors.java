package net.easysmarthouse.mobile.ui.android.dummy;

import net.easysmarthouse.provider.device.sensor.PlainSensor;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rusakovich on 27.01.2017.
 */
public class DummySensors {

    private DummySensors(){
    }

    public static final List<Sensor> SENSORS = new ArrayList<Sensor>();

    static {
        PlainSensor sensor1 = new PlainSensor();
        sensor1.setAddress("Sensor1 address");
        sensor1.setDescription("Sensor1 description");
        sensor1.setLabel("Sensor1 label");
        sensor1.setSensorType(SensorType.TemperatureSensor);
        sensor1.setValue(123.34);
        SENSORS.add(sensor1);

        PlainSensor sensor2 = new PlainSensor();
        sensor2.setAddress("Sensor2 address");
        sensor2.setDescription("Sensor2 description");
        sensor2.setLabel("Sensor2 label");
        sensor2.setSensorType(SensorType.HumiditySensor);
        sensor2.setValue(999.34);
        SENSORS.add(sensor2);

        PlainSensor sensor3 = new PlainSensor();
        sensor3.setAddress("Sensor3 address");
        sensor3.setDescription("Sensor3 description");
        sensor3.setLabel("Sensor3 label");
        sensor3.setSensorType(SensorType.PressureSensor);
        sensor3.setValue(1.4564345);
        SENSORS.add(sensor3);
    }

}
