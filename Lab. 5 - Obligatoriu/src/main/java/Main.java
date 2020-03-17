import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String args[]) {
        Main app = new Main();
        String testPath = "C:/Users/anava/Desktop/java-test/catalog.ser";
        app.testCreateSave(testPath);
        app.testLoadView(testPath);
    }

    private void testCreateSave(String testPath) {
        Catalog catalog = new Catalog("Java Resources", testPath);
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        //Testam exceptia care poate fi aruncata de functia save din CatalogUtil.
        try {
            CatalogUtil.save(catalog);
        } catch (IOException e) {
            System.out.println("Catalogul nu a putut fi salvat cu succes.");
        }
    }

    private void testLoadView(String testPath) {
        try {
            //Incercam sa incarcam catalogul de la locatia din testPath.
            Catalog catalog = CatalogUtil.load(testPath);
            //Gasim documentul pe care vrem sa il vizualizam cu functia findById.
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        } catch(URISyntaxException e) {
            //Exceptie folosita daca URI-ul nu este intr-un format corect.
            System.out.println("Catalogul nu a putut fi deschis (URI-ul introdus este gresit).");
        } catch (IOException e) {
            System.out.println("Catalogul nu a putut fi deschis.");
        } catch (ClassNotFoundException e) {
            System.out.println("Catalogul nu a putut fi deschis.");
        }
    }
}
