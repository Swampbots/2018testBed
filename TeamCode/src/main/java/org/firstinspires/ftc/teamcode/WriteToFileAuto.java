package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.io.*;

import java.io.FileWriter;

public class WriteToFileAuto extends LinearOpMode {

    public void runOpMode() {
        try {
            FileWriter writer = new FileWriter("Test_File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
