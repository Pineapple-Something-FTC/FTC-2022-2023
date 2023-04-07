package org.firstinspires.ftc.teamcode.Utility;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DrivePID extends PineappleSomething {
    private final double lastError = 0;
    ElapsedTime timer = new ElapsedTime();
    ElapsedTime maxTimer = new ElapsedTime();
    ElapsedTime timerD = new ElapsedTime();
    double lastErrorL;
    double lastErrorR;
    double lastErrorFR;
    double lastErrorFL;
    double lastErrorBR;
    double lastErrorBL;
    public static final int RED = 1;
    public static final int BLUE = 0;
    public void straight(double distance, boolean forwardOrBack, double customKP, double range) {
        ElapsedTime timer = new ElapsedTime();
        double kD = customKP / 95;
        double kP = customKP; // 10

        double encoderCounts;

        Drive.resetDriveEncoders();

        timer.reset();
        timerD.reset();
        if (forwardOrBack) {
            while (timer.seconds() < 0.25) {
                encoderCounts = 0.25 * (
                        frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() +
                        backLeft.getCurrentPosition() + backRight.getCurrentPosition()
                );

                double errorFR = distance + frontRight.getCurrentPosition();
                double errorFL = distance + frontLeft.getCurrentPosition();
                double errorBR = distance + backRight.getCurrentPosition();
                double errorBL = distance + backLeft.getCurrentPosition();

                double derivativeFR = (errorFR + lastErrorR) / timerD.seconds();
                double derivativeFL = (errorFL + lastErrorL) / timerD.seconds();
                double derivativeBR = (errorBR + lastErrorR) / timerD.seconds();
                double derivativeBL = (errorBL + lastErrorL) / timerD.seconds();

                lastErrorFR = errorFR;
                lastErrorFL = errorFL;
                lastErrorBR = errorBR;
                lastErrorBL = errorBL;

                if (Math.abs(distance + encoderCounts) > range) { // 5
                    timer.reset();
                }

                timerD.reset();
                frontLeft.setVelocity(-(((errorFL) * kP - derivativeFL * kD)));
                frontRight.setVelocity(-(((errorFR) * kP - derivativeFR * kD)));
                backLeft.setVelocity(-(((errorBL) * kP - derivativeBL * kD)));
                backRight.setVelocity(-(((errorBR) * kP - derivativeBR * kD)));
            }
        } else {
            while (timer.seconds() < 0.25) {
                encoderCounts = 0.25 * (frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() + backLeft.getCurrentPosition() + backRight.getCurrentPosition());

                double errorFR = distance - frontRight.getCurrentPosition();
                double errorFL = distance - frontLeft.getCurrentPosition();
                double errorBR = distance - backRight.getCurrentPosition();
                double errorBL = distance - backLeft.getCurrentPosition();

                double derivativeFR = (errorFR + lastErrorR) / timerD.seconds();
                double derivativeFL = (errorFL + lastErrorL) / timerD.seconds();
                double derivativeBR = (errorBR + lastErrorR) / timerD.seconds();
                double derivativeBL = (errorBL + lastErrorL) / timerD.seconds();

                lastErrorFR = errorFR;
                lastErrorFL = errorFL;
                lastErrorBR = errorBR;
                lastErrorBL = errorBL;

                if (Math.abs(distance - encoderCounts) > range) {
                    timer.reset();
                }
                timerD.reset();
                frontLeft.setVelocity((((errorFL) * kP - derivativeFL * kD)));
                frontRight.setVelocity((((errorFR) * kP - derivativeFR * kD)));
                backLeft.setVelocity((((errorBL) * kP - derivativeBL * kD)));
                backRight.setVelocity((((errorBR) * kP - derivativeBR * kD)));
            }
        }
        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }
    public void turn(double inputDegrees, boolean rightOrLeft) {
        double ticksPerDegree = 21539.0 / 1800.0;
        double degrees = inputDegrees * ticksPerDegree;
        ElapsedTime timer = new ElapsedTime();
        double kD = 0.0; // 0.05
        double kP = 7.5;

        double encoderCounts;
        Drive.resetDriveEncoders();

        timer.reset();
        timerD.reset();
        if (rightOrLeft) { //left
            while (timer.seconds() < 0.25) {
                encoderCounts = 0.25 * (frontLeft.getCurrentPosition() - frontRight.getCurrentPosition() + backLeft.getCurrentPosition() - backRight.getCurrentPosition());

                double errorFR = degrees + frontRight.getCurrentPosition();
                double errorFL = degrees - frontLeft.getCurrentPosition();
                double errorBR = degrees + backRight.getCurrentPosition();
                double errorBL = degrees - backLeft.getCurrentPosition();

                double derivativeFR = (errorFR + lastErrorR) / timerD.seconds();
                double derivativeFL = (errorFL - lastErrorL) / timerD.seconds();
                double derivativeBR = (errorBR + lastErrorR) / timerD.seconds();
                double derivativeBL = (errorBL - lastErrorL) / timerD.seconds();

                lastErrorFR = errorFR;
                lastErrorFL = errorFL;
                lastErrorBR = errorBR;
                lastErrorBL = errorBL;

                if (Math.abs(degrees - encoderCounts) > 5) {
                    timer.reset();
                }
                timerD.reset();
                frontLeft.setVelocity((((errorFL) * kP - derivativeFL * kD)));
                frontRight.setVelocity(-(((errorFR) * kP - derivativeFR * kD)));
                backLeft.setVelocity((((errorBL) * kP - derivativeBL * kD)));
                backRight.setVelocity(-(((errorBR) * kP - derivativeBR * kD)));
            }
            frontLeft.setVelocity(0);
            frontRight.setVelocity(0);
            backLeft.setVelocity(0);
            backRight.setVelocity(0);
        } else { //right
            while (timer.seconds() < 0.25) {
                encoderCounts = 0.25 * (
                        frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() +
                        backLeft.getCurrentPosition() + backRight.getCurrentPosition()
                );
                double errorFR = degrees - frontRight.getCurrentPosition();
                double errorFL = degrees + frontLeft.getCurrentPosition();
                double errorBR = degrees - backRight.getCurrentPosition();
                double errorBL = degrees + backLeft.getCurrentPosition();

                double derivativeFR = (errorFR - lastErrorR) / timerD.seconds();
                double derivativeFL = (errorFL + lastErrorL) / timerD.seconds();
                double derivativeBR = (errorBR - lastErrorR) / timerD.seconds();
                double derivativeBL = (errorBL + lastErrorL) / timerD.seconds();

                lastErrorFR = errorFR;
                lastErrorFL = errorFL;
                lastErrorBR = errorBR;
                lastErrorBL = errorBL;

                if (Math.abs(degrees - encoderCounts) > 5) {
                    timer.reset();
                }
                timerD.reset();
                frontLeft.setVelocity(-(((errorFL) * kP - derivativeFL * kD)));
                frontRight.setVelocity((((errorFR) * kP - derivativeFR * kD)));
                backLeft.setVelocity(-(((errorBL) * kP - derivativeBL * kD)));
                backRight.setVelocity((((errorBR) * kP - derivativeBR * kD)));
            }
            Drive.setVelocity(0);
        }
    }

    // change this if necessary
    public void followLine(double redOrBlue, double maxDistance) {
        double kD = 71;
        float kPL = 2100;
        float kPR = 2100;
        double encoderCounts = 0;
        Drive.resetDriveEncoders();
        leftCSensor.setGain(20);
        rightCSensor.setGain(20);

        NormalizedRGBA colorsL;
        NormalizedRGBA colorsR;

        double blueRight = 0.155;
        double blueLeft = 0.131;
        double redRight = 0.133; //0.14
        double redLeft = 0.180; //0.23
        timerD.reset();
        if (redOrBlue == BLUE) {
            while (encoderCounts >= maxDistance) {
                encoderCounts = 0.25 * (frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() + backLeft.getCurrentPosition() + backRight.getCurrentPosition());
                colorsL = leftCSensor.getNormalizedColors();
                colorsR = rightCSensor.getNormalizedColors();
                double errorR = blueRight - colorsR.blue;

                double errorL = blueLeft - colorsL.blue;

                double derivativeR = (errorR - lastErrorR) / timerD.seconds();
                double derivativeL = (errorL - lastErrorL) / timerD.seconds();

                lastErrorL = errorL;
                lastErrorR = errorR;
                timerD.reset();
                frontLeft.setVelocity(-(420 - ((errorR) * kPR + derivativeR * kD) + ((errorL) * kPL + derivativeL * kD)));
                frontRight.setVelocity(-(420 + ((errorR) * kPR + derivativeR * kD) - ((errorL) * kPL + derivativeL * kD)));
            }
        } else if (redOrBlue == RED) {
            while (encoderCounts >= maxDistance) {
                encoderCounts = 0.25 * (frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() + backLeft.getCurrentPosition() + backRight.getCurrentPosition());
                colorsL = leftCSensor.getNormalizedColors();
                colorsR = rightCSensor.getNormalizedColors();
                double errorR = redRight - colorsR.red;
                double errorL = redLeft - colorsL.red;

                double derivativeR = (errorR - lastErrorR) / timerD.seconds();
                double derivativeL = (errorL - lastErrorL) / timerD.seconds();

                lastErrorL = errorL;
                lastErrorR = errorR;
                timerD.reset();
                frontLeft.setVelocity(-(420 - ((errorR) * kPR + derivativeR * kD) + ((errorL) * kPL + derivativeL * kD)));
                frontRight.setVelocity(-(420 + ((errorR) * kPR + derivativeR * kD) - ((errorL) * kPL + derivativeL * kD)));
            }
        }
    }
}
