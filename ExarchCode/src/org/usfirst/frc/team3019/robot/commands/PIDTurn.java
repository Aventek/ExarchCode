package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTurn extends Command{

	public PIDTurn() {
		requires(Robot.PIDDrive);
		requires(Robot.mxpBreakout);
	}
	
	@Override
	protected void initialize() {
		
		//set active drivestate to PID driving
		Robot.driveState = DriveState.PID;

		//take initial angle values
		Robot.PIDDrive.initialYaw = Robot.mxpBreakout.getYaw();
		Robot.PIDDrive.initialAzimuth = SmartDashboard.getNumber("azimuthal", 0);
		
		//starts aiming process
		Robot.PIDDrive.enable();
		
	}

	@Override
	protected void execute() {
		
		//get the change in yaw since starting PIDDrive
		Robot.PIDDrive.deltaYaw = Robot.mxpBreakout.getYaw() - Robot.PIDDrive.initialYaw;
	
	}

	@Override
	protected boolean isFinished() {
		
		//stops correcting if within 0.3 degrees of target forward
		double error = Robot.PIDDrive.initialAzimuth + Robot.PIDDrive.deltaYaw;
		return Math.abs(error) < .3f;
	
	}

	@Override
	protected void end() {
		
		//when done aiming, stop motors, disable PID, and set drivestate back to Joystick
		Robot.driveTrain.arcadeDrive(0, 0);
		Robot.PIDDrive.disable();
		Robot.driveState = DriveState.JOYSTICK;
		
//		Robot.launcher.hasAligned = true;
	}

	@Override
	protected void interrupted() {
	}

}
