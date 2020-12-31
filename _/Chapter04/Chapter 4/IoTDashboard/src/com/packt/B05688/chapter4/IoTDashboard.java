package com.packt.B05688.chapter4;

/* Import Packages*/
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import java.util.*;
import com.pi4j.system.SystemInfo;
/* End Import Packages*/

/*
 *
 * @author Pradeeka
 */
public class IoTDashboard {
    
    private static final int DEVICE_ADDRESS = 0x48;

    public static void main(String[] args) {
        
        TemperatureSensor tmp102 = new TemperatureSensor(DEVICE_ADDRESS);
        LED led = new LED();
        
        try {

            MqttClient client = new MqttClient(
                    "tcp://io.adafruit.com:1883", 
                    MqttClient.generateClientId(),
                    new MemoryPersistence()); 
            
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("pradeeka");
            options.setPassword("c62d5f647eb44a4098ff0c46403bd639".toCharArray());
            
            /*code block for subscribe*/
            client.setCallback(new MqttCallback() {
 
            @Override
            public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker 
            }
 
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println(topic + ": " + message);
                if(message.toString() == "OFF"){
                    led.off();
                }
                if(message.toString() == "ON"){
                    led.on();
                }
            }
 
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete 
            }
        });
            /*end block */
            
            client.connect(options);

            while (true) {
                try {
                    Thread.sleep(60*1000);
                    client.publish("pradeeka/feeds/Temperature", Integer.toString((int)(Math.random() * 101)).getBytes("UTF8"), 0, true);
                    /* feed for TMP102 temperature sensor */
                    //client.publish("pradeeka/feeds/Temperature", Double.toString((double)(tmp102.getTemperature())).getBytes("UTF8"), 0, true);
                //client.publish("pradeeka/feeds/cpu-temperature", Float.toString((float)(SystemInfo.getCpuTemperature())).getBytes("UTF8"), 0, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//client.disconnect();
            }
            
            /*subscribe*/
            //client.subscribe("pradeeka/feeds/button", 0);
            //System.out.println();
            /*end subscribe*/
        } catch (MqttException me) {
            me.printStackTrace();
        }

    }

}