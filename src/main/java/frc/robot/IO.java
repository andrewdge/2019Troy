package frc.robot;


public final class IO{
    private IO(){}

    //CAN
    public static final int motorDriveLeftTop = 1;
    public static final int motorDriveLeftBottom = 2;
    public static final int motorDriveRightTop = 3;
    public static final int motorDriveRightBottom = 4;
    public static final int motorElevator = 3; //talon = 3
    public static final int motorBeaterBar = 6;
    
    
    //Solenoid
    public static final int ballDeployIn = 1;
    public static final int ballDeployOut = 0;
    public static final int hatchGrabberIn = 3;
    public static final int hatchGrabberOut = 2;
    public static final int vatorBrakeEngage = 4;
    public static final int vatorBrakeDisengage = 5 ;
    public static final int climb = 6;
    //public static final int climbRight = 7;
    


    

    //PWM
    //public static final int ballServo = 0;
    public static final int hatchDeployServoA = 1;
    public static final int hatchDeployServoB = 2;
    public static final int hatchCandyCane = 3;
    
    

    //analog
    

    //digital
    public static final int limitSwitchElevatorBottom = 0;
    //public static final int limitSwitchElevatorTop = 1;
    //public static final int encoderElevatorA = 2;
    //public static final int encoderElevatorB = 3;
    

}