package actions;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.*;
import lejos.hardware.lcd.*;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.robotics.*;

//This line follower example can be improved upon by controlling the rate 
//of turn based on how far away the color sensor value is from .100. 
//So for a value of .80 we would turn right less than for a value of .70  or .60. 
//This determines the rate of turn proportionally to how far from the edge of the tape the robot is. 
//Further from the edge, more turn, closer to the edge less turn. 
//This is called proportional control and should smooth out the movements of the robot. 
//Modify the example code to to do this.
public class LineFollower {
	
    static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
    static EV3TouchSensor   touch  = new EV3TouchSensor(SensorPort.S1);
    static SampleProvider touchSP = touch.getTouchMode();
    static EV3ColorSensor   color  = new EV3ColorSensor(SensorPort.S3);
    static SampleProvider colorSP = color.getColorIDMode();
  
    Brick brick;
	
	public LineFollower() {
		super();
		brick = LocalEV3.get();
		this.makeItWork();
	}
		
//	public static void main(String[] args) {
//		new LineFollower().makeItWork();		
//	}
	
	private void makeItWork() {
		String colorValue;

    	TextLCD display = brick.getTextLCD();
		display.drawString("Line Follower", 0, 3);

		color.getRedMode();
		color.setFloodlight(Color.RED);
		color.setFloodlight(true);

		Button.LEDPattern(4);    // flash green led and 
		Sound.beepSequenceUp();  // make sound when ready.

		display.drawString("Press any key to", 0, 6);
		display.drawString("start", 0, 7);

		Button.waitForAnyPress();
		
		LCD.clear(6);
		LCD.clear(7);

		motorA.setPower(40);
		motorB.setPower(40);

		// drive waiting for touch sensor or escape key to stop driving.
		while (!isTouched(touchSP) && Button.ESCAPE.isUp()) 
		{
			colorValue = color.getRedMode().toString();

			display.drawString("value="+colorValue, 0, 7);

			if (Double.valueOf(colorValue) > .100)
			{
				motorA.setPower(40);
				motorB.setPower(20);
			}
			else
			{
				motorA.setPower(20);    
				motorB.setPower(40);
			}
		}

		// stop motors with brakes on.
		motorA.stop();
		motorB.stop();

		// free up resources.
		motorA.close();
		motorB.close();
		touch.close();
		color.close();

		Sound.beepSequence(); // we are done.
	}

	// method to read touch sensor and return true or false if touched.
	private static boolean isTouched(SampleProvider sp)
	{
		float[] sample = new float[sp.sampleSize()];

		sp.fetchSample(sample, 0);

		if (sample[0] == 0)
			return false;
		else
			return true;
	}

}