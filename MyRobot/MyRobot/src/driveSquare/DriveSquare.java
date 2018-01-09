package driveSquare;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveSquare {
	
	Brick brick;
	
	public DriveSquare() {
		super();
		brick = LocalEV3.get();
		this.run();
	}
	
//    public static void main(String[] args) {
//    	new DriveSquare().run();
//    }
    
    private void run() {
    	TextLCD display = brick.getTextLCD();
		display.drawString("Drive in a Square", 0, 3);
		display.drawString("and Stop", 0, 4);
		display.drawString("Press any key to start", 0, 6);

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

        for (int i = 0; i < 4; i++) {
            // set motors to 50% power.
            motorA.setPower(50);
            motorB.setPower(50);

            // wait 2 seconds.
            Delay.msDelay(2000);

            // stop motors with brakes on. 
            motorA.stop();
            motorB.stop();

            // turn right by reversing the right motor.
            motorA.backward();
            motorB.forward();
 
            // make the turn.
            motorA.setPower(50);
            motorB.setPower(50);

            // adjust time to get a 90% turn.
            Delay.msDelay(1500);

            motorA.stop();
            motorB.stop();

            // set right motor back to forward motion.
            motorA.forward();
            motorB.forward();
        }

        // free up motor resources. 
        motorA.close(); 
        motorB.close();
 
        Sound.beepSequence(); // we are done.
    }
}