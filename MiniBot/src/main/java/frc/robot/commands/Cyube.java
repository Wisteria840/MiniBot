// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;


public class Cyube extends CommandBase {
  static final private class Config{
    private final static double inOutBurgerTime = 5; /* however much time it takes to insert or extract */
  }
  Intake m_intake;
  double m_initialTime;
  double m_currentTime;
  Timer m_timer = new Timer();
  /** Creates a new Cyube. */
  public Cyube(Intake intake) {
    m_intake = intake;

  }

  
  @Override
  public void initialize() {
    
    m_initialTime = m_timer.get();
    m_currentTime = m_initialTime;
  }

  
  @Override
  public void execute() {
    m_currentTime = m_timer.get();
    m_intake.cyube();
  }

  
  @Override
  public void end(boolean interrupted) {
    m_intake.halt();
  }


  @Override
  public boolean isFinished() {
    return m_initialTime - m_currentTime >= Config.inOutBurgerTime;
  }
}
