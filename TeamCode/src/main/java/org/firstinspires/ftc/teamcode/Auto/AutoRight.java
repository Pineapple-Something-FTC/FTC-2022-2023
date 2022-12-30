package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.PineappleOp.thing;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;




import java.util.ArrayList;

@Autonomous
public class AutoRight extends LinearOpMode {
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
                    if(tag.id == bobot.LEFT || tag.id == bobot.MIDDLE || tag.id == bobot.RIGHT) {
                        bobot.tagOfInterest = tag;
                        tagFound = true;
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

        bobot.move(2000, bobot.forward);

        /*
        a

        bobot.resetEncoders();

        intakeThing(bobot.IN, 850);
        armThing(842, bobot.UP, bobot.speed, 1100);


        move(200, bobot.forward, 1169,500);
        turn(1050, bobot.right, bobot.speed, 1670);
        move(15, bobot.forward, 1169, 120);
        strafe(1774, bobot.left, bobot.speed, 2000);
       // move(240, bobot.forward,1169,400);
        armThing(1120, bobot.UP, bobot.speed, 590);
        intakeThing(bobot.OUT, 200);
        intakeThing(bobot.NEUTRAL, 50);
        armThing(269, bobot.UP, bobot.speed, 700);
        //move(209, bobot.back, 1169, 430);

        strafe(959, bobot.left, bobot.speed, 1520);
        strafe(267, bobot.right, bobot.speed, 869);
       // turn(2069+4+2+6+9+3, bobot.right, bobot.speed, 2900);

        armThing(269+3+1, bobot.UP, bobot.speed, 1480);
        move(969+6+9+9+6+9, bobot.forward, 1169, 1369);
        armThing(175, bobot.UP, bobot.speed, 300);
        intakeThing(bobot.IN, 800);
        armThing(369, bobot.UP, bobot.speed, 1300);
        move(969, bobot.back, 1169, 1350);


        a
       */

















//        armThing(842, bobot.UP, bobot.speed, 1150);
//        turn(1542, bobot.right, bobot.speed, 2150);
//        move(420, bobot.forward, 1169, 750);
//        armThing(742, bobot.UP, bobot.speed, 300);
//        intakeThing(bobot.OUT, 300);
//        intakeThing(bobot.NEUTRAL, 10);
//        armThing(842, bobot.UP, bobot.speed, 400);
//        move(420, bobot.back, 1409, 600);
//        turn(469+2+2, bobot.right, bobot.speed, 800);
//
//        if(bobot.tagOfInterest.id==bobot.LEFT) {
//            move(942, bobot.forward, bobot.speed, 1000);
//        }
//        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
//            move(942, bobot.back, bobot.speed, 1000);
//        }
//        else {
//            move(69, bobot.forward, bobot.speed, 69);
//        }





//        bobot.resetEncoders();
//
//        intakeThing(bobot.IN, 850);
//        armThing(842, bobot.UP, bobot.speed, 1100);
//
//
//        move(200, bobot.forward, 1169,500);
//        turn(950, bobot.left, bobot.speed, 1670);
//        move(15, bobot.back, 1169, 120);
//        strafe(1774, bobot.right, bobot.speed, 2000);
//        move(240, bobot.forward,1169,400);
//        armThing(742, bobot.UP, bobot.speed, 590);
//        intakeThing(bobot.OUT, 200);
//        intakeThing(bobot.NEUTRAL, 50);
//        armThing(842, bobot.UP, bobot.speed, 700);
//        move(209, bobot.back, 1169, 430);
//
//        strafe(959, bobot.right, bobot.speed, 1520);
//        strafe(267, bobot.left, bobot.speed, 869);
//        turn(2069+4+2+6+9+3, bobot.right, bobot.speed, 2900);
//
//        armThing(269+3+1, bobot.UP, bobot.speed, 1480);
//        move(969+6+9+9+6+9, bobot.forward, 1169, 1369);
//        armThing(175, bobot.UP, bobot.speed, 300);
//        intakeThing(bobot.IN, 800);
//        armThing(369, bobot.UP, bobot.speed, 1300);
//        move(969, bobot.back, 1169, 1350);
//        armThing(842, bobot.UP, bobot.speed, 1150);
//        turn(1542, bobot.right, bobot.speed, 2150);
//        move(420, bobot.forward, 1169, 750);
//        armThing(742, bobot.UP, bobot.speed, 300);
//        intakeThing(bobot.OUT, 300);
//        intakeThing(bobot.NEUTRAL, 10);
//        armThing(842, bobot.UP, bobot.speed, 400);
//        move(420, bobot.back, 1409, 600);
//        turn(469+2+2, bobot.right, bobot.speed, 800);
//
//        if(bobot.tagOfInterest.id==bobot.LEFT) {
//            move(942, bobot.forward, bobot.speed, 1000);
//        }
//        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
//            move(942, bobot.back, bobot.speed, 1000);
//        }
//        else {
//            move(69, bobot.forward, bobot.speed, 69);
//        }

    }


//    public void move(int ticks, boolean forwardOrBackward, int sleep) {
//        bobot.move(ticks, forwardOrBackward);
//        sleep(sleep);
//    }
    public void turn(int degrees, boolean leftOrRight, int velocity, int sleep) {
        bobot.turn(degrees, leftOrRight, velocity);
        sleep(sleep);
    }
    public void strafe(int ticks, boolean leftOrRight, int velocity, int sleep) {
        bobot.strafe(ticks, leftOrRight, velocity);
        sleep(sleep);
    }
    public void armThing(int ticks, int direction, int speed, int sleep) {
        bobot.armThing(ticks, direction, speed);
        sleep(sleep);
    }
    public void intakeThing(int state, int sleep) {
        bobot.intakeThing(state);
        sleep(sleep);
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

