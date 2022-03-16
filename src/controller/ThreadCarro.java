package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int idCarro;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;
	private Semaphore semaforoChegada;
	private Semaphore semaforoSaida;
	
	public ThreadCarro(int idCarro, Semaphore semaforo, 
			Semaphore semaforoChegada, Semaphore semaforoSaida) {
		this.idCarro = idCarro;
		this.semaforo = semaforo; 
		this.semaforoChegada = semaforoChegada;
		this.semaforoSaida = semaforoSaida;
	}
	
	@Override
	public void run() {
		carroAndando();
		try {
			semaforo.acquire();
			carroEstacionado();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		carroSaindo();
	}

	private void carroAndando() {
		int distanciaPercorrida = 0;
		int distanciaTotal = (int)((Math.random() * 1001) + 1000);
		int deslocamento = 100;
		int tempo = 100;
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("O carro #"+idCarro+" percorreu "+distanciaPercorrida+" m.");
		}
		System.out.println("O carro #"+idCarro+" chegou");
	}

	private void carroEstacionado() {
		try {
			semaforoChegada.acquire();
			posChegada++;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			semaforoChegada.release();
		}

		System.out.println("O carro #"+idCarro+" foi o "+posChegada+"o. a estacionar");
		int tempo = (int)((Math.random() * 1001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			semaforoSaida.acquire();
			posSaida++;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			semaforoSaida.release();
		}

		System.out.println("O carro #"+idCarro+" foi o "+posSaida+"o. a sair");
	}

	private void carroSaindo() {
		System.out.println("O carro #"+idCarro+" saiu");
	}

}
