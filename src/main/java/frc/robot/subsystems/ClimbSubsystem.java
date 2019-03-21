package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.commands.DefaultLiftCommand;


public class ClimbSubsystem extends Subsystem {
  private Robot robot;

  public ClimbSubsystem(Robot robot){
    this.robot = robot;
  }

  Solenoid sol = new Solenoid(IO.climb);
  //Solenoid rightSol = new Solenoid(IO.climbRight);

  public void initDefaultCommand() {
    setDefaultCommand(new DefaultLiftCommand(robot));
  }

  public void deployLift(){
    sol.set(true);
    //rightSol.set(true);
  }

  public void retractLift(){
    sol.set(false);
    //rightSol.set(false);
  }

}
