package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TheaterModel {

	Theaterveranstaltung thVeranstaltung;
	

	public Theaterveranstaltung getThVeranstaltung() {
		return thVeranstaltung;
	}

	public void setThVeranstaltung(Theaterveranstaltung thVeranstaltung) {
		this.thVeranstaltung = thVeranstaltung;
	} 
	
	public void leseTheaterveranstaltungenAusCsvDatei() throws IOException{
		BufferedWriter aus = new BufferedWriter(new FileWriter("Theaterveranstaltung.csv", true));
		aus.write(this.getThVeranstaltung().gibTheaterveranstaltungZurueck(';'));
		aus.close();	
	}
	
	public void leseTheaterveranstaltungenAusTxtDatei() throws IOException{
		BufferedWriter aus = new BufferedWriter(new FileWriter("Theaterveranstaltung.txt", true));
		aus.write(this.getThVeranstaltung().gibTheaterveranstaltungZurueck(';'));
		aus.close();	
	}
}
