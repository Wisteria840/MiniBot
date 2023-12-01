// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ZagZigRight extends CommandBase {
  private static final class Config{
    
    private static final double speedMultiply = 0.05;
    private static final double tolerance = 0.1;

  }

  static private Drivetrain m_driveTrain;
  static private double m_turnDegrees;
  static private double m_turnRadius;


  private double rightGoal;
  private double outsideInsideRatio;



  public ZagZigRight(Drivetrain drivetrain, double turnDegrees, double turnRadius) {
    m_driveTrain = drivetrain;
    m_turnDegrees = turnDegrees;
    m_turnRadius = turnRadius;
    

  }

  @Override
  public void initialize() {
    outsideInsideRatio = (m_turnRadius + Drivetrain.Config.kRobotWidth)/(m_turnRadius);
    rightGoal = Drivetrain.toTicks((m_turnRadius) * (Math.toRadians(m_turnDegrees))); /* inside */
 
    rightGoal = rightGoal + m_driveTrain.getLeftPosition();

  }

  @Override
  public void execute() {
    m_driveTrain.setLeftSpeed(Config.speedMultiply * outsideInsideRatio);
    m_driveTrain.setRightSpeed(Config.speedMultiply);
    }

  @Override
  public void end(boolean interrupted) {
    /* remove later */
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return ((rightGoal - m_driveTrain.getRightPosition()) <= Config.tolerance);
  }


  


}

