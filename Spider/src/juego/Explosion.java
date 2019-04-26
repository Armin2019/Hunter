package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Explosion {
	private double x;
	private double y;
	private double radio;
	public Image imgExplosionMina;
	
	public Explosion(double x, double y, double radio) {
		super();
		this.x = x;
		this.y = y;
		this.radio = radio;
		imgExplosionMina = Herramientas.cargarImagen("explosionMina.png");
	}

	public void dibujarExplosion(Entorno c) {
		c.dibujarImagen(imgExplosionMina, x, y, 0, 2);
		// c.dibujarCirculo(x, y, 6*radio, Color.RED);
	}

	public boolean colisionCon(Mina[] mi) {
		for (int i = 0; i < mi.length; i++) {
			if (mi[i] != null) {
				double distancia = Math
						.sqrt((x - mi[i].getX()) * (x - mi[i].getX()) + (y - mi[i].getY()) * (y - mi[i].getY()));
				if (distancia < radio + mi[i].getRadio()) {
					return true;
				}
			}
		}
		return false;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

}
