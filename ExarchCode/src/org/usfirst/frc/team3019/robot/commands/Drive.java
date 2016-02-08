package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.utilities.DriveState;
import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	public Drive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {

		// Simple arcade drive from lStick axes if not doing PID
		if (Robot.driveState == DriveState.JOYSTICK) {

			Robot.driveTrain.arcadeDrive(-Robot.oi.xbox.getY() * RobotMap.driveNerf,
					-Robot.oi.xbox.getX() * RobotMap.driveNerf);

		}

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
