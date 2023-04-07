package org.firstinspires.ftc.teamcode.Auto;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Utility.Drive;
import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public class PineappleTag extends PineappleSomething {
    // Define Motor and Servo objects  (Make them private so they can't be accessed externally)
    public static final double fx = 578.272;
    /* Declare OpMode members. */
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
    // tagOfInterest = 2;
    // Define Drive constants.  Make them public so they CAN be used by the calling OpMode - no u
    public static final int speed = 900;
    public static final int forwardFirstCone = 407;
    public static final int backwardFirstCone = forwardFirstCone + (67);
    public static final int forwardSecondCone = 343;
    public static final int turnSecondCone = 0;
    // UNITS ARE METERS
    public static final double tagSize = 0.044;// Default value: 0.166
    public static final int LEFT = 1;
    public static final int MIDDLE = 2;
    public static final int RIGHT = 3;
    public static final double FEET_PER_METER = 3.28084;
    Drive drive = new Drive();
    // Define camera
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    AprilTagDetection tagOfInterest = null;

    // Define a constructor that allows the OpMode to pass a reference to itself.
    public PineappleTag() {
    }

    public void mapCamera() {
        // Camera things
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()
        );
        camera = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId
        );
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);
        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
            }
        });
    }

    public void justAprilTagThings() {
        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == PineappleTag.LEFT || tag.id == PineappleTag.MIDDLE || tag.id == PineappleTag.RIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                    } else {
                        tag.id = PineappleTag.MIDDLE;
                        tagOfInterest = tag;
                        telemetry.addData("Don't see the tag, default is set to center", tag);
                    }
                    break;
                }
                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if (tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
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
        if (tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }
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

    public void turnWithoutEncoder(double velocity) {
        drive.setModeNoEncoder();
        frontLeft.setVelocity(-(300 + 0.1 * velocity));
        frontRight.setVelocity(-(300 - 0.1 * velocity));
        backLeft.setVelocity(-(300 + 0.1 * velocity));
        backRight.setVelocity(-(300 - 0.1 * velocity));
    }
}