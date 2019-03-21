package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.commands.DefaultDriveCommand;

public class DriveSubsystem extends Subsystem {
  private Robot robot;
  public DifferentialDrive drive;

  public DriveSubsystem(Robot robot){
    this.robot = robot;
    WPI_VictorSPX leftSlave = new WPI_VictorSPX(IO.motorDriveLeftTop);
    WPI_VictorSPX leftMaster = new WPI_VictorSPX(IO.motorDriveLeftBottom);
    WPI_VictorSPX rightSlave = new WPI_VictorSPX(IO.motorDriveRightTop);
    WPI_VictorSPX rightMaster = new WPI_VictorSPX(IO.motorDriveRightBottom);

    //danger! makes output shut up with stupid errors. We are NOT safe
    leftSlave.setSafetyEnabled(false);
    leftMaster.setSafetyEnabled(false);
    rightSlave.setSafetyEnabled(false);
    rightMaster.setSafetyEnabled(false);

    //same side same speed
    SpeedControllerGroup left = new SpeedControllerGroup(leftMaster, leftSlave);
    SpeedControllerGroup right = new SpeedControllerGroup(rightMaster, rightSlave);

    //pass motors into drive 
    drive = new DifferentialDrive(left, right);
  }

  //drive motors using left and right speeds
  public void tankDrive(double leftSpeed, double rightSpeed){
    drive.tankDrive(leftSpeed, rightSpeed);
  }
  

  public void initDefaultCommand() {
    setDefaultCommand(new DefaultDriveCommand(robot));
  }
}
