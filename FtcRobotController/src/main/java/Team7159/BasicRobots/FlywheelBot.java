package Team7159.BasicRobots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import Team7159.Enums.Direction;
import Team7159.Enums.Version;
import Team7159.Utils.MotorGroup;
import Team7159.Utils.RobotComp;
import Team7159.Utils.RobotMath;

public class FlywheelBot {

    public DcMotorEx flywheelMotor;

    public DcMotor platformMotor;
    public DcMotor intakeMotor;

    public Servo insertionServo;

    public void init(HardwareMap Map) {

        flywheelMotor = Map.get(DcMotorEx.class, "flywheelMotor");

        intakeMotor = Map.dcMotor.get("intakeMotor");
        platformMotor = Map.dcMotor.get("platformMotor");

        insertionServo = Map.servo.get("insertionServo");

        flywheelMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        platformMotor.setMode((DcMotor.RunMode.RUN_USING_ENCODER));

        flywheelMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        platformMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //:crab: william is gone :crab:
    }
}
