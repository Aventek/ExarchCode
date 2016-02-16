package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AnglerState;
import org.usfirst.frc.team3019.robot.utilities.LauncherState;
import org.usfirst.frc.team3019.robot.utilities.ServoState;

import edu.wpi.first.wpilibj.command.Command;

public class Launch extends Command {

	String status;

	public Launch() {
		requires(Robot.launcher);
	}

	public Launch(String status) {
		this.status = status;
	}

	protected void initialize() {
	}

	protected void execute() {
		
		//normalize potentiometer angle from 1080 to 360 degrees
		Robot.launcher.potAngle = Robot.launcher.anglePot.get() / 3;

		// spinning launcher motors
		if (Robot.oi.xb5.get()) {

			// when lBump is held down run motors for launching
			Robot.launcher.launch(-1);
			Robot.launcherState = LauncherState.LAUNCH;

		} else if (Robot.oi.xb6.get()) {

			// when rBump is held down run motors for intake
			Robot.launcher.launch(0.4);
			Robot.launcherState = LauncherState.INTAKE;

		}  else {

			// when neither are held down dont run motors
			Robot.launcher.launch(0);
			Robot.launcherState = LauncherState.STILL;

		}
		
		if(Robot.anglerState != AnglerState.PID){
			// activate angler
			if (Robot.oi.xb2.get()) {

				// when B is held down angle launcher down
				Robot.launcher.angleLauncher(-.3);
				Robot.anglerState = AnglerState.ANGLING_DOWN;

			} else if (Robot.oi.xb3.get()) {

				// when X is held down, angle launcher up
				Robot.launcher.angleLauncher(.6);
				Robot.anglerState = AnglerState.ANGLING_UP;

			} else {
				// stop angling
				Robot.launcher.angleLauncher(0);
				Robot.anglerState = AnglerState.STILL;
			
			}
		}
		
		//control servo based on state
		if(Robot.servoState == ServoState.EXTENDED){
		
			Robot.launcher.servoControl(1);

		} else if(Robot.servoState == ServoState.RETRACTED){
		
			Robot.launcher.servoControl(.8);
		
		}

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
