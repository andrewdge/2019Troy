/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HatchSubsystem;
import frc.robot.vision.*;


public class Robot extends TimedRobot {

  /*
   *
   * IMPORTANT: READ THIS
   * 
   * This robot was built with a command based robot template
   * 
   * ****NOTE*****
   * No auto was used this year(2019) we had sandstorm mode and used cameras instead
   * - auto normally goes under commands, use the autnomousPeriod/init functions to see how to choose auto modes
   * 
   * If using CAN, install Phoenix Library (CTRE). There are instructions at screensteps under third party libraries
   * when starting from the getting started page. Make sure you hit the Wpilib button and manage vender deps, 
   * then do offline install of Phoenix library. You should see a folder named vendordeps above the .gitignore file at the 
   * bottom left, below Robot.java
   * 
   * Delete RobotMap.java and OI.java, create IO.java and Controls.java
   * 
   * 
   * Subsystems control hardware ONLY
   * Commands controls the software interfacing ONLY
   * IO is where you assign you ports
   * Controls is where you get a value output from buttons on a joystick
   * 
   * Subsystems:
   *  - Subsystems are for groups of robot components that relate to each other
   *  - private Robot robot
   *  - pass in Robot robot as constructor parameter
   *  - make sure this.robot = robot so robot instance is passed
   *  - needs a setDefaultCommand(new CommandNameHere(robot)) in initDefaultCommand
   *  - Create objects for motor controllers or motors
   *    - example: ObjectType name = new ObjectType(IO.portNumber)
   *    - example: WPI_VictorSPX leftMotor = new WPI_VictorSPX(IO.motorDriveLeft)
   *  - Create methods controlling actions such as setting speeds or positions
   *  - Adjust any configurations for motors such as safetyEnabled or output modes
   *  - Create methods returning values that the motors can calculate, like position or speed
   *    - usually use objectName.set(1) or something similar
   
   * 
   * Commands:
   *  - Commands describe the actions that a subsystem can take
   *  - Only 1 command per subsystem
   *  - You can call commands in other commands, and assign them to button presses 
   *    - example: if (Controls.methodName) then : new CommandNameHere(robot, arg1, arg2...).start()
   *  - private Robot robot
   *  - pass in Robot robot as constructor parameter
   *  - make sure this.robot = robot so robot instance is passed
   *  - each command requires a subsystem to run off of -- use requires(robot.subsystemNameHere)
   *  - map controls to subsystem methods to control the motors with joysticks
   *    - example: if (Controls.methodName) then: robot.subsystemName.actionMethodName()
   *  - further advanced logic such as elevator levels
   *  - if you created any public final static variables in your subsystems, they should be accessed here with just
   *    SubsystemName.variableName not robot.subsystemName.variableName -- capital on the first letter of the 
   *    subsystem as the variable is static and you are directly calling it from the subsystem and not through the robot
   *    instance.
   *  - most checks in commands for button presses and other things go in execute
   *  - if using an instant command the only things that are called are init and end/isFinished
   * 
   * IO:
   *  - create empty constructor
   *  - assign all ports for PWM, CAN, USB here
   *  - separate each into input type/ object type such as motors, pneumatic solenoids, servos, cameras, or gyros
   *  - call ports from here using IO.portName
   *  - example: public static final int motorLeft = 1;
   * 
   * Controls:
   *  - Create empty constructor
   *  - Copy and paste the XBOX/JOYSTICK values 
   *  - create new methods for getting the values/states of buttons/sticks
   *  - deadband ALL sticks
   *  - NO method should return void, but every method should return something
   *  - call control methods using Controls.methodName
   *  - if method returns boolean just check for a true or false in commands
   *  - if method returns int or double, check if returned value is >, <, = another number
   *  - example: public boolean shooter(){
   *      return xboxController.getRawButton(XBOX_BUTTON_A);
   *    }
   *  - return type is true or false for pressed or not pressed
   *  - shooter is what button a does, activate shooter or not
   *  - getRawButton is the way to go for basically everything
   *  - XBOX_BUTTON_A is a predefined button number from the copy pasted list
   * 
   * VSCode Tips:
   *  - tab to autocomplete
   *  - type a few letters or press . after object names are complete to get all the methods an object can use
   *  - hover over method to see what its parameters are
   *  - sysout for fast System.out.println()
   *  
   * Resources:
   * - Screensteps wpilib
   * - wpilib docs
   * - CTRE phoenix docs
   * - Phoenix tuner (.exe file)
   * - Shuffleboard/SmartDashboard (.jar file) -- you can view smartdashboard from shuffleboard
   * - ChiefDelphi (forums)
   * 
   * 
   * you can contact me at shinyzorua007@gmail.com for any questions
   * 
   * 
   * 
   * 
   */



  
  //Instantiate subsystems. Only 1 robot object is created and is passed to each subsystem
  public DriveSubsystem driveSubsystem = new DriveSubsystem(this);
  public BallSubsystem ballSubsystem = new BallSubsystem(this);
  public HatchSubsystem hatchSubsystem = new HatchSubsystem(this);
  public ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(this);
  public ClimbSubsystem climbSubsystem = new ClimbSubsystem(this);

  //create top and bottom USBCamera objects
  public static UsbCamera camBot, camTop;


  Compressor comp = new Compressor(0);
  VisionThread visionThread;

  double centerX = 0.0;
  DifferentialDrive drive = driveSubsystem.drive;
  final Object imgLock = new Object();
  

  

  
  
  public void robotInit() {

    /* Start camera feed at port 0 and set FPS. Name is camBot */
    camBot = CameraServer.getInstance().startAutomaticCapture("camBot", 0);
    camBot.setFPS(12);

    //camBot.setResolution(320, 240);

    camTop = CameraServer.getInstance().startAutomaticCapture("camTop", 1);
    camTop.setFPS(12);
    //camTop.setResolution(width, height)

    


  

    

    

    
    
  }

  public void robotPeriodic() {
    
    //print data to dashboard
    SmartDashboard.putNumber("encoder Current Value", elevatorSubsystem.getEncoderCurrentValue());
    SmartDashboard.putBoolean("limit Boolean Value", elevatorSubsystem.getLimitCurrentValue());
    //SmartDashboard.putNumber("encoder Zero Value", elevatorSubsystem.zeroEncoderValue);
    //SmartDashboard.putNumber("Inches off Ground", elevatorSubsystem.inchesAboveZero());
    //SmartDashboard.putBoolean("Compressor Status", Compres)
    SmartDashboard.putNumber("Upper Limit", elevatorSubsystem.getUpperLimitValue());
  }

  public void disabledInit() {
  }

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
 
  }

  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  public void teleopInit() {

  }

  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }


  public void testInit(){

  }
  public void testPeriodic() {
    
  }
}
