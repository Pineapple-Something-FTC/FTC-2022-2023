package org.firstinspires.ftc.teamcode.Utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
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
    public static DcMotorEx armMotor1;
    public static DcMotorEx armMotor2;
    public static DcMotorEx armMotor3;
    // Tape Measure Motor
    public static DcMotorEx tapeMeasureMotor;
    // Intake
    public static CRServo intakeServo;
    // Potentiometer
    public static AnalogInput potentiometer;
    public static ElapsedTime runtime = new ElapsedTime();
    // Color Sensor
    public static NormalizedColorSensor leftCSensor;
    public static NormalizedColorSensor rightCSensor;

    // Map all motors, servos, sensor, etc.
    public void mapHardware() {
        //// Motors
        // Drive Motors
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        // Arm motors
        armMotor1 = hardwareMap.get(DcMotorEx.class, "g");
        armMotor2 = hardwareMap.get(DcMotorEx.class, "h");
        armMotor3 = hardwareMap.get(DcMotorEx.class, "j");
        // Etc
        tapeMeasureMotor = hardwareMap.get(DcMotorEx.class, "compliiiiant");

        //// Servos
        intakeServo = hardwareMap.get(CRServo.class, "thing");

        //// Sensors
        // Potentiometer
        potentiometer = hardwareMap.get(AnalogInput.class, "deez2");
        // Camera
        leftCSensor = hardwareMap.get(NormalizedColorSensor.class, "deez");
        rightCSensor = hardwareMap.get(NormalizedColorSensor.class, "nuts");
    }

    public void runToPosition(int velocity) {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);
    }
    public void runToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoders() {
        // Resets Encoders
        armMotor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor3.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }
    @Override
    public void runOpMode() {
    }
}