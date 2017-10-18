package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sathv on 10/17/2017.
 */

@TeleOp(name = "Blue", group = "Sathvik")
//@Disabled
public class Blue extends LinearOpMode {

    DcMotor leftfMotor, rightfMotor;
    DcMotor leftbMotor, rightbMotor;

    Servo backServo;

    ColorSensor cs;

    public void runOpMode() throws InterruptedException {
        leftfMotor = hardwareMap.dcMotor.get("m0");
        rightfMotor = hardwareMap.dcMotor.get("m1");
        leftbMotor = hardwareMap.dcMotor.get("m2");
        rightbMotor = hardwareMap.dcMotor.get("m3");
        cs = hardwareMap.colorSensor.get("cs");

        //rightfMotor.setDirection(DcMotor.Direction.REVERSE);
        //rightbMotor.setDirection(DcMotor.Direction.REVERSE);

        backServo = hardwareMap.servo.get("back_servo");

        waitForStart();

        backServo.setPosition(.45);
        //omniDrive(leftbMotor, rightbMotor, leftfMotor, rightfMotor, -.25, -.25, 10000);

        while(opModeIsActive()) {

            telemetry.addData("Color Sensor blue: ", cs.blue());
            telemetry.addData("Color Sensor red: ", cs.red());
            telemetry.addData("Color Sensor green: ", cs.green());
            telemetry.update();


        }
    }

    public void omniDrive(DcMotor leftbMotor, DcMotor rightbMotor, DcMotor leftfMotor, DcMotor rightfMotor, double powerR, double powerL, int time ) {
        leftfMotor.setPower(-powerL);
        rightfMotor.setPower(powerR);
        leftbMotor.setPower(-powerL);
        rightbMotor.setPower(powerR);

        sleep(time);

        leftfMotor.setPower(0);
        rightfMotor.setPower(0);
        leftbMotor.setPower(0);
        rightbMotor.setPower(0);
    }
}
