package org.usfirst.frc.team9001.robot.util;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 	A mixin for classes that put values on SmartDashboard.
 * 	Used to reinforce the source of information.
 *  <p>
 *  Subsytems that use this mixin should put their logData inside Robot's logData() 
 */
public interface SmartdashBoardLoggable {
	
	public void logData();
	public String getFormattedName();
	
	
	default void putNumberSD(String key, double value) {
		SmartDashboard.putNumber(getFormattedName() + key, value);
	}
	
	default void putBooleanSD(String key, boolean value) {
		SmartDashboard.putBoolean(getFormattedName() + key, value);
	}
	
	default void putStringSD(String key, String value) {
		SmartDashboard.putString(getFormattedName() + key, value);
	}
	
	default void putDataSD(String key, Sendable data) {
		SmartDashboard.putData(getFormattedName() + key, data);
	}
	
}
