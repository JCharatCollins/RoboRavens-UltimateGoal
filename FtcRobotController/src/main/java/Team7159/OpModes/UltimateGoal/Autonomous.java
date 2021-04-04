package Team7159.OpModes.UltimateGoal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import Team7159.ComplexRobots.FlywheelBot;
import Team7159.Enums.Direction;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
public class Autonomous extends LinearOpMode {

    private FlywheelBot robot = new FlywheelBot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        robot.intakeServo.scaleRange(0.34, 0.80);
        robot.intakeServo.setPosition(0.0);

        robot.flywheelServo.setPosition(1.0);

        waitForStart();

        robot.platformMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        robot.platformMotor.setTargetPosition(166);
        robot.platformMotor.setPower(0.075);
        robot.platformMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        drive(0.5, 2);

        //drive up to line
        sleep(500);

        //fire
        readyFlywheel();
        fireRing();
        fireRing();           
        fireRing();
        unreadyFlywheel();

        //drive onto line
        drive(0.5, 0.75);
    }

    private void drive(double pow, double time) {
        double t = time*1000;
        int t1 = (int)t;
        robot.LFMotor.setPower(pow);
        robot.RFMotor.setPower(pow);
        robot.LBMotor.setPower(pow);
        robot.RBMotor.setPower(pow);
        sleep(t1);
        stopMotors();
    }

    private void stopMotors(){
        robot.stop();
    }

    public void strafe2(Direction direction, double power, int t) {
        if(direction == Direction.LEFT) {
            robot.LFMotor.setPower(-power);
            robot.RFMotor.setPower(power);
            robot.LBMotor.setPower(power);
            robot.RBMotor.setPower(-power);
        } else if(direction == Direction.RIGHT) {
            robot.LFMotor.setPower(power);
            robot.RFMotor.setPower(-power);
            robot.LBMotor.setPower(-power);
            robot.RBMotor.setPower(power);
        }
        sleep(t*1000);
        stopMotors();
    }

    public void readyFlywheel() {
        robot.flywheelMotor.setPower(0.50);
        sleep(2000);
    }
    public void unreadyFlywheel() {
        robot.platformMotor.setTargetPosition(0);
        robot.platformMotor.setPower(0.075);
        robot.platformMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.flywheelMotor.setPower(0.0);
        sleep(2000);
    }
    public void fireRing() {
        robot.flywheelServo.setPosition(0.0);
        sleep(500);
        robot.flywheelServo.setPosition(1.0);
        sleep(500);
    }
}
