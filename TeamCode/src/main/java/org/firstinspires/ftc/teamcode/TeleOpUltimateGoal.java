package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Team7159.BasicRobots.TestRobot;
import Team7159.ComplexRobots.LauncherBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Ultimate Goal TeleOp")
public class TeleOpUltimateGoal extends LinearOpMode {

    TestRobot robot = new TestRobot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            robot.testMotor.setVelocity(5000);
        }
    }
}
