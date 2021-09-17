package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import Team7159.ComplexRobots.TestBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Test Bot TeleOp")
public class TestbotTeleop extends LinearOpMode {

    private TestBot robot = new Testbot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {

            accel = -gamepad1.left_stick_y;

            //Right Stick--Rotation
            rotate = -gamepad1.right_stick_x;

            //Determines ratio of motor powers (by sides) using the right stick
            double rightRatio = 0.5 - (0.5 * rotate);
            double leftRatio = 0.5 + (0.5 * rotate);
            //Declares the maximum power any side can have
            double maxRatio = 1;

            //If we're turning left, the right motor should be at maximum power, so it decides the maxRatio. If we're turning right, vice versa.
            if (rotate < 0) {
                maxRatio = 1 / rightRatio;
            } else {
                maxRatio = 1 / leftRatio;
            }

            //Uses maxRatio to max out the motor ratio so that one side is always at full power.
            powR = rightRatio * maxRatio;
            powL = leftRatio * maxRatio;
            //Uses left trigger to determine slowdown.
            robot.RFMotor.setPower(powR * accel);
            robot.RBMotor.setPower(powR * accel);
            robot.LFMotor.setPower(powL * accel);
            robot.LBMotor.setPower(powL * accel);

            if(gamepad1.right_bumper && gamepad1.left_bumper) {
                robot.RFMotor.setPower(0);
                robot.LFMotor.setPower(0);
                robot.RBMotor.setPower(0);
                robot.LBMotor.setPower(0);
            } else if(gamepad1.left_bumper) {
                robot.RFMotor.setPower(-1);
                robot.LFMotor.setPower(1);
                robot.RBMotor.setPower(-1);
                robot.LBMotor.setPower(1);
            } else if(gamepad1.right_bumper) {
                robot.RFMotor.setPower(1);
                robot.LFMotor.setPower(-1);
                robot.RBMotor.setPower(1);
                robot.LBMotor.setPower(-1);
            }
            //Strafing controls (thanks Nick)
            if (gamepad1.dpad_up) {
                if (gamepad1.dpad_right) {
                    robot.RFMotor.setPower(1);
                    robot.LFMotor.setPower(0);
                    robot.RBMotor.setPower(0);
                    robot.LBMotor.setPower(1);
                } else if (gamepad1.dpad_left) {
                    robot.RFMotor.setPower(0);
                    robot.LFMotor.setPower(1);
                    robot.RBMotor.setPower(1);
                    robot.LBMotor.setPower(0);
                } else {
                    robot.RFMotor.setPower(1);
                    robot.LFMotor.setPower(1);
                    robot.RBMotor.setPower(1);
                    robot.LBMotor.setPower(1);
                }
            } else if (gamepad1.dpad_down) {
                if (gamepad1.dpad_right) {
                    robot.RFMotor.setPower(0);
                    robot.LFMotor.setPower(-1);
                    robot.RBMotor.setPower(-1);
                    robot.LBMotor.setPower(0);
                } else if (gamepad1.dpad_left) {
                    robot.RFMotor.setPower(-1);
                    robot.LFMotor.setPower(0);
                    robot.RBMotor.setPower(0);
                    robot.LBMotor.setPower(-1);
                } else {
                    robot.RFMotor.setPower(-1);
                    robot.LFMotor.setPower(-1);
                    robot.RBMotor.setPower(-1);
                    robot.LBMotor.setPower(-1);
                }
            }
            else {
                if (gamepad1.dpad_right) {
                    robot.RFMotor.setPower(1);
                    robot.LFMotor.setPower(-1);
                    robot.RBMotor.setPower(-1);
                    robot.LBMotor.setPower(1);
                } else if (gamepad1.dpad_left) {
                    robot.RFMotor.setPower(-1);
                    robot.LFMotor.setPower(1);
                    robot.RBMotor.setPower(1);
                    robot.LBMotor.setPower(-1);
                }
            }
            telemetry.update();
        }
    }
}