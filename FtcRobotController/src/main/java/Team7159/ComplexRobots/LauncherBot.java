package Team7159.ComplexRobots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Team7159.BasicRobots.BasicMecanum;

public class LauncherBot extends BasicMecanum {
    public DcMotorEx intakeMotor;

    public DcMotorEx launchMotor;

    public DcMotorEx armMotor;

    public Servo clawServo;

    public Servo intakeServo;

    public void init (HardwareMap Map) {

        super.init(Map);

        intakeMotor = Map.get(DcMotorEx.class, "intakeMotor");
        launchMotor = Map.get(DcMotorEx.class, "launchMotor");
        armMotor = Map.get(DcMotorEx.class, "armMotor");

        clawServo = Map.servo.get("ClawServo");
        intakeServo = Map.servo.get("IntakeServo");

        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
