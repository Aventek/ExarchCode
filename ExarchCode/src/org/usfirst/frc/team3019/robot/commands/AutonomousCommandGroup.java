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
    		addSequential(new Drive(.6,0),3);
    		Shoot();
    	} else if (mode == AutonomousMode.MOAT){
    		//drive forward
    		addSequential(new Drive(.6,0),3);
    		Shoot();
    	} else if (mode == AutonomousMode.RAMPART){
    		//drive forward
    		addSequential(new Drive(.6,0),3);
    		Shoot();
    	} else if (mode == AutonomousMode.ROCKWALL){
    		addSequential(new Drive(.6,0),3);
    		Shoot();
    	} else if (mode == AutonomousMode.SPY){
    		Shoot();
    	} else {
    		//drive forward
    		addSequential(new Drive(.6,0),3);
    	}
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
    public void Shoot(){
    	//line up the shot
    	addSequential(new PIDTurn());
		addSequential(new PIDTurn());
		//set target angle
		addSequential(new PIDAngle());
		//spin up ball
		addSequential(new Launch(true),3);
		//FALCON PUUUUNCH
		addSequential(new FalconPunch(),1);
		//do nothing for a second
		addSequential(new FalconPunch(true),1);
		//reset shooter
		addSequential(new FalconPunch(),1);
		//level the shooter
		addSequential(new PIDAngle(0));
    }
}
