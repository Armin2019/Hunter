package juego;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Exterminador exterminador;
	private Edificio[] edificios;
	private Spider[] spiders;
	private Bala[] balas;
	private Mina[] minas;
	private Telaspider[] telas;
	private Explosion[] explosiones;
	private Random spider;
	private Random tela;
	private Random edificio;
	
	
	int aparece;
	boolean inactivo= false;
	double px;
	double py;
	double edx;
	double edy;
	double angulo;
	int cont;
	int contador;
	Image imgExter;
	Image imgEdif;
	Image imgBala;
	Image imgMina;
	Image imgExplosionMina;
	Image imgSpider;
	Image imgFondo;
	Image imgTelas;
	Image imgOver;
	Image imgWin;
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Choqueticlla, Veron", 800, 600);
		this.exterminador = new Exterminador(400,300,Math.PI*2, 15,3,0.05);
		this.edificios = new Edificio[(int)(Math.random()*5+4)];
		this.spiders = new Spider[10];
		this.balas = new Bala[100];
		this.minas = new Mina[10];
		this.explosiones = new Explosion[10];
		this.telas = new Telaspider[10];
		this.spider = new Random();
		this.tela = new Random();
		this.edificio = new Random();
		this.contador = 0;
		this.aparece = 200;
		this.edx =100;
		this.edy =100;

		
		
		
		
		cont=0;
		imgExter= Herramientas.cargarImagen("exterminador.png");
		imgEdif = Herramientas.cargarImagen("edificios.png");
		imgBala = Herramientas.cargarImagen("Bala.png");
		imgMina= Herramientas.cargarImagen("granada.png");
		imgExplosionMina = Herramientas.cargarImagen("explosionMina.png");
		imgSpider = Herramientas.cargarImagen("spider.png");
		imgFondo = Herramientas.cargarImagen("fondo.jpg");
		imgTelas = Herramientas.cargarImagen("spidertela.png");
		imgOver = Herramientas.cargarImagen("gameover.png");
		imgWin = Herramientas.cargarImagen("win.jpg");
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {

		entorno.dibujarImagen(imgFondo, 0, 0, 0, 3);
		entorno.cambiarFont(Font.DIALOG, 50, Color.WHITE);
		entorno.escribirTexto("PUNTOS: " + contador, 50, 50);

		// dibujo telas
		for (Telaspider red : telas) {
			if (red != null) {
				red.dibujarTela(entorno);

			}

		}
		exterminador.dibujarse(entorno);
		//aleatoridad edificios
		for (int i = 0; i < edificios.length; i++) {
			int Randomex = this.edificio.nextInt(entorno.ancho());
			int Randomey = this.edificio.nextInt(entorno.alto());	
			
			if (Randomex > 100 && Randomex < 300 || Randomex > 450 && Randomex < 600) 
				edx = Randomex;
			
			if (Randomey >100 && Randomey <230 || Randomey > 380 && Randomey<520) 
				edy = Randomey;
			if (edificios[i]==null) {
			this.edificios[i] = new Edificio (edx, edy);
			}
		}
			//dibuja edificios
		for (int e =0; e<edificios.length;e++) {
			
		if (edificios[e]!=null) {
			
			if (edificios[e].colisionasteCon(edificios)) {
				edificios[e] = new Edificio( edx,edy);
			}

			else
				edificios[e].dibujarse(entorno);
			
			}
		}
		

		// dibujarEdificios();
		
		
		
		// dibuja explosiones
		// for (Explosion explo : explosiones) {
		// if (explo != null) {
		// explo.dibujarExplosion(entorno);
		// }
		// }
		controlarExterminador();

		// desaparecer telaspider
		for (int i = 0; i < telas.length; i++) {
			int RandomSpider = this.spider.nextInt(400);
			if (spiders[i] != null && RandomSpider == 100) {
				telas[i] = null;
			}

		}
		// aleatoriedad spider
		int RandomSpider = this.spider.nextInt(400);
		int Randompx = this.spider.nextInt(entorno.ancho());
		int Randompy = this.spider.nextInt(entorno.alto());
		if (RandomSpider == 10 || RandomSpider == 100) {
			for (int x = 0; x < this.spiders.length; x++) {
				if (Randompx < 50 && Randompx > 10
						|| Randompx > entorno.ancho() - 50 && Randompx < entorno.ancho() - 15) {
					px = Randompx;
				}
				if (Randompy < 50 && Randompy > 10
						|| Randompy > entorno.alto() - 50 && Randompy < entorno.alto() - 15) {
					py = Randompy;
				}
				if (spiders[x] == null) {
					spiders[x] = new Spider(px, py, 0, 11, (int) (Math.random() * 4 + 1));
					break;
				}
			}
		}

		// aleatoriedad telas
		int Randomtela = this.tela.nextInt(400);
		if (Randomtela == 100 || Randomtela == 200) {
			for (int x = 0; x < this.spiders.length; x++) {
				if (telas[x] == null && spiders[x] != null) {
					telas[x] = new Telaspider(spiders[x].getX(), spiders[x].getY(), 20);
					break;
				}
			}
		}

		// creador de minas
		if (entorno.sePresiono('m')) {
			for (int mi = 0; mi < minas.length; mi++) {
				if (minas[mi] == null && aparece == 0) {
					minas[mi] = new Mina(exterminador.getX(), exterminador.getY());
					aparece = 10;
				}
			}
		}

		// crea balas
		if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			for (int b = 0; b < balas.length; b++) {
				if (balas[b] == null && aparece == 0) {
					balas[b] = new Bala(exterminador.getX(), exterminador.getY(), exterminador.getAngulo(), 8);
					aparece = 50;
				}

			}
		}
		// hace que salga una a una las minas y balas
		if (aparece != 0) {
			aparece--;
		}

		// dibuja minas
		for (int m = 0; m < minas.length; m++) {
			if (minas[m] != null) {
				minas[m].dibujar(entorno);
			}
		}

		// dibuja balas
		for (Bala b : balas) {
			if (b != null) {
				b.dibujar(entorno);
				b.moverAdelante();

			}

		}
		// dibujo spider
		for (Spider spider : spiders) {
			if (spider != null) {
				spider.dibujar(entorno);
			}
		}

		// colision bala edificio
		for (int b = 0; b < balas.length; b++) {
			if (balas[b] != null && edificios != null) {
				if (balas[b].colisionCon(edificios)) {
					balas[b] = null;
				}
			}
		}

		// Spiders siguen Exterminador
		for (int s = 0; s < spiders.length; s++) {
			if (spiders[s] != null && edificios != null) {
				double dx;
				double dy;
				dx = exterminador.getX() - spiders[s].getX();
				dy = exterminador.getY() - spiders[s].getY();
				this.angulo = Math.atan(dy / dx);

				if (spiders[s].colisionCon(edificios)) {
					spiders[s].setAngulo(Math.PI / 2);
				} else {
					spiders[s].setAngulo(angulo);
				}
				if (exterminador.getX() >= spiders[s].getX()) {
					spiders[s].moverAdelante();
				} else {
					spiders[s].moverAtras();
				}
			}

		}

		// colision spider - balas
		for (int s1 = 0; s1 < spiders.length; s1++) {
			for (int ba = 0; ba < balas.length; ba++) {
				if (spiders[s1] != null && balas[ba] != null) {
					if (spiders[s1].colisionCon(balas)) {
						spiders[s1] = null;
						balas[ba] = null;
						contador++;
					}

				}
			}

		}

		// colision spider- minas
		for (int s1 = 0; s1 < spiders.length; s1++) {
			for (int g = 0; g < minas.length; g++) {

				if (spiders[s1] != null && minas[g] != null) {
					if (spiders[s1].colisionCon(minas)) {
						minas[g] = null;
						spiders[s1] = null;
						contador++;
					}
				}
			}
		}
		win();
		gameOver();

	}

