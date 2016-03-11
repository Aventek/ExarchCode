package org.usfirst.frc.team3019.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	// assign objects to correct ports
	Compressor c = new Compressor(0);
	DoubleSolenoid soli1 = new DoubleSolenoid(1, 0);
	DoubleSolenoid soli2 = new DoubleSolenoid(2,3);

	public void initDefaultCommand() {

		// when pneumatics starts set solenoid off and start compressing
		soli1.set(DoubleSolenoid.Value.kOff);
		soli2.set(DoubleSolenoid.Value.kOff);
		c.start();
		c.setClosedLoopControl(true);

	}

	// DO: turn solenoid to forward
	public void soliForward() {

		soli1.set(DoubleSolenoid.Value.kReverse);
		soli2.set(DoubleSolenoid.Value.kForward);
	}

	// DO: turn solenoid to reverse
	public void soliReverse() {

		soli1.set(DoubleSolenoid.Value.kForward);
		soli2.set(DoubleSolenoid.Value.kReverse);
	}

	// DO: turn solenoid off
	public void soliOff() {

		soli1.set(DoubleSolenoid.Value.kOff);
		soli2.set(DoubleSolenoid.Value.kOff);
	}

}
