package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorOutput extends Command {

	public SensorOutput() {
	// TODO Auto-generated constructor stub
		requires(Robot.mxpBreakout);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
