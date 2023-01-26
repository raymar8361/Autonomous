package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    static final double INCREMENT   = 0.01;
    static final double MAX_POS     =  0.0;
    static final double MIN_POS     =   1.0;
    static final int    CYCLE_MS    =   50;
    double rate = .004;

    double position = MIN_POS;
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor motorActuatorLeft = hardwareMap.dcMotor.get("motorActuatorLeft");
        DcMotor motorActuatorRight = hardwareMap.dcMotor.get("motorActuatorRight");
        DcMotor motorActuatorBottom = hardwareMap.dcMotor.get("motorActuatorBottom");
        Servo clawServo = hardwareMap.servo.get("clawServo");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorActuatorBottom.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {
            double y = .25 * -gamepad1.left_stick_y; // Remember, this is reverse
            double x = .25 * gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = .25 * gamepad1.right_stick_x;
            double z = .75*-gamepad2.left_stick_y;
            double w = .75*gamepad2.right_stick_y;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            double leftActuatorPower = z;
            double rightActuatorPower = z;
            double bottomActuatorPower = z;

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
            motorActuatorLeft.setPower(leftActuatorPower);
            motorActuatorRight.setPower(rightActuatorPower);
            motorActuatorBottom.setPower(bottomActuatorPower);

            clawServo.setPosition(position);
            if (gamepad2.left_bumper && position > MAX_POS)
            {
                position -= INCREMENT;
                clawServo.setPosition(position);
               /* position -= INCREMENT;
                motorBackRight.setPower(1);
                clawServo.setPosition(position);
                */
            }
            if (gamepad2.right_bumper && position < MIN_POS)
            {
                //clawServo.setPosition(MAX_POS);
                position += INCREMENT;
                clawServo.setPosition(position);
            }


        }

        sleep(CYCLE_MS);
        idle();
    }
}
