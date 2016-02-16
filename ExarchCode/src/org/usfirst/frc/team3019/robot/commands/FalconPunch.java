package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.ServoState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FalconPunch extends Command {

	boolean retracted = true;
	
	public FalconPunch() {
		requires(Robot.launcher);
	}

	protected void initialize() {
		
		//if retracted then extend and change state
		if(retracted){
			
			Robot.servoState = ServoState.EXTENDED;
			
		}else if(!retracted){
			
			Robot.servoState = ServoState.RETRACTED;
			
		}
		
		retracted = !retracted;

	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
