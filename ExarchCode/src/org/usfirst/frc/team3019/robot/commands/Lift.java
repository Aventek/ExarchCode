package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.LiftState;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lift extends Command {

	double liftSpeed = 0;
	
	public Lift() {
		requires(Robot.lifter);
	}
	
	public Lift(String param){
		Robot.liftState = LiftState.LOWER;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.liftState == LiftState.TELEOP){
			double rightAxisValue = Robot.oi.xbox2.getRawAxis(5);
			Robot.lifter.canGoUp = true;
			Robot.lifter.canGoDown = true;
			if(rightAxisValue < -0.1){
				liftSpeed = rightAxisValue * 1;
			}else if(rightAxisValue > 0.1 /*&& Robot.lifter.armLimitSwitch.get()*/){
				liftSpeed = rightAxisValue * 1;
			}else{
				liftSpeed = 0;
			}
		}
		
		if(Robot.liftState == LiftState.AUTO || Robot.liftState == LiftState.LOWER){
			if(!Robot.lifter.armLimitSwitch.get() ){
				liftSpeed = 0.5;
			}else{
				liftSpeed = 0;
				Robot.liftState = LiftState.TELEOP;
			}
		}
		// use the right stick y axis to control the lift angler motors
		Robot.lifter.LiftControl(liftSpeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
