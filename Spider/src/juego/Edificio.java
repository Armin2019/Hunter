package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Edificio {
	// Variables de instancia
	private double x, y;
	private double ancho;
	private double alto;
	public Image imgEdif;

	public Edificio(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 100; //fijo
		this.alto = 80; //fijo
		imgEdif = Herramientas.cargarImagen("edificios.png");
	}

	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(imgEdif, x, y, 0, 0.2);
	}

	public boolean colisionasteCon(Edificio[] edificios) {
		for (int i = 0; i < edificios.length; i++) {
			if (this != edificios[i]) {
				if (edificios[i].x < x + ancho && edificios[i].x + ancho > x && edificios[i].y < y + alto
						&& edificios[i].y + alto > y) {
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

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

}

