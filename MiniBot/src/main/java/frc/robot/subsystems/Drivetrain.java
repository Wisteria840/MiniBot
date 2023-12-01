package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {/* you are going have to fix this */
  public static final class Config {
    public static final int kRightPrimaryMotor = 3;
    public static final int kRightSecondaryMotor = 4;
    public static final int kLeftPrimaryMotor = 1;
    public static final int kLeftSecondaryMotor = 2;

    private static VictorSPXControlMode kControlMode = VictorSPXControlMode.PercentOutput;
    
    public static final double kWheelDiameter = 6;
    public static final double kTicksPerRevolution = 2048; //Change this
    public static final double kRobotWidth = 23;
    private static final double kGearRatio = 9.8; //Change 

  }

  private VictorSPX m_rightPrimary = new VictorSPX(Config.kRightPrimaryMotor);
  private VictorSPX m_rightSecondary = new VictorSPX(Config.kRightSecondaryMotor);
  private VictorSPX m_leftPrimary = new VictorSPX(Config.kLeftPrimaryMotor);
  private VictorSPX m_leftSecondary = new VictorSPX(Config.kLeftSecondaryMotor);



  public Drivetrain() {
    m_leftSecondary.follow(m_leftPrimary);
    m_rightSecondary.follow(m_rightPrimary);
    m_leftPrimary.setInverted(true);
    m_leftSecondary.setInverted(true);
  }


  public void setRightSpeed(double rightSpeed){
    m_rightPrimary.set(Config.kControlMode, rightSpeed);
  }

  public void setLeftSpeed(double leftSpeed) {
    m_leftPrimary.set(Config.kControlMode, leftSpeed);
  }
  public void setIdleMode(NeutralMode idleMode) {
    m_leftPrimary.setNeutralMode(idleMode);
    m_leftSecondary.setNeutralMode(idleMode);
    m_rightPrimary.setNeutralMode(idleMode);
    m_rightSecondary.setNeutralMode(idleMode);
  }

 

  public double getLeftPosition() {
    return m_leftPrimary.getSelectedSensorPosition();
  }

  public double getRightPosition() {
    return m_rightPrimary.getSelectedSensorPosition();
  }

  public static double toTicks(double distance){ /* input distance in inches output encoder ticks*/
    return (distance/(Config.kWheelDiameter*Math.PI)) * Config.kTicksPerRevolution * Config.kGearRatio;
    
  }





  @Override
  public void periodic() {
  }
}
