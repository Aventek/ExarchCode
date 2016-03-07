package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.ServoState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FalconPunch extends Command {

	boolean retracted = true;
	boolean doNothing = false;
	public FalconPunch() {
		requires(Robot.launcher);
		this.doNothing = false;
	}

	public FalconPunch(boolean doNothing) {
		// TODO Auto-generated constructor stub
		this();
		this.doNothing = doNothing;
	}

	protected void initialize() {
		
		//if retracted then extend and change state
		if(!doNothing){
			if(retracted){
				Robot.servoState = ServoState.EXTENDED;
			}else if(!retracted){
				Robot.servoState = ServoState.RETRACTED;
			}
			
			retracted = !retracted;
		} else {}
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
