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

        bobot.resetEncoders();

        intakeThing(bobot.IN, 969);
        armThing(-2636, 690+69+42+69+69+69+69+69, 100);
        move(2284+250, bobot.forward, 2000,2269);
        move(250, bobot.back, 2000, 369);
        strafe(642, bobot.left, 1500, 969);

      //  turn(969/2 + 4 + 2 + 6+4+6+9+6+9+6+9, bobot.left, bobot.speed, 1242);
        move(269-1, bobot.forward,bobot.speed,690);
        intakeThing(bobot.OUT, 100);
        intakeThing(bobot.NEUTRAL, 50);
        move(269-1, bobot.back, bobot.speed, 690);
        strafe(642, bobot.right, 1000, 969);
        armThing(-(569+69+4+6+2+6+9), 690+69+42, 250);
        turn(969+6+9+6, bobot.right, 969, 1690);
      //  turn(969/2+969+4+2+6+9+2+9+6+4+2+2+4+2+6+9+3+5+6+9+2+1, bobot.right, bobot.speed, 2000);

        intakeThing(bobot.IN, 150);
        move(1369, bobot.forward, 1200, 1269);
        armThing(-269+42-6-9, 750, 696+42);

        armThing(-2630, 1690, 640);
        move(2169+42, bobot.back, 2069, 2490);
        strafe(642,bobot.left,1000,969);
//        turn((420+69+20), bobot.left, 2000, 690);
        move(269, bobot.forward, bobot.speed, 420);
        move(69, bobot.back, bobot.speed, 269);
        intakeThing(bobot.OUT, 150);
        intakeThing(bobot.NEUTRAL, 50);
        move(269, bobot.back, 2000, 420);
        strafe(642, bobot.right, 1000, 969);
        armThing(-(569+69+4+2+6+9), 690+69, 10);
      //  turn(420+69+6+9+6+9+4+1+22+6+5, bobot.right, 2000, 690);
        intakeThing(bobot.IN, 150);
        move(2169+69+6+9+69+42+69, bobot.forward, 1142, 1690);

        armThing(-269, 500, 1690);



        if(bobot.tagOfInterest.id==bobot.LEFT) {
            armThing(-1869+42, 1690, 690);
            move(2169, bobot.back, 2000, 2000);
            turn(420+69+69+4+2+6+9, bobot.right, 2000, 690);
            move(420, bobot.forward, bobot.speed, 690);
            intakeThing(bobot.OUT, 150);
            move(369, bobot.back, bobot.speed, 550);
            armThing(-1, 2000, 10);
            turn(420+69+69+4+2+6+9, bobot.left, 2000, 690);
            move(200, bobot.back, 2000, 420);

        }
        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
            armThing(-1069, 1690, 690);
            move(242, bobot.back, 2000,690);
            turn(969/2+969+4+2+6+9+9, bobot.right, bobot.speed, 2000);
            move(420, bobot.forward, bobot.speed, 690);
            intakeThing(bobot.OUT, 150);
            move(369, bobot.back, bobot.speed, 550);
            armThing(-1, 2000, 10);
            turn(469+4+2+8, bobot.left, 2000, 2000);
//            bobot.resetDriveEncoders();
//            bobot.frontLeft.setTargetPosition(1690);
//            bobot.frontRight.setTargetPosition(-1690);
//
//            // Set mode
//            bobot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            bobot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // Set velocity
//            bobot.frontLeft.setVelocity(2000);
//            bobot.frontRight.setVelocity(2000);

        }
        else {
            armThing(-1069, 1690, 690);
            move(1169, bobot.back, 2000, 1069);
            strafe(690, bobot.right, 2000, 969);
            move(369, bobot.forward, bobot.speed, 420);
            intakeThing(bobot.OUT, 150);
            move(242, bobot.back, bobot.speed, 469);
            armThing(-1, 2000, 2000);


        }

