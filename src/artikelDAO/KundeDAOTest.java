package artikelDAO;
public class KundeDAOTest {
    
    public static void main(String[] args) {
    	//Zugriff auf DAO-Objekt bekommen
    	ArtikelDAO artikelDAO = ArtikelDAO.getInstance();
    	
        System.out.println("Erzeuge einen Kunden");
        Artikel derKunde = new Artikel(4711l, "Priemer", 1, 10, 10.6);
        System.out.println("Setze lokale Variable auf NULL und hole Artikel zurück");
        derKunde = null;
        derKunde = artikelDAO.read(4711l); //So muss der Nutzer KundeDAO kennen (nicht gut!)
//        derKunde = Kunde.read(4711l); //So bleibt KundeDAO vor Nutzerklassen verborgen!
        System.out.println("Kunde ist " + derKunde.getKundennummer() + " "
                + derKunde.getName()+", "+derKunde.getKundengruppe());
        System.out.println("Aktualisiere den Kunden. Setze Gruppe auf 2");
        derKunde.setKundengruppe(2);
        derKunde = null;
        derKunde = artikelDAO.read(4711l);
//        derKunde = Kunde.read(4711l);
        System.out.println("Kunde hat jetzt Gruppe " + derKunde.getKundengruppe());

        // Jetzt wird der Kunde gel�scht
        artikelDAO.delete(derKunde); //So muss der Nutzer die Klasse KundeDAO kennen (nicht gut!)
//        derKunde.delete(); //So bleibt KundeDAO vor Nutzerklassen verborgen!
        derKunde = null;
        System.out.println("Versuche den Kunden nach L�schung erneut zu lesen:");
        derKunde = artikelDAO.read(4711l);
        System.out.println(derKunde);

    }
    
}
