package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

public class overHim_new extends LinearOpMode {
    
    private DcMotorEx driveL;
    private DcMotorEx driveR;

    //double driveTicksDeg = 336/(3.5*Math.PI);
    double driveTicksInches = 300/12; // 25 ticks per inch
     double driveTicksRotation = 1560;
    double driveTicksPerDegree = driveTicksRotation/360;
    public void runOpMode() {
        //// INIT ////
        driveL = hardwareMap.get(DcMotorEx.class, "driveL");
        driveR = hardwareMap.get(DcMotorEx.class, "driveR");

        
        driveL.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
        
        waitForStart();
        //// START ////
        // move(20);
        // resetEncoders();
        // sleep(500);
        // turn(180);
        // resetEncoders();
        // sleep(500);
        // move(20);
        // resetEncoders();
        // sleep(500);
        turn(180);
    
       
        

        // while (opModeIsActive()) {
        //     // Prints ticks of motor on hub
        //     telemetry.addData("driveL", currentDistance);
        //     telemetry.addData("driveR", currentDistance);
        //     telemetry.update();
        // }
    }
    public void move(int distanceToDrive) {
        // Sets Target
        driveL.setTargetPosition(Math.round((float)(distanceToDrive * driveTicksInches)));
        driveR.setTargetPosition(Math.round((float)(distanceToDrive * driveTicksInches)));

        // Sets Mode
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        double currentDistance = (distanceToDrive - driveL.getCurrentPosition()/driveTicksInches);
        double kP = 29.2-6.9-4.2-6.9-4.2;
        double kD = 6900;
        double pastDistance = currentDistance;
        
        while(currentDistance > 0) {        
            double p = kP * currentDistance;
            double d = kD * (currentDistance - pastDistance);
            
            driveL.setVelocity(p + d);
            driveR.setVelocity(p + d);
            pastDistance = currentDistance;
            currentDistance -= driveL.getCurrentPosition()/driveTicksInches;
            
            sleep(50);
        }
        driveL.setVelocity(0);
        driveR.setVelocity(0); 
    }
     public void turn(int degrees) {
        driveL.setTargetPosition(-Math.round((float)(degrees * driveTicksPerDegree)));
        driveR.setTargetPosition(Math.round((float)(degrees * driveTicksPerDegree)));

        // Sets Mode
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        double currentDegrees = Math.abs(degrees - driveR.getCurrentPosition()/driveTicksPerDegree);
    
        double kP = 6.9/2+0.69+0.42+0.69+0.42; //2
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
