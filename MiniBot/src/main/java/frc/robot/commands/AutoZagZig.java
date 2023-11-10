// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.sql.Driver;

import edu.wpi.first.math.Drake;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;


public class AutoZagZig extends SequentialCommandGroup {
  private static final class Config{
    private static final double kWheelDiameter = 12; /* there are two types of contries */
    private static final double kTicksPerRevolution = 2048; 

  }
  public AutoZagZig(Drivetrain drivetrain) {
    addCommands(
      /* add the excrement in here */
      new 

    );
  }

  public static double toTicks(double distance){ /* input distance in inches output encoder ticks*/
    return (distance/(Config.kWheelDiameter*Math.PI)) * Config.kTicksPerRevolution;
  }

}
