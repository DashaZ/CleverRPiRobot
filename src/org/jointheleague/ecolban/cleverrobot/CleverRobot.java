package org.jointheleague.ecolban.cleverrobot;

/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter {
	Sonar sonar = new Sonar();

	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		CleverRobot rob = new CleverRobot(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		driveDirect(100, 100);
	}

	private boolean loop() throws Exception {
		// go straight if not bumped anywhere
		driveDirect(1000, 1000);

		// read Sensors
		readSensors(100);
		if(isLightBump() == true){
			// if statement if light bump stop for 2 seconds
			driveDirect(0,0);
			Thread.sleep(2000);
			// turn left for 1.7 seconds
			driveDirect(250,1000);
			Thread.sleep(1700);
		}
		if (isBumpRight() == true) {
			System.out.println("I bumped Right.");
			// if statement checking if the Rumba is bumped right. If true,
			// the Rumba backs up for 1 second at max speed.
			driveDirect(-1000, -1000);
			System.out.println("I backed up fast.");
			Thread.sleep(1500);

			// Rumba turns left at a steep angle for 3.5 seconds. 1:10 ration.
			driveDirect(250, 1000);
			System.out.println("I turned.");
			Thread.sleep(1700);

		}
		// readSensors(100);
		if (isBumpLeft() == true) {
			System.out.println("I bumped Left.");
			// if statement checking if the Rumba is bumped left. If true,
			// the Rumba backs up for 1 second at max speed
			driveDirect(-1000, -1000);
			Thread.sleep(1500);

			// then the Rumba turns right for 2 seconds, max speed at 10:1
			// ratio.
			driveDirect(1000, 250);
			Thread.sleep(1700);
		}
		
		if (isBumpRight() == true && isBumpLeft() == false) {
			//if statement 
			System.out.println("I bumped right, but not left. ");

		}

		if (isBumpLeft() == true && isBumpRight() == false) {
			System.out.println("I bumped left, but not right. ");

		}
		if (isBumpLeft() == true && isBumpRight() == true) {
			System.out.println("I bumped right and left.");
			driveDirect(-500, -500);
			Thread.sleep(200);
			driveDirect(700, 1000);
			Thread.sleep(200);
		}
		if (isBumpRight() == false && isBumpLeft() == false) {
			driveDirect(1000, 1000);
		}

		return true;

	}

	private void driveDirect(int i) {
		// TODO Auto-generated method stub
		
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
