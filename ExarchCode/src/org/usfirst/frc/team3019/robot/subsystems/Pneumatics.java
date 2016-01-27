package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.commands.Compress;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	
	Compressor c = new Compressor(0);
	
	DoubleSolenoid soli1 = new DoubleSolenoid(1,2);
	
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	soli1.set(DoubleSolenoid.Value.kOff);
    	c.start();
    	c.setClosedLoopControl(true);
        // Set the default command for a subsystem here.
//        setDefaultCommand(new Compress());
    }
    public void soliForward(){
    	soli1.set(DoubleSolenoid.Value.kForward);
    }
    public void soliReverse(){
    	soli1.set(DoubleSolenoid.Value.kReverse);
    }
    public void soliOff(){
    	soli1.set(DoubleSolenoid.Value.kOff);
    }
}

