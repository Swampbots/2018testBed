package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.io.*;

import java.io.FileWriter;

public class WriteToFileAuto extends LinearOpMode {

    public void runOpMode() {

        waitForStart();

        try {
            FileWriter writer = new FileWriter("Test_File");

            writer.append("Hello");
            writer.append("\nThis thing working?");
            writer.flush();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(opModeIsActive()) {

        }

    }
}
