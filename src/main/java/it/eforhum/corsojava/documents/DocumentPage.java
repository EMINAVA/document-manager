package it.eforhum.corsojava.documents;

import java.util.List;

public class DocumentPage {
    private final List<Document> documents;
	private final int totalPages;
	private final int totalResults;

	private DocumentPage(List<Document> documents, int totalPages, int totalResults) {
		this.documents = documents;
		this.totalPages = totalPages;
		this.totalResults = totalResults;
	}

	public List<Document> getDocuments() {
        return documents;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }


    public static class Builder {
        private List<Document> documents;
		private int totalPages;
		private int totalResults;

        public Builder() {
            this.totalResults = 0;
            this.totalPages = 0;
        }

        public DocumentPage buildDocPage() {
            return new DocumentPage(documents, totalPages, totalResults);
        }

        public Builder setDocuments(List<Document> documents) {
            this.documents = documents;
            return this;
        }

        public Builder setTotalResults(int totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        public Builder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }
    }

	public static DocumentPage createDocumentPage(List<Document> documents, int totalPages, int totalResults) {
		return new DocumentPage(documents, totalPages, totalResults);
	}
}
