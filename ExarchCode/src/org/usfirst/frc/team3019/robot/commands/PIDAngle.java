package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDAngle extends Command {

	public PIDAngle() {
		requires(Robot.launcher);
	}

	protected void initialize() {

		// retrieve distance from target
		Robot.PIDAngle.distance = SmartDashboard.getNumber("distance", 0);

		// calculation for 10 m/s shoot speed
		Robot.PIDAngle.angle = 0.2075
				* (Math.pow(Robot.PIDAngle.distance, 2) - 0.1817 * (Robot.PIDAngle.distance) + 44.395);

		Robot.PIDAngle.setSetpoint(Robot.PIDAngle.angle);
		Robot.PIDAngle.enable();

	}

	protected void execute() {
	}

	protected boolean isFinished() {

		double error = Robot.PIDAngle.angle - Robot.launcher.anglePot.get();
		return Math.abs(error) < 0.3f;

	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
