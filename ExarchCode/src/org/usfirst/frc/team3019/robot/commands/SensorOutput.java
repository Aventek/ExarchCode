package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorOutput extends Command {

	public SensorOutput() {
		requires(Robot.mxpBreakout);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		
		//print all data from breakout board to SmartDash
		SmartDashboard.putData("imu", Robot.mxpBreakout.getImu());
		SmartDashboard.putNumber("pitch", Robot.mxpBreakout.getPitch());
		SmartDashboard.putNumber("yaw", Robot.mxpBreakout.getYaw());
		SmartDashboard.putNumber("roll", Robot.mxpBreakout.getRoll());
		SmartDashboard.putNumber("pressure", Robot.mxpBreakout.getPressure());
		SmartDashboard.putNumber("x", Robot.mxpBreakout.getX());
		SmartDashboard.putNumber("xRate", Robot.mxpBreakout.getXRate());
		SmartDashboard.putNumber("AccelX", Robot.mxpBreakout.getAccelX());
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
