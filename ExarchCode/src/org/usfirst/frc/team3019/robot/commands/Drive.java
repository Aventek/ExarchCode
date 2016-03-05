package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.utilities.DriveState;
import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	
	
	double speed;
	double turn;
	boolean auto;
	public Drive(double speed, double turn) {
		this();
		this.speed = speed;
		this.turn = turn;
		auto = true;
		Robot.driveState = DriveState.AUTO;
	}
	public Drive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		// Simple arcade drive from lStick axes if not doing PID
		if (Robot.driveState != DriveState.PID) {
			double moveValue = Robot.oi.xbox.getY();
			double rotateValue = -Robot.oi.xbox.getX();
			Robot.driveTrain.arcadeDrive(moveValue * RobotMap.driveNerf,
					rotateValue * RobotMap.driveNerf);
		}
//		double moveValue = Robot.oi.xbox.getY();
//		double rotateValue = -Robot.oi.xbox.getX();
//		Robot.driveTrain.arcadeDrive(moveValue, rotateValue);
//		Robot.driveTrain.tankDrive(Robot.oi.xbox.getRawAxis(5), Robot.oi.xbox.getY() * 0.75);

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
