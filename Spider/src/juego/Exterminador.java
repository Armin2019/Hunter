package juego;
import java.awt.Image;
import java.awt.Color;
import entorno.Herramientas;
import entorno.Entorno;

public class Exterminador {

	private double x;
	private double y;
	private double angulo;
	double radio;
	private double velocidad;
	private double velocidadDeGiro;
	private boolean estado;
	public Image imgExter;

	

	public Exterminador(double x, double y, double angulo,double radio, double velocidad,
			double velocidadDeGiro) {
		
		this.x = x;
		this.y = y;
		this.angulo= angulo;
		this.radio = radio;
		this.velocidad = velocidad;
		this.velocidadDeGiro = velocidadDeGiro;
		imgExter = Herramientas.cargarImagen("exterminador.png");
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imgExter, this.x, this.y, this.angulo, 1);
		// entorno.dibujarCirculo(x, y, radio*2, Color.BLUE);

	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getVelocidadDeGiro() {
		return velocidadDeGiro;
	}

	public void setVelocidadDeGiro(double velocidadDeGiro) {
		this.velocidadDeGiro = velocidadDeGiro;
	}

	public void girarSentidoHorario() {
		angulo += velocidadDeGiro;
	}

	public void girarSentidoAntihorario() {
		angulo -= velocidadDeGiro;
	}

	public void moverAdelante() {
		this.x += Math.cos(this.angulo) * velocidad;
		this.y += Math.sin(this.angulo) * velocidad;
	}

	public boolean chocasteCon(Entorno e) {
		return x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio;
	}

	public boolean colisionCon(Edificio[] edificios) {
		for (int i = 0; i < edificios.length; i++) {
			if (edificios[i] != null) {
				double px = x;
				double py = y;
				if (px < edificios[i].getX() - edificios[i].getAncho() / 2) {
					px = edificios[i].getX();
				}
				if (px > edificios[i].getX() - edificios[i].getAncho() / 2 + edificios[i].getAncho()) {
					px = edificios[i].getX() - edificios[i].getAncho() / 2 + edificios[i].getAncho();
				}
				if (py < edificios[i].getY() - edificios[i].getAlto() / 2) {
					py = edificios[i].getY();
				}
				if (py > edificios[i].getY() - edificios[i].getAlto() / 2 + edificios[i].getAlto()) {
					py = edificios[i].getY() - edificios[i].getAlto() / 2 + edificios[i].getAlto();
				}
				double distancia = Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
				if (distancia < radio) {
					return true;
				}
			}
		}
		return false;
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

	public boolean colisionCon(Telaspider[] telas) {
		for (int i = 0; i < telas.length; i++) {
			if (telas[i] != null) {
				double distancia = Math.sqrt(
						(telas[i].getX() - x) * (telas[i].getX() - x) + (telas[i].getY() - y) * (telas[i].getY() - y));
				if (distancia < radio + telas[i].getRadio()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean colisionCon(Explosion[] explo) {
		for (int i = 0; i < explo.length; i++) {
			if (explo[i] != null) {
				double distancia = Math.sqrt(
						(x - explo[i].getX()) * (x - explo[i].getX()) + (y - explo[i].getY()) * (y - explo[i].getY()));
				if (distancia < radio + explo[i].getRadio()) {
					return true;
				}
			}
		}
		return false;
	}

	public void reducirVelocidad() {
		velocidad = velocidad - 0.2;
	}

	public void cambiarTrayectoria() {
		angulo += Math.PI;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getAngulo() {
		return this.angulo;
	}

}
