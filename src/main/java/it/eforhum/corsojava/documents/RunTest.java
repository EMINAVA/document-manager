package it.eforhum.corsojava.documents;

public class RunTest {
	public static void main(String[] args) {
//		Document doc = new Document.Builder()
//				.setId("1")
//				.setCode("000DOC1")
//				.setDescription("Primo documento")
//				.buildDoc();

		// Oppure

//		doc = Document.createDocument("1", "000DOC1", "Primo documento");

		var docManager = new DocumentManager();

		docManager.generateRandom();
		docManager.generateRandom();
		docManager.generateRandom();
		docManager.generateRandom();
		docManager.generateRandom();
		docManager.generateRandom();
		docManager.generateRandom();
		docManager.generateRandom();

		var page = docManager.getPage(1);

		System.out.println(page);
	}
}
