package it.eforhum.corsojava.documents;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class DocumentManager {
    private final DocumentRepository repository = new DocumentRepository();
    private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
            .withinRange('0', 'z')
            .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
            .build();
    private int lastDocument = 1;

    //  2) caricamento attraverso generazione causale di informazioni
    public Document generateRandom() {
        Lorem lorem = LoremIpsum.getInstance();
        String desc = truncate(lorem.getWords(10, 30));

        String id = StringUtils.leftPad(String.valueOf(lastDocument++), 6);
        String cod = randomStringGenerator.generate(6).toUpperCase();

        Document doc = Document.createDocument(id, cod, desc);
        repository.add(doc);
        return doc;
    }

    //  4) stampa paginata della lista documenti così rappresentata
    public DocumentPage getPage(int numPage) {
        var page = new DocumentPage(numPage);
        repository.paginatedView(page);
        return page;
    }

//	1) inserimento manuale dei dati
	public Document userDocument(String id, String cod, String desc) {
		var doc = Document.createDocument(id, cod, truncate(desc));
        repository.add(doc);
        return doc;
    }
//  3) ricerca e stampa di un documento attraverso l'identificativo
	public Document getById(String id) {
        return repository.getById(id);
    }
//  5) modifica documento presente (cod e descrizione)
	public Document updateDocument(String id, String cod, String desc) {

    }
//  6) rimozione di un documento presente
//  7) stampa di tutti i documenti che contengono nella descrizione una query di ricerca

    public String truncate(String str) {
        if (str.length() <= 30) {
            return str;
        }
        String res = str.substring(0, 27);
        return res + "...";
    }


}

/*
 * scrivere un programma che sia in grado di gestire un archivio di documenti
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
 * funzionalità a disposizioni dell'utente
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