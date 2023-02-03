package org.firstinspires.ftc.teamcode.sussy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PineappleSomething extends LinearOpMode {
    public static final boolean left = true;
    public static final boolean right = false;
    public static final boolean forward = true;
    public static final boolean back = false;
    public static final int speed = 700;
    // Drive motors
    public static DcMotorEx frontLeft;
    public static DcMotorEx backLeft;
    public static DcMotorEx frontRight;
    public static DcMotorEx backRight;
    // Arm motors
    public static DcMotorEx g;
    public static DcMotorEx h;
    public static DcMotorEx j;
    // Tape Measure Motor
    public static DcMotorEx shutUpNathan;
    // Intake
    public static CRServo thing;
    // Potentiometer
    public static AnalogInput deeznuts;
    public static ElapsedTime runtime = new ElapsedTime();

    public static void runToPosition(int velocity) {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);
    }

    public void resetEncoders() {
        // Resets Encoders
        g.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        h.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        j.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void resetDriveEncoders() {
        // Resets Drive Encoders
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void mapHardwareAndReverseMotors() {
        deeznuts = hardwareMap.get(AnalogInput.class, "deez2");
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        shutUpNathan = hardwareMap.get(DcMotorEx.class, "compliiiiant");
        g = hardwareMap.get(DcMotorEx.class, "g");
        h = hardwareMap.get(DcMotorEx.class, "h");
        j = hardwareMap.get(DcMotorEx.class, "j");
        thing = hardwareMap.get(CRServo.class, "thing");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void runOpMode() {
    }
}
