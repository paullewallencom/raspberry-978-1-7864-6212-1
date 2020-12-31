package com.packt.B05688.chapter5;

/*Java Core*/
import java.util.*;
import java.io.*;
/*End Java Core*/

/*Pi4J*/
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
/*End Pi4J*/

/**
 *
 * @author Pradeeka
 */
public class ZumoRobot {

    /*Variables for motor conrol pins*/
    private static final int LEFT_FORWARD = 1; //GPIO pin 18 equivalent to WiringPi pin 1
    private static final int LEFT_BACKWARD = 4; //GPIO pin 23 equivalent to WiringPi pin 4
    private static final int RIGHT_FORWARD = 5; //GPIO pin 24 equivalent to WiringPi pin 5
    private static final int RIGHT_BACKWARD = 6; //GPIO pin 25 equivalent to WiringPi pin 6
    /*End Variables for motor conrol pins*/

    public static void main(String[] args) throws IOException {

        Gpio.wiringPiSetup();

        /*Initializing GPIO pins*/
        //left motor forward
        SoftPwm.softPwmCreate(LEFT_FORWARD, 0, 100); //GPIO 18 equivalent to WiringPi 1
        //left motor reverse
        SoftPwm.softPwmCreate(LEFT_BACKWARD, 0, 100); //GPIO 23 equivalent to WiringPi 4
        //right motor forward
        SoftPwm.softPwmCreate(RIGHT_FORWARD, 0, 100); //GPIO 24 equivalent to WiringPi 5
        //left motor reverse
        SoftPwm.softPwmCreate(RIGHT_BACKWARD, 0, 100); //GPIO 25 equivalent to WiringPi 6
        /*End Initializing GPIO pins*/

        Scanner keyboard = new Scanner(System.in);

        boolean quit = false;

        //Scanning input until a 'q' is pressed
        while (!quit) {
            System.out.print("Play Zumo (Press h for help): ");
            char move = keyboard.next().charAt(0);

            switch (move) {
                case 'w':
                    System.out.println("forwading...");
                    forward();// move forward
                    break;
                case 'z':
                    System.out.println("reversing...");
                    backward();// move backward 
                    break;
                case 'a':
                    System.out.println("point turning to left...");
                    pointTurnLeft(); // point turn left 
                    break;
                case 's':
                    System.out.println("point turning to right...");
                    pointTurnRight();  // point turn right
                    break;
                case 'x':
                    System.out.println("forward swing turning to left...");
                    forwardSwingTurnLeft(); // forward swing turn left 
                    break;
                case 'c':
                    System.out.println("backward swing turning to left...");
                    backwardSwingTurnLeft();  // backward swing turn left
                    break;
                case 'n':
                    System.out.println("forward swing turning to right...");
                    forwardSwingTurnRight(); // forward swing turn right 
                    break;
                case 'm':
                    System.out.println("backward turning to right...");
                    backwardSwingTurnRight();  // backward swing turn right
                    break;
                case 'h':
                    System.out.println("Starting help...");
                    help();  // help
                    break;
                case 'q':
                    quit = true; // quit playing
                    break;
                default:
                    System.out.println("That is not a valid command!");
            }
        }
    }

    /*Forward*/
    public static void forward() {
        SoftPwm.softPwmWrite(LEFT_FORWARD, 100);
        SoftPwm.softPwmWrite(RIGHT_FORWARD, 100);
    }

    /*End Forward*/

 /*Backward*/
    public static void backward() {
        SoftPwm.softPwmWrite(LEFT_BACKWARD, 100);
        SoftPwm.softPwmWrite(RIGHT_BACKWARD, 100);
    }

    /*End Reverse*/

 /*Point Turn*/
 /*Turn Left*/
    public static void pointTurnLeft() {
        SoftPwm.softPwmWrite(LEFT_BACKWARD, 100);
        SoftPwm.softPwmWrite(RIGHT_FORWARD, 100);
    }

    /*End Turn Left*/

 /*Turn Right*/
    public static void pointTurnRight() {
        SoftPwm.softPwmWrite(LEFT_FORWARD, 100);
        SoftPwm.softPwmWrite(RIGHT_BACKWARD, 100);
    }

    /*End Turn Right*/

 /*Swing Turn*/
 /*Forward Left Swing Turn*/
    public static void forwardSwingTurnLeft() {
        SoftPwm.softPwmWrite(LEFT_FORWARD, 0);
        SoftPwm.softPwmWrite(RIGHT_FORWARD, 100);
    }

    /*End Forward Left Swing Turn*/

 /*Backward Left Swing Turn*/
    public static void backwardSwingTurnLeft() {
        SoftPwm.softPwmWrite(LEFT_FORWARD, 0);
        SoftPwm.softPwmWrite(RIGHT_BACKWARD, 100);
    }

    /*End Backward Left Swing Turn*/

 /*Forward Right Swing Turn*/
    public static void forwardSwingTurnRight() {
        SoftPwm.softPwmWrite(LEFT_FORWARD, 100);
        SoftPwm.softPwmWrite(RIGHT_FORWARD, 0);
    }

    /*End Forward Right Swing Turn*/

 /*Backward Right Swing Turn*/
    public static void backwardSwingTurnRight() {
        SoftPwm.softPwmWrite(LEFT_BACKWARD, 100);
        SoftPwm.softPwmWrite(RIGHT_FORWARD, 0);
    }

    /*End Backward Right Swing Turn*/

 /*Stop*/
    public static void stop() {
        SoftPwm.softPwmWrite(LEFT_FORWARD, 0);
        SoftPwm.softPwmWrite(LEFT_BACKWARD, 0);
        SoftPwm.softPwmWrite(RIGHT_FORWARD, 0);
        SoftPwm.softPwmWrite(RIGHT_BACKWARD, 0);
    }

    /*End Stop*/

 /*Help*/
    public static void help() {
        System.out.println("w -> Move Forward.");
        System.out.println("z -> Move Backward.");
        System.out.println("a -> Point Turn Left.");
        System.out.println("s -> Point Turn Right.");
        System.out.println("x -> Forward Swing Turn Left.");
        System.out.println("c -> Backward Swing Turn Left.");
        System.out.println("n -> Forward Swing Turn Right.");
        System.out.println("m -> Backward Swing Turn Right.");
        System.out.println("h -> Help.");
    }
    /*End Help*/
}
