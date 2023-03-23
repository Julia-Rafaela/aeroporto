package view;

import java.util.concurrent.Semaphore;

import controller.aviao;

public class main {

	public static void main(String[] args) {
		Semaphore PistaSul = new Semaphore(1);
		Semaphore PistaNorte = new Semaphore(1);
		Semaphore [] pista = {PistaSul , PistaNorte};
		
      for (int aviao = 0; aviao <12; aviao++) {
    	  Thread fly = new aviao(pista, aviao);
			fly.start();
      }
	}

}
