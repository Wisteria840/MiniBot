// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class RightSpin extends CommandBase {
  private static final class Config{
    private static final double rotSped = 0.2; //motor speed
    private static final double tolerance = 2; // in ticks
  }

  double m_goalDegree;
  double m_leftGoal;
  double m_initalLeftPosition;
  double m_currentLeftPosition;
  Drivetrain m_driveTrain;

  public RightSpin(double goalDegree, Drivetrain drivetrain, double ticks) {
    
    m_leftGoal = Drivetrain.toTicks((Math.toRadians(goalDegree)) * (Drivetrain.Config.kRobotWidth/2));
    SmartDashboard.putNumber("Goal distance", m_leftGoal);
    m_leftGoal = ticks;
    m_driveTrain = drivetrain;
    m_driveTrain.setIdleMode(NeutralMode.Brake);
    addRequirements(m_driveTrain);
    
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("start position", m_driveTrain.getLeftPosition());
    m_leftGoal = m_leftGoal + m_driveTrain.getLeftPosition();
    
    
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Left Goal", m_leftGoal);

    m_driveTrain.setLeftSpeed(Config.rotSped);
    m_driveTrain.setRightSpeed(-Config.rotSped); /* be sure this negative sign is working */
    
  }

  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
    SmartDashboard.putNumber("end position", m_driveTrain.getLeftPosition());
  }

  @Override
  public boolean isFinished() {
    return m_leftGoal - m_driveTrain.getLeftPosition() <= Config.tolerance;
  }
}
