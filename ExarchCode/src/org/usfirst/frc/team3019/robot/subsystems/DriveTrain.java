
package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.*;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    Talon leftMotor;
    Talon rightMotor;
//    Talon leftLaunchMotor;
//    Talon rightLaunchMotor;
    RobotDrive drive;
//    public static int State = 0;
//    RobotDrive launcher;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveTrain(){
		super();
		rightMotor = new Talon(RobotMap.rightMotorPWM);
		leftMotor = new Talon(RobotMap.leftMotorPWM);
		rightMotor.setInverted(true);
//		leftLaunchMotor = new Talon(2);
//		rightLaunchMotor = new Talon(3);
		
		drive = new RobotDrive(leftMotor, rightMotor);
//		launcher = new RobotDrive(leftLaunchMotor, rightLaunchMotor);
		
	}
	@Override
    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }
	public void drive(double magnitude, double curve){
		drive.drive(magnitude, curve);
	}
	
	
	public void arcadeDrive(double moveValue, double rotateValue){
		drive.arcadeDrive(moveValue , rotateValue );
	}
	public void tankDrive(double leftValue, double rightValue){
		drive.tankDrive(leftValue, rightValue);
		
	}
}

