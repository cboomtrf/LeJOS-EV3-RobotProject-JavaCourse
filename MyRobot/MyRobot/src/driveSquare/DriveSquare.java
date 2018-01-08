package driveSquare;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveSquare
{
    public static void main(String[] args)
    {
        System.out.println("Drive in a Square\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.B);

        for (int i = 0; i < 4; i++) {
            // set motorspeed to 150 (values in degrees/sec).
            motorLeft.setSpeed(150);
            motorRight.setSpeed(150);

            // wait 2 seconds.
            Delay.msDelay(2000);

            // stop motors with brakes on. 
            motorLeft.stop();
            motorRight.stop();

            // turn right by reversing the right motor.
            motorLeft.backward();
            motorRight.forward();
 
            // make the turn.
            motorLeft.setSpeed(150);
            motorRight.setSpeed(150);

            // adjust time to get a 90% turn.
            Delay.msDelay(1500);

            motorLeft.stop();
            motorRight.stop();

            // set right motor back to forward motion.
            motorLeft.forward();
            motorRight.forward();
        }

        // free up motor resources. 
        motorLeft.close(); 
        motorRight.close();
 
        Sound.beepSequence(); // we are done.
    }
}