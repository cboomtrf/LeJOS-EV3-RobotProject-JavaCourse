package driveForward;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveForward {
	
	Brick brick;
	
	public DriveForward() {
		super();
		brick = LocalEV3.get();
		this.run();
	}
	
//    public static void main(String[] args) {
//    	new DriveForward().run();
//    }
    
    private void run() {
    	TextLCD display = brick.getTextLCD();
		display.drawString("Drive Forward", 0, 3);
		display.drawString("and Stop", 0, 4);
		display.drawString("Press any key to", 0, 6);
		display.drawString("start", 0, 7);

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        waitForKey(Button.ENTER);

        // create two motor objects to control the motors.
        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

        // set motors to 50% power.
        motorA.setPower(50);
        motorB.setPower(50);

        // wait 2 seconds.
        Delay.msDelay(2000);

        // stop motors with brakes on. 
        motorA.stop();
        motorB.stop();

        // free up motor resources. 
        motorA.close(); 
        motorB.close();
 
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
