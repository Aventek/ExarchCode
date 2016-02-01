package org.usfirst.frc.team3019.robot.utilities;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SPI;

public class SPIGYRO extends GyroBase{
	
	ADXRS450_Gyro gyro;
	
	public SPIGYRO() {
		gyro = new ADXRS450_Gyro();
		
	}

	@Override
	public void calibrate() {
		gyro.calibrate();
	}

	@Override
	public void reset() {
		gyro.reset();
	}

	@Override
	public double getAngle() {
		return gyro.getAngle();
	}

	@Override
	public double getRate() {
		return gyro.getRate();
	}
	
	
}
