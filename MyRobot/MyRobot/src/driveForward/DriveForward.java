package driveForward;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveForward
{
    public static void main(String[] args)
    {
        System.out.println("Drive Forward\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.B);

        // set motorspeed to 150 (values in degrees/sec).
        motorLeft.setSpeed(150);
        motorRight.setSpeed(150);

        // wait 2 seconds.
        Delay.msDelay(2000);

        // stop motors with brakes on. 
        motorLeft.stop();
        motorRight.stop();

        // free up motor resources. 
        motorLeft.close(); 
        motorRight.close();
 
        Sound.beepSequence(); // we are done.
    }
}
