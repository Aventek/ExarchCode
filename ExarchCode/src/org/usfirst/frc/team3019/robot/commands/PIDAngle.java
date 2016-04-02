package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AnglerState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDAngle extends Command {

	public static boolean exit = false;
	public static boolean isRunning = false;
	double targetAngle = -180;
	boolean isAuto = false;
	public PIDAngle() {
		requires(Robot.launcher);
		requires(Robot.PIDAngling);
	}
	public PIDAngle(double target){
		this();
		this.targetAngle = target;
	}
	public PIDAngle(String param){
		this();
		exit = true;
	}

	protected void initialize() {
		isRunning = true;
		Robot.anglerState = AnglerState.PID;
		isAuto = true;
		setTimeout(3);
		// retrieve distance from target
		Robot.PIDAngling.distance = SmartDashboard.getNumber("distance", 0);

		//for custom angle and not auto angle
		if(targetAngle != -180){
			Robot.PIDAngling.angle = targetAngle;
		}else{
			Robot.PIDAngling.angle = Robot.launcher.targetAngle;
		}
			// calculation for 10 m/s shoot speed
//		} else if(Robot.table.getNumber("VISdistance", 0) > 50 && Robot.table.getNumber("VISdistance", 0) < 100){
//			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 7;
//		}else if(Robot.table.getNumber("VISdistance", 0) > 100 && Robot.table.getNumber("VISdistance", 0) < 120){
//			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 6;
////		}else if(Robot.table.getNumber("VISdistance", 0) > 120 && Robot.table.getNumber("VISdistance", 0) < 140){
//			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 5;
//		}else if(Robot.table.getNumber("VISdistance", 0) > 140 && Robot.table.getNumber("VISdistance", 0) < 160){
//			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 3;
//		}
		SmartDashboard.putNumber("targetAngle", Robot.PIDAngling.angle);
		Robot.PIDAngling.setSetpoint(0);
		if(Robot.PIDAngling.distance != 0){
			Robot.PIDAngling.enable();
		}else{
			exit = true;
		}
		

		double error = Robot.PIDAngling.angle - Robot.launcher.potAngle;
		SmartDashboard.putNumber("PIDAngleError", error);
		
	}

	protected void execute() {
		if(!isAuto){
			exit = true;
		}
	}

	protected boolean isFinished() {

		double error = Robot.PIDAngling.angle - Robot.launcher.potAngle;
		SmartDashboard.putNumber("PIDAngleError", error);
		return (Math.abs(error) < .5f) || exit || isTimedOut();

	}

	protected void end() {
		
		Robot.launcher.angleLauncher(0);
		Robot.anglerState = AnglerState.STILL;
		Robot.PIDAngling.disable();
		isRunning = false;
		exit = false;
	}

	protected void interrupted() {
	}
}