//        bobot.resetEncoders();
//
//        intakeThing(bobot.IN, 800);
//        armThing(-2636, 690+69+42, 100);
//        move(2284+250, bobot.forward, 2000,2469);
//        move(250, bobot.back, 1000, 469);
//        turn(969/2 + 4 + 2 + 6+4, bobot.left, bobot.speed, 1500);
//        move(342, bobot.forward,bobot.speed,1000);
//        intakeThing(bobot.OUT, 100);
//        intakeThing(bobot.NEUTRAL, 50);
//        move(442 , bobot.back,bobot.speed,420);
//        armThing(-(569+69+4+6+2+6), 690+69+42, 250);
//        turn(969/2+969+4+2+6+9+2+9+6+4+2+2+4+2, bobot.right, bobot.speed, 2000);
//
//        intakeThing(bobot.IN, 150);
//        move(1120, bobot.forward, 1200, 1000);
//        armThing(-269, 750, 1200);
//
//        armThing(-2630, 1000, 420);
//        move(1069, bobot.back, 2000, 1000);
//        turn((969/2+969+4+2+6+9+6+42+42+21+6+9), bobot.left, bobot.speed, 2900);
//        move(569+2, bobot.forward, bobot.speed, 869);
//        move(69+42, bobot.back, bobot.speed, 300);
//        sleep(250);
//        intakeThing(bobot.OUT, 150);
//        intakeThing(bobot.NEUTRAL, 50);
//        move(542-69-42, bobot.back, bobot.speed, 869);
//        armThing(-(569+69+4+2+6), 690+69, 250);
//        turn(969/2+969+4+2+6+9+2+9+6+2+4+42+6+9+2+6+9+4+2, bobot.right, bobot.speed, 2000);
//        intakeThing(bobot.IN, 150);
//        move(1100, bobot.forward, 1200, 1069);
//
//        armThing(-269, 750, 690);
//
//        armThing(-1069, 1690, 690);
//        intakeThing(bobot.OUT, 150);
//        armThing(-6, 769, 10);
//
//
//        if(bobot.tagOfInterest.id==bobot.LEFT) {
//            move(2169, bobot.back, 2000, 2000);
//        }
//        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
//            move(69, bobot.back, 2000, 2000);
//        }
//        else {
//            move(1169, bobot.back, 2000, 2000);
//        }





        /*
        if(bobot.tagOfInterest.id==bobot.LEFT) {
            armThing(-1669-4, 569, 969);
            move(1690, bobot.back, bobot.speed, 1000);
            turn(420+69+69, bobot.right, bobot.speed, 969);
            move(569, bobot.forward, bobot.speed, 690);
            intakeThing(bobot.OUT, 100);
            intakeThing(bobot.NEUTRAL, 69);
            move(420, bobot.back, bobot.speed, 469);

        }
        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
            armThing(-690, 569, 969);
        }
        else {
            armThing(-1669-4, 569, 969);
            move(1069, bobot.back, 2000, 1000);
            turn(969/2+969+4+2+6+9+2+9+6+4+2+2+4+2, bobot.right, bobot.speed, );
            move(569, bobot.forward, bobot.speed, 690);
            intakeThing(bobot.OUT, 100);
            intakeThing(bobot.NEUTRAL, 69);
            move(420, bobot.back, bobot.speed, 469);
        }

        */

        /*
        move(1069, bobot.back, 2000, 1000);
        turn(420+69+69, bobot.right, bobot.speed, 969);
        move(569, bobot.forward, bobot.speed, 690);
        intakeThing(bobot.OUT, 100);
        intakeThing(bobot.NEUTRAL, 69);
        move(420, bobot.back, bobot.speed, 469);
        //armThing(169, 969, 150);
        turn(420, bobot.left, bobot.speed, 1000);

       */
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

// code for scoring backwards
//      bobot.resetEncoders();
//
//        intakeThing(bobot.IN, 850);
//        armThing(842, bobot.UP, bobot.speed, 1100);
//
//
//        move(200, bobot.forward, 1169,500);
//        turn(1050, bobot.right, bobot.speed, 1670);
//        move(15, bobot.forward, 1169, 120);
//        strafe(1774, bobot.left, bobot.speed, 2000);
//       // move(240, bobot.forward,1169,400);
//        armThing(1120, bobot.UP, bobot.speed, 590);
//        intakeThing(bobot.OUT, 200);
//        intakeThing(bobot.NEUTRAL, 50);
//        armThing(269, bobot.UP, bobot.speed, 700);
//        //move(209, bobot.back, 1169, 430);
//
//        strafe(959, bobot.left, bobot.speed, 1520);
//        strafe(267, bobot.right, bobot.speed, 869);
//       // turn(2069+4+2+6+9+3, bobot.right, bobot.speed, 2900);
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
// end
 /*
   //     armThing(742, bobot.UP, bobot.speed, 590);
       // intakeThing(bobot.OUT, 200);
        //intakeThing(bobot.NEUTRAL, 50);
  //      armThing(842, bobot.UP, bobot.speed, 700);
        move(209, bobot.back, 1169, 430);

        strafe(959, bobot.right, bobot.speed, 1520);
        strafe(267, bobot.left, bobot.speed, 869);
        turn(2069+4+2+6+9+3, bobot.right, bobot.speed, 2900);

     //   armThing(269+3+1, bobot.UP, bobot.speed, 1480);
        move(969+6+9+9+6+9, bobot.forward, 1169, 1369);
     //   armThing(175, bobot.UP, bobot.speed, 300);
        //intakeThing(bobot.IN, 800);
       // armThing(369, bobot.UP, bobot.speed, 1300);
        move(969, bobot.back, 1169, 1350);
       // armThing(842, bobot.UP, bobot.speed, 1150);
        turn(1542, bobot.right, bobot.speed, 2150);
        move(420, bobot.forward, 1169, 750);
      //  armThing(742, bobot.UP, bobot.speed, 300);
        //intakeThing(bobot.OUT, 300);
        //intakeThing(bobot.NEUTRAL, 10);
       // armThing(842, bobot.UP, bobot.speed, 400);
        move(420, bobot.back, 1409, 600);
        turn(469+2+2, bobot.right, bobot.speed, 800);

        if(bobot.tagOfInterest.id==bobot.LEFT) {
            move(942, bobot.forward, bobot.speed, 1000);
        }
        else if (bobot.tagOfInterest.id==bobot.RIGHT) {
            move(942, bobot.back, bobot.speed, 1000);
        }
        else {
            move(69, bobot.forward, bobot.speed, 69);
        }
*/
