package org.firstinspires.ftc.teamcode.sussy;

import org.firstinspires.ftc.teamcode.Auto.PineappleSomething;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Score extends PineappleSomething {

    public static void ground() {

    }
    public static void low() {
        double pLimit = 1.28;
        while (deeznuts.getVoltage() < pLimit) {
            g.setPower(0.5);
        }
        if (deeznuts.getVoltage() >= pLimit) {
            g.setPower(0.16);
        }
    }
    public static void medium() {

    }
    public static void high() {

    }
    public static void executorFunc() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new intake());
    }
    public static class intake implements Runnable {
        public void run() {
            thing.setPower(1);
        }
    }
    public static class armUp implements Runnable {
        public void run() {
            g.setVelocity(500);
        }
    }
}
