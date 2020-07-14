package artikelDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArtikelDAOTest {
    public static void main(String[] args) throws SQLException {
        Artikel a1 = new Artikel(1, "Artikel 1",5,1, 9.99);
        Artikel a2 = new Artikel(2, "Artikel 2", 6, 5, 1.23);
        Artikel a3 = new Artikel(3, "Artikel 3", 1, 5, 1.0);

        a1.insert();
        a2.insert();
        a3.insert();

        // Verweis auf a1 löschen.
        a1 = null;

        // Artikel zurückholen
        System.out.println("Artikel 1:");
        a1 = Artikel.find((long) 1);
        printArtikel(a1);

        // Artikel mit Unterschreitung vom Mindestbestand
        System.out.println("\nArtikel mit Unterschreitung vom Mindestbestand:");
        ArrayList<Artikel> artikelListe = Artikel.find("Bestand < Mindestbestand");
        for(Artikel a: artikelListe) {
            printArtikel(a);
        }

        // Bestand von Artikel 2 auf 4 ändern.
        System.out.println("\nSetze Bestand von Artikel 2 auf 4.");
        a2.setBestand(4);

        // Artikel mit Unterschreitung vom Mindestbestand
        System.out.println("\nArtikel mit Unterschreitung vom Mindestbestand:");
        artikelListe = Artikel.find("Bestand < Mindestbestand");
        for(Artikel a: artikelListe) {
            printArtikel(a);
        }

        System.out.println("\nLösche Artikel 3.");
        a3.delete();
        a3 = null;
        System.out.println("\nPrüfe, dass Artikel 3 nicht mehr vorhanden ist:");
        a3 = Artikel.find((long) 3);
        if(a3 == null) {
            System.out.println("Artikel 3 nicht gefunden.");
        } else{
            System.out.println("Artikel 3 noch vorhanden. Irgendwas ist schief gelaufen");
        }
    }

    private static void printArtikel(Artikel a) {
        System.out.println("Artikelnr: " + a.getArtikelnummer()
                + " | Beschreibung: " + a.getBeschreibung()
                + " | Preis: " + a.getPreis()
                + " | Bestand: " + a.getBestand()
                + " | Mindestbestand: " + a.getMindestbestand()
        );
    }
}