//	public void dibujarEdificios() {
//		
//		int i = 0;
//		while (i < edificios.length) {
//			
//			if (edificios[i].colisionasteCon(edificios)) {
//				edificios[i] = new Edificio( edx,edy);
//			}
//
//			else
//				edificios[i].dibujarse(entorno);
//			i++;
//		}
//		
//	}

	public void controlarExterminador() {
		if (exterminador.chocasteCon(entorno)) {
			exterminador.cambiarTrayectoria();
		}
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			exterminador.moverAdelante();
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			exterminador.girarSentidoHorario();
		}

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			exterminador.girarSentidoAntihorario();
		}
		if (exterminador.colisionCon(edificios)) {
			exterminador.cambiarTrayectoria();
		}
		if (exterminador.colisionCon(telas)) {
			exterminador.reducirVelocidad();
		} else
			exterminador.setVelocidad(3);

	}

	public void win() {
		if (contador >= 20) {

			entorno.dibujarImagen(imgWin, 400, 300, 0, 2);
			entorno.cambiarFont("Verdana", 15, Color.BLUE);
			entorno.escribirTexto("GANASTE!,Presiona Enter para salir", 300, 360);
			if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
				System.exit(0);
			}
		}

	}

	public boolean gameOver() {
		for (Spider spider : spiders) {
			if (exterminador.colisionCon(spiders) || exterminador.colisionCon(explosiones)) {
				entorno.dibujarImagen(imgOver, 400, 300, 0, 0.5);
				entorno.cambiarFont("Times New Roman", 15, Color.cyan);
				entorno.escribirTexto("PERDISTE!,Presiona Enter para salir", 300, 410);
				if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
					System.exit(0);
				}
				return true;

			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
