package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utility.Arm;
import org.firstinspires.ftc.teamcode.Utility.DrivePID;
import org.firstinspires.ftc.teamcode.Utility.PineappleSomething;

@Autonomous
public class AutoLEFT12RED extends PineappleTag {
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
        drive.turn(516, PineappleTag.right, 500, 2000);
        drive.straight(PineappleTag.forwardFirstCone, PineappleTag.forward, PineappleTag.speed, 699);

        arm.intakeThing(PineappleTag.OUT, 250);
        arm.intakeThing(PineappleTag.NEUTRAL, 0);

        drive.straight(PineappleTag.backwardFirstCone - 22, PineappleTag.back, PineappleTag.speed, 699);

        arm.armThing(-769, 1500, 0);

        drive.turn(1557, PineappleTag.left, 1500, 1690);
        drive.straight(420, PineappleTag.forward, PineappleTag.speed, 569);

        arm.intakeThing(PineappleTag.IN, 0);

        drivePID.followLine(DrivePID.RED, -702);

        arm.armThing(-369, 1269, 569);
        arm.armThing(-769, 1269, 742);

        drive.straight(2169, PineappleTag.back, 969, 2690);

        arm.armThing(-2350, 1690, 569);

        drive.turn(621, PineappleTag.right, 2000, 769);
        drive.straight(306, PineappleTag.forward, 2000, 542);

        arm.intakeThing(PineappleTag.OUT, 250);
        arm.intakeThing(PineappleTag.NEUTRAL, 0);

        drive.straight(277, PineappleTag.back, 2000, 469);

        arm.armThing(-769, 1500, 10);

        drive.turn(625, PineappleTag.left, 969, 969);
        drive.straight(1496, PineappleTag.forward, 2000, 1069);

        arm.intakeThing(PineappleTag.IN, 0);

        drivePID.followLine(DrivePID.RED, -469);

        arm.armThing(-290, 1269, 542);
        arm.armThing(-1042, 1269, 742);

        drive.straight(869, PineappleTag.back, 2000, 869);
        drive.turn(773, PineappleTag.left, 2000, 642);
        drive.straight(297, PineappleTag.forward, 2000, 290);

        arm.intakeThing(PineappleTag.OUT, 250);
        arm.intakeThing(PineappleTag.NEUTRAL, 0);

        drive.straight(296, PineappleTag.back, 3000, 369);

        arm.armThing(-1, 2000, 0);

        drive.turn(739, PineappleTag.right, 2000, 690);

        if (tagOfInterest.id == PineappleTag.LEFT) {
            drive.straight(742, PineappleTag.forward, 3000, 6690);
        } else if (tagOfInterest.id == PineappleTag.RIGHT) {
            drive.straight(1249, PineappleTag.back, 3000, 6690);
        } else {
            drive.straight(269, PineappleTag.back, 3000, 6690);
        }
    }
}