package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utility.Arm;
import org.firstinspires.ftc.teamcode.Utility.DrivePID;
import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;

@Autonomous
public class AutoRight12BLUE extends PineappleTag {
    @Override
    public void runOpMode() {
        DrivePID drivePID = new DrivePID();
        Arm arm = new Arm();

        mapCamera();
        justAprilTagThings();

        PineappleSomething.leftCSensor.setGain(20);
        PineappleSomething.rightCSensor.setGain(20);
        telemetry.setMsTransmissionInterval(50);

        // code for scoring in front
        drive.resetEncoders();

        arm.intakeThing(PineappleTag.IN, 1342);
        arm.armThing(-2350, 1146, 0);

        drive.straight(2584, PineappleTag.forward, 1500, 2569);
        drive.straight(300, PineappleTag.back, 2000, 690);
        drive.turn(530, PineappleTag.left, 500, 2000);
        drive.straight(PineappleTag.forwardFirstCone, PineappleTag.forward, PineappleTag.speed, 699);

        arm.intakeThing(PineappleTag.OUT, 250);
        arm.intakeThing(PineappleTag.NEUTRAL, 0);

        drive.straight(PineappleTag.backwardFirstCone, PineappleTag.back, PineappleTag.speed, 699);

        arm.armThing(-769, 1500, 0);

        drive.turn(1547, PineappleTag.right, 1500, 1690);
        drive.straight(420, PineappleTag.forward, PineappleTag.speed, 569);

        arm.intakeThing(PineappleTag.IN, 0);

        drivePID.followLine(DrivePID.BLUE, -674);

        arm.armThing(-369, 1269, 569);
        arm.armThing(-769, 1269, 742);

        drivePID.straight(2169, PineappleTag.back, 4.79, 24);

        arm.armThing(-2350, 1690, 569);

        drive.turn(656, PineappleTag.left, 2000, 769);
        drive.straight(285, PineappleTag.forward, 2000, 542);

        arm.intakeThing(PineappleTag.OUT, 250);
        arm.intakeThing(PineappleTag.NEUTRAL, 0);

        drive.straight(298, PineappleTag.back, 2000, 469);

        arm.armThing(-769, 1500, 10);

        drive.turn(640, PineappleTag.right, 569, 1690);
        drive.straight(1496, PineappleTag.forward, 2000, 1069);

        arm.intakeThing(DrivePID.BLUE, -469);
        arm.armThing(-290, 1269, 542);
        arm.armThing(-1042, 1269, 742);

        drive.straight(869, PineappleTag.back, 2000, 869);
        drive.turn(718, PineappleTag.right, 2000, 642);
        drive.straight(296, PineappleTag.forward, 2000, 290);

        arm.intakeThing(PineappleTag.OUT, 250);
        arm.intakeThing(PineappleTag.NEUTRAL, 0);

        drive.straight(184, PineappleTag.back, 2000, 275);

        arm.armThing(-1, 2000, 0);

        drive.turn(770, PineappleTag.left, 2000, 690);

        if (tagOfInterest.id == PineappleTag.RIGHT) {
            drive.straight(742, PineappleTag.forward, 3000, 6690);
        } else if (tagOfInterest.id == PineappleTag.LEFT) {
            drive.straight(1318, PineappleTag.back, 3000, 6690);
        } else {
            drive.straight(342, PineappleTag.back, 3000, 6690);
        }
    }
}