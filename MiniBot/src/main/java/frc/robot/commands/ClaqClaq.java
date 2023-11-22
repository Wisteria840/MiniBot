// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class ClaqClaq extends CommandBase {
  private static final class Config{
    private static final double speedMultiply = 0.2;
    private static final double tolerance = 2; //in ticks
    private static final double wheelWidth = 6; //measure later in inches
  }

  private double m_goal;
  private Drivetrain m_driveTrain;
  private double m_initalPosition;

  public ClaqClaq(double goal, Drivetrain drivetrain) { /* goal in inches*/
    m_goal = AutoZagZig.toTicks(goal);
    SmartDashboard.putNumber("goal difference", m_goal);
    
    m_driveTrain = drivetrain;
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
    m_initalPosition = m_driveTrain.getLeftPosition();
    m_goal = m_initalPosition + m_goal;
    SmartDashboard.putNumber("Initial Position", m_initalPosition);
    
    SmartDashboard.putNumber("Goal", m_goal);
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Current Position", m_driveTrain.getLeftPosition());
    m_driveTrain.setLeftSpeed(Config.speedMultiply);
    m_driveTrain.setRightSpeed(Config.speedMultiply);
  }

  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
    /* remove this later */
  }

  @Override
  public boolean isFinished() {
    return m_goal - m_driveTrain.getLeftPosition() <= Config.tolerance; /* might have to flip the signs */
  }
}
