package Team7159.ComplexRobots;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlywheelTestBot {

    public DcMotorEx flywheelMotor;

    public void init(HardwareMap Map) {

        flywheelMotor = Map.get(DcMotorEx.class, "flywheelMotor");

        flywheelMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        flywheelMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        flywheelMotor.setPower(0);
    }
}
