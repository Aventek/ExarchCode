package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTurn extends Command {

	public PIDTurn() {
		requires(Robot.PIDDriving);
		requires(Robot.mxpBreakout);
	}

	@Override
	protected void initialize() {

		// set active drivestate to PID driving
		Robot.driveState = DriveState.PID;

		// take initial angle values
		Robot.PIDDriving.initialYaw = Robot.mxpBreakout.spigyro.getAngle();
		Robot.PIDDriving.initialAzimuth = SmartDashboard.getNumber("azimuth", 0);

		// starts aiming process
		Robot.PIDDriving.enable();

	}

	@Override
	protected void execute() {

		// get the change in yaw since starting PIDDrive
		Robot.PIDDriving.deltaYaw = Robot.mxpBreakout.spigyro.getAngle() - Robot.PIDDriving.initialYaw;
		
		double error = Robot.PIDDriving.initialAzimuth + Robot.PIDDriving.deltaYaw;
		SmartDashboard.putNumber("PIDTurn error", error);

	}

	@Override
	protected boolean isFinished() {

		// stops correcting if within 0.3 degrees of target forward
		double error = Robot.PIDDriving.initialAzimuth + Robot.PIDDriving.deltaYaw;
		return Math.abs(error) < 0.25f;

	}

	@Override
	protected void end() {

		// when done aiming, stop motors, disable PID, and set drivestate back
		// to Joystick
		Robot.PIDDriving.disable();
		Robot.driveTrain.ArcadeDrive(0, 0);
		Robot.driveState = DriveState.STILL;

		// Robot.launcher.hasAligned = true;
	}

	@Override
	protected void interrupted() {
	}

}
