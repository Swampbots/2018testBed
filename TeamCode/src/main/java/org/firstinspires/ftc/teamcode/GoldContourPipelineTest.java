package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

@Autonomous(name = "Gold Contour Pipeline Test", group = "Testing")
public class GoldContourPipelineTest extends LinearOpMode {

    GoldContourPipeline vision;


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
    }
}
