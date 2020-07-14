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

    public void setPreis(Double preis) throws SQLException {
        this.preis = preis;
        artikelDAO.update(this);
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) throws SQLException {
        this.bestand = bestand;
        artikelDAO.update(this);
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public void setMindestbestand(int mindestbestand) throws SQLException {
        this.mindestbestand = mindestbestand;
        artikelDAO.update(this);
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
	public static Artikel find(long artikelnummer) throws SQLException {

	    return ArtikelDAO.getInstance().read(artikelnummer);
	}
    public static ArrayList<Artikel> find(String condition) throws SQLException {
        return ArtikelDAO.getInstance().read(condition);
    }

	public void insert() throws SQLException {
	    artikelDAO.create(this);
    }
}
