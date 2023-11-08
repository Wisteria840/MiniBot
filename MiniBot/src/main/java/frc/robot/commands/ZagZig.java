// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.reflect.Array;

import org.opencv.core.Mat;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class ZagZig extends CommandBase {
  private static final class Config{
    private static final double kRobotWidth = 30; /* width in inches */
    private static final double kWheelDiameter = 12; /* in inches */
    private static final int kTicksPerRevolution = 2048;

    private static final double kP = 0.5;
    private static final double kI = 0;
    private static final double kD = 0;

    private static final double speedMultiply = 0.5;
    private static final double tolerance = 0.1;

  }

  static private Drivetrain m_driveTrain;
  static private double m_turnDegrees;
  static private double m_turnRadius;
  static private boolean m_left;

  private double leftPosition;
  private double rightPosition;

  private double leftGoal;
  private double rightGoal;
  private double leftSpeed;
  private double rightSpeed;

  private PIDController m_pidController = new PIDController(Config.kP, Config.kI, Config.kD);
  public ZagZig(Drivetrain drivetrain, double turnDegrees, double turnRadius, boolean left) {
    m_driveTrain = drivetrain;
    m_turnDegrees = turnDegrees;
    m_turnRadius = turnRadius;
    m_left = left;


  }

  @Override
  public void initialize() {
    double OutsideInsideRatio = (m_turnRadius + Config.kRobotWidth)/m_turnRadius;
    leftSpeed = Config.speedMultiply;
    rightSpeed = leftSpeed * OutsideInsideRatio;
    leftGoal = toTicks((m_turnRadius) * (Math.toRadians(m_turnDegrees))); /* inside */
    rightGoal = toTicks((m_turnRadius + Config.kRobotWidth) * (Math.toRadians(m_turnDegrees))); /* outside */
    if (m_left == true){

    } else {
      double temp = leftGoal;
      leftGoal = rightGoal;
      rightGoal = temp;

      temp = leftSpeed;
      leftSpeed = rightSpeed;
      rightSpeed = temp;
    }
    leftGoal = leftGoal + m_driveTrain.getLeftPosition();
    rightGoal = rightGoal + m_driveTrain.getRightPosition();
  }

  @Override
  public void execute() {
    leftPosition = m_driveTrain.getLeftPosition();
    rightPosition = m_driveTrain.getRightPosition();
    m_driveTrain.setLeftSpeed(leftSpeed);
    m_driveTrain.setRightSpeed(rightSpeed);
    }

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if ((leftGoal - leftPosition) <= Config.tolerance){
      return true;
    } else {
      return false;
    }
  }


  


  public static double toTicks(double distance){ /* input distance in inches output encoder ticks*/
    return (distance/(Config.kWheelDiameter*Math.PI)) * Config.kTicksPerRevolution;
  }
}

