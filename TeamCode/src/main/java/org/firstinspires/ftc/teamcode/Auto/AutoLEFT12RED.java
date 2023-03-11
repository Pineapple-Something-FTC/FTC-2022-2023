package org.firstinspires.ftc.teamcode.Auto;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Autonomous
public class AutoLEFT12RED extends LinearOpMode {
    public static final double FEET_PER_METER = 3.28084;
    double encoderCounts = 0;
    float kPL = 3000;
    float kPR = 3000;
    double lastErrorL;
    double lastErrorR;
    ElapsedTime timerD = new ElapsedTime();
    PineappleBobot bobot = new PineappleBobot();

    @Override
    public void runOpMode() {

        bobot.init(hardwareMap);
        NormalizedRGBA colorsL = bobot.leftCSensor.getNormalizedColors();
        NormalizedRGBA colorsR = bobot.rightCSensor.getNormalizedColors();

        bobot.leftCSensor.setGain(20);
        bobot.rightCSensor.setGain(20);
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

        intakeThing(bobot.IN, 1342);
        armThing(-2350, 690+69+42+69+69+69+69+69, 0);
        move(2284+300, bobot.forward, 1500,2569);
        move(300, bobot.back, 2000, 690);
        turn(969/2 + 4 + 2 + 6+4+6+9, bobot.right, 500, 2000);
        move(bobot.forwardFirstCone, bobot.forward,bobot.speed,699);
        intakeThing(bobot.OUT, 250);
        intakeThing(bobot.NEUTRAL, 0);
        move(bobot.backwardFirstCone-19-2-1, bobot.back,bobot.speed,699);
        armThing(-(769), 1500, 0);
        turn((969/2+969+4+19+9+4+2+6+19+9+6+4+2+19), bobot.left, 1500, 1690);
        move(420, bobot.forward, bobot.speed, 569);
        intakeThing(bobot.IN, 0);
        followLine(bobot.RED, -699+69-6-9-6-9-4-2-1-4-2-1-19-9);

        armThing(-369, 1269, 569);
        armThing(-769, 1269, 742);

        move(2169, bobot.back, 969, 2690);
        armThing(-2350, 1690, 569);
        turn((420+69+20+6+19+21+19+9+9+42+6+9+4+2+1-19-9-4-2-1), bobot.right, 2000, 769);
        move(369+21-42-42, bobot.forward, 2000, 542);

        intakeThing(bobot.OUT, 250);
        intakeThing(bobot.NEUTRAL, 0);
        move(400-48+21+9-21-42-42, bobot.back, 2000, 469);
        armThing(-(769), 1500, 10);
        turn(420+69+69+9+42+6+9+7+2+4+2+1+6+9+4-19+1-9-4-2-1, bobot.left, 969, 969);
        move(1496, bobot.forward, 2000, 1069);
        intakeThing(bobot.IN, 0);
        followLine(bobot.RED, -469);

        armThing(-290, 1269, 542);
        armThing(-1042, 1269, 742);

        move(869, bobot.back, 2000, 869);
        turn(699+19+4+2+19+2+19+9, bobot.left, 2000, 642);
        move(296-69+2+19+4+2+6+2+1+4+2+19+9, bobot.forward, 2000, 369-42-19-9-9);
        intakeThing(bobot.OUT, 250);
        intakeThing(bobot.NEUTRAL, 0);

        move(142+42+19+19+4+2+19+4+2+6+2+1+4+2+19+9, bobot.back, 3000, 369);
        armThing(-1, 2000, 0);
        turn(699+9+4-1+19+9, bobot.right, 2000, 690);

        if(bobot.tagOfInterest.id==bobot.LEFT) {

            move(742, bobot.forward, 3000, 6690);

        }

        else if (bobot.tagOfInterest.id==bobot.RIGHT) {

            move(1242+6+1, bobot.back, 3000, 6690);

        }

        else {

            move(269, bobot.back, 3000, 6690);

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
        bobot.ttuurrnn(velocity);
    }
    public void followLine(double redOrBlue, double maxDistance) {
        bobot.followLine(redOrBlue, maxDistance);
    }

    public void turnPID(double degrees, boolean rightOrLeft) {
        bobot.turnPID(degrees, rightOrLeft);
    }
    public void movePID(double distance, boolean forwardOrBack, double customkP, double range) {
        bobot.movePID(distance, forwardOrBack, customkP, range);

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

