package TP2; /**
 * 
 */
import java.lang.String;

class Exclusion{}

public class Affichage extends Thread{
	String texte;
	//static Exclusion ex = new Exclusion();
	static semaphoreBinaire semaphoreBinaire = new semaphoreBinaire(1);

	public Affichage (String txt){texte=txt;}


	//Méthode synchronisée par "synchronised"
	/*
	public void run(){
		synchronized (ex){
			for (int i=0; i<texte.length(); i++){
					System.out.print(texte.charAt(i));
				try {sleep(100);} catch(InterruptedException e){};
			}
		}

	}*/

	//Méthode synchronisée par Sémaphore
	public void run(){
		semaphoreBinaire.syncWait();
		System.out.println("je rentre en section critique");
		for (int i=0; i<texte.length(); i++){
			System.out.print(texte.charAt(i));
			try {sleep(100);} catch(InterruptedException e){};
		}

		semaphoreBinaire.syncSignal();
		System.out.println("\nje sors de section critique \n");
	}
}
