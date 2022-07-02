package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="NewerFowlder", group="Linear Opmode")

public class NewerFowlder extends LinearOpMode {

    // Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx arm1;
    private DcMotorEx arm2;
    double cP;
    double oS = 60;
    cP = oS;

    float arm1TicksDeg = 100/180f;
    int arm1IntDeg = Math.round(arm1TicksDeg);
    
    float arm2TicksDeg = 100/140f;
    int arm2IntDeg = Math.round(arm2TicksDeg);

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        arm1  = hardwareMap.get(DcMotorEx.class, "arm0");
        arm2 = hardwareMap.get(DcMotorEx.class, "arm1");

        // Code
        waitForStart();
        moveToPoint(50, 50);
        sleep(500)

        while (opModeIsActive()) {

            // Updates Telemetry
            telemetry.update();
        }
    }
    public void move(int distanceArm1, int distanceArm2, int velocityArm1, int velocityArm2){

        // Resets Motors
        resetMotors();

        // Sets Target
        arm1.setTargetPosition(distanceArm1 * arm1IntDeg);
        arm2.setTargetPosition(distanceArm2 * arm2IntDeg);

        // Sets Mode
        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Sets Speed
        arm1.setVelocity(velocityArm1);
        arm2.setVelocity(velocityArm2);

        // Adding Amount to cP
        cP += distanceArm1;
        cP += distanceArm2;
    
        while (opModeIsActive() && (arm1.isBusy() && arm2.isBusy())) {

            // Prints on Hub Position of Motor
            telemetry.addData("arm2", arm1.getCurrentPosition());
            telemetry.addData("right", arm2.getCurrentPosition());
            telemetry.update();
        }
    }

    public static double[] tR(double s1, double s2, double s3){

        // Gets Array
        double[] theta = {0, 0, 0};

        // Equation 1 & Turning Radians to Degrees
        theta[0] = Math.acos( (s1 * s1 - s2 * s2 - s3 * s3) / (-2 * s2 * s3) );
        theta[0] = theta[0] * 180 / Math.PI;

        // Equation 2 & Turning Radians to Degrees
        theta[1] = Math.acos( (s2 * s2 - s1 * s1 - s3 * s3) / (-2 * s1 * s3) );
        theta[1] = theta[1] * 180 / Math.PI;

        // Equation 3 & Turning Radians to Degrees
        theta[2] = Math.acos( (s3 * s3 - s2 * s2 - s1 * s1) / (-2 * s2 * s1) );
        theta[2] = theta[2] * 180 / Math.PI;

        // Returning the Theta
        return theta;
    }
    
    // Get X & Y
    public static void moveToPoint(int x, int y) {
        double c = pythag(x,y);
        double[] targetAngles = tR(x,y,c);
        move(cP - targetAngles[0], cP - targetAngles[1],2000,2000);
    }

    // Uses X & Y for Pythagorean Theorem
    public static double pythag(double a, double b) {
        return Math.sqrt(a*a + b*b);
    }
    
    // Get Circumfrence of a cCircle
    public static double piD() {
        return Math.PI*2*radius
    }

    public void resetMotors() {

        // Resets Encoders
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
