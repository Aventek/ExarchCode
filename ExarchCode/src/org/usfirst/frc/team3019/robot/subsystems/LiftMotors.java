
package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.*;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftMotors extends Subsystem {
    
    Talon liftMotor;
    Talon armMotor;
//    RobotDrive drive;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public LiftMotors(){
		super();
//		rightMotor = new Talon(1);
//		leftMotor = new Talon(0);
		liftMotor = new Talon(RobotMap.liftMotorsPWM);
		armMotor = new Talon(RobotMap.armMotorsPWM);
		
		
	}
	@Override
    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Lifter());
    }
	public void setSpeed(double speed){
		liftMotor.set(speed);
	}
	public void armsSpeed(double speed){
		armMotor.set(speed);
//		Timer.delay(1);
//		wait(1000);
//		armMotor.stopMotor();
	}
//	public void arcadeDrive(double moveValue, double rotateValue){
//		drive.arcadeDrive(moveValue , rotateValue );
//	}
}

