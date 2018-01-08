package projects;

import lejos.hardware.Brick;
import lejos.hardware.ev3.LocalEV3;

public class Robot {
	
	Brick brick;
	
	public Robot() {
		super();
		brick = LocalEV3.get();
	}
		
	public static void main(String[] args) {
		new Robot().makeItWork();		
	}
	
	private void makeItWork() {
		
	}

}
