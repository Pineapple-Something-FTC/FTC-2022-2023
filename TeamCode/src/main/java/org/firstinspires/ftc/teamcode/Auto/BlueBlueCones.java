package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.teamcode.PineappleOp.thing;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class BlueBlueCones extends LinearOpMode {
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    public static boolean right = false;
    public static boolean left = true;
    public static boolean forward = true;
    public static boolean back = false;
    int UP = 1;
    int DOWN = -1;
    int IN = 1;
    int OUT = -1;
    int NEUTRAL = 0;
    final int speed = 1000;
    // UNITS ARE METERS
    double tagSize = 0.044;// Default value: 0.166

    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    AprilTagDetection tagOfInterest = null;
    public static DcMotorEx frontLeft;
    public static DcMotorEx backLeft;
    public static DcMotorEx frontRight;
    public static DcMotorEx backRight;
    public static CRServo thing;
    public static DcMotorEx g;

    public static AnalogInput deeznuts;

    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor1");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor2");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor3");
        backRight = hardwareMap.get(DcMotorEx.class, "motor4");
        g = hardwareMap.get(DcMotorEx.class, "g");
        thing = hardwareMap.get(CRServo.class, "thing");
        deeznuts = hardwareMap.get(AnalogInput.class, "deez2");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

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
        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("Potentiometer Voltage:", deeznuts.getVoltage());

            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0) {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections) {
                    //// CHANGE FROM ORIGINAL
                    //if(tag.id == ID_TAG_OF_INTEREST)
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }
            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }


        //// AUTONOMOUS CODE HERE:
        //sleep(7000);
       // int asdf = tagOfInterest.id;

//        intakeThing(IN,2000);
        resetEncoders();
        armThing(600, UP, speed, 1500);

        move(200, forward, speed,1000);
        turn(942, left, speed, 1690);
        move(69, back, speed, 300);
        strafe(1769, right, speed, 2000);
        move(150,forward,speed,1000);
        move(150, back, speed, 1000);
        //intake thing

        strafe(1000, right, speed, 2000);
        strafe(269-4-2+51, left, speed, 1000);
        turn(2069+4+2+6+9, right, speed, 3000);

        armThing(200, UP, speed, 1500);
        move(869, forward, speed, 1500);
        move(869, back, speed, 1500);
        armThing(650, UP, speed, 2000);
        turn(1542,right, speed, 2500);
        move(300, forward, speed, 1000);
        move(300, back, speed, 1000);
        turn(469, right, speed, 1000);

        if(tagOfInterest.id==LEFT) {
            move(969, forward, speed, 1000);
        }
        else if (tagOfInterest.id==RIGHT) {
            move(969, back, speed, 1000);
        }
        else {
            move(69, forward, speed, 69);
        }







//        turn(1542, left, speed, 1500);
//
//        strafe(42, left, speed, 500);
//        armThing(200, UP, speed, 1500);
//        move(869, forward, speed, 2000);
//        move(869, back, speed, 2000);
//        armThing(650, UP, speed, 2000);
//        turn(1542,right, speed, 2500);
//        move(300, forward, speed, 1000);
//        move(300, back, speed, 1000);
//        turn(1542, left, speed, 1500);



//        resetEncoders();
        //Score.executorFunc();
//        sleep(3000);
        // Score.low();

//        if (asdf == 3) {
//            strafe(1400, left, 700,2000);
//            move(1400, forward, 700,2000);
//        } else if (asdf == 2) {
//            move(1400, forward, 700,2000);
//        } else {
//            strafe(1700, right, 700,2000);
//            move(1400, forward, 700,2000);


//            thing.setPower(-1);
//            sleep(1000);
//            thing.setPower(0);
//            Move.straight(200, back, 400);
//            sleep(500);
//            Move.strafe(800, left, 700);
//            sleep(1000);
        }


    public static void resetEncoders() {
        // Resets Encoders
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    @SuppressLint("DefaultLocale")
    void tagToTelemetry(AprilTagDetection detection) {
        telemetry.addLine("\nDetected tag ID: " + detection.id);
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }

    public void move(int ticks, boolean forwardOrBackward, int velocity, int sleep) {
        resetEncoders();
        if (forwardOrBackward == true) {
            // Drive forwards if `forwardOrBackward` is true
            // Set target position
            frontLeft.setTargetPosition(-ticks);
            frontRight.setTargetPosition(-ticks);
            backLeft.setTargetPosition(-ticks);
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
        } else if (forwardOrBackward == false) {
            // Drive backwards if `forwardOrBackward` is false
            // Set target position
            frontLeft.setTargetPosition(ticks);
            frontRight.setTargetPosition(ticks);
            backLeft.setTargetPosition(ticks);
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
        }

        sleep(sleep);

    }
    public void turn(int degrees, boolean leftOrRight, int velocity, int sleep) {
        resetEncoders();
        if(leftOrRight == true) {
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
        } else if (leftOrRight == false) {
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
        sleep(sleep);
    }
    public void strafe(int ticks, boolean leftOrRight, int velocity, int sleep) {
        resetEncoders();
        if (leftOrRight == true) {
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
        } else if (leftOrRight == false) {
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
        sleep(sleep);
    }
    public void armThing(int ticks, int direction, int speed, int sleep) {

        g.setTargetPosition(direction*ticks);
        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        g.setVelocity(600);
        sleep(sleep);
    }
    public void intakeThing(int state, int sleep) {
        if(state == IN) {
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.execute(new intake());
        }
        else {
            thing.setPower(state);
        }
        sleep(sleep);
    }
}
//    public void scoreLow() {
//        resetEncoders();
//        // Set target position
//        thing.setPower(0.1);
//        sleep(1000);
//        g.setTargetPosition(280);
//        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        g.setVelocity(600);
//        sleep(1000);
//        sleep(3000);
//        thing.setPower(-0.1);
//        sleep(1000);
//
//
//
//
//    }
//    public void scoreMedium() {
//        resetEncoders();
//        // Set target position
//        thing.setPower(0.1);
//        sleep(1000);
//        g.setTargetPosition(700);
//        g.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        g.setVelocity(600);
//        sleep(1000);
//        sleep(3000);
//        thing.setPower(-0.1);
//        sleep(1000);
//    }




//class intake implements Runnable
//    {
//        public void run()
//        {
//            thing.setPower(1);
//        }
//    }

