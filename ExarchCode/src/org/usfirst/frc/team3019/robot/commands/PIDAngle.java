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
	
	public PIDAngle() {
		requires(Robot.launcher);
		requires(Robot.PIDAngling);
	}
	public PIDAngle(String param){
		this();
		exit = true;
	}

	protected void initialize() {
		isRunning = true;
		Robot.anglerState = AnglerState.PID;

		// retrieve distance from target
		Robot.PIDAngling.distance = SmartDashboard.getNumber("distance", 0);

		// calculation for 10 m/s shoot speed
		if(Robot.table.getNumber("VISdistance", 0) > 50 && Robot.table.getNumber("VISdistance", 0) < 100){
			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 5;
		}else if(Robot.table.getNumber("VISdistance", 0) > 100 && Robot.table.getNumber("VISdistance", 0) < 120){
			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 4;
		}else if(Robot.table.getNumber("VISdistance", 0) > 120 && Robot.table.getNumber("VISdistance", 0) < 140){
			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 3;
		}else if(Robot.table.getNumber("VISdistance", 0) > 140 && Robot.table.getNumber("VISdistance", 0) < 160){
			Robot.PIDAngling.angle = Robot.launcher.targetAngle - 2;
		}
		//0.2075
//		* (Math.pow(Robot.PIDAngling.distance, 2) - 0.1817 * (Robot.PIDAngling.distance) + 44.395);

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
		if(!Robot.oi.xbox.getRawButton(8) && isRunning){
			exit = true;
		}
	}

	protected boolean isFinished() {

		double error = Robot.PIDAngling.angle - Robot.launcher.potAngle;
		SmartDashboard.putNumber("PIDAngleError", error);
		return (Math.abs(error) < .1f) || exit;

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
