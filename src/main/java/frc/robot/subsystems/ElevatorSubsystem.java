package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.commands.DefaultElevatorCommand;


public class ElevatorSubsystem extends Subsystem {
  private Robot robot;
  private WPI_TalonSRX talon;
  private DoubleSolenoid brake;
  private DigitalInput limitBot;

  public double inches;

  //Zero Encoder value
  public double zeroEncoderValue;


  //"hard capped" upper limit
  public static int maxUpperLimit = -25500;//-25500;

  

  //vator mode
  public static final int shipMode = 0;
  public static final int ballMode = 1;
  public static final int hatchMode = 2;

  //for printing to shuffleboard for Operator usage
  public static String[] modeNames = {"Cargo Ship Mode", "Rocket Ball Mode", "Rocket Hatch Mode"};

  public int currentLevel = 0;
  public int currentMode = shipMode;

  //levels - cargo - rocket  hatch - rocket ball
  public static int homeLevel = 0;

  public static int[] c_shipLevels = {homeLevel, -3000,-7000};
  
  public static int[] r_hatchLevels = {homeLevel, homeLevel, -9747, maxUpperLimit};//fix this
  

  public static int[] r_ballLevels = {homeLevel, -4000,-13786,-37140};


  //Elevator encoder is negative values


  public ElevatorSubsystem(Robot robot){
    this.robot = robot;
    talon = new WPI_TalonSRX(IO.motorElevator);
    talon.setSafetyEnabled(false);
    //encoder = new Encoder(IO.encoderElevatorA, IO.encoderElevatorB);
    brake = new DoubleSolenoid(IO.vatorBrakeEngage, IO.vatorBrakeDisengage);
    limitBot = new DigitalInput(IO.limitSwitchElevatorBottom);
  }
  
  public void initDefaultCommand() {
    setDefaultCommand(new DefaultElevatorCommand(robot));
  }

  public void setSpeed(double speed){
    //you are going the wrong way
    // NEGATIVE = CW = UP

    
    if(speed > 0 && getEncoderCurrentValue() > maxUpperLimit){

      breakDisengage();
      talon.set(speed);

    } else if (speed < 0 && !getLimitCurrentValue()){

      breakDisengage();
      talon.set(speed);

    } else if (speed >= -0.05 && speed <= 0.05) {
      talon.stopMotor();
      breakEngage();
    }

  }

  private void breakEngage(){
    brake.set(DoubleSolenoid.Value.kForward);
  }

  private void breakDisengage(){
    brake.set(DoubleSolenoid.Value.kReverse);
  }

  //Zeroes the Encoder value
  public void checkBotLimitResetEncoder(){
    // if (getLimitCurrentValue()){
    //   zeroEncoderValue = getEncoderCurrentValue();
    // }
    // lastLimitBooleanValue = getLimitCurrentValue();
    // talon.reset
  }

//-15523
//-1599
//40.5 in separation
//ticks / in = 15523 / 40.5 == 383.28
//


  //Limit Methods


  //true = triggered
  public boolean getLimitCurrentValue(){
    return limitBot.get();
  }


  //Encoder Methods


  //positive value = up
  public int getEncoderCurrentValue(){
    return talon.getSelectedSensorPosition();
  }

  public int getUpperLimitValue(){
    return maxUpperLimit;
  }

  //convert revolutions to inches
  public double inchesAboveZero(){
    return inches = getEncoderCurrentValue() / (15523 / 40.5);
  }

  //Set Mode / Level

  public void setMode(int targetMode){
    if (targetMode == shipMode && currentLevel == 3){
      setLevel(2);
    } 
    currentMode = targetMode;

    SmartDashboard.putString("Current Mode", modeNames[currentMode]);    
  }

  public void setLevel(int targetLevel){
    currentLevel = targetLevel;

    SmartDashboard.putNumber("Current Level", currentLevel);
  }

}
