package org.firstinspires.ftc.teamcode.Utility;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Arm extends PineappleSomething {

    // Macro for low goal using potentiometer
    public static void low() {
        double pLimit = 1.28;
        while (potentiometer.getVoltage() < pLimit) {
            armMotor1.setPower(0.5);
        }
        if (potentiometer.getVoltage() >= pLimit) {
            armMotor1.setPower(0.16);
        }
    }
    public static void executorFunc() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Intake());
    }
    public static class Intake implements Runnable {
        public void run() {
            intakeServo.setPower(1);
        }
    }
    public static class ArmUp implements Runnable {
        public void run() {
            armMotor1.setVelocity(500);
        }
    }
    public void armThing(int ticks, int speed, long sleep) {
        armMotor1.setTargetPosition(ticks);
        armMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor1.setVelocity(speed);
        armMotor2.setTargetPosition(ticks);
        armMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor2.setVelocity(speed);
        armMotor3.setTargetPosition(ticks);
        armMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor3.setVelocity(speed);
        sleep(sleep);
    }
    public void intakeThing(int state, long sleep) {
        intakeServo.setPower(state);
        sleep(sleep);
    }

    public void runUsingEncoder() {
        armMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    //Resets only the lift encoders
    //Makes sure the macros, min, and max height restrictions work
    public void resetEncoders() {
        armMotor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor3.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        setTargetPosition(0);
    }
    public void setTargetPosition(int targetPosition) {
        armMotor1.setTargetPosition(targetPosition);
        armMotor2.setTargetPosition(targetPosition);
        armMotor3.setTargetPosition(targetPosition);
    }
    public void setPower(double power) {
        armMotor1.setPower(power);
        armMotor2.setPower(power);
        armMotor3.setPower(power);
    }
}