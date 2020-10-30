package Team7159.ComplexRobots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Team7159.BasicRobots.BasicMecanum;

public class LauncherBot extends BasicMecanum {
    public DcMotor intakeMotor;

    public DcMotor launchMotor;

    public DcMotor armMotor;

    public Servo clawServo;

    public Servo intakeServo;

    public void init (HardwareMap Map) {

        super.init(Map);

        intakeMotor = Map.dcMotor.get("IntakeMotor");
        launchMotor = Map.dcMotor.get("LaunchMotor");
        armMotor = Map.dcMotor.get("ArmMotor");

        clawServo = Map.servo.get("ClawServo");
        intakeServo = Map.servo.get("IntakeServo");

        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
