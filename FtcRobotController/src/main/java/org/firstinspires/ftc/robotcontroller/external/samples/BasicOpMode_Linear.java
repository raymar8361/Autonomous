package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous

public class GG {
    private Blinker control_Hub;
    private Blinker expansion_Hub_7;
    private Servo clawServo;
    private Gyroscope imu;
    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;
    private DcMotor motorActuatorLeft;
    private DcMotor motorActuatorRight;
    private DcMotor motorActuatorBottom;
    private Servo ralle;

    public void GG()
    {
        double power1 = 1;
        double power2 = -1;
        double count = 0;


        if (count < 10) {
            motorFrontLeft.setPower(power1);
            motorFrontRight.setPower(power2);
            motorBackLeft.setPower(power1);
            motorBackRight.setPower(power2);
            count++;
        }
        if (count > 10) {
            motorFrontLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackLeft.setPower(0);
            motorBackRight.setPower(0);
        }

    }
}