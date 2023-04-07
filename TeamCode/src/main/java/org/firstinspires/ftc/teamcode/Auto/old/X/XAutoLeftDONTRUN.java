package org.firstinspires.ftc.teamcode.Auto;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Autonomous(name = "Something")
public class XAutoLeftDONTRUN extends LinearOpMode {
    public static final double FEET_PER_METER = 3.28084;

    PineappleTag bobot = new PineappleTag();

    @Override
    public void runOpMode() {
        bobot.init(hardwareMap);

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {

            ArrayList<AprilTagDetection> currentDetections = bobot.aprilTagDetectionPipeline.getLatestDetections();

            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    //// CHANGE FROM ORIGINAL
                    //if(tag.id == ID_TAG_OF_INTEREST)
                    if (tag.id == PineappleTag.LEFT || tag.id == PineappleTag.MIDDLE || tag.id == PineappleTag.RIGHT) {
                        bobot.tagOfInterest = tag;
                        tagFound = true;
                        break;
                    } else {
                        tag.id = PineappleTag.MIDDLE;
                        bobot.tagOfInterest = tag;
                        telemetry.addData("Don't see the tag, default is set to center, also sussy balls 69 afaf asoajfoia sLOL HeheheheHAW", " hi");
                        break;
                    }

                }

                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(bobot.tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if (bobot.tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(bobot.tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (bobot.tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(bobot.tagOfInterest);
                }
            }
            telemetry.addData("L BOZO: ", PineappleSomething.frontLeft.getCurrentPosition());

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if (bobot.tagOfInterest != null) {
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

        intakeThing(PineappleTag.IN, 1200);
        armThing(-2636, 690 + 69 + 42 + 69 + 69 + 69 + 69 + 69, 100);
        move(2284 + 250, PineappleTag.forward, 2000, 2469);
        move(250, PineappleTag.back, 2000, 369);
        turn(969 / 2 + 4 + 2 + 6 + 4 + 6 + 9 + 6 + 9, PineappleTag.right, PineappleTag.speed, 1242);
        move(369, PineappleTag.forward, PineappleTag.speed, 690);
        intakeThing(PineappleTag.OUT, 100);
        intakeThing(PineappleTag.NEUTRAL, 50);
        move(469, PineappleTag.back, PineappleTag.speed, 420);
        armThing(-(569 + 69 + 4 + 6 + 2 + 6 + 9), 690 + 69 + 42, 250);
        turn(969 / 2 + 969 + 4 + 2 + 6 + 9 + 2 + 9 + 6 + 4 + 2 + 2 + 4 + 2 + 6 + 9 + 3 + 4 + 2, PineappleTag.left, PineappleTag.speed, 2000);

        intakeThing(PineappleTag.IN, 150);
        move(1169, PineappleTag.forward, 1200, 1269);
        armThing(-269, 750, 1200);

        armThing(-2630, 1690, 640);
        move(2169, PineappleTag.back, 969, 2969);
        turn((420 + 69 + 20), PineappleTag.right, 2000, 690);
        move(569 - 4 - 2 + 9 + 6 + 4, PineappleTag.forward, PineappleTag.speed, 690);

        sleep(1000);
        intakeThing(PineappleTag.OUT, 150);
        intakeThing(PineappleTag.NEUTRAL, 50);
        move(342, PineappleTag.back, 2000, 469);
        armThing(-(569 + 69 + 4 + 2 + 6 + 9), 690 + 69, 10);
        turn(420 + 69 + 6 + 9 + 6 + 9 + 4 + 1 + 22, PineappleTag.left, 2000, 690);
        intakeThing(PineappleTag.IN, 150);
        move(2169 + 69 + 6 + 9, PineappleTag.forward, 1142, 1690);

        armThing(-269, 500, 1690);


        if (bobot.tagOfInterest.id == PineappleTag.RIGHT) {
            armThing(-1699, 1690, 690);
            move(2169, PineappleTag.back, 2000, 2000);
            turn(420 + 69 + 69, PineappleTag.left, 2000, 690);
            move(420, PineappleTag.forward, PineappleTag.speed, 690);
            intakeThing(PineappleTag.OUT, 150);
            move(369, PineappleTag.back, PineappleTag.speed, 550);
            armThing(-1, 2000, 10);
            turn(420 + 69 + 69, PineappleTag.right, 2000, 690);
            move(200, PineappleTag.back, 2000, 420);

        } else if (bobot.tagOfInterest.id == PineappleTag.LEFT) {
            armThing(-1069, 1690, 690);
            move(242, PineappleTag.back, 2000, 690);
            turn(969 / 2 + 969 + 4 + 2 + 6 + 9 + 9, PineappleTag.left, PineappleTag.speed, 2000);
            move(420, PineappleTag.forward, PineappleTag.speed, 690);
            intakeThing(PineappleTag.OUT, 150);
            move(369, PineappleTag.back, PineappleTag.speed, 550);
            armThing(-1, 2000, 10);
            turn(469 + 4 + 2 + 8, PineappleTag.right, 2000, 2000);
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

        } else {
            armThing(-1069, 1690, 690);
            move(1169, PineappleTag.back, 2000, 1069);
            strafe(690, PineappleTag.left, 2000, 969);
            move(469, PineappleTag.forward, PineappleTag.speed, 690);
            intakeThing(PineappleTag.OUT, 150);
            move(269 + 4 + 2, PineappleTag.back, PineappleTag.speed, 550);
            armThing(-1, 2000, 2000);


        }

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
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }


}

