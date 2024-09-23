package frc.robot.subsystems;

import frc.robot.Constants.ElevatorConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.VoltageOut;

public class Elevator1Motor extends SubsystemBase {
    private TalonFX ElevatorMotor;
    private final VoltageOut elevatorRequest = new VoltageOut(0);

    public Elevator1Motor() {
        ElevatorMotor = new TalonFX(ElevatorConstants.ElevatorMotorID);

        new Thread(() -> {
        try {
            Thread.sleep(250);
            ElevatorMotor.setInverted(ElevatorConstants.ElevatorMotorInvert);
            ElevatorMotor.setPosition(ElevatorConstants.ElevatorMotorStartPosition);
        } catch (Exception e) {
            // Intended to be blank
        }
        }).start();
    }

    public void setElevatorMotorSpeeds(double speed, boolean ignoreBoundsRequirements) {
        if (checkElevatorMotorInvertsAreCorrect()) {
            if (checkElevatorMotorMovementsAreValid(speed) || ignoreBoundsRequirements) {
                ElevatorMotor.setControl(elevatorRequest.withOutput(speed * ElevatorConstants.maxElevatorMotorVoltage * ElevatorConstants.ElevatorMotorOutput));
            } else {
                brakeElevatorMotors();
            }
        } else {
            brakeElevatorMotors();
            System.out.println("[setElevatorMotorSpeeds] WARNING: Elevator motor inverts are INCORRECT!");
        }
    }

    public void brakeElevatorMotors() {
        ElevatorMotor.setControl(elevatorRequest.withOutput(0));
    }

    public double getElevatorMotorPosition() {
        var ElevatorMotorPositionSignal = ElevatorMotor.getPosition();
        double ElevatorMotorAnglePosition = ElevatorMotorPositionSignal.getValueAsDouble();
        return Units.rotationsToDegrees(ElevatorMotorAnglePosition);
    }

    public boolean checkElevatorMotorMovementsAreValid(double speed) {
        if (getElevatorMotorPosition() < ElevatorConstants.ElevatorMotorMinPosition) {
            if (speed > 0) {
                return true;
            } else {
                return false;
            }
        } else if (getElevatorMotorPosition() > ElevatorConstants.ElevatorMotorMaxPosition) {
            if (speed < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean checkElevatorMotorInvertsAreCorrect() {
        if (ElevatorMotor.getInverted() != ElevatorConstants.ElevatorMotorInvert) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Left Elevator Position: ", getLeftElevatorMotorPosition());
        // SmartDashboard.putNumber("Right Elevator Position: ", getRightElevatorMotorPosition());
        // System.out.println("Motor Inverts (L/R): " + leftElevatorMotor.getInverted() + ", " + rightElevatorMotor.getInverted());
    }
    
}
