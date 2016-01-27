package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.commands.SensorOutput;
import org.usfirst.frc.team3019.robot.utilities.ADIS16448_IMU;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MXPBreakout extends Subsystem {

	ADIS16448_IMU imu;
	
	public MXPBreakout() {
		// TODO Auto-generated constructor stub
		imu = new ADIS16448_IMU();
		
		imu.calibrate();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new SensorOutput());
	}
	public double getRoll(){
		return imu.getRoll();
	}
	public double getYaw(){
		return imu.getYaw();
	}
	public double getPitch(){
		return imu.getPitch();
	}
	public double getPressure(){
		return imu.getBarometricPressure();
	}
	public double getX(){
		return imu.getAngleX();
	}
	public double getXRate(){
		return imu.getRateX();
	}
	public double getAccelX(){
		return imu.getAccelX();
	}
	public ADIS16448_IMU getImu(){
		return imu;
		
	}
	

}
