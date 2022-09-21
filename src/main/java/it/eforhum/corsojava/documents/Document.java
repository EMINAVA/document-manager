package it.eforhum.corsojava.documents;

public class Document {
	private final String id;
	private final String code;
	private final String description;

	private Document(String id, String code, String description) {
		this.code = code;
		this.id = id;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static class Builder {		// 	Build pattern
		private String code;
		private String id;
		private String description;
		
		public Builder() {
			this.id = "";
			this.code = "";
			this.description = "";
		}
		
		public Document buildDoc() {
			return new Document(id, code, description);
		}

		public Builder setCode(String code) {
			this.code = code;
			return this;
		}

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
	}
	// Oppure

	public static Document createDocument(String id, String code, String description) {
		return new Document(id, code, description);
	}
}
