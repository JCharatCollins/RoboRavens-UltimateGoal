package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Team7159.ComplexRobots.LauncherBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Ultimate Goal TeleOp")
public class TeleOpUltimateGoal extends LinearOpMode {

    LauncherBot robot = new LauncherBot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {

        }
    }
}
