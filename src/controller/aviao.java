package controller;

import java.util.concurrent.Semaphore;

public class aviao extends Thread{
	
	private Semaphore [] pista;
	private int aviao;
	private static int afastamento;
	
      public aviao (Semaphore []pista, int aviao) {
    	this.pista = pista;
    	this.aviao = aviao;
      }
      @Override
    public void run() {
    	int select = 0;
    	try {
			sleep((int) (Math.random()*1000));
			manobra();
	    	taxiar();
	    	select = escolher();
	    	pista[select].acquire();
	    	terminou(select);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} finally {
			pista[select].release();
			voar();
		}
    	
    }
	private void voar() {
		System.out.println(" avião " +aviao+ " se afastando... ");
		int tempo = (int) (Math.random()*5000)+ 3000;
		try {
			sleep(tempo);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		afastamento ++;
		System.out.println(" avião " +aviao+ " foi o"+afastamento+ "° a se afastar("+tempo+"s).");
		
	}
	private void terminou(int select) {
		String nome = select == 0 ? "sul" : "norte";
		System.out.println(" avião " +aviao+ " decolando na pista "+ nome);
		int tempo = (int) (Math.random()*5000)+ 1000;
		try {
			sleep(tempo);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(" avião " +aviao+ " decolou na pista" +nome+" ("+tempo+"s).");
	}
	private int escolher() {
		int selected =(int) Math.round(Math.random());
		if (pista[selected].availablePermits()!=0) {
		
		return selected;
		}else {
			if (pista[0].getQueueLength() <= pista[1].getQueueLength()) {
				return 0;
			
		}else {
			return 1;
		}
			
		
		}
	}
	private void taxiar() {
		System.out.println(" avião " +aviao+ " taxiando");
		int tempo = (int) (Math.random()*5000)+ 5000;
		try {
			sleep(tempo);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(" avião " +aviao+ " acabou de taxiar ("+tempo+"s).");
	}
	private void manobra() {
		System.out.println(" avião " +aviao+ " manobrando");
		int tempo = (int) (Math.random()*4000)+ 3000;
		try {
			sleep(tempo);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(" avião " +aviao+ " acabou de manobrar ("+tempo+"s).");
		
	}
}
