package it.eforhum.corsojava.documents;

public class RunTest {
	public static void main(String[] args) {
		Document doc = new Document.Builder()
				.setId("1")
				.setCode("000DOC1")
				.setDescription("Primo documento")
				.buildDoc();

		// Oppure

//		doc = Document.createDocument("1", "000DOC1", "Primo documento");
	
		System.out.println(doc.getId());
		System.out.println(doc.getCode());
		System.out.println(doc.getDescription());
	}
}
