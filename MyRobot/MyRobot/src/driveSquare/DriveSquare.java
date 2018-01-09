package driveSquare;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class DriveSquare {
	
	Brick brick;
	
	public DriveSquare() {
		super();
		brick = LocalEV3.get();
	}
	
    public static void main(String[] args) {
    	DriveSquare driveSquare = new DriveSquare();
    	driveSquare.run();
    }
    
    private void run() {
    	TextLCD display = brick.getTextLCD();
		display.drawString("Drive in a Square", 0, 3);
		display.drawString("and Stop", 0, 4);
		display.drawString("Press any key to start", 0, 6);

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        RegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
        RegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.B);

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