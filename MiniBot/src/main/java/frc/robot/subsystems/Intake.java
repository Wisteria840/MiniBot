// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private static final class Config{
    private static final int intakeMotorID = 5;
    private static final double intakeSpeed = 0.1;
  }
  private CANSparkMax m_intakeMotor = new CANSparkMax(Config.intakeMotorID, MotorType.kBrushless);

  public Intake() {}

  @Override
  public void periodic() {
  }

  public InstantCommand cyube(){
    return new InstantCommand(this::cube, this);
  }
  public void cube(){
    m_intakeMotor.set(Config.intakeSpeed); /* check if the cube or cone is in the positive direction. */
  }

  public InstantCommand coone(){
    return new InstantCommand(this::cone, this);
  }

  public void cone(){
    m_intakeMotor.set(Config.intakeSpeed * -1); /* check if this is the right direction */
  }
  }

