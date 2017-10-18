package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sathv on 10/17/2017.
 */

@TeleOp(name = "DriveTank", group = "Sathvik")
//@Disabled
public class DriveTank extends LinearOpMode {
    DcMotor leftfMotor, rightfMotor, leftbMotor, rightbMotor, rPMotor;
    Servo leftClamp, rightClamp;
    float leftY, rightY;
    float leftZ, rightZ;
    boolean bumperL, bumperR;
    float clampMove = 0;

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException {
        leftfMotor = hardwareMap.dcMotor.get("m0");
        rightfMotor = hardwareMap.dcMotor.get("m1");
        leftbMotor = hardwareMap.dcMotor.get("m2");
        rightbMotor = hardwareMap.dcMotor.get("m3");
        rPMotor = hardwareMap.dcMotor.get("rPMotor");
        leftClamp = hardwareMap.servo.get("left_servo");
        rightClamp = hardwareMap.servo.get("right_servo");
        rightfMotor.setDirection(DcMotor.Direction.REVERSE);
        rightbMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.


        waitForStart();

        leftY = 0;
        rightY = 0;
        leftZ = 0;
        rightZ = 0;
        bumperL = false;
        bumperR = false;

        while (opModeIsActive()) {

            telemetry.addData("left stick y ", gamepad1.left_stick_y);
            telemetry.update();
            leftY = -gamepad1.left_stick_y;

            rightY = -gamepad1.right_stick_y;

            leftZ = gamepad1.left_trigger;
            rightZ = gamepad1.right_trigger;

            bumperL = gamepad1.left_bumper;
            bumperR = gamepad1.right_bumper;


            leftfMotor.setPower(leftY);
            leftbMotor.setPower(leftY);
            rightfMotor.setPower(rightY);
            rightbMotor.setPower(rightY);


            if (leftZ > 0) {
                rPMotor.setPower(leftZ/10);

            } else if (rightZ > 0) {
                rPMotor.setPower(-rightZ/10);

            }


            telemetry.addData("Mode", "running");
            telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
            telemetry.addData("trig", "  left=" + leftZ + "  right=" + rightZ);
            telemetry.update();


            if (bumperL) {

                clampMove += .01;
                leftClamp.setPosition(-clampMove);
                rightClamp.setPosition(clampMove);

            } else if (bumperR) {

                clampMove -= .01;
                leftClamp.setPosition(-clampMove);
                rightClamp.setPosition(clampMove);

            }

            idle();


        }
    }
}
