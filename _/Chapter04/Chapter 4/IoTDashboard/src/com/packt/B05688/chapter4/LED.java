package com.packt.B05688.chapter4;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author Pradeeka
 */
public class LED {
    
    final GpioController gpio;
    final GpioPinDigitalOutput pin;
    
    public LED()
    {
        gpio = GpioFactory.getInstance();
        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
        pin.setShutdownOptions(true, PinState.LOW);
    }
    
    public void on()
    {
        pin.low();
    }
    
     public void off()
    {
        pin.low();
    }
}
