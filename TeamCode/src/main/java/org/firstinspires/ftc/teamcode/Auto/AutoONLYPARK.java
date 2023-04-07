package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utility.Drive;

@Autonomous
public class AutoONLYPARK extends PineappleTag {
    @Override
    public void runOpMode() {
        Drive drive = new Drive();

        mapCamera();
        mapHardware();

        telemetry.setMsTransmissionInterval(50);

        // code for scoring in front
        drive.resetEncoders();
        drive.straight(1169, PineappleTag.forward, 2000, 2000);

        if (tagOfInterest.id == PineappleTag.LEFT) {
            drive.strafe(1296, PineappleTag.left, 2000, 6690);
        } else if (tagOfInterest.id == PineappleTag.RIGHT) {
            drive.strafe(1296, PineappleTag.right, 2000, 6690);
        } else {
            drive.straight(0, PineappleTag.back, 2000, 6690);
        }
    }
}