package org.firstinspires.ftc.teamcode.sussy;

public class Move extends PineappleSomething {
    public static void straight(int ticks, boolean direction, int velocity) {
//        resetEncoders();
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
    }
    public static void strafe(int ticks, boolean direction, int velocity) {
       // resetEncoders();
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
    }
    public static void turn(int ticks, boolean direction, int velocity) {
       // resetEncoders();
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
    }
}
