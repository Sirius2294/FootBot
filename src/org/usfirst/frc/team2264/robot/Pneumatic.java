package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class Pneumatic {
	
	private DoubleSolenoid solenoid;
	private Compressor compressor;
	
	public Pneumatic() {
		solenoid = new DoubleSolenoid(0, 0, 1);
		compressor = new Compressor(0);
		
		// Starts the compressor
		compressor.start();
	}
	
	public void updateSolenoidState(Joystick leftJoystick, Joystick rightJoystick) {
		if(rightJoystick.getTrigger()) {
			solenoid.set(DoubleSolenoid.Value.kForward);
		}
		else if(leftJoystick.getTrigger()) {
			solenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}

}
