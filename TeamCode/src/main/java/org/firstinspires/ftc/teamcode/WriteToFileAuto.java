package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.io.*;

import java.io.FileWriter;

@Disabled
@Autonomous(name = "Write to File", group = "File Testing")
public class WriteToFileAuto extends LinearOpMode {

    public void runOpMode() {

        waitForStart();

        boolean finished = false;

        try {
            Writer writer = new FileWriter("Test_File");

            writer.append("Hello");
            writer.append("\nThis thing working?");
            writer.flush();

            writer.close();

            finished = true;
        } catch (IOException e) {
            e.printStackTrace();

            finished = false;
        }

        while(opModeIsActive()) {
            telemetry.addData("Finished", finished);
            telemetry.update();

        }

    }
}
