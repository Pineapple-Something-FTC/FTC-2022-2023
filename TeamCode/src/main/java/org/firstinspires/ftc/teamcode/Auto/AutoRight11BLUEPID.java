package org.firstinspires.ftc.teamcode.Auto;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Autonomous
public class AutoRight11BLUEPID extends LinearOpMode {
    public static final double FEET_PER_METER = 3.28084;
    double encoderCounts = 0;
    float kPL = 3000;
    float kPR = 3000;
    double lastErrorL;
    double lastErrorR;
    ElapsedTime timerD = new ElapsedTime();
    PineappleTag bobot = new PineappleTag();

    @Override
    public void runOpMode() {

        bobot.init(hardwareMap);
        NormalizedRGBA colorsL = PineappleSomething.leftCSensor.getNormalizedColors();
        NormalizedRGBA colorsR = PineappleSomething.rightCSensor.getNormalizedColors();

        PineappleSomething.leftCSensor.setGain(20);
        PineappleSomething.rightCSensor.setGain(20);
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
        armThing(-2350, 690 + 69 + 42 + 69 + 69 + 69 + 69 + 69, 100);
        movePID(2284 + 300, PineappleTag.forward);
        movePID(300, PineappleTag.back);
        turn(969 / 2 + 4 + 2 + 6 + 4 + 6 + 9 + 6 + 9 + 19, PineappleTag.left, 500, 2000);
        move(PineappleTag.forwardFirstCone, PineappleTag.forward, 969, 969);
        intakeThing(PineappleTag.OUT, 500);
        intakeThing(PineappleTag.NEUTRAL, 50);
        move(PineappleTag.backwardFirstCone, PineappleTag.back, 969, 1269);

        turn((969 / 2 + 969 + 4 + 2 + 19 + 19 + 19 + 4 + 1 + 19), PineappleTag.right, 969, 2690);
        armThing(-(769), 1500, 20);
        movePID(142, PineappleTag.forward);
        intakeThing(PineappleTag.IN, 150);
        followLine(PineappleTag.BLUE, -699 - 6 - 9 - 4 - 2 - 69 - 19 - 69 - 69);


        armThing(-369, 1269, 690);
        armThing(-769, 1269, 742);

        movePID(2169, PineappleTag.back);
        armThing(-2350, 1690, 569);
        turn((420 + 69 + 20 + 6 + 19 + 21 + 19 + 9 + 9 + 42), PineappleTag.left, 2000, 769);
        movePID(PineappleTag.forwardSecondCone, PineappleTag.forward);

        intakeThing(PineappleTag.OUT, 350);
        intakeThing(PineappleTag.NEUTRAL, 50);
        movePID(400 - 49, PineappleTag.back);
        armThing(-(1), 1500, 10);
        turn(420 + 69 + 69 + 9 + 42 + 4 + 2 + 1, PineappleTag.right, 569, 1690);


        if (bobot.tagOfInterest.id == PineappleTag.RIGHT) {

            movePID(1869 + 42 - 2 - 4, PineappleTag.forward);

        } else if (bobot.tagOfInterest.id == PineappleTag.MIDDLE) {
            movePID(869 + 19 + 4 + 2, PineappleTag.forward);

        } else {
            movePID(100 - 4 - 2 - 6 + 1, PineappleTag.back);
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

    public void ttuurrnn(double velocity) {
        bobot.turnWithoutEncoder(velocity);
    }

    public void followLine(double redOrBlue, double maxDistance) {
        bobot.followLine(redOrBlue, maxDistance);
    }

    public void movePID(double distance, boolean forwardOrBack) {
        bobot.movePID(distance, forwardOrBack, 10, 5);
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

