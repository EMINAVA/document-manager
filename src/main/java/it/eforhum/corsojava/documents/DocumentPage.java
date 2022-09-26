package it.eforhum.corsojava.documents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentPage {
    public static final int  RESULTS_PER_PAGE = 5;
    private List<Document> documents = new ArrayList<>();
    private int pageNumber;
	private int totalPages;
	private int totalResults;

	public DocumentPage(int pageNumber) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Non è possibile prendere una pagina negativa");
        }
        this.pageNumber = pageNumber;
	}

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return Arrays.toString(documents.toArray());
    }
}
