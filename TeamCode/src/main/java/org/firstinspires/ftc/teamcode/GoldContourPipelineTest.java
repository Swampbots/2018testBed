package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous(name = "Gold Contour Pipeline Test", group = "Testing")
public class GoldContourPipelineTest extends LinearOpMode {

    GoldContourPipeline vision;


    // HSV Threshold input variables
    private final double HSV_MAX = 255.0;
    private final double HSV_MIN = 0.0;

    private double[] hsvHue = new double[]{0.0, 255.0};
    private double[] hsvSat = new double[]{0.0, 255.0};
    private double[] hsvVal = new double[]{0.0, 255.0};


    // Cooldown variables
    private final double COOLDOWN = 0.125; // 125 milliseconds

    private double dpUpSnapshot = 0.0;
    private double dpUpRuntimeDif = 0.0;
    private boolean dpUpReady = false;


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

            dpUpRuntimeDif = (getRuntime() - dpUpSnapshot);
            dpUpReady = dpUpRuntimeDif > COOLDOWN;






            //------------------------------------------
            // START HSV THRESHOLD CONTROLS
            //------------------------------------------

            /*
                CONTROLS: (increase, decrease)

                Hue min: gp1.up,   gp1.down
                Hue max: gp1.y,    gp1.a

             */
            // Modify threshold variables if the buttons are pressed and thresholds are within outer limits 0 & 255

            // HUE MINIMUM
            if(gamepad1.dpad_down) {
                if (hsvHue[0] > HSV_MIN)   hsvHue[0] -= 1.0;
                else                        hsvHue[0] = HSV_MIN;
            }

            if(gamepad1.dpad_up && dpUpReady) {
                if(hsvHue[0] < hsvHue[1])  hsvHue[0] += 1.0;
                else                        hsvHue[0] = hsvHue[1];
                dpUpSnapshot = getRuntime();
            }

//
//            // HUE MAXIMUM
//            if(hsvHue[1] <= HSV_MAX && gamepad1.a)  hsvHue[1] -= 1.0;
//            else hsvHue[1] = HSV_MAX;
//
//            if(hsvHue[1] >= hsvHue[0] && gamepad1.y)  hsvHue[1] += 1.0;
//            else hsvHue[1] = hsvHue[0];

            //------------------------------------------
            // END HSVTHRESHOLD CONTROLS
            //------------------------------------------





            // TELEMETRY
            telemetry.addData("Hue min", hsvHue[0]);
            telemetry.addData("Hue max", hsvHue[1]);
            telemetry.addLine();
            telemetry.addData("dpUpReady", dpUpReady);
            telemetry.addData("runtime - snapshot", dpUpRuntimeDif);
            telemetry.update();
        }

        vision.disable();
    }
}
