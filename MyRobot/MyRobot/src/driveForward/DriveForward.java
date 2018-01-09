package driveForward;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class DriveForward {
	
	Brick brick;
	
	public DriveForward() {
		super();
		brick = LocalEV3.get();
	}
	
    public static void main(String[] args) {
    	DriveForward driveForward = new DriveForward();
    	driveForward.run();
    }
    
    private void run() {
    	TextLCD display = brick.getTextLCD();
		display.drawString("Drive Forward", 0, 3);
		display.drawString("and Stop", 0, 4);
		display.drawString("Press any key to start", 0, 6);

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        waitForKey(Button.ENTER);

        // create two motor objects to control the motors.
        RegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
        RegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.B);

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
    
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
	
}
