package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lift extends Command {

	public double liftspeed = 0;
	
	public Lift() {
		requires(Robot.lifter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		if(Robot.oi.xbox.getRawAxis(3) >= 0.3){
			liftspeed = Robot.oi.xbox.getRawAxis(3) * 0.9;
		}else if(Robot.oi.xbox.getRawAxis(3) <= -0.3){
			liftspeed = Robot.oi.xbox.getRawAxis(3) * 0.9;
		}else{
			liftspeed = 0;
		}
		*/
		
		liftspeed = Robot.oi.xbox.getRawAxis(5);
		
		// use the right stick y axis to control the lift angler motors
		Robot.lifter.LiftControl(liftspeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
