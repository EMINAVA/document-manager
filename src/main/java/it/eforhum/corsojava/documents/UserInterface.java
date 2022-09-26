package it.eforhum.corsojava.documents;

import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.util.List;
import java.util.Scanner;

public class UserInterface implements Closeable {
    private final Scanner scanner = new Scanner(System.in);
    private final DocumentManager docManager = new DocumentManager();

    public static void main(String[] args) {
        try (var ui = new UserInterface()) {
            ui.run();
        }
    }

    public void run() {
        int option = menu();

        switch (option) {
            // 1) inserimento manuale dei dati
            case 1:
                manualInsertion();
                break;
            // 2) caricamento attraverso generazione causale d'informazioni
            case 2:
                randomInsertion();
                break;
            // 3) ricerca e stampa di un documento attraverso l'identificativo
            case 3:
                searchById();
                break;
            // 4) stampa paginata della lista documenti così rappresentata
            case 4:
                paginatedView();
            // 5) modifica documento presente (cod e descrizione)
            case 5:
                break;
            // 6) rimozione di un documento presente
            case 6:
                break;
            // 7) stampa di tutti i documenti che contengono nella descrizione una query di ricerca
            case 7:
                break;
            // 8) Esci
            case 8:
                System.out.println("Grazie per aver usato DocumentManager™!");
                return;
            default:
                System.out.println("Scelta invalida!");
                run();
        }
        run();
    }

    private int menu() {
        var prompt = "Benvenuto su DocumentManager™\n" +
                "Scegli un'opzione: \n" +
                "1) inserimento manuale dei dati\n" +
                "2) caricamento attraverso generazione causale di informazioni\n" +
                "3) ricerca e stampa di un documento attraverso l'identificativo\n" +
                "4) stampa paginata della lista documenti così rappresentata\n" +
                "5) modifica documento presente (cod e descrizione)\n" +
                "6) rimozione di un documento presente\n" +
                "7) stampa di tutti i documenti che contengono nella descrizione una query di ricerca\n" +
                "8) Esci\n\n" +
                "La tua opzione: ";
        System.out.print(prompt);

        int res = 0;
        try {
            res = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Scelta invalida!");
            menu();
        }

        if (res < 1 || res > 8) {
            System.out.println("Scelta invalida!");
            menu();
        }

        return res;
    }

    private void manualInsertion() {
        var code = askCode();
        var description = askDescription();
        var doc = docManager.userDocument(code, description);
        System.out.println("Documento aggiunto!");
        printDocument(doc);
    }

    private void randomInsertion() {
        var doc = docManager.generateRandom();
        System.out.println("Documento aggiunto!");
        printDocument(doc);
    }

    private void searchById() {
        var id = askId();
        var doc = docManager.getById(id);
        if (doc == null) {
            System.out.println("Nessun documento trovato!");
            return;
        }
        System.out.println("Documento trovato!");
        printDocument(doc);
    }

    private void paginatedView() {
        var page = askPage();
        if (page == -1) {
            return;
        }
        var res = docManager.getPage(page);
        System.out.printf("Totale risultati: %d\t\tTotale pagine: %d%n", res.getTotalResults(), res.getTotalPages());
        printDocuments(res.getDocuments());
    }

    private String askId() {
        System.out.print("Inserisci l'id del documento: ");
        var id = scanner.nextLine();
        if (id.length() > Document.ID_MAX_LENGTH) {
            System.out.printf("L'id deve avere massimo %d caratteri!%n", Document.ID_MAX_LENGTH);
            return askId();
        }
        return id;
    }

    private String askDescription() {
        System.out.print("Inserisci la descrizione del documento: ");
        var desc = scanner.nextLine();
        if (desc.length() > Document.DESCRIPTION_MAX_LENGTH) {
            System.out.println("La descrizione verrà troncata!");
        }
        return desc;
    }

    private String askCode() {
        System.out.printf("Inserisci il codice del documento (max %d caratteri): ", Document.CODE_MAX_LENGTH);
        var code = scanner.nextLine();
        if (code.length() > Document.CODE_MAX_LENGTH) {
            System.out.printf("Inserisci un codice con massimo %d caratteri!%n", Document.CODE_MAX_LENGTH);
            return askCode();
        }
        if (!StringUtils.isAlphanumeric(code)) {
            System.out.println("Il codice può contenere solo lettere e numeri!");
            return askCode();
        }
        return code;
    }

    private int askPage() {
        System.out.print("Inserisci il numero di pagina richiesta o E per uscire: ");
        var in = scanner.nextLine().trim();
        if (in.equalsIgnoreCase("E")) {
            return -1;
        }
        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            System.out.println("Numero invalido!");
            return askPage();
        }
    }

    private static void printDocument(Document document) {
        printDocuments(List.of(document));
    }

    private static String r(int n) {
        return StringUtils.repeat('─', n);
    }

    private static void printLine(String id, String code, String description) {
        printLine(id, code, description, '│', '│', '│');
    }
    private static void printLine(String id, String code, String description, char s, char m, char e) {
        System.out.println(s + StringUtils.leftPad(id, Document.ID_MAX_LENGTH) + m + StringUtils.leftPad(code, Document.CODE_MAX_LENGTH) + m + StringUtils.leftPad(description, Document.DESCRIPTION_MAX_LENGTH) + e);
    }
    private static void printDocuments(List<Document> documents) {
        printLine(r(Document.ID_MAX_LENGTH), r(Document.CODE_MAX_LENGTH), r(Document.DESCRIPTION_MAX_LENGTH), '┌', '┬', '┐');
        printLine("ID", "CODE", "DESCRIPTION");
        printLine(r(Document.ID_MAX_LENGTH), r(Document.CODE_MAX_LENGTH), r(Document.DESCRIPTION_MAX_LENGTH), '├', '┼', '┤');
        for (Document document : documents) {
            printLine(document.getId(), document.getCode(), document.getDescription());
        }
        printLine(r(Document.ID_MAX_LENGTH), r(Document.CODE_MAX_LENGTH), r(Document.DESCRIPTION_MAX_LENGTH), '└', '┴', '┘');
    }

    @Override
    public void close() {
        scanner.close();
    }
}
