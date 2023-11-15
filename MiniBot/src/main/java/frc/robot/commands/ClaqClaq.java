// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class ClaqClaq extends CommandBase {
  private static final class Config{
    private static final double speedMultiply = 0.5;
    private static final double tolerance = 50; //in ticks
    private static final double wheelWidth = 6; //measure later in inches
    private static final double wheelCircumference = wheelWidth * Math.PI;
    private static final double ticksPerRevolution = 2048;
  }

  private double m_goal;
  private Drivetrain m_driveTrain;
  private double m_initalPosition;
  private double m_currentPosition;
  private double m_error;

  public ClaqClaq(double goal, Drivetrain drivetrain) { /* goal in inches*/
    m_goal = ((goal * Config.ticksPerRevolution) / Config.wheelCircumference);
    m_driveTrain = drivetrain;
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
    m_initalPosition = m_driveTrain.getRightPosition();
  }

  @Override
  public void execute() {
    m_currentPosition = m_driveTrain.getRightPosition() - m_initalPosition;
    m_error = m_goal - m_currentPosition;
    m_driveTrain.setLeftSpeed(Config.speedMultiply);
    m_driveTrain.setRightSpeed(Config.speedMultiply);
  }

  @Override
  public void end(boolean interrupted) {
    //m_driveTrain.setLeftSpeed(0);
    //m_driveTrain.setRightSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return m_error <= Config.tolerance; /* might have to flip the signs */
  }
}
