package com.packt.B05688.chapter4;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
//import com.pi4j.system.SystemInfo;
import java.io.IOException;

/**
 *
 * @author Pradeeka
 */
public class TemperatureSensor {

    private I2CBus bus = null;
    private I2CDevice device = null;

    public TemperatureSensor(int sensorAddress) {
        try {
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
            if (bus != null) {
                device = bus.getDevice(sensorAddress);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double getTemperature() throws IOException {
        double result = getReadingFromDevice() * 0.0625;
        return result;
    }
    
    private int getReadingFromDevice() throws IOException {

        int result = 0;
        
        if (device != null) {
            byte[] tempBuffer = new byte[2];
            int bytesRead = device.read(tempBuffer, 0, 2);
            if (bytesRead == 2) {
                int MSB = tempBuffer[0] < 0 ? 256 + tempBuffer[0] : tempBuffer[0];
                int LSB = tempBuffer[1] < 0 ? 256 + tempBuffer[1] : tempBuffer[1];

                result = ((MSB << 8) | LSB) >> 4;
            }
        }

        return result;
    }

}
