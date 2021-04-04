package Team7159.ComplexRobots;

/**
 * Created by Joshua Charat-Collins, 2020-2021
 * Robot used in Ultimate Goal.
 * This season sucked.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import Team7159.BasicRobots.BasicMecanum;

public class FlywheelBot2 extends BasicMecanum {

    public DcMotorEx flywheelMotor;
    public DcMotorEx platformMotor;

    public DcMotor intakeMotor;

    public Servo flywheelServo;
    public Servo intakeServo;
    public Servo clawServo;

    public void init(HardwareMap Map) {

        super.init(Map);

        flywheelMotor = Map.get(DcMotorEx.class, "flywheelMotor");
        platformMotor = Map.get(DcMotorEx.class, "platformMotor");

        intakeMotor = Map.dcMotor.get("intakeMotor");

        flywheelServo = Map.servo.get("flywheelServo");
        clawServo = Map.servo.get("clawServo");
        intakeServo = Map.servo.get("intakeServo");

        flywheelMotor.setDirection(DcMotorEx.Direction.REVERSE);

        flywheelMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        platformMotor.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));

        flywheelMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        platformMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        flywheelMotor.setPower(0);
        intakeMotor.setPower(0);
        platformMotor.setPower(0);

        flywheelServo.scaleRange(0.21, 0.65);
        intakeServo.setPosition(0.0);

        //:crab: william is gone :crab:
    }
}
