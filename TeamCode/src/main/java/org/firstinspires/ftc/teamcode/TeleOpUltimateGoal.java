package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import Team7159.ComplexRobots.FlywheelBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Ultimate Goal TeleOp")
public class TeleOpUltimateGoal extends LinearOpMode {

    final int PLATFORM_BASE = 0;
    final int PLATFORM_RAISED = 166;
    final int PLATFORM_TOLERANCE = 5;
    final double PLATFORM_MOTOR_POWER = 0.15;

    final double MAX_POWER = 0.52;

    final double CLAWMOTOR_INCREMENT = 0.2;

    private FlywheelBot robot = new FlywheelBot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        robot.intakeServo.scaleRange(0.34, 0.80);
        robot.intakeServo.setPosition(0.0);

        robot.platformMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        int platformPos = PLATFORM_BASE;
        double clawMotorPower = 0.0;
        boolean previousY = false;
        boolean clawDecrease = false;
        boolean previousA = false;
        boolean clawIncrease = false;
        boolean previousB = false;
        boolean clawToggle = false;

        double accel;
        double rotate;
        double powR;
        double powL;

        while(opModeIsActive()) {
            //TODO: Use scaleRange to fix intake servo, rotate intake servo on servo mount to actually make it usable
            //telemetry.addData("Servo Position: ", robot.flywheelServo.getPosition());
            telemetry.addData("Platform Position: ", robot.platformMotor.getCurrentPosition());
            telemetry.addData("Velocity: ", robot.flywheelMotor.getVelocity());
            telemetry.addData("RF Motor Power: ", robot.RFMotor.getPower());
            telemetry.addData("LF Motor Power: ", robot.LFMotor.getPower());
            telemetry.addData("RB Motor Power: ", robot.RBMotor.getPower());
            telemetry.addData("LB Motor Power: ", robot.LBMotor.getPower());


            // robot.flywheelServo.setPosition(flywheelServoPos);

            if (gamepad1.x) {
                platformPos = PLATFORM_RAISED;
                robot.flywheelMotor.setPower(MAX_POWER);
            } else {
                platformPos = PLATFORM_BASE;
                robot.flywheelMotor.setPower(0.0);
            }
            robot.platformMotor.setTargetPosition(platformPos);
            robot.platformMotor.setPower(PLATFORM_MOTOR_POWER);
            robot.platformMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (gamepad1.y && !previousY) {
                clawDecrease = !clawDecrease;
            }
            previousY = gamepad1.y;

            if (gamepad1.a && !previousA) {
                clawIncrease = !clawIncrease;
            }
            previousA = gamepad1.a;

            if (clawDecrease) {
                clawMotorPower = clawMotorPower - CLAWMOTOR_INCREMENT;
                clawDecrease = false;
            }
            if (clawIncrease) {
                clawMotorPower = clawMotorPower + CLAWMOTOR_INCREMENT;
                clawIncrease = false;
            }
            robot.clawMotor.setPower(clawMotorPower);

            if (gamepad1.b && !previousB) {
                clawToggle = !clawToggle;
            }
            previousB = gamepad1.b;
            if (clawToggle) {
                robot.clawServo.setPosition(0.5);
            } else {
                robot.clawServo.setPosition(0.0);
            }

            robot.flywheelServo.setPosition(1.0-gamepad1.right_trigger);

            if (gamepad1.left_trigger > 0.5) {
                robot.intakeServo.setPosition(1.0);
                robot.intakeMotor.setPower(1.0);
            } else {
                robot.intakeServo.setPosition(0.0);
                robot.intakeMotor.setPower(0.0);
            }

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
