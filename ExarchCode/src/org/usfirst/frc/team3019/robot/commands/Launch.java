package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AnglerState;
import org.usfirst.frc.team3019.robot.utilities.LauncherState;
import org.usfirst.frc.team3019.robot.utilities.ServoState;

import edu.wpi.first.wpilibj.command.Command;

public class Launch extends Command {

	String status;
	
	public static final double extendedPosition = 1;
	public static final double retractedPosition = 0.75;
	final double motorDownSpeed = -0.3;
	final double motorUpSpeed = 0.6;
	final double shootSpeed = .85;
	final double loadSpeed = -0.4;
	final double barSpeed = -.75;
	
	
	boolean auto = false;
	public Launch(boolean auto){
		requires(Robot.launcher);
		this.auto = auto;
	}
	public Launch() {
		this(false);
	}
	

	public Launch(String status) {
		this.status = status;
	}

	protected void initialize() {
	}

	protected void execute() {
		
		// spinning launcher motors
		if (Robot.oi.buttonLaunch.get()) {
			// when lBump is held down run motors for launching
			Robot.launcher.launch(shootSpeed);
			Robot.launcherState = LauncherState.LAUNCH;
		} else if (Robot.oi.buttonIntake.get()) {
			// when rBump is held down run motors for intake
			Robot.launcherState = LauncherState.INTAKE;
			Robot.launcher.launch(loadSpeed);
			Robot.launcher.intakeControl(barSpeed);
			Robot.launcherState = LauncherState.INTAKE;
		} else if (auto){
			Robot.launcher.launch(shootSpeed);
			Robot.launcher.intakeControl(0);
			Robot.launcherState = LauncherState.AUTO;
			
		} else {
			// when neither are held down dont run motors
			Robot.launcher.intakeControl(0);
			Robot.launcher.launch(0);
			Robot.launcherState = LauncherState.STILL;
		}
		
		if(Robot.anglerState != AnglerState.PID){
			// activate angler
			if (Robot.oi.buttonShooterDown.get()) {
				// when B is held down angle launcher down
				Robot.launcher.angleLauncher(motorDownSpeed);
				Robot.anglerState = AnglerState.ANGLING_DOWN;
			} else if (Robot.oi.buttonShooterUp.get()) {
				// when X is held down, angle launcher up
				Robot.launcher.angleLauncher(motorUpSpeed);
				Robot.anglerState = AnglerState.ANGLING_UP;
			} else {
				// stop angling
				Robot.launcher.angleLauncher(0);
				Robot.anglerState = AnglerState.STILL;
			}
		}
		
		//control servo based on state
		if(Robot.servoState == ServoState.RETRACTED){
//			Robot.launcher.servoControl(extendedPosition);
		} else if(Robot.servoState == ServoState.EXTENDED){
//			Robot.launcher.servoControl(retractedPosition);
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
