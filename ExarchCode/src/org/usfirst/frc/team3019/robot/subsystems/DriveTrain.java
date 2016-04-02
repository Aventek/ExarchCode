
package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Drive;
import org.usfirst.frc.team3019.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	// instantiate motors and drives used
	VictorSP rearLeftMotor;
	VictorSP rearRightMotor;
	VictorSP frontLeftMotor;
	VictorSP frontRightMotor;
	
	RobotDrive drive;

	public DriveTrain() {

		super();

		// setting left and right motors to correct ports
		rearRightMotor = new VictorSP(RobotMap.rightRearDrivePWM);
		frontRightMotor = new VictorSP(RobotMap.rightFrontDrivePWM);
		rearLeftMotor = new VictorSP(RobotMap.leftRearDrivePWM);
		frontLeftMotor = new VictorSP(RobotMap.leftFrontDrivePWM);
		drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	// DO: Drive function using arcade drive (1 stick with twist to turn)
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
	
	public void DefaultArcadeDrive(double moveValue, double rotateValue){
		
		drive.arcadeDrive(moveValue, rotateValue);
		
	}
	public void ArcadeDrive(double moveValue, double rotateValue){
		boolean useBoost = Robot.oi.buttonSpeedRacer.get();
		double leftMotorSpeed;
	    double rightMotorSpeed;
	    double leftFrontSpeed = 0;
	    double rightFrontSpeed = 0;
	    if (moveValue >= 0.0) {
	        moveValue = (moveValue * moveValue);
	      } else {
	        moveValue = -(moveValue * moveValue);
	      }
	      if (rotateValue >= 0.0) {
	        rotateValue = (rotateValue * rotateValue);
	      } else {
	        rotateValue = -(rotateValue * rotateValue);
	      }
	    
	    if (moveValue > 0.0) {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = Math.max(moveValue, rotateValue);
	      } else {
	        leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      }
	    } else {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      } else {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	      }
	    }

	    //if we want to turn
	    if(Math.abs(rotateValue) > Math.abs(moveValue)) {
	    	frontLeftMotor.set(leftMotorSpeed);
	    	frontRightMotor.set(-rightMotorSpeed * RobotMap.driveStraightCorrection);
	    	rearLeftMotor.set(leftMotorSpeed);
	    	rearRightMotor.set(-rightMotorSpeed * RobotMap.driveStraightCorrection);
	    } else if(Math.abs(moveValue) > Math.abs(rotateValue)) {
	    	leftFrontSpeed = leftMotorSpeed;
	    	rightFrontSpeed = -rightMotorSpeed * RobotMap.driveStraightCorrection;
	    	
	    	frontLeftMotor.set(leftFrontSpeed);
	    	frontRightMotor.set(rightFrontSpeed);
	    	if(useBoost){
	    		rearLeftMotor.set(leftFrontSpeed);
	    		rearRightMotor.set(rightFrontSpeed);
	    	}
//	    	rearLeftMotor.set(0);
//	    	rearRightMotor.set(0);
	    } else{ //if we want to go straight and turn
	    	frontLeftMotor.set(leftMotorSpeed);
	    	frontRightMotor.set(-rightMotorSpeed * RobotMap.driveStraightCorrection);
	    	if(useBoost){
	    		rearLeftMotor.set(leftMotorSpeed);
	    		rearRightMotor.set(-rightMotorSpeed * RobotMap.driveStraightCorrection);
	    	}
//	    	rearLeftMotor.set(0);
//	    	rearRightMotor.set(0);
	    }
	}
}