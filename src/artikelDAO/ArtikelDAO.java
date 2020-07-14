package artikelDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtikelDAO extends AbstractDAO<Artikel> {

    private static ArtikelDAO artikelDAO = new ArtikelDAO();

    static ArtikelDAO getInstance() {
        return artikelDAO;
    }

    @Override
    protected String findStatementBase() {
        return "SELECT * FROM Artikel WHERE ";
    }

    @Override
    protected String findStatement() {
        return findStatementBase() + "Artikelnr = ?";
    }

    @Override
    protected String deleteStatement() {
        return "DELETE FROM Artikel WHERE Artikelnr = ?";
    }

    @Override
    protected Long doInsert(Artikel o) {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = getConnection().prepareStatement("INSERT INTO Artikel (Artikelnr, Bezeichnung, Preis, Bestand, Mindestbestand) VALUES (?, ?, ?, ?, ?)");
            insertStatement.setLong(1, o.getArtikelnummer());
            insertStatement.setString(2, o.getBeschreibung());
            insertStatement.setDouble(3, o.getPreis());
            insertStatement.setInt(4, o.getBestand());
            insertStatement.setInt(5, o.getMindestbestand());
            insertStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.getArtikelnummer();
    }

    @Override
    protected Long doUpdate(Artikel o) {
        PreparedStatement updateStatement = null;
        try {
            updateStatement = getConnection().prepareStatement("UPDATE Artikel SET Bezeichnung = ?, Preis = ?, Bestand = ?, Mindestbestand = ? WHERE Artikelnr = ?");
            updateStatement.setString(1, o.getBeschreibung());
            updateStatement.setDouble(2, o.getPreis());
            updateStatement.setInt(3, o.getBestand());
            updateStatement.setInt(4, o.getMindestbestand());
            updateStatement.setLong(5, o.getArtikelnummer());
            updateStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.getArtikelnummer();
    }

    @Override
    protected Long getKey(Artikel o) {
        return o.getArtikelnummer();
    }

    @Override
    protected Artikel doLoad(ResultSet rs) throws SQLException {
        long id = rs.getLong("Artikelnr");
        String bezeichnung = rs.getString("Bezeichnung");
        double money = rs.getDouble("Preis");
        int bestand = rs.getInt("Bestand");
        int mindestBestand = rs.getInt("Mindestbestand");
        Artikel article = new Artikel(id, bezeichnung, bestand, mindestBestand, money);
        return article;
    }
}
