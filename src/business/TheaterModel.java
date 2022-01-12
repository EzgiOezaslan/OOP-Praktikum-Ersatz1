package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TheaterModel {

	// speichert temporaer Objekte vom Typ Theaterveranstaltung
	private ArrayList<Theaterveranstaltung> theaterveranstaltungen = new ArrayList<>();

	public ArrayList<Theaterveranstaltung> getTheaterveranstaltungen() {
		return theaterveranstaltungen;
	}

	public void setTheaterveranstaltungen(ArrayList<Theaterveranstaltung> theaterveranstaltungen) {
		this.theaterveranstaltungen = theaterveranstaltungen;
	}

	public ArrayList<Theaterveranstaltung> leseTheaterveranstaltungenAusCsvDatei() throws Exception {
		BufferedReader ein = new BufferedReader(new FileReader("Theaterveranstaltungen.csv"));
		String zeile = null;
		String[] zeileAsArray = null;
		ArrayList<Theaterveranstaltung> ergebnis = new ArrayList<>();
		do {
			zeile = ein.readLine();
			if (zeile != null) {
				zeileAsArray = zeile.split(";");
				ergebnis.add(new Theaterveranstaltung(zeileAsArray[0], zeileAsArray[1], zeileAsArray[2],
						zeileAsArray[3], zeileAsArray[4], zeileAsArray[5], Integer.parseInt(zeileAsArray[6])));

			}
		} while (zeile != null);
		ein.close();
		return ergebnis;
	}

	public ArrayList<Theaterveranstaltung> leseTheaterveranstaltungenAusTxtDatei() throws Exception {
		BufferedReader ein = new BufferedReader(new FileReader("Theaterveranstaltungen.txt"));
		String zeile = null;
		ArrayList<Theaterveranstaltung> ergebnis = new ArrayList<>();
		do {
			zeile = ein.readLine();
			if (zeile != null) {
				ergebnis.add(new Theaterveranstaltung(zeile, ein.readLine(), ein.readLine(), ein.readLine(),
						ein.readLine(), ein.readLine(), Integer.parseInt(ein.readLine())));
			}
		} while (zeile != null);
		ein.close();
		return ergebnis;
	}

	public Theaterveranstaltung gibTheaterveranstaltung(String name) {
		Theaterveranstaltung theaterveranstaltung = null;
		int i = 0;
		while (theaterveranstaltung == null && i < this.theaterveranstaltungen.size()) {
			if (name.equals(this.theaterveranstaltungen.get(i).getName())) {
				theaterveranstaltung = this.theaterveranstaltungen.get(i);
			}
			i++;
		}
		return theaterveranstaltung;
	}

}
