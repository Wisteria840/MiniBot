// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class ClaqClaq extends CommandBase {
  private static final class Config{
    private static final double speedMultiply = 0.2;
  }

  private double m_goal;
  private Drivetrain m_driveTrain;


  public ClaqClaq(double goal, Drivetrain drivetrain) { /* goal in inches*/
    
    
    m_driveTrain = drivetrain;
    m_goal = goal;
    m_driveTrain.setIdleMode(NeutralMode.Brake);;
    
    
    
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
    
    SmartDashboard.putNumber("start position", m_driveTrain.getLeftPosition());
    m_goal = (Drivetrain.toTicks(m_goal)) + m_driveTrain.getLeftPosition();
    SmartDashboard.putNumber("m_goal", m_goal);
  }

  @Override
  public void execute() {
    m_driveTrain.setLeftSpeed(Config.speedMultiply);
    m_driveTrain.setRightSpeed(Config.speedMultiply);

    
    
  }

  @Override
  public void end(boolean interrupted) {
    //m_driveTrain.setLeftSpeed(0);
    //m_driveTrain.setRightSpeed(0);
    SmartDashboard.putNumber("end posotion", m_driveTrain.getLeftPosition());

    /* remove this later */
  }

  @Override
  public boolean isFinished() {
    return m_goal - m_driveTrain.getLeftPosition() <= 0; /* might have to flip the signs */
  }
}
