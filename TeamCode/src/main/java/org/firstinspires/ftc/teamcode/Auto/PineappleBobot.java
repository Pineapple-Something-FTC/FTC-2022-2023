package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.sussy.PineappleSomething;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class PineappleBobot extends PineappleSomething {

    /* Declare OpMode members. */

    // Define Motor and Servo objects  (Make them private so they can't be accessed externally)

    private double lastError = 0;
    ElapsedTime timer = new ElapsedTime();
    double integralSum = 0;
    double kP = 1.5;
    double kI = 0;
    double kD = 0;


    // Define camera
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    AprilTagDetection tagOfInterest = null;
    // tagOfInterest = 2;
    // Define Drive constants.  Make them public so they CAN be used by the calling OpMode - no u

    public static final double fx = 578.272;
    public static final double fy = 578.272;
    public static final double cx = 402.145;
    public static final double cy = 221.506;
    public static final boolean right = false;
    public static final boolean left = true;
    public static final boolean forward = true;
    public static final boolean back = false;
    public static final int IN = 1;
    public static final int OUT = -1;
    public static final int NEUTRAL = 0;
    public static final int speed = 900;
    // UNITS ARE METERS
    public static final double tagSize = 0.044;// Default value: 0.166

    public static final int LEFT = 1;
    public static final int MIDDLE = 2;
    public static final int RIGHT = 3;

    // Define a constructor that allows the OpMode to pass a reference to itself.
    public PineappleBobot () {
    }

    public void init(HardwareMap hwMap)    {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        frontLeft = hwMap.get(DcMotorEx.class, "motor1");
        frontRight = hwMap.get(DcMotorEx.class, "motor3");
        backLeft = hwMap.get(DcMotorEx.class, "motor2");
        backRight = hwMap.get(DcMotorEx.class, "motor4");
        g = hwMap.get(DcMotorEx.class, "g");
        h = hwMap.get(DcMotorEx.class, "h");
        j = hwMap.get(DcMotorEx.class, "j");
        thing = hwMap.get(CRServo.class, "thing");




        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // If there are encoders connected, switch to RUN_USING_ENCODER mode for greater accuracy
        // leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Camera things
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hwMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);
        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
            }
        });

    }
    public double pidControl(double reference, double state) {
        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();
        double output = (error * kP) + (derivative * kD) + (integralSum * kI);
        return output;
    }
    public void resetDriveEncoders() {

        // Resets Encoders
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void move(int ticks, boolean forwardOrBackward, int velocity) {
        resetDriveEncoders();
        if (forwardOrBackward) {
            // Drive forwards if `forwardOrBackward` is true
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(-ticks);

            // Set mode
            setModeRunToPosition();

            // Set velocity
//            while(!(frontLeft.getCurrentPosition() > ticks - 100 && frontLeft.getCurrentPosition() < ticks + 100)) {
//                frontLeft.setVelocity(pidControl(ticks, frontLeft.getCurrentPosition()));
//                frontRight.setVelocity(pidControl(ticks, frontRight.getCurrentPosition()));
//                backLeft.setVelocity(pidControl(ticks, backLeft.getCurrentPosition()));
//                backRight.setVelocity(pidControl(ticks, backRight.getCurrentPosition()));
//            }
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        } else if (!forwardOrBackward) {
            // Drive backwards if `forwardOrBackward` is false
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(ticks);

            // Set mode
            setModeRunToPosition();

            // Set velocity
//            while(!(frontLeft.getCurrentPosition() > ticks - 200 && frontLeft.getCurrentPosition() < ticks + 200)) {
//                frontLeft.setVelocity(pidControl(ticks, frontLeft.getCurrentPosition()));
//                frontRight.setVelocity(pidControl(ticks, frontRight.getCurrentPosition()));
//                backLeft.setVelocity(pidControl(ticks, backLeft.getCurrentPosition()));
//                backRight.setVelocity(pidControl(ticks, backRight.getCurrentPosition()));
//            }
                frontLeft.setVelocity(velocity);
                frontRight.setVelocity(velocity);
                backLeft.setVelocity(velocity);
                backRight.setVelocity(velocity);

        }


    }
    public void turn(int degrees, boolean leftOrRight, int velocity) {
        resetDriveEncoders();
        if(leftOrRight) {
            // Drive left if `leftOrRight` is true
            // Set target position
            frontLeft.setTargetPosition(degrees);
            frontRight.setTargetPosition(-degrees);
            backLeft.setTargetPosition(degrees);
            backRight.setTargetPosition(-degrees);

            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        } else if (!leftOrRight) {
            // Drive right if `leftOrRight` is false
            // Set target position
            frontLeft.setTargetPosition(-degrees);
            frontRight.setTargetPosition(degrees);
            backLeft.setTargetPosition(-degrees);
            backRight.setTargetPosition(degrees);

            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        }
    }

    public void strafe(int ticks, boolean leftOrRight, int velocity) {
        resetDriveEncoders();
        if (leftOrRight) {
            // Strafe left if `leftOrRight` is true
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
            backRight.setTargetPosition(ticks);

            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        } else if (!leftOrRight) {
            // Strafe right if `leftOrRight` is false
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
            backRight.setTargetPosition(-ticks);

            // Set mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set velocity
            frontLeft.setVelocity(velocity);
            frontRight.setVelocity(velocity);
            backLeft.setVelocity(velocity);
            backRight.setVelocity(velocity);
        }
    }
    public void armThing(int ticks, int speed) {

        g.setTargetPosition(ticks);
        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        g.setVelocity(speed);
        h.setTargetPosition(ticks);
        h.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        h.setVelocity(speed);
        j.setTargetPosition(ticks);
        j.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        j.setVelocity(speed);

    }
    public void intakeThing(int state) {

        thing.setPower(state);
    }
    public void resetEncoders() {
        // Resets Encoders
        g.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        h.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        j.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void setModeNoEncoder() {
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setModeRunToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}