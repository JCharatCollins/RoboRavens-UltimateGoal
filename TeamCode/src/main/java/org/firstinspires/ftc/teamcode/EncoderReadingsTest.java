package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import Team7159.ComplexRobots.FlywheelBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Encoder Positional Test")
public class EncoderReadingsTest extends LinearOpMode {

    private FlywheelBot robot = new FlywheelBot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        double flywheelServoPos = 0.0;
        double intakeServoPos = 0.0;

        robot.platformMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        int platformPos = robot.platformMotor.getCurrentPosition();

        while(opModeIsActive()) {
            robot.flywheelServo.setPosition(flywheelServoPos);
            robot.intakeServo.setPosition(intakeServoPos);

            telemetry.addData("Flywheel Servo Position: ", robot.flywheelServo.getPosition());
            telemetry.addData("Intake Servo Position: ", robot.intakeServo.getPosition());
            telemetry.addData("Platform Position: ", robot.platformMotor.getCurrentPosition());

            if (gamepad1.a) {
                flywheelServoPos -= 0.01;
            }
            if (gamepad1.y) {
                flywheelServoPos += 0.01;
            }
            if (gamepad1.x) {
                intakeServoPos -= 0.01;
            }
            if (gamepad1.b) {
                intakeServoPos += 0.01;
            }

            telemetry.update();
        }
    }
}
