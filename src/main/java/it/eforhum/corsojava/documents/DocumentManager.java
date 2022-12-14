package it.eforhum.corsojava.documents;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.util.List;

public class DocumentManager {

	private final DocumentRepository repository = new DocumentRepository();

	private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
			.withinRange('0', 'z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();

	private int lastDocument = 1;

	// 1) inserimento manuale dei dati
	public Document userDocument(String cod, String desc) {
		Document doc = new Document.Builder().setId(nextId()).setDescription(truncate(desc)).setCode(fixCode(cod))
				.buildDoc();

		repository.add(doc);
		return doc;
	}

	// 2) caricamento attraverso generazione causale d'informazioni
	public Document generateRandom() {
		Lorem lorem = LoremIpsum.getInstance();
		String desc = truncate(lorem.getWords(10, 30));

		String id = nextId();
		String cod = randomStringGenerator.generate(Document.CODE_MAX_LENGTH).toUpperCase();

		Document doc = Document.createDocument(id, cod, desc);
		repository.add(doc);
		return doc;
	}

	// 3) ricerca e stampa di un documento attraverso l'identificativo
	public Document getById(String id) {
		return repository.getById(id);
	}

	// 4) stampa paginata della lista documenti così rappresentata
	public DocumentPage getPage(int numPage) {
		DocumentPage page = new DocumentPage(numPage);
		repository.paginatedView(page);
		return page;
	}

	// 5) modifica documento presente (cod e descrizione)
	public Document updateDocument(String id, String cod, String desc) {
		return repository.update(id, fixCode(cod), truncate(desc));
	}

	// 6) rimozione di un documento presente
	public boolean removeDocument(String id) {
		return repository.delete(id);
	}

	// 7) stampa di tutti i documenti che contengono nella descrizione una query di
	// ricerca
	public List<Document> printByDescription(String query) {
		return repository.searchByDescription(query);
	}

	private String truncate(String str) {
		if (str.length() <= Document.DESCRIPTION_MAX_LENGTH) {
			return str;
		}
		String res = str.substring(0, 27);
		return res + "...";
	}

	/*
	 * COD - codice alfanumerico di 6 caratteri il codice deve essere completato con
	 * degli 0 a sinistra quando necessario dato inserito dall'utente
	 */

	private String fixCode(String cod) {
		if (cod.length() < Document.CODE_MAX_LENGTH) {
			return StringUtils.leftPad(cod, Document.CODE_MAX_LENGTH, "0");
		}
		if (cod.length() > Document.CODE_MAX_LENGTH || !StringUtils.isAlphanumeric(cod)) {
			throw new IllegalArgumentException(String
					.format("Il codice del documento dev'essere lungo massimo %d caratteri", Document.CODE_MAX_LENGTH));
		}
		return cod;
	}

	private String nextId() {
		return StringUtils.leftPad(String.valueOf(lastDocument++), Document.ID_MAX_LENGTH);
	}
}
