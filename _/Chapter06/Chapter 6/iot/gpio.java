/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
 
 
public class gpio extends HttpServlet {
 private static final long serialVersionUID = 1L;
 GpioController gpio;
 GpioPinDigitalOutput gpio7;
 

 public gpio() {
 
  GpioController gpio = GpioFactory.getInstance();
  
  gpio7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "GPIO 07",
    PinState.LOW);
 
 }
 
 
 public void doPost(HttpServletRequest request,
   HttpServletResponse response) throws IOException, ServletException {
  // get the number of the LED to turn on and print to console
  String action = request.getParameter("action");
  System.out.println("action=" + request.getParameter("action"));
 
  try {
   if (action.equals("1")) {
    System.out.println("led is on");
    gpio7.high();
   
 
   } else if (action.equals("0")) {
    System.out.println("led is off");
    gpio7.low();
   }
 
  } catch (Exception e) {
 
   System.out.print("Exception ");
  }
 
 
  PrintWriter out = response.getWriter();
 
  String pagehtml = "";
 
  // if there was a valid LED number display a message
 
  if ("10".contains(action)) {
   pagehtml = pagehtml + "LED number " + action + " selected";
  }
  
   String pagehtmlform = "<form action=\"./IoTController\" method=\"POST\"><input name=\"action\" type=\"text\"><input type=\"submit\" value=\"Send\"></form>";
 
   out.println(pagehtml + pagehtmlform);
 
 }
 
}
