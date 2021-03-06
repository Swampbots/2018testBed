package org.firstinspires.ftc.teamcode;

public class ButtonCooldown {

    private final double COOLDOWN = 0.075; // 75 milliseconds
    private double snapshot = 0.0;

    public void updateSnapshot(double runtime) {
        snapshot = runtime;
    }

    public boolean ready(double runtime) {
        return(runtime - snapshot) > COOLDOWN;
    }
}
