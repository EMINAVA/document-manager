package it.eforhum.corsojava.documents;

import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {
    private final ArrayList<Document> documents = new ArrayList<>();

    public void add(Document doc) {
        documents.add(doc);
    }

    public Document getById(String id) {
        for (Document document : documents) {
            if (compareIds(id, document.getId())) {
                return document;
            }
        }
        return null;
    }

    public List<Document> searchByDescription(String query) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents) {
          
        	if (document.getDescription().toLowerCase().contains(query)) {
                results.add(document);
            }
        }

        
        return results;
    }


    public void paginatedView(DocumentPage page) {
        int firstRes = (page.getPageNumber() - 1) * DocumentPage.RESULTS_PER_PAGE;
        int lastResult = firstRes + DocumentPage.RESULTS_PER_PAGE;

        if (documents.size() > firstRes && documents.size() < lastResult) {
            lastResult = documents.size();
        }

        List<Document> res;
        try {
            res = documents.subList(firstRes, lastResult);
        } catch (IndexOutOfBoundsException e) {
            res = new ArrayList<>();
        }

        int numPag = documents.size() / DocumentPage.RESULTS_PER_PAGE;
        if (documents.size() % DocumentPage.RESULTS_PER_PAGE > 0) {
            numPag++;
        }

        page.setDocuments(res);
        page.setTotalPages(numPag);
        page.setTotalResults(documents.size());
    }

	public Document update(String id, String code, String description) {
        var doc = getById(id);
        if (doc == null) {
            return null;
        }
        doc.setCode(code);
        doc.setDescription(description);
        return doc;
    }

    public boolean delete(String id) {
        Document document = getById(id);
        if (document == null) {
            return false;
        }
        return documents.remove(document);
    }

    private static boolean compareIds(String id1, String id2) {
        try {
            return Integer.parseInt(id1.trim()) == Integer.parseInt(id2.trim());
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
