package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTurn extends Command{

	public PIDTurn() {
		// TODO Auto-generated constructor stub
		requires(Robot.PIDdrive);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.PIDdrive.enable();
//		Robot.driveTrain.State = 1;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("onTarget", Robot.PIDdrive.onTarget());
		SmartDashboard.putNumber("Error", Robot.PIDdrive.getPosition());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		double error = Robot.PIDdrive.getPosition();
		 
		return Math.abs(error) < .3f;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.arcadeDrive(0, 0);
//		Robot.driveTrain.State = 0;
		Robot.PIDdrive.disable();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
