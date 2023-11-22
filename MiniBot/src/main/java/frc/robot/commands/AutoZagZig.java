// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;


public class AutoZagZig extends SequentialCommandGroup {
  public static final class Config{
    public static final double kWheelDiameter = 5; /* there are two types of contries */
    public static final double kTicksPerRevolution = 2048; 
    public static final double kRobotWidth = 360;
  }
  public AutoZagZig(Drivetrain drivetrain) {
    addCommands(
      new ClaqClaq(10, drivetrain)

    );
  }

  public static double toTicks(double distance){ /* input distance in inches output encoder ticks*/
    return (distance/(Config.kWheelDiameter*Math.PI)) * Config.kTicksPerRevolution;
  }

}
