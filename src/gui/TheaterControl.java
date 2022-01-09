package gui;

import java.io.IOException;

import business.TheaterModel;
import javafx.stage.Stage;


public class TheaterControl {

    private TheaterModel thModel;
    private TheaterView thView;

    public TheaterControl(Stage primaryStage) {
        this.thModel = new TheaterModel();
        this.thView = new TheaterView(primaryStage, this, thModel);
    }

    void bearbeiteImport(String dateityp) {
        try {
            if ("csv".contentEquals(dateityp)) {
                thModel.leseTheaterveranstaltungenAusCsvDatei();
                thView.zeigeInformationsfensterAn("Theater wurden gespeichert!");

            } else if ("txt".equals(dateityp)) {
                thModel.leseTheaterveranstaltungenAusTxtDatei();
                thView.zeigeInformationsfensterAn("Theater wurden gespeichert!");

            }

        } catch (IOException exc) {
            thView.zeigeFehlermeldungsfensterAn("IOException beim Speicher", dateityp);
        } catch (Exception exc) {
            thView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern", dateityp);
        }
    }
}
