package org.usfirst.frc.team9001.robot;

import org.usfirst.frc.team9001.robot.util.PixyCmu5;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static AnalogInput ultrasonic;
	public static PixyCmu5 pixyCam;
	
	
	
	
	public static void init() {
		try {
			pixyCam = new PixyCmu5(0xa8, 0.2);
		}catch(Exception e) {
			DriverStation.reportError("Error instantiating PixyCam!", true);
			pixyCam = null;
		}
		
		ultrasonic = new AnalogInput(3);
	}
}
