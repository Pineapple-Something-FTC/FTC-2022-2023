package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous

public class overHimOld extends LinearOpMode {
    
    private DcMotorEx driveL;
    private DcMotorEx driveR;

    
    //double driveTicksDeg = 336/(3.5*Math.PI);
    double driveTicksInches = 300/12;
    double driveTicksRotation = 1420;
    double driveTicksPerDegree = driveTicksRotation/360;
    public void runOpMode() {
        //// INIT ////
        driveL = hardwareMap.get(DcMotorEx.class, "driveL");
        driveR = hardwareMap.get(DcMotorEx.class, "driveR");
        
        resetEncoders();
        
        waitForStart();
        
        move(20);
        telemetry.addData("Stuff: ", driveTicksInches);
        telemetry.update();
        
        while (opModeIsActive()) {
            // Prints ticks of motor on hub
            telemetry.addData("driveL", driveL.getCurrentPosition());
            telemetry.addData("driveR", driveR.getCurrentPosition());
            telemetry.update();
        }
    }
    public void move(int distanceDrive) {
        // Sets Target
        driveL.setTargetPosition(-Math.round((float)(distanceDrive * driveTicksInches)));
        driveR.setTargetPosition(Math.round((float)(distanceDrive * driveTicksInches)));

        // Sets Mode
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            boolean m;
        double currentDistance = (distanceDrive - driveL.getCurrentPosition()/driveTicksInches);
        double kP = 69 - 42.0+4.20-1-1;
        double kD = 6900;
        double pastDistance = currentDistance;
        
        while(currentDistance>0.69) {
        // Sets Speed
        currentDistance = (distanceDrive - driveL.getCurrentPosition()/driveTicksInches);
        double p = currentDistance*kP;
        double d = kD * (currentDistance - pastDistance);
        
        driveL.setVelocity(p + d);
        driveR.setVelocity(p + d);
        pastDistance = currentDistance;
        sleep(50);
        telemetry.addData("Stuff: ", currentDistance);
            telemetry.update();
        }
     
        driveL.setVelocity(0);
        driveR.setVelocity(0);
        
        
        
    }
    public void turn(int degrees) {
        driveL.setTargetPosition(-Math.round((float)(degrees * driveTicksPerDegree)));
        driveR.setTargetPosition(-Math.round((float)(degrees * driveTicksPerDegree)));

        // Sets Mode
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        double currentDegrees = Math.abs(degrees - driveR.getCurrentPosition()/driveTicksPerDegree);
    
        double kP = 50; //2
        double kD = 0.00; //1.5
        double pastDegrees = currentDegrees;
        
        while(currentDegrees>0.69) {
            // Sets Speed
            currentDegrees = Math.abs(degrees - driveR.getCurrentPosition()/driveTicksPerDegree);
            double p = currentDegrees*kP;
            double d = Math.abs(kD * (currentDegrees - pastDegrees));
            
            driveL.setVelocity(p+d);
            driveR.setVelocity(p+d);
            pastDegrees = currentDegrees;
            //currentDegrees -= Math.abs(driveR.getCurrentPosition()/driveTicksPerDegree);
            sleep(50);
            telemetry.addData("Stuff: ", currentDegrees);
            telemetry.update();
            
        }
        telemetry.addData("Stuff: ", currentDegrees);
        telemetry.update();
        driveL.setVelocity(0);
        driveR.setVelocity(0);
        
        
        
    }
    
     public void resetEncoders() {
        // Resets Encoders
        driveL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
    }
}
