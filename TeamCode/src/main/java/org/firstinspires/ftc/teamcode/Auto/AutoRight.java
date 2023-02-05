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

//        while(opModeIsActive()) {
//            telemetry.addData("motor:", bobot.frontLeft.getCurrentPosition());
//            telemetry.addData("motor:", bobot.frontRight.getCurrentPosition());
//            telemetry.addData("motor:", bobot.backLeft.getCurrentPosition());
//            telemetry.addData("motor:", bobot.backRight.getCurrentPosition());
//            telemetry.update();
//
//
//        }


//          bobot.resetEncoders();
//          turn(1032, bobot.left, 6900, 5000);
//          turn(1032, bobot.right, 6900, 20000);
        bobot.resetEncoders();

//        intakeThing(bobot.IN, 969);
//        armThing(-2636, 690+69+42+69+69+69+69+69, 100);

       // move(2284+250, bobot.forward, 2000,2269);
       // move(420, bobot.forward, 2000,20000); p

        turn((1)*(4200-690-69-4-9+69+20-6-4-2+2+69+420+42), bobot.right, 2000,20000);

//        move(250, bobot.back, 2000, 369);
//
//        turn(969/2 + 4 + 2 + 6+4+6+9+6+9+6+9, bobot.left, bobot.speed, 69);
//        move(269-1+9, bobot.forward,bobot.speed,690);
//        intakeThing(bobot.OUT, 100);
//        intakeThing(bobot.NEUTRAL, 50);
//        move(269-1+9-42-6, bobot.back, bobot.speed, 690);
//
//        armThing(-(569+69+4+6+2+6+9), 690, 250);
//
//        turn(969/2+969+4+2+6+9+2+9+6+4+2+2+4+2+6+9+3+5+6+9+2+1+6+9+9+6, bobot.right, bobot.speed, 69);
//
//        intakeThing(bobot.IN, 150);
//        move(1369, bobot.forward, 1200, 1300);
//        armThing(-269+42-6-9, 750, 696+42);
//
//        armThing(-2630, 1690, 640);
//        move(2169, bobot.back, 2069, 2490);
//
//        turn((420+69+20), bobot.left, 2000, 690);
//        move(269, bobot.forward, bobot.speed, 420);
//
//        intakeThing(bobot.OUT, 150);
//        intakeThing(bobot.NEUTRAL, 50);
//        move(269, bobot.back, 2000, 420);
//
//        armThing(-(569+69+4+2+6+9), 690+69, 10);
//        turn(420+69+6+9+6+9+4+1, bobot.right, 2000, 690);
//        intakeThing(bobot.IN, 150);
//        move(2169+69+6+9+69+42+69, bobot.forward, 1142, 1690);
//
//        armThing(-269, 500, 1690);
//
//
//
//        if(bobot.tagOfInterest.id==bobot.LEFT) {
//            armThing(-1869+42, 1690, 690);
//            move(2169, bobot.back, 2000, 2000);
//            turn(420+69+69+4+2+6+9, bobot.right, 2000, 690);
//            move(420, bobot.forward, bobot.speed, 690);
//            intakeThing(bobot.OUT, 150);
//            move(369, bobot.back, bobot.speed, 550);
//            armThing(-1, 2000, 10);
//            turn(420+69+69+4+2+6+9, bobot.left, 2000, 690);
//            move(200, bobot.back, 2000, 420);
//
//        }
//        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
//            armThing(-1069, 1690, 690);
//            move(242, bobot.back, 2000,690);
//            turn(969/2+969+4+2+6+9+9, bobot.right, bobot.speed, 2000);
//            move(420, bobot.forward, bobot.speed, 690);
//            intakeThing(bobot.OUT, 150);
//            move(369, bobot.back, bobot.speed, 550);
//            armThing(-1, 2000, 10);
//            turn(469+4+2+8, bobot.left, 2000, 2000);
//
//
//        }
//        else {
//            armThing(-1069, 1690, 690);
//            move(1169, bobot.back, 2000, 1069);
//            strafe(690, bobot.right, 2000, 969);
//            move(369, bobot.forward, bobot.speed, 420);
//            intakeThing(bobot.OUT, 150);
//            move(242, bobot.back, bobot.speed, 469);
//            armThing(-1, 2000, 2000);
//
//
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
