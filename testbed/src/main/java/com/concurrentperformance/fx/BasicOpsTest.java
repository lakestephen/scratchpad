package com.concurrentperformance.fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * TODO SJL Comment
 *
 * @author Stephen Lake
 */
public class BasicOpsTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Drawing Operations Test");
		Group root = new Group();
		Canvas canvas = new Canvas(300, 250);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1.2);

		gc.strokeLine(40, 400, 10, 40);



		BoxBlur blur = new BoxBlur();
		blur.setWidth(1);
		blur.setHeight(1);
		blur.setIterations(1);
		gc.setEffect(blur);



		gc.strokeLine(snap(50), snap(500), snap(20), snap(50));

		gc.beginPath();
		gc.moveTo(   0.0,   0.0);
		gc.lineTo(100.0, 10.0);
		gc.stroke();


/*		gc.fillOval(10, 60, 30, 30);
		gc.strokeOval(60, 60, 30, 30);
		gc.fillRoundRect(110, 60, 30, 30, 10, 10);
		gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
		gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
		gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
		gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
		gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
		gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
		gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
		gc.fillPolygon(new double[]{10, 40, 10, 40},
				new double[]{210, 210, 240, 240}, 4);
		gc.strokePolygon(new double[]{60, 90, 60, 90},
				new double[]{210, 210, 240, 240}, 4);
		gc.strokePolyline(new double[]{110, 140, 110, 140},
				new double[]{210, 210, 240, 240}, 4);*/

	}

	private double snap(double y) {
		return ((int) y) + .5;
	}
}