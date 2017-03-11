package org.usfirst.frc.team687.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	private int screenWidth = 1240;
	private int screenHeight = 960;
	
	private Point ptleft, ptright, ptup, ptdown;
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Scalar crosshairColor;
	private Mat mat;

    public Vision() {
    	ptleft = new Point((screenWidth/2) - 30, screenHeight/2);
    	ptright = new Point((screenWidth/2) + 30, screenHeight/2);
    	ptup = new Point(screenWidth/2, (screenHeight/2) + 30);
    	ptdown = new Point(screenWidth/2, (screenHeight/2) - 30);
    	
    	mat = new Mat();
    	
    	camera = CameraServer.getInstance().startAutomaticCapture();
    	camera.setResolution(screenWidth, screenHeight);

    	cvSink = CameraServer.getInstance().getVideo();
    	
    	outputStream = CameraServer.getInstance().putVideo("Rectangle", screenWidth, screenHeight);
    	
    	crosshairColor = new Scalar(0,255,0);
    }

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void display(){
    	
		if (cvSink.grabFrame(mat) == 0) {
			outputStream.notifyError(cvSink.getError());
		}
		
		Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),new Scalar(255, 255, 255), 5);
		Imgproc.line(mat, ptleft, ptright, crosshairColor, 3);
		Imgproc.line(mat, ptup, ptdown, crosshairColor, 3);
		
		outputStream.putFrame(mat);
	}
}