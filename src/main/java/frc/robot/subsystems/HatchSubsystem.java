package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.commands.DefaultHatchCommand;


public class HatchSubsystem extends Subsystem {
  private Robot robot;


  //current time is time now, delayed time is time to wait until wipers are deployed
  long currentTime, delayedTime;

  //create two pairs of servo objects, there is a pwm splitter on the robot
  Servo servoA = new Servo(IO.hatchDeployServoA);
  Servo servoB = new Servo(IO.hatchDeployServoB);

  Servo candyCane = new Servo(IO.hatchCandyCane);

  //create doublesolenoid for hatch arm
  DoubleSolenoid hatchGrabber = new DoubleSolenoid(IO.hatchGrabberOut, IO.hatchGrabberIn);

  public HatchSubsystem(Robot robot){
    this.robot = robot;
    candyCane.set(0);
  }
  
  public void initDefaultCommand() {
    setDefaultCommand(new DefaultHatchCommand(robot));
  }

  //servo out
  public void deployEnable(){
    servoA.set(0.0); //change this number
    servoB.set(1.0-servoA.get());
  }

  //servo in
  public void deployDisable(){
    servoA.set(1.0); //change this number
    servoB.set(1.0-servoA.get());
  }

  public void toggleCandyCane(){
    candyCane.set(1-candyCane.get());
    }

 
   

  // public void servoAset(double value){
  //   servoA.set(value);
  // }

  // public void servoBset(double value){
  //   servoB.set(value);
  // }

  public double getServoAPosition(){
    return servoA.get();
  }

  public double getServoBPosition(){
    return servoB.get();
  }

  //windshield wipers 
  public void pulseDeploy(){
    
    for (int i = 0; i < 3; i++){
      currentTime = getCurrentTime();
      deployEnable();
      pulseDelay(500);
    }
    
  }

  //get time now
  public long getCurrentTime(){
    return System.currentTimeMillis();
  }

  //use current time and add a delay, then close servos when time is up
  public void pulseDelay(long waitTime){
    delayedTime = currentTime + waitTime;
    System.out.println(delayedTime);
    
    //work on logic here
    if (getCurrentTime() == delayedTime){
      deployDisable();
    } else {
      deployEnable();
    }
  }

  //piston out
  public void armEnable(){
    hatchGrabber.set(DoubleSolenoid.Value.kForward);
  }

  //piston in
  public void armDisable(){
    hatchGrabber.set(DoubleSolenoid.Value.kReverse);
  }
  
}
