package org.usfirst.frc.team3019.robot;

import org.usfirst.frc.team3019.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	
	/* xbox controller */
	public Joystick xbox = new Joystick(0);

	/*AXES USED
	 * 
	 * LEFT STICK
	 * 	X-AXIS: TURNING FOR ARCADE
	 * 	Y-AXIS: FWD/REV FOR ARCADE
	 * 
	 * RIGHT STICK
	 *  X-AXIS: NOT USED
	 * 	Y-AXIS: RAISING PISTON ARMS
	 * 
	 */
	
	/*BUTTONS USED
	 * 
	 * 1 A     -used for toggling solenoid
	 * 2 B     -used to angle down
	 * 3 X     -used to angle up
	 * 4 Y     -used to toggle servo
	 * 5 lBump   -used to manual run launch motors fwd
	 * 6 rBump   -used to manual run launch motors intake
	 * 7 back    -used to manual do PIDTurn
	 * 8 start   -used to manual do PIDAngle
	 * 9 lClick  -NOT USED
	 * 10 rClick -NOT USED
	 * 
	 */
	
	public Button xb1 = new JoystickButton(xbox, 1);
	public Button xb2 = new JoystickButton(xbox, 2);
	public Button xb3 = new JoystickButton(xbox, 3);
	public Button xb4 = new JoystickButton(xbox, 4);
	public Button xb5 = new JoystickButton(xbox, 5);
	public Button xb6 = new JoystickButton(xbox, 6);
	public Button xb7 = new JoystickButton(xbox, 7);
	public Button xb8 = new JoystickButton(xbox, 8);
	public Button xb9 = new JoystickButton(xbox, 9);
	public Button xb10 = new JoystickButton(xbox, 10);

	public OI() {
		
		if (RobotMap.usePID) {

			// PID commands
			xb7.whenPressed(new PIDTurn());
			//testing command group
			xb8.whenPressed(new PIDAngle());
//			xb8.whenPressed(new ShootCommandGroup());
			
		}

		if (RobotMap.usePneumatics) {

			// Compressor 
			xb1.whenPressed(new SolenoidToggle());

		}

		if (RobotMap.usePuncher) {

			// launch servo command
			xb4.whenPressed(new FalconPunch());

		}

	}
}
