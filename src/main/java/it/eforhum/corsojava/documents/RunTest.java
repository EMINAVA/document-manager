package it.eforhum.corsojava.documents;

/*
 * Scrivere un programma che sia in grado di gestire un archivio di documenti
 * ogni documento è composto da
 *
 * ID - identificativo numerico che rappresenta in modo univoco il documento, max 6 digit
 * dato generale dal programma e non inserito dall'utente
 *
 * COD - codice alfanumerico di 6 caratteri - es. 0000B1 - il codice deve essere completato con degli 0 a sinistra quando necessario
 * dato inserito dall'utente
 *
 * DESC - descrizione del documento di 30 caratteri
 * dato inserito dall'utente, se si inseriscono più di 30 caratteri la descrizione sarà terminata con "..."
 *
 * Funzionalità a disposizioni dell'utente
 *
 * 1) inserimento manuale dei dati
 * 2) caricamento attraverso generazione causale di informazioni
 * 3) ricerca e stampa di un documento attraverso l'identificativo
 * 4) stampa paginata della lista documenti così rappresentata
 * 5) modifica documento presente (cod e descrizione)
 * 6) rimozione di un documento presente
 * 7) stampa di tutti i documenti che contengono nella descrizione una query di ricerca
 *
 * pagina 1:
 * da record 1 a record 5
 * ------|------|-------------------
 * ID    |COD   |DESC
 * ------|------|-------------------
 *      1|0000B1|descrizione prova
 *      2|05FG8R|altra descrizione di prova
 *      3|XXXXXX|altra descrizione troppo lu
 */


import java.util.List;

public class RunTest {
	
	public static void main(String[] args) {
		try (UserInterface userInterface = new UserInterface()) {
			userInterface.run();
		}
        DocumentManager docManager = new DocumentManager();
		
		docManager.userDocument("000D44", "primo documento");
		docManager.userDocument("000D45", "secondo documento");

        List<Document> page = docManager.printByDescription("primo");

		System.out.println(page);
	}
}
