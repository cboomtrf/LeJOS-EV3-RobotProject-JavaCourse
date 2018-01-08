package projects;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Color;
import lejos.hardware.sensor.EV3ColorSensor;

//This line follower example can be improved upon by controlling the rate 
//of turn based on how far away the color sensor value is from .100. 
//So for a value of .80 we would turn right less than for a value of .70  or .60. 
//This determines the rate of turn proportionally to how far from the edge of the tape the robot is. 
//Further from the edge, more turn, closer to the edge less turn. 
//This is called proportional control and should smooth out the movements of the robot. 
//Modify the example code to to do this.
public class LineFollower 
{ 
    static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.B);
    static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.C);
    static EV3TouchSensor   touch = new EV3TouchSensor(SensorPort.S1);
    static EV3ColorSensor   color = new EV3ColorSensor(SensorPort.S3);
  
    Brick brick;
	
	public LineFollower() {
		super();
		brick = LocalEV3.get();
	}
		
	public static void main(String[] args) {
		new LineFollower().makeItWork();		
	}
	
	private void makeItWork() {
		float colorValue;

		System.out.println("Line Follower\n");

		color.getRedMode();
		color.setFloodlight(Color.RED);
		color.setFloodlight(true);

		Button.LEDPattern(4);    // flash green led and 
		Sound.beepSequenceUp();  // make sound when ready.

		System.out.println("Press any key to start");

		Button.waitForAnyPress();

		motorA.setPower(40);
		motorB.setPower(40);

		// drive waiting for touch sensor or escape key to stop driving.

		while ((touch.getTouchMode() == 0) && Button.ESCAPE.isUp()) 
		{
			colorValue = color.getRedMode();

			Lcd.clear(7);
			Lcd.print(7,  "value=%.3f", colorValue);

			if (colorValue > .100)
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

}
   

 
