package application;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

/**
 * Klasse zur Implementierung der Monte Carlo Simulation (berechnung der
 * Kreiszahl pi)
 * 
 * @author Hannes Harnisch
 *
 */
public class MonteCarlo {
	private Random rand1 = new Random();
	/**
	 * Anzahl der simulierten "Regentropfen"
	 */
	private static int numberofraindrops = 1;
	/**
	 * boolean ob der "Regentropfen" im Kreis gelandet ist
	 */
	private boolean isincircle = false;

	/**
	 * Methode mit der der User eine Anzahl von Regentropfen festlegen kann
	 * 
	 * @param i
	 *            : nummer der Regentropfen
	 */
	public void init(int i) {
		numberofraindrops = i;
	}

	/**
	 * errechent mit der Anzahl von Regentropfen im Kreis und der Gesammtanzahl
	 * der Regentropfen pi
	 * 
	 * @return Kreiszahl pi
	 */
	public double calculate(Group groupcenter, Scene scene) {
		double pi = 0;
		int ti = loop(groupcenter, scene);
		pi = (4 * (double) ti) / (double) numberofraindrops;
		return pi;
	}

	/**
	 * simuliert Regentropfen
	 * 
	 * @return Anzahl von Regentropfen im Kreis
	 */
	private int loop(Group groupcenter, Scene scene) {
		/*
		 * ti ist die Anzahl von Regentropfen die im Kreis liegen
		 */
		int ti = 0;
		/*
		 * tnoti ist die Anzahl von Regentropfen die nicht im Kreis liegen
		 */
		int tnoti = 0;
		for (int i = 0; i < numberofraindrops; i++) {

			
			double x = rand1.nextDouble();
			//System.out.println(x);
			double y = rand1.nextDouble();
			//System.out.println(y);
			plotraindrop(groupcenter, x, y, scene);
			isincircle(x, y);
			if (isincircle) {
				//System.out.println("isincircle: " + isincircle + " / " + ti + "  / " + tnoti);
				ti++;
			} else {
				//System.out.println("isincircle: " + isincircle + "!!!!!!!  " + ti + " / " + tnoti);
				tnoti++;
			}
			System.out.println(i+1+"\t"+(4 * (double) ti) / (double) numberofraindrops);
			// System.out.println(x+" : "+y);

		}
		return ti;
	}

	private void plotraindrop(Group groupcenter, double x, double y, Scene scene) {
		// TODO Auto-generated method stub
		Circle c = new Circle(2);
		c.setOpacity(0.7);
		c.setFill(Color.BLUE);
		c.getTransforms().add(new Translate(50 + x * (scene.getWidth()-100), scene.getHeight()-50 - y * (scene.getHeight()-100)));
		groupcenter.getChildren().add(c);
	}

	/**
	 * errechnet ob der "Regentropfen" im Kreis liegt
	 * 
	 * @param x ist die x koordiante des "Regentropfens"
	 * 
	 * @param y ist die y koordiante des "Regentropfens"
	 * 
	 */
	private void isincircle(double x, double y) {
		// TODO Auto-generated method stub
		/*System.out.println("x2: " + x * x);
		System.out.println("y2: " + y * y);
		System.out.println("y2+x2: " + x * x + y * y);
		System.out.println("wurzel: " + Math.sqrt(x * x + y * y));*/
		double c = Math.sqrt(((x * x) + (y * y)));
		//System.out.println(c);
		if (c <= 1) {
			isincircle = true;
		} else {
			isincircle = false;
		}
	}

}
