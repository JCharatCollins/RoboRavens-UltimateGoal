package Team7159.BasicRobots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import Team7159.Enums.Direction;
import Team7159.Enums.Version;
import Team7159.Utils.MotorGroup;
import Team7159.Utils.RobotComp;
import Team7159.Utils.RobotMath;

public class TestRobot {

    public DcMotorEx testMotor;

    public void init(HardwareMap Map) {

        testMotor = Map.get(DcMotorEx.class, "launchMotor");
        testMotor.setPower(0);
        //:crab: william is gone :crab:
    }
}
