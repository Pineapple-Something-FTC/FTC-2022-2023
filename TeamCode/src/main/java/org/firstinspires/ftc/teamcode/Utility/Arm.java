package org.firstinspires.ftc.teamcode.Utility;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Arm extends PineappleSomething {

    // Macro for low goal using potentiometer
    public static void low() {
        double pLimit = 1.28;
        while (deeznuts.getVoltage() < pLimit) {
            g.setPower(0.5);
        }
        if (deeznuts.getVoltage() >= pLimit) {
            g.setPower(0.16);
        }
    }
    public static void executorFunc() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new intake());
    }
    public static class intake implements Runnable {
        public void run() {
            thing.setPower(1);
        }
    }
    public static class armUp implements Runnable {
        public void run() {
            g.setVelocity(500);
        }
    }
    public void armThing(int ticks, int speed, long sleep) {
        g.setTargetPosition(ticks);
        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        g.setVelocity(speed);
        h.setTargetPosition(ticks);
        h.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        h.setVelocity(speed);
        j.setTargetPosition(ticks);
        j.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        j.setVelocity(speed);
        sleep(sleep);
    }
    public void intakeThing(int state, long sleep) {
        thing.setPower(state);
        sleep(sleep);
    }

    public void runUsingEncoder() {
        g.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        h.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        j.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    //Resets only the lift encoders
    //Makes sure the macros, min, and max height restrictions work
    public void resetEncoders() {
        g.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        h.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        j.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        setTargetPosition(0);
    }
    public void setTargetPosition(int targetPosition) {
        g.setTargetPosition(targetPosition);
        h.setTargetPosition(targetPosition);
        j.setTargetPosition(targetPosition);
    }
    public void setPower(double power) {
        g.setPower(power);
        h.setPower(power);
        j.setPower(power);
    }
}