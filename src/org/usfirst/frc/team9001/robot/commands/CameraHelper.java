package org.usfirst.frc.team9001.robot.commands;

import java.sql.Driver;
import java.util.List;

import org.usfirst.frc.team9001.robot.Robot;
import org.usfirst.frc.team9001.robot.util.PixyCmu5;
import org.usfirst.frc.team9001.robot.util.PixyCmu5.PixyFrame;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraHelper extends Command {
	
	private PixyCmu5 cam;
	
	private static final double TARGET_WIDTH = 0.508;//meters
	
	private List<PixyFrame> frames;
	private PixyFrame activeFrame;
	private boolean isReading;
	private int numFrames;
	private double dataAge;
	private double camToTarget;
	
	@Override
	protected void initialize() {
		cam = Robot.pixyCam;
		
		isReading = false;
		numFrames = 0;
		dataAge = 0;
	}
	
	@Override
	protected void execute() {
		calcData();
		logData();
	}
	
	private void calcData() {
		frames = cam.getCurrentframes();
		isReading = cam.getIsReading();
		dataAge = cam.getDataAge();
		numFrames = frames.size();
		activeFrame = calcBiggestFrame(frames);
		
		try {
			camToTarget = (TARGET_WIDTH/2) / Math.tan(Math.toRadians(activeFrame.width/2 * PixyCmu5.PIXY_X_DEG_PER_PIXEL));
			camToTarget *= 3.28084; //fine, in feet TODO: get rid of to change to meters for real use! #hardcore_metric_fan #imperial_sucks
		}catch(Exception e) {
			DriverStation.reportError("Error calculating distance to target, frame cannot be null!", true);
		}
	}

	private PixyFrame calcBiggestFrame(List<PixyFrame> frames) {
		int size = frames.size();
		
		if (size<=0)
			return null;
		else {
			int maxWidth = 0, activeIdx = -1;
			//loops through all the frames
			for (int i=1; i<=size; i++) {
				//stores the current frame and width of that frame (temporary)
				PixyFrame tempFrame = frames.get(i-1);
				int tempWidth = tempFrame.width;
				//compares the width of the current frame to the biggest width so far
				if (tempWidth > maxWidth) {
					//sets the new biggest width
					maxWidth = tempWidth;
					//sets the new index of the biggest frame
					activeIdx = i-1;
				}
			}
			//returns the biggest frame
			return frames.get(activeIdx);
		}
	}
	
	@Override
	protected void interrupted() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		
	}
	
	private void logData() {
		SmartDashboard.putBoolean("IsReading", isReading);
		SmartDashboard.putNumber("DataAge", dataAge);
		SmartDashboard.putNumber("NumFrames", numFrames);
		
		//logs all frames
//		for (int i=1; i<=frames.size(); i++) {
//			PixyFrame tempFrame = frames.get(i-1);
//			SmartDashboard.putNumber("Frame["+i+"]Timestamp", tempFrame.timestamp);
//			SmartDashboard.putNumber("Frame["+i+"]Width", tempFrame.width);
//			SmartDashboard.putNumber("Frame["+i+"]Height", tempFrame.height);
//			SmartDashboard.putNumber("Frame["+i+"]CenterX", tempFrame.xCenter);
//			SmartDashboard.putNumber("Frame["+i+"]CenterY", tempFrame.yCenter);
//			SmartDashboard.putNumber("Frame["+i+"]Angle", tempFrame.angle);
//			SmartDashboard.putNumber("Frame["+i+"]Area", tempFrame.area);
//		}
		
		//logs active frame
		if (activeFrame != null) {
			SmartDashboard.putNumber("activeFrame_Timestamp", activeFrame.timestamp);
			SmartDashboard.putNumber("activeFrame_Width", activeFrame.width);
			SmartDashboard.putNumber("activeFrame_Height", activeFrame.height);
			SmartDashboard.putNumber("activeFrame_CenterX", activeFrame.xCenter);
			SmartDashboard.putNumber("activeFrame_CenterY", activeFrame.yCenter);
			SmartDashboard.putNumber("activeFrame_Angle", activeFrame.angle);
			SmartDashboard.putNumber("activeFrame_Area", activeFrame.area);
			SmartDashboard.putNumber("activeFrame_xDegOffset", cam.degreesXFromCenter(activeFrame));
			SmartDashboard.putNumber("activeFrame_CamToTarget", camToTarget);
		}
	}
	
}
