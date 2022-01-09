package gui;


import business.Theaterveranstaltung;
import java.io.*;
import java.util.ArrayList;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class TheaterAnwendersystem {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					 = new  Pane();
    private Label lblTheater    	 		 = new Label("Theater");
    private Label lblVeranstaltungen    	 = new Label("Veranstaltungen");
    private Label lblName 					 = new Label("Theatername:");
    private Label lblStrasseHnr      		 = new Label("Straﬂe und Hausnummer:");
    private Label lblPlzOrt   	 	    	 = new Label("Plz und Ort:");
    private ComboBox<String> cmbBxName		 = new ComboBox<String>();
    private TextField txtStrasseHnr	    	 = new TextField();
    private TextField txtPlz	        	 = new TextField();
    private TextField txtOrt	        	 = new TextField();
    private TextArea txtVeranstaltungen		 = new TextArea();
    private Button btnAnzeigeVeranstaltungen = new Button("Anzeige Veranstaltungen");
    private MenuBar mnbrMenuLeiste  		 = new MenuBar();
    private Menu mnDatei             		 = new Menu("Datei");
    private MenuItem mnItmCsvImport 		 = new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		 = new MenuItem("txt-Import");
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer Objekte vom Typ Theaterveranstaltung
    private ArrayList<Theaterveranstaltung> theaterveranstaltungen = new ArrayList<>();
    
    public TheaterAnwendersystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 600, 280);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Theatern");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
    	// Labels
       	lblTheater.setLayoutX(20);
    	lblTheater.setLayoutY(40);
    	Font font = new Font("Arial", 24);
    	lblTheater.setFont(font);
    	lblTheater.setStyle("-fx-font-weight: bold;"); 
    	lblVeranstaltungen.setLayoutX(360);
    	lblVeranstaltungen.setLayoutY(40);
      	lblVeranstaltungen.setFont(font);
       	lblVeranstaltungen.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblStrasseHnr.setLayoutX(20);
    	lblStrasseHnr.setLayoutY(130);
    	lblPlzOrt.setLayoutX(20);
    	lblPlzOrt.setLayoutY(170);
       	pane.getChildren().addAll(lblTheater, lblVeranstaltungen, 
       		lblName, lblStrasseHnr, lblPlzOrt);
    
    	// Textfelder
       	cmbBxName.setLayoutX(170);
       	cmbBxName.setLayoutY(90);
       	cmbBxName.setPrefWidth(170);
    	txtStrasseHnr.setLayoutX(170);
    	txtStrasseHnr.setLayoutY(130);
    	txtStrasseHnr.setPrefWidth(170);
    	txtPlz.setLayoutX(170);
    	txtPlz.setLayoutY(170);
    	txtPlz.setPrefWidth(50);
      	txtOrt.setLayoutX(230);
    	txtOrt.setLayoutY(170);
    	txtOrt.setPrefWidth(110);
       	pane.getChildren().addAll( 
       		cmbBxName, txtStrasseHnr, txtPlz, txtOrt);
     	
        // Textbereich	
        txtVeranstaltungen.setEditable(false);
     	txtVeranstaltungen.setLayoutX(360);
    	txtVeranstaltungen.setLayoutY(90);
     	txtVeranstaltungen.setPrefWidth(220);
    	txtVeranstaltungen.setPrefHeight(125);
       	pane.getChildren().add(txtVeranstaltungen); 
       	
        // Button
        btnAnzeigeVeranstaltungen.setLayoutX(360);
        btnAnzeigeVeranstaltungen.setLayoutY(230);
        pane.getChildren().add(btnAnzeigeVeranstaltungen); 
        
 		// Menu
   	    mnbrMenuLeiste.getMenus().add(mnDatei);
  	    mnDatei.getItems().add(mnItmCsvImport);
  	    mnDatei.getItems().add(mnItmTxtImport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    bearbeiteImport("csv");
            }
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    bearbeiteImport("txt");
            }
	    });
	    cmbBxName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
           	    zeigeTheateradresseAn();
            }
	    });
	    btnAnzeigeVeranstaltungen.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeVeranstaltungenAn();
	        } 
   	    });  
    }
   
   
   
   
   
   private void bearbeiteImport(String dateityp) {
		try {
			if("csv".equals(dateityp)){
			    this.theaterveranstaltungen = leseTheaterveranstaltungenAusCsvDatei();
			}    
			else if("txt".equals(dateityp)){
			    this.theaterveranstaltungen = leseTheaterveranstaltungenAusTxtDatei();
			} 
			else {
				throw new Exception();
			}	
			this.setzeCmBxName();  
			this.zeigeInformationsfensterAn( 
				"Die Theaterveranstaltungen sind gelesen worden.");
		}	
		catch(Exception Exc) {
			this.zeigeFehlermeldungsfensterAn("Fehler beim Lesen", 
				"Die " + dateityp + "-Datei konnte nicht gelesen werden.");
		}
	}
   
   
   
   
   
	private ArrayList<Theaterveranstaltung> leseTheaterveranstaltungenAusCsvDatei() 
	    throws Exception {
		BufferedReader ein 
			= new BufferedReader(new FileReader("Theaterveranstaltungen.csv"));
		String zeile = null;			
		String[] zeileAsArray = null;
		ArrayList<Theaterveranstaltung> ergebnis = new ArrayList<>();
		do {
			zeile = ein.readLine();
			if(zeile != null) {
				zeileAsArray = zeile.split(";");
			    ergebnis.add(new Theaterveranstaltung(
                   	zeileAsArray[0], zeileAsArray[1], zeileAsArray[2], zeileAsArray[3],
               	    zeileAsArray[4], zeileAsArray[5], Integer.parseInt(zeileAsArray[6])));
       
			}    
		} while (zeile != null);
		ein.close();
		return ergebnis;
	}
		
	private ArrayList<Theaterveranstaltung> leseTheaterveranstaltungenAusTxtDatei()  
		throws Exception {
		BufferedReader ein 
		 	= new BufferedReader(new FileReader("Theaterveranstaltungen.txt"));
		String zeile = null;
		ArrayList<Theaterveranstaltung> ergebnis = new ArrayList<>();
		do {
			zeile = ein.readLine();
			if(zeile != null) {
                ergebnis.add(new Theaterveranstaltung(
                	zeile, ein.readLine(), ein.readLine(), ein.readLine(),
                	ein.readLine(), ein.readLine(), Integer.parseInt(ein.readLine())));	
   			}
		} while (zeile != null);
		ein.close();
		return ergebnis;
	}	
	
	private void setzeCmBxName() {
		this.cmbBxName.getItems().clear();
		for(int i = 0; i < this.theaterveranstaltungen.size(); i++) {
			this.cmbBxName.getItems().add(
				this.theaterveranstaltungen.get(i).getName());
		}  
	}
	
	private void zeigeTheateradresseAn() {
		String name = this.cmbBxName.getValue();
		if(name == null) {
			this.zeigeInformationsfensterAn( 
				"W‰hlen Sie einen Theaternamen aus.");
		}
		else {
			Theaterveranstaltung theaterveranstaltung = gibTheaterveranstaltung(name);
			this.txtStrasseHnr.setText(theaterveranstaltung.getStrasseHnr());
			this.txtPlz.setText(theaterveranstaltung.getPlz());
			this.txtOrt.setText(theaterveranstaltung.getOrt());
		}
    }
	
	private void zeigeVeranstaltungenAn() {
		String name = this.cmbBxName.getValue();
		if(name == null) {
			this.zeigeInformationsfensterAn( 
				"W‰hlen Sie einen Theaternamen aus.");
		}
		else {
			Theaterveranstaltung theaterveranstaltung = gibTheaterveranstaltung(name);
			this.txtVeranstaltungen.setText(theaterveranstaltung.gibTheaterveranstaltungZurueck('\\'));
		}
    }
	
	private Theaterveranstaltung gibTheaterveranstaltung(String name) {
		Theaterveranstaltung theaterveranstaltung = null;
		int i = 0;
		while (theaterveranstaltung == null && i < this.theaterveranstaltungen.size()) {
			if(name.equals(this.theaterveranstaltungen.get(i).getName())){
			    theaterveranstaltung = this.theaterveranstaltungen.get(i);
			}
			i++;
		}
		return theaterveranstaltung;
	}
	
    private void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    private void zeigeFehlermeldungsfensterAn(String titel, String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	titel, meldung).zeigeMeldungsfensterAn();
    }

}

