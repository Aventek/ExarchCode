package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AnglerState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDAngle extends Command {

	public PIDAngle() {
		requires(Robot.launcher);
		requires(Robot.PIDAngling);
	}

	protected void initialize() {

		Robot.anglerState = AnglerState.PID;

		// retrieve distance from target
		Robot.PIDAngling.distance = SmartDashboard.getNumber("distance", 0);

		// calculation for 10 m/s shoot speed
		Robot.PIDAngling.angle = 28;
//0.2075
//		* (Math.pow(Robot.PIDAngling.distance, 2) - 0.1817 * (Robot.PIDAngling.distance) + 44.395);

		SmartDashboard.putNumber("targetAngle", Robot.PIDAngling.angle);
		Robot.PIDAngling.setSetpoint(Robot.PIDAngling.angle);
		Robot.PIDAngling.enable();

	}

	protected void execute() {
	}

	protected boolean isFinished() {

		double error = Robot.PIDAngling.angle - Robot.launcher.potAngle;
		SmartDashboard.putNumber("PIDAngleError", error);
		return Math.abs(error) < .5f;

	}

	protected void end() {

		Robot.anglerState = AnglerState.STILL;

	}

	protected void interrupted() {
	}
}
