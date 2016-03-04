package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AnglerState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDAngle extends Command {

	public boolean exit = false;
	
	public PIDAngle() {
		requires(Robot.launcher);
		requires(Robot.PIDAngling);
	}

	protected void initialize() {

		//tell the robot we are in PID state
		Robot.anglerState = AnglerState.PID;

		// retrieve distance from target
		Robot.PIDAngling.distance = SmartDashboard.getNumber("distance", 0);

		//snapshots the target angle for use in PID
		Robot.PIDAngling.angle = Robot.launcher.targetAngle;

//ELY'S CALCULATION		
//		0.2075 * (Math.pow(Robot.PIDAngling.distance, 2) - 0.1817 * (Robot.PIDAngling.distance) + 44.395);

		//put target angle in smartdash so we can see it
		SmartDashboard.putNumber("targetAngle", Robot.PIDAngling.angle);
		Robot.PIDAngling.setSetpoint(0);
		
		//only enable if we have a distance (target in sight)
		if(Robot.PIDAngling.distance != 0){
			Robot.PIDAngling.enable();
		}else{//if we do not have a target exit the pid code
			exit = true;
		}
		
		//difference between target angle and shooter angle must go to 0 (go to same angle)
		double error = Robot.PIDAngling.angle - Robot.launcher.potAngle;

		//put error in smartdash so we can see
		SmartDashboard.putNumber("PIDAngleError", error);
		
	}

	protected void execute() {
	}

	protected boolean isFinished() {

		//error is angle off of our target angle
		double error = Robot.PIDAngling.angle - Robot.launcher.potAngle;
		SmartDashboard.putNumber("PIDAngleError", error);
		
		//go until we are less than .1 degree off, or the robot is manually told to exit
		return (Math.abs(error) < .1f) || (exit);

	}

	protected void end() {
		
		//when ending stop launcher movement and set state back to still, then disable pid
		Robot.PIDAngling.disable();
		Robot.launcher.angleLauncher(0);
		Robot.anglerState = AnglerState.STILL;
		exit = false;
	}

	protected void interrupted() {
	}
}
