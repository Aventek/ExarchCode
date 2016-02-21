
package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Drive;
import org.usfirst.frc.team3019.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	// instantiate motors and drives used
	Talon leftMotor;
	Talon rightMotor;
	RobotDrive drive;

	public DriveTrain() {

		super();

		// setting left and right motors to correct ports
		rightMotor = new Talon(RobotMap.rightDrivePWM);
		leftMotor = new Talon(RobotMap.leftDrivePWM);

		leftMotor.setInverted(true);
		
		// standard drive system
		drive = new RobotDrive(leftMotor, rightMotor);

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	// DO: Drive function using arcade drive (1 stick with twist to turn)
	public void arcadeDrive(double moveValue, double rotateValue) {

		// Set the proper drive state based on values passed in
		if (moveValue > 0.3 && rotateValue > -0.3 && rotateValue < 0.3) {
			Robot.driveState = DriveState.FORWARD;
		} else if (rotateValue > 0.3) {
			Robot.driveState = DriveState.TURNING_RIGHT;
		} else if (rotateValue < -0.3) {
			Robot.driveState = DriveState.TURNING_LEFT;
		} else if (moveValue < -0.3 && rotateValue > -0.3 && rotateValue < 0.3) {
			Robot.driveState = DriveState.REVERSE;
		} else {
			Robot.driveState = DriveState.STILL;
		}

		drive.arcadeDrive(moveValue, rotateValue);

	}

	// Drive function using tank drive (2 sticks, 1 left one right, each
	// controlling one side of drivetrain)
	public void tankDrive(double leftValue, double rightValue) {

		// set the proper drive state based on values passed in
		if (leftValue > 0.3 && rightValue > 0.3) {
			Robot.driveState = DriveState.FORWARD;
		} else if (leftValue > 0.3 && rightValue < -0.3) {
			Robot.driveState = DriveState.TURNING_RIGHT;
		} else if (leftValue < -0.3 && rightValue > 0.3) {
			Robot.driveState = DriveState.TURNING_LEFT;
		} else if (leftValue < -0.3 && rightValue < -0.3) {
			Robot.driveState = DriveState.REVERSE;
		} else {
			Robot.driveState = DriveState.STILL;
		}

		drive.tankDrive(leftValue, rightValue);

	}
}
