package org.usfirst.frc.team9001.robot.commands;

import org.usfirst.frc.team9001.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicHelper extends Command {
	
	AnalogInput ultrasonic;
	private double voltage, avgVoltage, dist;//meters
	
	@Override
	protected void initialize() {
		ultrasonic = RobotMap.ultrasonic;
	}
	
	@Override
	protected void execute() {
		voltage = ultrasonic.getVoltage();
		avgVoltage = ultrasonic.getAverageVoltage();
		dist = 1.087*voltage-0.039;
		
		logData();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
	
	public void logData() {
		SmartDashboard.putNumber("Ultrasonic_voltage", voltage);
		SmartDashboard.putNumber("Ultrasonic_avgVoltage", avgVoltage);
		SmartDashboard.putNumber("Ultrasonic_dist(meters)", dist);
	}
}
