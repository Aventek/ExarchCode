package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.utilities.AutonomousMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public  AutonomousCommandGroup(AutonomousMode mode) {
    	
    	//lower the arms to lower cog
    	if(mode == AutonomousMode.LOW_BAR){
    		//drive forward
    		addSequential(new Drive(0,0), 0.5);
    		addSequential(new Drive(-.8,0),3);
    		addSequential(new Drive(0,-.7),.25);
    		Shoot();
    	} else if (mode == AutonomousMode.MOAT){
    		//drive forward
    		addSequential(new Drive(-.6,0),2);
    		Shoot();
    	} else if (mode == AutonomousMode.RAMPART){
    		//drive forward
    		addSequential(new Drive(-.6,0),2);
    		Shoot();
    	} else if (mode == AutonomousMode.ROCKWALL){
    		addSequential(new Drive(-.6,0),2);
    		Shoot();
    	} else if (mode == AutonomousMode.SPY){
    		Shoot();
    	} else if(mode == AutonomousMode.ROUGH_TERRAAIN){
    		addSequential(new Drive(-1,0),4);
    	} else {
    		//drive forward
    		addSequential(new Drive(0,0), 0.5);
    		addSequential(new Drive(-.8,0),3);
    		addSequential(new Drive(0,-.7),.25);
    		Shoot();
    	}
    	
     }
    public void Shoot(){
    	//line up the shot
    	addSequential(new Drive(0,0), .2);		
		addSequential(new PIDTurn());
    	addSequential(new Drive(0,0), .2);
		addSequential(new PIDTurn());
    	addSequential(new Drive(0,0), .2);
    	addSequential(new PIDTurn());
		//set target angle
		addSequential(new PIDAngle(),3);
		//spin up ball
		addSequential(new Launch(true),5);
		//FALCON PUUUUNCH
		addSequential(new Jerk());
		//do nothing for a second
		//reset shooter
		//level the shooter
//		addSequential(new PIDAngle(0));
    }
}
