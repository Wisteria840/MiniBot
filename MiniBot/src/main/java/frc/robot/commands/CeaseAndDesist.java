// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class CeaseAndDesist extends CommandBase {
  boolean dozer;
  Drivetrain m_drivetrain;
  public CeaseAndDesist(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    dozer = true;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setLeftSpeed(0);
    m_drivetrain.setRightSpeed(0);
    /* maybe set neutral mode or something */
  }

  @Override
  public boolean isFinished() {
    return dozer;
  }
}
