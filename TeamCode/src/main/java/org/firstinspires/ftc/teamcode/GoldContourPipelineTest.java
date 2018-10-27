package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous(name = "Gold Contour Pipeline Test", group = "Testing")
public class GoldContourPipelineTest extends LinearOpMode {

    GoldContourPipeline vision;


    // HSV Threshold input variables
    private final double THRESHOLD_STEP = 1.0;

    private final double HSV_MAX = 255.0;
    private final double HSV_MIN = 0.0;

    private double[] hsvHue = new double[]{0.0, 255.0};
    private double[] hsvSat = new double[]{0.0, 255.0};
    private double[] hsvVal = new double[]{0.0, 255.0};


    // Cooldown variables
    private final double COOLDOWN = 0.075; // 75 milliseconds

    // DPAD UP
    private double dpUpSnapshot = 0.0;
    private double dpUpRuntimeDif = 0.0;
    private boolean dpUpReady = false;

    // DPAD DOWN
    private double dpDownSnapshot = 0.0;
    private double dpDownRuntimeDif = 0.0;
    private boolean dpDownReady = false;

    // DPAD LEFT
    private double dpLeftSnapshot = 0.0;
    private double dpLeftRuntimeDif = 0.0;
    private boolean dpLeftReady = false;

    // DPAD RIGHT
    private double dpRightSnapshot = 0.0;
    private double dpRightRuntimeDif = 0.0;
    private boolean dpRightReady = false;

    // Y
    private double ySnapshot = 0.0;
    private double yRuntimeDif = 0.0;
    private boolean yReady = false;

    // A
    private double aSnapshot = 0.0;
    private double aRuntimeDif = 0.0;
    private boolean aReady = false;


    public void runOpMode() {

        vision = new GoldContourPipeline();
        telemetry.addLine("GoldPipeline instance created.");
        telemetry.update();
        sleep(800);

        vision.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        telemetry.addLine("Vision initialized.");
        telemetry.update();
        sleep(800);

        vision.enable();
        telemetry.addLine("Vision enabled.");
        telemetry.addLine("Press play to start.");
        telemetry.update();
        sleep(2500);

        waitForStart();

        while(opModeIsActive()) {

            // COOLDOWNS

            /*
             When a button is pressed, set the button's cooldown variable to the current runtime.
             While the difference between the cooldown variable and the runtime is below some constant: don't allow thresholds to change
              */

            // DPAD UP
            dpUpRuntimeDif = (getRuntime() - dpUpSnapshot);
            dpUpReady = dpUpRuntimeDif > COOLDOWN;

            // DPAD DOWN
            dpDownRuntimeDif = (getRuntime() - dpDownSnapshot);
            dpDownReady = dpDownRuntimeDif > COOLDOWN;

            // DPAD LEFT
            dpLeftRuntimeDif = (getRuntime() - dpLeftSnapshot);
            dpLeftReady = dpLeftRuntimeDif > COOLDOWN;

            // DPAD RIGHT
            dpRightRuntimeDif = (getRuntime() - dpRightSnapshot);
            dpRightReady = dpRightRuntimeDif > COOLDOWN;

            // Y
            yRuntimeDif = (getRuntime() - ySnapshot);
            yReady = yRuntimeDif > COOLDOWN;

            // A
            aRuntimeDif = (getRuntime() - aSnapshot);
            aReady = aRuntimeDif > COOLDOWN;




            //------------------------------------------
            // START HSV THRESHOLD CONTROLS
            //------------------------------------------

            /*
                CONTROLS: (increase, decrease)

                Hue min: gp1.up,    gp1.down
                Hue max: gp1.y,     gp1.a

                Sat min: gp1.right, gp1.left
                Sat max: gp1.b,     gp1.x

             */

            // Modify threshold variables if the buttons are pressed and thresholds are within outer limits 0 & 255

            // HUE MINIMUM
            if(gamepad1.dpad_down && dpDownReady) {
                if (hsvHue[0] > HSV_MIN)   hsvHue[0] -= THRESHOLD_STEP;
                else                        hsvHue[0] = HSV_MIN;
                dpDownSnapshot = getRuntime();
            }

            if(gamepad1.dpad_up && dpUpReady) {
                if(hsvHue[0] < hsvHue[1])  hsvHue[0] += THRESHOLD_STEP;
                else                        hsvHue[0] = hsvHue[1];
                dpUpSnapshot = getRuntime();
            }


            // HUE MAXIMUM
            if(gamepad1.y && yReady) {
                if (hsvHue[1] < HSV_MAX)   hsvHue[1] += THRESHOLD_STEP;
                else                        hsvHue[1] = HSV_MAX;
                ySnapshot = getRuntime();
            }

            if(gamepad1.a && aReady) {
                if(hsvHue[1] > hsvHue[0])  hsvHue[1] -= THRESHOLD_STEP;
                else                        hsvHue[1] = hsvHue[0];
                aSnapshot = getRuntime();
            }




            // SAT MINIMUM
            if(gamepad1.dpad_left && dpLeftReady) {
                if (hsvHue[0] > HSV_MIN)   hsvHue[0] -= THRESHOLD_STEP;
                else                        hsvHue[0] = HSV_MIN;
                dpLeftSnapshot = getRuntime();
            }

            if(gamepad1.dpad_right && dpRightReady) {
                if(hsvHue[0] < hsvHue[1])  hsvHue[0] += THRESHOLD_STEP;
                else                        hsvHue[0] = hsvHue[1];
                dpRightSnapshot = getRuntime();
            }


//            // SAT MAXIMUM
//            if(gamepad1.y && yReady) {
//                if (hsvHue[1] < HSV_MAX)   hsvHue[1] += THRESHOLD_STEP;
//                else                        hsvHue[1] = HSV_MAX;
//                ySnapshot = getRuntime();
//            }
//
//            if(gamepad1.a && aReady) {
//                if(hsvHue[1] > hsvHue[0])  hsvHue[1] -= THRESHOLD_STEP;
//                else                        hsvHue[1] = hsvHue[0];
//                aSnapshot = getRuntime();
//            }


            //------------------------------------------
            // END HSV THRESHOLD CONTROLS
            //------------------------------------------

            // Set HSV thresholds
            vision.setHsvHue(hsvHue);
//            vision.setHsvVal(hsvVal);
//            vision.setHsvSat(hsvSat);





            // TELEMETRY
//            telemetry.addData("Hue min", hsvHue[0]);
//            telemetry.addData("Hue max", hsvHue[1]);
//            telemetry.addLine();
            telemetry.addData("Sat min", hsvSat[0]);
            telemetry.addData("Sat max", hsvSat[1]);
            telemetry.update();
        }

        vision.disable();
    }
}
