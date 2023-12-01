// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SimpleAuto extends SequentialCommandGroup {
  /** Creates a new SimpleAuto. */
  public SimpleAuto(Drivetrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new LeftSpin(45, drivetrain),
      new ClaqClaq(45.962, drivetrain),
      new RightSpin(90, drivetrain),
      new ClaqClaq(84.853, drivetrain),
      new LeftSpin(90, drivetrain),
      new ClaqClaq(77.782, drivetrain),
      new RightSpin(90, drivetrain),
      new ClaqClaq(38.891, drivetrain),
      new LeftSpin(45, drivetrain),
      //release cone
      //go backwards
      new LeftSpin(180, drivetrain)
    );
  }
}
