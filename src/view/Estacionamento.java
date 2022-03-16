package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

/*
 * 10 carros est�o distantes de um estacionamento de 1 a 2 km
 * Os carros rodam a velocidade m�dia de 100 m/s
 * O estacionamento tem 3 vagas
 * Os 3 primeiros a chegar estacionam e ficam de 1 a 2 s.
 * Se o estacionamento estiver lotado, os seguintes aguardam em fila
 * A fila � por ordem de chegada
 * Quando um carro sai do estacionamento, o pr�ximo da fila entra
 * Saber a ordem de chegada e de sa�da � importante
 */

public class Estacionamento {

	public static void main(String[] args) {

		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		Semaphore semaforoChegada = new Semaphore(1);
		Semaphore semaforoSaida = new Semaphore(1);
		
		for (int idCarro = 0 ; idCarro < 10 ; idCarro++) {
			Thread tCarro = new ThreadCarro(idCarro, semaforo, 
					semaforoChegada, semaforoSaida);
			tCarro.start();
		}
		
	}

}
