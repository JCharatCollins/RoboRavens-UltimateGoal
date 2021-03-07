package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import Team7159.ComplexRobots.FlywheelBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Ultimate Goal TeleOp")
public class TeleOpUltimateGoal extends LinearOpMode {

    final int PLATFORM_BASE = 0;
    final int PLATFORM_RAISED = 142;
    final int PLATFORM_TOLERANCE = 10;
    final double PLATFORM_MOTOR_POWER = 0.1;

    final double MAX_VELOCITY = 1500.0;

    final double CLAWMOTOR_INCREMENT = 0.5;

    private FlywheelBot robot = new FlywheelBot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        robot.platformMotor.setPositionPIDFCoefficients(0.01);
        robot.platformMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        int platformPos = PLATFORM_BASE;
        double clawMotorPower = 0.0;
        // double flywheelServoPos = 0.0;
        // double

        while(opModeIsActive()) {
            //TODO: Use scaleRange to fix intake servo, rotate intake servo on servo mount to actually make it usable
            // telemetry.addData("Servo Position: ", robot.flywheelServo.getPosition());
            telemetry.addData("Platform Position: ", robot.platformMotor.getCurrentPosition());
            telemetry.addData("Velocity: ", robot.flywheelMotor.getVelocity());

            // robot.flywheelServo.setPosition(flywheelServoPos);

            //if platform pos is either 0 or 1, switch it, otherwise it stays the same
            if (gamepad1.x) {
                if (Math.abs(robot.platformMotor.getCurrentPosition() - PLATFORM_BASE) < PLATFORM_TOLERANCE) platformPos = PLATFORM_RAISED;
                else if (Math.abs(robot.platformMotor.getCurrentPosition() - PLATFORM_RAISED) < PLATFORM_TOLERANCE) platformPos = PLATFORM_BASE;
            }
            robot.platformMotor.setTargetPosition(platformPos);
            robot.platformMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (gamepad1.a) {
                clawMotorPower -= CLAWMOTOR_INCREMENT;
            } else if (gamepad1.y) {
                clawMotorPower += CLAWMOTOR_INCREMENT;
            }
            robot.clawMotor.setPower(clawMotorPower);

            robot.flywheelMotor.setVelocity(gamepad1.right_trigger * MAX_VELOCITY);

            if (gamepad1.left_trigger > 0.0f) {
                robot.intakeMotor.setPower(1.0);
            } else {
                robot.intakeMotor.setPower(0.0);
            }

//            if (gamepad1.right_bumper) {
//                flywheelServoPos -= 0.01;
//            }
//            if (gamepad1.left_bumper) {
//                flywheelServoPos += 0.01;
//            }

            double accel = -gamepad1.left_stick_y;

            //Right Stick--Rotation
            double rotate = gamepad1.right_stick_x;

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
            double powR = rightRatio * maxRatio;
            double powL = leftRatio * maxRatio;
            //Uses left trigger to determine slowdown.
            if(gamepad1.left_trigger>0.5) {
                robot.RFMotor.setPower((powR * accel)/2);
                robot.RBMotor.setPower((powR * accel)/2);
                robot.LFMotor.setPower((powL * accel)/2);
                robot.LBMotor.setPower((powL * accel)/2);
            } else {
                robot.RFMotor.setPower(powR * accel);
                robot.RBMotor.setPower(powR * accel);
                robot.LFMotor.setPower(powL * accel);
                robot.LBMotor.setPower(powL * accel);
            }

            if(gamepad1.right_bumper && gamepad1.left_bumper) {
                robot.RFMotor.setPower(0);
                robot.LFMotor.setPower(0);
                robot.RBMotor.setPower(0);
                robot.LBMotor.setPower(0);
            } else if(gamepad1.right_bumper) {
                if(gamepad1.left_trigger>0.5) {
                    robot.RFMotor.setPower(-0.5);
                    robot.LFMotor.setPower(0.5);
                    robot.RBMotor.setPower(-0.5);
                    robot.LBMotor.setPower(0.5);
                } else {
                    robot.RFMotor.setPower(-1);
                    robot.LFMotor.setPower(1);
                    robot.RBMotor.setPower(-1);
                    robot.LBMotor.setPower(1);
                }
            } else if(gamepad1.left_bumper) {
                if(gamepad1.left_trigger>0.5) {
                    robot.RFMotor.setPower(0.5);
                    robot.LFMotor.setPower(-0.5);
                    robot.RBMotor.setPower(0.5);
                    robot.LBMotor.setPower(-0.5);
                } else {
                    robot.RFMotor.setPower(1);
                    robot.LFMotor.setPower(-1);
                    robot.RBMotor.setPower(1);
                    robot.LBMotor.setPower(-1);
                }
            }

            //Strafing controls (thanks Nick)
            if (gamepad1.dpad_up) {
                if (gamepad1.dpad_left) {
                    robot.RFMotor.setPower(1);
                    robot.LFMotor.setPower(0);
                    robot.RBMotor.setPower(0);
                    robot.LBMotor.setPower(1);
                } else if (gamepad1.dpad_right) {
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
                if (gamepad1.dpad_left) {
                    robot.RFMotor.setPower(0);
                    robot.LFMotor.setPower(-1);
                    robot.RBMotor.setPower(-1);
                    robot.LBMotor.setPower(0);
                } else if (gamepad1.dpad_right) {
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
                if (gamepad1.dpad_left) {
                    robot.RFMotor.setPower(1);
                    robot.LFMotor.setPower(-1);
                    robot.RBMotor.setPower(-1);
                    robot.LBMotor.setPower(1);
                } else if (gamepad1.dpad_right) {
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
