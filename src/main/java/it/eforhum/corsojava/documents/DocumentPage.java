package it.eforhum.corsojava.documents;

import java.util.List;

public class DocumentPage {
	private List<Document> documents;
	private int totalResults;
	private int totalPages;
	
	public List<Document> getDocuments() {
		return documents;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public int getTotalPages() {
		return totalPages;
	}
	private void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	private void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	private void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
