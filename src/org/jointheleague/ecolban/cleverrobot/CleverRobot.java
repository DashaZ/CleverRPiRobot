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
		driveDirect(100, 100);

		// read Sensors
		readSensors(100);
		int[] bumps = getLightBumps();
		for (int i = 0; i < 6; i++) {
			System.out.print(bumps[i] + " ");
		}

		// if statement checking if the Rumba is bumped right. If true,
		// the Rumba turns left
		if (isBumpRight() == true) {
			System.out.println("I bumped Right.");
			driveDirect(-100, 100);
			Thread.sleep(1500);

		}
		// readSensors
		readSensors(100);
		if (isBumpLeft() == true) {
			System.out.println("I bumped Left.");
			// if statement checking if the Rumba is bumped left. If true,
			// the Rumba turns right.
			driveDirect(100, -100);
			Thread.sleep(1500);
		}
		System.out.println("");

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
