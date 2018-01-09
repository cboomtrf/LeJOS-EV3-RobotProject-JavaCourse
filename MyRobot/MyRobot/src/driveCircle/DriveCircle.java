package driveCircle;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class DriveCircle {
	
	Brick brick;
	
	public DriveCircle() {
		super();
		brick = LocalEV3.get();
		this.run();
	}
	
//  public static void main(String[] args) {
//		new DriveCircle().run();
//}
    
    private void run() {
    	EV3TouchSensor sensor1 = new EV3TouchSensor(SensorPort.S1);
    	SampleProvider touchSP = sensor1.getTouchMode();

    	TextLCD display = brick.getTextLCD();
    	display.drawString("Drive Circle", 0, 3);
    	display.drawString("and Stop", 0, 4);
    	display.drawString("Press any key to", 0, 6);
    	display.drawString("start", 0, 7);

    	Button.LEDPattern(4);    // flash green led and 
    	Sound.beepSequenceUp();  // make sound when ready.

    	Button.waitForAnyPress();

    	// create two motor objects to control the motors.
    	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

    	// set motors to different power levels. Adjust to get a circle.
    	motorA.setPower(70);
    	motorB.setPower(30);

    	// wait doing nothing for touch sensor to stop driving.
    	while (!isTouched(touchSP)) {}

    	// stop motors with brakes on.
    	motorA.stop();
    	motorB.stop();

    	// free up resources.
    	motorA.close();
    	motorB.close();
    	sensor1.close();

    	Sound.beepSequence(); // we are done.
    }

    // method to read touch sensor and return true or false if touched.
    private static boolean isTouched(SampleProvider sp)
    {
    	float [] sample = new float[sp.sampleSize()];

    	sp.fetchSample(sample, 0);

    	if (sample[0] == 0)
    		return false;
    	else
    		return true;
    }
}