package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Team7159.ComplexRobots.FlywheelTestBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Flywheel Testing")
public class FlywheelTest extends LinearOpMode {

    private FlywheelTestBot robot = new FlywheelTestBot();

    double flywheelSpeed = 0.0;
    
    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Flywheel Velocity: ", robot.flywheelMotor.getPower());
            if (gamepad1.right_trigger > 0) {
                robot.flywheelMotor.setPower(flywheelSpeed);
            } else {
                robot.flywheelMotor.setPower(0.0);
            }
            if (gamepad1.a && flywheelSpeed > 0.0) {
                flywheelSpeed -= 0.01;
            }
            if (gamepad1.y) {
                flywheelSpeed += 0.01;
            }
            telemetry.update();
        }
    }
}
