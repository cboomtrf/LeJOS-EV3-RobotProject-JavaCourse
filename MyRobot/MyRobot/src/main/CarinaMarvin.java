package main;

import driveCircle.DriveCircle;
import driveForward.DriveForward;
import driveSquare.DriveSquare;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import lineFollower.LineFollower;

public class CarinaMarvin {

	Brick brick;
	
	public CarinaMarvin() {
		super();
		brick = LocalEV3.get();
	}
	
	//File = > run as = > leJOS ev3 program. 
	//Dan vertaalt eclipse programma naar robot die deze meteen als jar file uitvoert.
	public static void main(String[] args) {
		CarinaMarvin marvin = new CarinaMarvin();
		marvin.run();
		marvin.run2();
		//new Marvin().run(); < hoef je geen naam voor te bedenken.
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welcome!", 0, 3);
		display.drawString("Team One", 0, 4);
		waitForKey(Button.ENTER);
	}
	
	private void run2() {
		Sound.beep();
		new DriveCircle();
		new DriveSquare();
		new DriveForward();
		new LineFollower();
		Sound.beep();
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
