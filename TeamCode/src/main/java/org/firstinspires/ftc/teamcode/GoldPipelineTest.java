package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous(name = "Gold Pipeline Test", group = "Testing")
public class GoldPipelineTest extends LinearOpMode {

    private GoldPipeline vision;

    @Override
    public void runOpMode() {

        telemetry.addLine("Creating pipeline instance...");
        telemetry.update();
        sleep(2500);

        vision = new GoldPipeline();
        telemetry.addLine("GoldPipeline instance created.");
        telemetry.update();
        sleep(2500);

        vision.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        telemetry.addLine("Vision initialized.");
        telemetry.update();
        sleep(2500);

        vision.enable();

        telemetry.addLine("Vision enabled.");
        telemetry.addLine("Press play to start.");
        telemetry.update();
        sleep(2500);


        waitForStart();


        while(opModeIsActive()) {

            // vision.process();
        }

        vision.disable();
    }
}
