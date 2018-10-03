package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class Drivetrain {
	
	private TalonSRX left;
	private TalonSRX right;
	
	private final double outputMax = 0.4;
	private final double deadzone = .05;
	
	public Drivetrain() {
		left = new TalonSRX(2);
		right = new TalonSRX(1);
	}
	
	// This method moves the robot using tank drive
	public void tankDrive(Joystick leftJoystick, Joystick rightJoystick) {
		left.set(ControlMode.PercentOutput, -1 * leftJoystick.getY(GenericHID.Hand.kLeft) * outputMax);
		right.set(ControlMode.PercentOutput, rightJoystick.getY(GenericHID.Hand.kRight) * outputMax);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Asymptotes of tan x: pi/2 + pi*n
	public void arcadeDrive(Joystick joystick, ADXRS450_Gyro gyro) {
		double xVector = joystick.getX(GenericHID.Hand.kRight);
		double yVector = joystick.getY(GenericHID.Hand.kLeft);
		// angle is measured in radians
		double angle = Math.atan(yVector / xVector);
		double magnitude = Math.sqrt(Math.pow(xVector, 2) + Math.pow(yVector, 2));
		
		double robotAngle = gyro.getAngle();
		
		
	}
	
	public void pseudoArcadeDrive(Joystick joystick) {
		double xVector = joystick.getX(GenericHID.Hand.kRight);
		double yVector = joystick.getY(GenericHID.Hand.kRight);
		// angles are measured in degrees
		double controlAngle;
		try {
			if(xVector > 0)
				controlAngle = Math.toDegrees(Math.atan(yVector / xVector));
			else {
				controlAngle = 180.0 + Math.toDegrees(Math.atan(yVector / xVector));
			}
		}catch(Exception e) {
			if(yVector > 0) {
				controlAngle = 90;
			}
			else {
				controlAngle = 270;
			}
		}
		
		double angleDiff = 90 - controlAngle;
		
		double turnRate = outputMax / 90.0;
		
		left.set(ControlMode.PercentOutput, -1 * (yVector * outputMax) * (1 + turnRate * angleDiff));
		right.set(ControlMode.PercentOutput, (yVector * outputMax) * (1 - turnRate * angleDiff));
		System.out.printf("Left: %0$.2f\tRight: %1$.2f\tAngle: %2$.2f\n", (yVector * outputMax) * (1 + turnRate * angleDiff), (yVector * outputMax) * (1 - turnRate * angleDiff), controlAngle);
	}

}
