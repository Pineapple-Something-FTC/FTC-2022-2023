package org.firstinspires.ftc.teamcode.Utility;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drive extends PineappleSomething {
    public void straight(int ticks, boolean direction, int velocity, long sleep) {
        if (direction) {
            // Drive forwards if `direction` is true
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(-ticks);
        } else {
            // Drive backwards if `direction` is false
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(ticks);
        }
        runToPosition(velocity);
        sleep(sleep);
    }
    public void strafe(int ticks, boolean direction, int velocity, long sleep) {
        if (direction) {
            // Strafe left if `direction` is true
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(ticks);
        } else {
            // Strafe right if `direction` is false
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(-ticks);
        }
        runToPosition(velocity);
        sleep(sleep);
    }
    public void turn(int ticks, boolean direction, int velocity, long sleep) {
        if (direction) {
            // Tank turn left if `direction` is true
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(-ticks);
        } else {
            // Tank turn right if `direction` is false
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(ticks);
        }
        runToPosition(velocity);
        sleep(sleep);
    }
    public void diagonal(int ticks, boolean direction, int velocity) {
        resetDriveEncoders();
        if (direction) {
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(ticks);

            // Set mode
            runToPosition();

            // Set velocity
            frontLeft.setVelocity(0);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(0);
        } else {
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(-ticks);

            // Set mode
            runToPosition();

            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(0);
            backLeft.setVelocity(0);
            backRight.setVelocity(velocity);
        }
    }
    public void setModeNoEncoder() {
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setModeRunEncoder() {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}