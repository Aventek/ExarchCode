package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lifter extends Command{
	
//	boolean runArms;
	
	public Lifter() {
		// TODO Auto-generated constructor stub
		requires(Robot.liftMotors);
	}
//	public Lifter(double num){
////		requires(Robot.liftMotors);
//		runArms = true;
//	}
//	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
//kyler is dumb
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		Robot.liftMotors.setSpeed(Robot.oi.xbox.getY()* 0.6);
		SmartDashboard.putNumber("rightYAXISNUMBERBLABLABLA", Robot.oi.xbox.getRawAxis(5));
		double armSpeed = 0;
		if(Robot.oi.xbox.getRawAxis(5) > 0.3){
			armSpeed = 0.5;
		}else if(Robot.oi.xbox.getRawAxis(5) < -0.3){
			armSpeed = -0.5;
		}else{
			armSpeed = 0;
		}
		SmartDashboard.putNumber("armSpeed", armSpeed);
		Robot.liftMotors.armsSpeed(armSpeed);
//		Robot.driveTrain.tankDrive(-Robot.oi.stick.getY(), Robot.oi.stick.getY());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}
}
