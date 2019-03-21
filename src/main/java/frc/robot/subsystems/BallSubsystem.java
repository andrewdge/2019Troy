package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Servo;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.commands.DefaultBallCommand;


public class BallSubsystem extends Subsystem {
  private Robot robot;

  //ball piston
  DoubleSolenoid ballDeploy = new DoubleSolenoid(IO.ballDeployOut, IO.ballDeployIn);

  //Servo gate = new Servo(IO.ballServo);

  //beater bar
  WPI_VictorSPX intake = new WPI_VictorSPX(IO.motorBeaterBar);
  
  

  //constant speed for in and out. 1 is max
  public static final double kSpeed = 1.0;

  public BallSubsystem(Robot robot){
    this.robot = robot;
    intake.setSafetyEnabled(false);
    
  }
  
  public void initDefaultCommand() {
    setDefaultCommand(new DefaultBallCommand(robot));
  }

  //for thumbsticks only
  public void setIntakeSpeed(double speed){
    intake.set(speed);
  }

  //y button - forward
  public void setIntakeConstantForwardSpeed(){
    intake.set(kSpeed);
  }

  //x button - back
  public void setIntakeConstantReverseSpeed(){
    intake.set(-kSpeed);
  }

  public double getIntakeSpeed(){
    return intake.get();
  }

  public void disableMotor(){
    intake.stopMotor();
  }

  //push out
  public void deployEnable(){
    ballDeploy.set(DoubleSolenoid.Value.kForward);
  }

  //pull in
  public void deployDisable(){
    ballDeploy.set(DoubleSolenoid.Value.kReverse);
  }

  /*
  public void setGate(double position){
    gate.set(position);
  }
  */
}
