// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {/* you are going have to fix this */
  public static final class Config {
    public static final int kRightPrimaryMotor = 3;
    public static final int kRightSecondaryMotor = 4;
    public static final int kLeftPrimaryMotor = 1;
    public static final int kLeftSecondaryMotor = 2;

    public static final double kWheelDiameter = 6; /* there are two types of contries */
    public static final double kTicksPerRevolution = 2048; 
    public static final double kRobotWidth = 23;
    private static final double gearRatio = 9.8;
  }

  private WPI_TalonFX m_rightPrimary = new WPI_TalonFX(Config.kRightPrimaryMotor);
  private WPI_TalonFX m_rightSecondary = new WPI_TalonFX(Config.kRightSecondaryMotor);
  private WPI_TalonFX m_leftPrimary = new WPI_TalonFX(Config.kLeftPrimaryMotor);
  private WPI_TalonFX m_leftSecondary = new WPI_TalonFX(Config.kLeftSecondaryMotor);



  /** Creates a new Drivetrain. */
  public Drivetrain() {
    m_leftSecondary.follow(m_leftPrimary);
    m_rightSecondary.follow(m_rightPrimary);
    m_leftPrimary.setInverted(true);
    m_leftSecondary.setInverted(true);
  }


  public void setRightSpeed(double rightSpeed){
    m_rightPrimary.set(rightSpeed);
  }

  public void setLeftSpeed(double leftSpeed) {
    m_leftPrimary.set(leftSpeed);
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
    return (distance/(Config.kWheelDiameter*Math.PI)) * Config.kTicksPerRevolution * Config.gearRatio;
    
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
