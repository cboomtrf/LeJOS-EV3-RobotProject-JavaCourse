package actions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

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
    	// create two motor objects to control the motors & touchsensor object.
    	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
    	EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
    	SampleProvider touchSP = touchSensor.getTouchMode();

    	TextLCD display = brick.getTextLCD();
    	display.drawString("Drive Circle", 0, 3);
    	display.drawString("and Stop", 0, 4);
    	display.drawString("Press any key to", 0, 6);
    	display.drawString("start", 0, 7);

    	Button.LEDPattern(4);    // flash green led and 
    	Sound.beepSequenceUp();  // make sound when ready.

		Button.waitForAnyPress();
    	
		LocalDateTime soon = LocalDateTime.now().plusSeconds(10);
				
    	// set motors to different power levels. Adjust to get a circle.
    	motorA.setPower(70);
    	motorB.setPower(30);
    	
    	while(true) {
    		
    		if(isTouched(touchSP)) {
    			System.out.println("break");
    			break;
    		}
    		
    		if( LocalDateTime.now().compareTo(soon) != -1) {
    			break;
    		}
    	}
    	
    	//only start to drive if the touchSensor is touched.
//    	if (isTouched(touchSP)) {
//    		  		
//	    	// set motors to different power levels. Adjust to get a circle.
//	    	motorA.setPower(70);
//	    	motorB.setPower(30);
//	    	
//	    	//while touch isn't touched, wait to stop driving.
//	    	//while (!isTouched(touchSP)) {};
//    	};
//    	
//    	//wait 10 seconds.
//    	Delay.msDelay(10000);
//    	
    	// stop motors with brakes on.
    	motorA.stop();
    	motorB.stop();

    	// free up resources.
    	motorA.close();
    	motorB.close();
    	touchSensor.close();

    	Sound.beepSequence(); // we are done.
    }

    // method to read touch sensor and return true or false if touched.
    private static boolean isTouched(SampleProvider sp) {
    	float[] sample = new float[sp.sampleSize()];

    	sp.fetchSample(sample, 0);

    	if ((int)sample[0] == 0)
    		return false;
    	else
    		return true;
    }
}