package it.eforhum.corsojava.documents;

import java.util.List;

public class DocumentPage {
    public static final int  RESULTS_PER_PAGE = 5;
    private List<Document> documents;
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
        if (documents.size() == 0) {
            return "[]";
        }
        var sb = new StringBuilder("[");
        for (Document document : documents) {
            sb.append(document).append(", \n");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
