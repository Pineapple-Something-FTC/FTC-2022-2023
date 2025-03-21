package org.firstinspires.ftc.teamcode.Utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class PineappleSomething extends LinearOpMode {
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

    /// Map all motors, servos, sensor, etc. to Java objects, so we can use them.
    /// > The value for `deviceName` is defined on the Driver Hub
    public void mapHardware() {
        // MOTORS
        // Drive
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        // Arm
        armMotor1 = hardwareMap.get(DcMotorEx.class, "g");
        armMotor2 = hardwareMap.get(DcMotorEx.class, "h");
        armMotor3 = hardwareMap.get(DcMotorEx.class, "j");
        // Etc
        tapeMeasureMotor = hardwareMap.get(DcMotorEx.class, "compliiiiant");

        // SERVOS
        intakeServo = hardwareMap.get(CRServo.class, "thing");

        // SENSORS
        // Potentiometer
        potentiometer = hardwareMap.get(AnalogInput.class, "deez2");
        // Camera
        leftCSensor = hardwareMap.get(NormalizedColorSensor.class, "deez");
        rightCSensor = hardwareMap.get(NormalizedColorSensor.class, "nuts");
    }

    /// Run to desired position
    public void runToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /// Run to desired position and set all motors to desired velocity
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

    /// Stop and reset the encoders for all motors.
    public void stopAndResetEncoders() {
        // Resets Encoders
        armMotor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor3.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }
}