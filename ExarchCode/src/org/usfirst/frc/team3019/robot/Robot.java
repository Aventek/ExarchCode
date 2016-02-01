
package org.usfirst.frc.team3019.robot;

import org.usfirst.frc.team3019.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3019.robot.subsystems.Launcher;
import org.usfirst.frc.team3019.robot.subsystems.MXPBreakout;
import org.usfirst.frc.team3019.robot.subsystems.PIDDrive;
import org.usfirst.frc.team3019.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	
//instantiate networktable for vision tracking
	NetworkTable table;

//instantiate all subsystems
	public static DriveTrain driveTrain;
	public static PIDDrive PIDdrive;
	public static MXPBreakout mxpBreakout;
	public static Pneumatics pneumatics;
	public static Launcher launcher;
	public static OI oi;
	
//autonomous command (not in use)
    Command autonomousCommand;
    
    public Robot() {
		
	}

    public void robotInit() {
    	
    	//creating networktable for towertracking and putting testString
    	table = NetworkTable.getTable("TowerTracker");
    	table.putString("TEST", "isRunning");
    	
    	//creating all instances of subsystems
		driveTrain = new DriveTrain();
		pneumatics = new Pneumatics();
		mxpBreakout = new MXPBreakout();
		launcher = new Launcher();
		PIDdrive = new PIDDrive(0.8, 0.0, 0.0,0);
		oi = new OI();
		
    }
    
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
//VISION PROCESSING
    	SmartDashboard.putNumber("distance", table.getNumber("VISdistance", 0));
        double angleOff;
        
        //fixing angleOff to be relative to forwards
        if(table.getNumber("VISazimuth", 0) > 180){
        	angleOff = 360 - table.getNumber("VISazimuth", 0);
        }else{
        	angleOff = -table.getNumber("VISazimuth", 0);
        }
        
        //putting azimuthal to SmartDash
        SmartDashboard.putNumber("azimuth", angleOff);
        
        Scheduler.getInstance().run(); 

    }
   
    public void testPeriodic() {
        LiveWindow.run();
        Scheduler.getInstance().run();
    }
}
