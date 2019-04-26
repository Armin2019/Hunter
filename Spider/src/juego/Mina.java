package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mina {
	private double x;
	private double y;
	private double radio;
	private boolean viva;
	public Image imgMina;
	public Image imgExplosionMina;

	public Mina(double x, double y) {

		this.x = x;
		this.y = y;
		this.radio = 20;
		this.viva = true;
		imgMina= Herramientas.cargarImagen("granada.png");
		imgExplosionMina = Herramientas.cargarImagen("explosionMina.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imgMina, x, y, 0, 2);
		// e0.dibujarCirculo(x, y, 2 * radio, Color.CYAN);
	}
	public void dibujarExplosion(Entorno c) {
		c.dibujarImagen(imgExplosionMina, x, y, 0, 2);
		// c.dibujarCirculo(x, y, 6*radio, Color.RED);
	}

	public boolean colisionCon(Spider[] spiders) {
		for (int i = 0; i < spiders.length; i++) {
			if (spiders[i] != null) {
				double distancia = Math.sqrt((x - spiders[i].getX()) * (x - spiders[i].getX())
						+ (y - spiders[i].getY()) * (y - spiders[i].getY()));
				if (distancia < radio + spiders[i].getRadio()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean colisionCon(Mina[] minas) {
		for (int i = 0; i < minas.length; i++) {
			if (this != minas[i]) {
				double distancia = Math.sqrt((x - minas[i].getX()) * (x - minas[i].getX())
						+ (y - minas[i].getY()) * (y - minas[i].getY()));
				if (distancia < radio + minas[i].getRadio()) {
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

	public boolean isViva() {
		return viva;
	}

	public void setViva(boolean viva) {
		this.viva = viva;
	}

}
