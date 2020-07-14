package artikelDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Artikel {
    
    private long artikelnummer;
    private String bezeichnung;
    private Double preis;
    private int bestand;
    private int mindestbestand;

    private ArtikelDAO artikelDAO = ArtikelDAO.getInstance();
    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public void setMindestbestand(int mindestbestand) {
        this.mindestbestand = mindestbestand;
    }
    
    public Artikel(long anr, String bschr, int bstd, int minbstd, double prs) {
        this.artikelnummer = anr;
        this.bezeichnung = bschr;
        this.bestand = bstd;
        this.mindestbestand = minbstd;
        this.preis = prs;
    }

    public long getArtikelnummer() {
        return artikelnummer;
    }

    public void setKundennummer(long kundennummer) throws SQLException {
        this.artikelnummer = kundennummer;
        artikelDAO.update(this);
    }

    public String getBeschreibung() {
        return bezeichnung;
    }

    public void setName(String name) throws SQLException {
        this.bezeichnung = name;
        artikelDAO.update(this);
    }
    
    /** Löschen eines vorhandenen Artikel als Objekt und in der
     * Datenhaltungsschicht
     */
    public void delete() throws SQLException {
    	artikelDAO.delete(this.artikelnummer);
    }
	
	/** Holen eines vorhandenen Artikels aus der Datenhaltungsschicht
	*   Über die Artikelnummer
	**/
	public static Artikel read(long artikelnummer) throws SQLException {

	    return ArtikelDAO.getInstance().read(artikelnummer);
	}
    public static ArrayList<Artikel> read(String condition) throws SQLException {
        return ArtikelDAO.getInstance().read(condition);
    }

	public void insert() throws SQLException {
	    artikelDAO.create(this);
    }
}
