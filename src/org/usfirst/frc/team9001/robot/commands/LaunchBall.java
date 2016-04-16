package org.usfirst.frc.team9001.robot.commands;

import org.usfirst.frc.team9001.robot.Robot;
import org.usfirst.frc.team9001.robot.subsystems.Launcher;

import edu.wpi.first.wpilibj.command.Command;

public class LaunchBall extends Command {
	private enum State {
		WAIT_LET_GO_BALL, WAIT_AIM_AT_SETPOINT, WAIT_BALL_LEAVE, WAIT_AIM_AT_FINISH;
	}
	
	CameraHelper camHelper;
	Launcher launcher;
	State state;
	
	public LaunchBall() {
		camHelper = CameraHelper.getInstance();
		launcher = Robot.launcher;
		state = State.WAIT_LET_GO_BALL;
	}

	@Override
	protected void initialize() {
		launcher.setArmSetpoint(60);
	}

	@Override
	protected void execute() {
		switch (state) {
		case WAIT_LET_GO_BALL:
			//TODO: add safety to check if launcher is stuck
			if (launcher.getArmPosition() < 30) launcher.setPuncherPlate(true);
			else {
				launcher.setPuncherPlate(false);
			}
			break;
		case WAIT_AIM_AT_SETPOINT:
			
			break;
		case WAIT_BALL_LEAVE:
			
			break;
		case WAIT_AIM_AT_FINISH:
			
			break;
		default:
			state = State.WAIT_LET_GO_BALL;
			break;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
