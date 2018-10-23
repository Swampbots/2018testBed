package org.firstinspires.ftc.teamcode;

import android.support.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadFromFileAuto extends LinearOpMode {

    public void runOpMode() {

        String str = "";

        waitForStart();

        try {
            Reader reader = new FileReader("Test_File");
            str.concat(String.valueOf((char)reader.read()));


            reader.close();
        } catch (Exception e) {
            if (e.equals(new FileNotFoundException())) str = "File not found.";
            else if (e.equals(new IOException())) str = "I/O Exception thrown.";
            else str = "Error";
            e.printStackTrace();
        }

        while(opModeIsActive()) {
            telemetry.addLine(str);
            telemetry.update();
        }
    }
}
