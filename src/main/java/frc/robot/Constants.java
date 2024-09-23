// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

  public static class DriverConstants {
    public static final int weaponsControllerPort = 0;
  }

  /* All constants related to ElevatorSubsystem.java and TeleopElevator.java */
  public static class ElevatorConstants {
    /* The constants below are used in ElevatorSubsystem.java */
    public static final int ElevatorMotorID = 1;

    public static final boolean ElevatorMotorInvert = true;

    public static final double ElevatorMotorOutput = 0.25;

    public static final double ElevatorMotorMaxPosition = 36000;
    public static final double ElevatorMotorMinPosition = -36000;

    public static final double ElevatorMotorStartPosition = 0;

    public static final double maxElevatorMotorVoltage = 6;

    /* The constants below are used in ElevatorCommand.java */
    public static final double elevatorStickDeadband = 0.1;

    /* The constants below are used in PIDElevator.java */
    public static final double elevatorP = 0.1;
    public static final double elevatorI = 0;
    public static final double elevatorD = 0;
  }
}
