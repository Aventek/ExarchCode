package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIDTurn extends Command{

	public PIDTurn() {
		requires(Robot.PIDdrive);
	}
	
	@Override
	protected void initialize() {
		
		//starts aiming process
		Robot.PIDdrive.enable();

	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		
		//stops correcting if within 0.3 degrees of target forward
		double error = Robot.PIDdrive.getPosition(); 
		return Math.abs(error) < .3f;
	
	}

	@Override
	protected void end() {
		
		//when done aiming, stop motors and disable PID
		Robot.driveTrain.arcadeDrive(0, 0);
		Robot.PIDdrive.disable();
		
	}

	@Override
	protected void interrupted() {
	}

}
