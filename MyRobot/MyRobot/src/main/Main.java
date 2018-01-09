package main;

import actions.DriveCircle;
import actions.DriveForward;
import actions.DriveSquare;
import actions.LineFollower;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;

public class Main {

	Brick brick;
	
	public Main() {
		super();
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		Main marvin = new Main();
		marvin.run();
		marvin.run2();
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welcome!", 0, 3);
		display.drawString("Team One", 0, 4);
		waitForKey(Button.ENTER);
	}
	
	private void run2() {
		Sound.beep();
//		DriveForward forward = new DriveForward();
//		DriveSquare square = new DriveSquare();
		DriveCircle circle = new DriveCircle();
//		LineFollower lineFollow = new LineFollower();
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
