package business;

public class Theaterveranstaltung {
	
	// Name des Theaters, muss eindeutig sein
    private String name;
    // Strasse und Hausnummer des Theaters
    private String strasseHnr;
    // Plz des Theaters
    private String plz;
    // Ort des Theaters
    private String ort;
    // Titel der Veranstaltung des Theaters, muss je Theater eindeutig sein
    private String titel;
    // Beschreibung der Veranstaltung des Theaters
    private String beschreibung;
    // Eintrittspreis der Veranstaltung des Theaters in Eurocent
    private int eintrittspreis;
    
    public Theaterveranstaltung(String name, String strasseHnr, String plz, String ort, 
    	String titel, String beschreibung, int eintrittspreis){   
		super();
		this.name = name;
		this.strasseHnr = strasseHnr;
		this.plz = plz;
		this.ort = ort;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.eintrittspreis = eintrittspreis;
	}
 
	public String gibTheaterveranstaltungZurueck(char trenner){
  		return this.getTitel() + trenner
  		    + this.getBeschreibung() + trenner
  		    + this.getEintrittspreis() + " Eurocent";
  	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrasseHnr() {
		return strasseHnr;
	}

	public void setStrasseHnr(String strasseHnr) {
		this.strasseHnr = strasseHnr;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getEintrittspreis() {
		return eintrittspreis;
	}

	public void setEintrittspreis(int eintrittspreis) {
		this.eintrittspreis = eintrittspreis;
	}   
  	
}

