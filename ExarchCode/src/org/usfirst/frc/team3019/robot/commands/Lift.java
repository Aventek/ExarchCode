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

	protected void initialize() {
	}

	protected void execute() {
		//get speed for lift from the right stick Y-axis
		liftspeed = Robot.oi.xbox.getRawAxis(5);
		
		// use the right stick y axis to control the lift angler motors
		Robot.lifter.LiftControl(liftspeed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
