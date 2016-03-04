package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.subsystems.MXPBreakout;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorOutput extends Command {

	double first = 0;
	double corrected = 0;

	public SensorOutput() {
		requires(Robot.mxpBreakout);
	}

	@Override
	protected void initialize() {
		first = Robot.mxpBreakout.getYaw();
	}

	@Override
	protected void execute() {
		corrected = first - Robot.mxpBreakout.getYaw();
		// print all data from breakout board to SmartDash
//		SmartDashboard.putData("imu", Robot.mxpBreakout.getImu());
		SmartDashboard.putNumber("pitch", Robot.mxpBreakout.getPitch());
		SmartDashboard.putNumber("yaw", corrected);
		SmartDashboard.putNumber("roll", Robot.mxpBreakout.getRoll());
		SmartDashboard.putNumber("spiAngle", MXPBreakout.spigyro.getAngle());

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
