package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FalconPunch extends Command {

	public FalconPunch() {
		requires(Robot.launcher);
	}

	protected void initialize() {

		// toggle state of servo
		Robot.launcher.falconPunch();

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
