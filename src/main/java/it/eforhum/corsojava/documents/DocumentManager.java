package it.eforhum.corsojava.documents;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import java.util.ArrayList;
import java.util.List;

public class DocumentManager {

	private final DocumentRepository repository = new DocumentRepository();

	private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
			.withinRange('0', 'z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();

	private int lastDocument = 1;

	// 2) caricamento attraverso generazione causale di informazioni
	public Document generateRandom() {
		Lorem lorem = LoremIpsum.getInstance();
		String desc = truncate(lorem.getWords(10, 30));

		String id = StringUtils.leftPad(String.valueOf(lastDocument++), 6);
		String cod = randomStringGenerator.generate(6).toUpperCase();

		Document doc = Document.createDocument(id, cod, desc);
		repository.add(doc);
		return doc;
	}

	// 4) stampa paginata della lista documenti così rappresentata
	public DocumentPage getPage(int numPage) {
		var page = new DocumentPage(numPage);
		repository.paginatedView(page);
		return page;
	}

//	1) inserimento manuale dei dati
	public Document userDocument(String id, String cod, String desc) {
		
		final int MAX_COD_SIZE = 6;
		
		Document doc = new Document.Builder()
				.setId(id)
				.setDescription(truncate(desc))
				.buildDoc();
		
		/* 
		 * COD - codice alfanumerico di 6 caratteri 
		 * il codice deve essere completato con degli 0 a 
		 * sinistra quando necessario
		 * dato inserito dall'utente
		*/
		
		if (cod.length() < MAX_COD_SIZE) {
			doc.setCode(StringUtils.leftPad(cod, MAX_COD_SIZE, "0" ));
		}
		else {
			doc.setCode(cod);
		}
		
		repository.add(doc);
		return doc;
	}

//  3) ricerca e stampa di un documento attraverso l'identificativo
	public Document getById(String id) {
		return repository.getById(id);
	}

//  5) modifica documento presente (cod e descrizione)
	public Document updateDocument(String id, String cod, String desc) {
		var doc = repository.update(id, cod, desc);
		repository.add(doc);
		return doc;
	}

//  6) rimozione di un documento presente
	public void removeDocument(String id) {
		repository.delete(id);
	}

//  7) stampa di tutti i documenti che contengono nella descrizione una query di ricerca

	public List<Document> printByDescription(String query) {
		List<Document> results = repository.searchByDescription(query);
		return results;
	}

	public String truncate(String str) {
		if (str.length() <= 30) {
			return str;
		}
		String res = str.substring(0, 27);
		return res + "...";
	}

}
