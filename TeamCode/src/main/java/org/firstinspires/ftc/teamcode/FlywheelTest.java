package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Team7159.ComplexRobots.FlywheelBot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Flywheel Testing")
public class FlywheelTest extends LinearOpMode {

    private FlywheelBot robot = new FlywheelBot();

    double flywheelSpeed = 0.0;
    
    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Flywheel Velocity: ", robot.flywheelMotor.getVelocity());
            if (gamepad1.right_trigger > 0) {
                robot.flywheelMotor.setVelocity(flywheelSpeed);
            } else {
                robot.flywheelMotor.setPower(0.0);
            }
            if (gamepad1.a && flywheelSpeed > 0.0) {
                flywheelSpeed -= 0.05;
            }
            if (gamepad1.y) {
                flywheelSpeed += 0.05;
            }
            telemetry.update();
        }
    }
}
