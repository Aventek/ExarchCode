package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTurn extends Command{

	public PIDTurn() {
		requires(Robot.PIDdrive);
		requires(Robot.mxpBreakout);
	}
	
	@Override
	protected void initialize() {
		
		//starts aiming process
		Robot.PIDdrive.enable();
		
		//set active drivestate to PID driving
		Robot.state = DriveState.PID;

	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		
		//stops correcting if within 0.3 degrees of target forward
		double error = SmartDashboard.getNumber("azimuth",0);
		return Math.abs(error) < .3f;
	
	}

	@Override
	protected void end() {
		
		//when done aiming, stop motors and disable PID
		Robot.driveTrain.arcadeDrive(0, 0);
		Robot.PIDdrive.disable();
		Robot.state = DriveState.JOYSTICK;
		
	}

	@Override
	protected void interrupted() {
	}

}
