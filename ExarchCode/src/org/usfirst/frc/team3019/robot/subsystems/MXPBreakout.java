package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.commands.SensorOutput;
import org.usfirst.frc.team3019.robot.utilities.ADIS16448_IMU;
import org.usfirst.frc.team3019.robot.utilities.SPIGYRO;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MXPBreakout extends Subsystem {

	ADIS16448_IMU imu;
	public static SPIGYRO spigyro;
	
	public MXPBreakout() {

		//assign breakout board then calibrate (runs when robot starts initially)
		imu = new ADIS16448_IMU();
		spigyro = new SPIGYRO();
		imu.calibrate();
		spigyro.calibrate();
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SensorOutput());
	}
	
//DO: returns roll of board
	public double getRoll(){
		return imu.getRoll();
	}
	
//DO: returns yaw of board	
	public double getYaw(){
		return imu.getYaw();
	}
	
//DO: returns pitch of board
	public double getPitch(){
		return imu.getPitch();
	}
	
//DO: returns pressure around board	
	public double getPressure(){
		return imu.getBarometricPressure();
	}
	
//DO: returns X angle (rotation) of board	
	public double getX(){
		return imu.getAngleX();
	}
	
//DO: returns rate of change of X angle (rotation) of board
	public double getXRate(){
		return imu.getRateX();
	}

//DO: returns Acceleration of X angle (rotation) of board
	public double getAccelX(){
		return imu.getAccelX();
	}

//DO: returns all data from breakout board	
	public ADIS16448_IMU getImu(){
		return imu;	
	}
	
}