import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CatalogUtil {

    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
            throws IOException, ClassNotFoundException {
        Catalog catalog;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path))) {
            catalog = (Catalog) ois.readObject();
            return catalog;
        }
    }

    public static void view(Catalog catalog) throws IOException, URISyntaxException {
        //Functia de vizualizare catalog deschide toate documentele din acel catalog, prin metoda view
        //ce ia ca parametru un document.
        for (Document doc:catalog.getDocuments())
            view(doc);
    }

    public static void view(Document doc) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        if (doc.getLocation().startsWith("http")) {
            //Daca documentul este o pagina web, il deschidem folosind browserul implicit.
            desktop.browse(new URI(doc.getLocation()));
        }
        else {
            //In caz contrar, folosim functia open, care deschide fisierul cu programul implicit
            //pentru deschiderea fisierelor de tipul sau.
            desktop.open(new File(doc.getLocation()));
        }
    }
}