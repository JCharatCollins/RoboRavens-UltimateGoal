package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import Team7159.BasicRobots.FlywheelBot;
import Team7159.ComplexRobots.LauncherBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Ultimate Goal TeleOp")
public class TeleOpUltimateGoal extends LinearOpMode {
 
    FlywheelBot robot = new FlywheelBot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        double servoPos = 0.0;
        int platformPos = robot.platformMotor.getCurrentPosition();

        while(opModeIsActive()) {
            telemetry.addData("Servo Position: ", robot.insertionServo.getPosition());
            telemetry.addData("Platform Position: ", robot.platformMotor.getCurrentPosition());

            robot.insertionServo.setPosition(servoPos);
            robot.platformMotor.setTargetPosition(platformPos);
            robot.platformMotor.setPower(0.2);

            robot.flywheelMotor.setVelocity(gamepad1.right_trigger * 1500);
            telemetry.addData("Velocity: ", robot.flywheelMotor.getVelocity());

            if (gamepad1.x) {
                robot.intakeMotor.setPower(-1.0);
            }
            if (gamepad1.b) {
                robot.intakeMotor.setPower(0.0);
            }

            if (gamepad1.a) {
                servoPos -= 0.01;
            }
            if (gamepad1.y) {
                servoPos += 0.01;
            }

            if (gamepad1.right_bumper) {
                platformPos += 1;
            }
            if (gamepad1.left_bumper) {
                platformPos -= 1;
            }

            telemetry.update();
        }
    }
}
