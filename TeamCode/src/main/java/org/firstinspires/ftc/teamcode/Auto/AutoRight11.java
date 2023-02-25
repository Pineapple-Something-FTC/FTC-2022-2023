package org.firstinspires.ftc.teamcode.Auto;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Autonomous
public class AutoRight11 extends LinearOpMode {
    public static final double FEET_PER_METER = 3.28084;


//    OpenCvCamera camera;
//    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    // static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
//    double fx = 578.272;
//    double fy = 578.272;
//    double cx = 402.145;
//    double cy = 221.506;
//    public static boolean right = false;
//    public static boolean left = true;
//    public static boolean forward = true;
//    public static boolean back = false;
//    int UP = 1;
//    int DOWN = -1;
//    int IN = 1;
//    int OUT = -1;
//    int NEUTRAL = 0;
//    final int speed = 1000;
//    // UNITS ARE METERS
//    double tagSize = 0.044;// Default value: 0.166
//
//    int LEFT = 1;
//    int MIDDLE = 2;
//    int RIGHT = 3;

//    AprilTagDetection tagOfInterest = null;
//    public static DcMotorEx frontLeft;
//    public static DcMotorEx backLeft;
//    public static DcMotorEx frontRight;
//    public static DcMotorEx backRight;
//    public static CRServo thing;
//    public static DcMotorEx g;

    // public static AnalogInput deeznuts;
//   private DcMotorEx frontLeft   = null;
//    private DcMotorEx frontRight  = null;
//    private DcMotorEx backLeft = null;
//    private DcMotorEx backRight = null;
//    private DcMotorEx g = null;
//    private CRServo thing = null;
//    OpenCvCamera camera;
//    AprilTagDetectionPipeline aprilTagDetectionPipeline;
//    AprilTagDetection tagOfInterest = null;

    PineappleBobot bobot = new PineappleBobot();

    @Override
    public void runOpMode() {

        bobot.init(hardwareMap);
        NormalizedRGBA colorsL = bobot.leftCSensor.getNormalizedColors();
        NormalizedRGBA colorsR = bobot.rightCSensor.getNormalizedColors();



//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        // aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);
//        frontLeft = robot.frontLeft;
//        backLeft = robot.backLeft;
//        backRight = robot.backRight;
//        frontRight = robot.frontRight;
//        g = robot.g;
//        thing = robot.thing;
//        camera = robot.camera;
        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {

            ArrayList<AprilTagDetection> currentDetections = bobot.aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0) {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections) {
                    //// CHANGE FROM ORIGINAL
                    //if(tag.id == ID_TAG_OF_INTEREST)
                    if (tag.id == bobot.LEFT || tag.id == bobot.MIDDLE || tag.id == bobot.RIGHT) {
                        bobot.tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                    else {
                        tag.id = bobot.MIDDLE;
                        bobot.tagOfInterest = tag;
                        telemetry.addData("Don't see the tag, default is set to center, also sussy balls 69 afaf asoajfoia sLOL HeheheheHAW", " hi");
                        break;
                    }

                }

                if(tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(bobot.tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(bobot.tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(bobot.tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if(bobot.tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(bobot.tagOfInterest);
                }
            }
            telemetry.addData("L BOZO: ", bobot.frontLeft.getCurrentPosition());

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(bobot.tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(bobot.tagOfInterest);
            telemetry.update();
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }


        // AUTONOMOUS CODE HERE:

// the numbers, the camera mount, better holding for electronics
// code for scoring in front


        followLine(bobot.BLUE, colorsL, colorsR, 10);


//        bobot.resetEncoders();
//
//        intakeThing(bobot.IN, 1200);
//        armThing(-2350, 690+69+42+69+69+69+69+69, 100);
//        move(2284+300, bobot.forward, 1500,2969);
//        move(300, bobot.back, 2000, 690);
//        turn(969/2 + 4 + 2 + 6+4+6+9+6+9, bobot.left, 500, 2000);
//        move(365, bobot.forward,bobot.speed,690);
//        intakeThing(bobot.OUT, 500);
//        intakeThing(bobot.NEUTRAL, 50);
//        move(436-4, bobot.back,bobot.speed,690);
//        armThing(-(569+69+4+6+2+6+9), 690+69+42, 250);
//        turn((969/2+969+4+2+6+9+2+9+6+4+2+2+4+2+6+9+3+6+9+19+4+2+1), bobot.right, 769, 3690);
//
//        intakeThing(bobot.IN, 150);
//        move(1242, bobot.forward, 869, 1690+42);
//      //  move(200, bobot.forward, 690, 300);
//        armThing(-269, 750, 1200);
//        armThing(-769, 969, 742);
//
//        move(2169, bobot.back, 769, 3690);
//        armThing(-2350, 1690, 569);
//        turn((420+69+20+6+19+21+19+9), bobot.left, 2000, 690);
//        move(348, bobot.forward, bobot.speed, 542);
//
//        intakeThing(bobot.OUT, 500);
//        intakeThing(bobot.NEUTRAL, 50);
//        move(400, bobot.back, 2000, 469);
//        armThing(-(1), 969, 10);
//        turn(420+69+69, bobot.right, 569, 2690);
//
//
//
//
//        if(bobot.tagOfInterest.id==bobot.MIDDLE) {
//
//            move(969, bobot.forward, 2000, 6690);
//
//        }
//        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
//            move(2169, bobot.forward, 2000, 6690);
//
//        }
//        else {
//            move(100-4-2-6-9-6, bobot.back, 2000, 6690);
//        }

    }


    public void move(int ticks, boolean forwardOrBackward, int velocity, int sleep) {
        bobot.move(ticks, forwardOrBackward, velocity);
        sleep(sleep);
    }
    public void turn(int degrees, boolean leftOrRight, int velocity, int sleep) {
        bobot.turn(degrees, leftOrRight, velocity);
        sleep(sleep);
    }
    public void strafe(int ticks, boolean leftOrRight, int velocity, int sleep) {
        bobot.strafe(ticks, leftOrRight, velocity);
        sleep(sleep);
    }
    public void armThing(int ticks, int speed, int sleep) {
        bobot.armThing(ticks, speed);
        sleep(sleep);
    }
    public void intakeThing(int state, int sleep) {
        bobot.intakeThing(state);
        sleep(sleep);
    }
    public void ttuurrnn(double velocity) {
        bobot.ttuurrnn(velocity);
    }
    public void followLine(double redOrBlue, NormalizedRGBA colorsL, NormalizedRGBA colorsR, double maxTime) {
        bobot.followLine(redOrBlue, colorsL, colorsR, maxTime);
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


}