package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Command{
	public Drive() {
		// TODO Auto-generated constructor stub
		requires(Robot.driveTrain);
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
//		Robot.driveTrain.State = 0;
	}
//kyler is dumb
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
//		if(Robot.driveTrain.State == 0){
		Robot.driveTrain.arcadeDrive(-Robot.oi.stick.getY() *0.5 , -Robot.oi.stick.getTwist()*0.5);
//		}
//		Robot.driveTrain.tankDrive(.5, .5);
//		Robot.driveTrain.tankDrive(Robot.oi.stick.getRawAxis(1),Robot.oi.stick.getRawAxis(6));
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
