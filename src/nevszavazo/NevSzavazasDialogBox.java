/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nevszavazo;

import nevszavazo.listenerek.SzavazoGombListener;
import nevszavazo.listenerek.CancelListener;
import nevszavazo.listenerek.OKListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author tamas
 */
public class NevSzavazasDialogBox extends Stage implements OKCancelDialogBox
{
  private static final double ABLAK_SZELESSEG = 420;
  private static final double ABLAK_MAGASSAG  = 480;
  
  private static final double ALSO_RESZ_VBOX_SPACING = 8;
  private static final double NEV_KEZ_KTRL_HBOX_SPACING = 8;
  
  private static final String KERDES_S = "Melyik nev a szimpatikusabb ?";
  private static final String EZ_S     = "Ez";

  private static final String OK_BUTTON_S = "OK";
  private static final String CANCEL_BUTTON_S = "Cancel";
  
  private final Label baloNevLabel  = new Label( "balo nev"); //*.getProperty().bind( StringProperty -> SimpleStringProperty)
  private final Label jobboNevLabel = new Label( "jobbo nev");
  private final Button baloNevButton  = new Button( EZ_S);
  private final Button jobboNevButton = new Button( EZ_S);

  private SzavazoGombListener szavazoGombListener;

  private OsszNevSzavazat[] osszNevSzavazatTomb;
  private OsszNevSzavazat   lokModOsszNevSzavazat;
  
  // Modality.APPLICATION_MODAL : Modality.NONE
  public NevSzavazasDialogBox( /*Stage owner,*/ Modality modality, String title, OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    super();

    ablakInicializal( modality, title);
    
    Group root  = new Group();
    Scene scene = new Scene(root, ABLAK_SZELESSEG, ABLAK_MAGASSAG);
    
    setScene( scene);

    osszNevSzavazatTomb = iniOsszNevSzavazatTomb;

    // A NevSzavazat-okat csak egyszeruen atveszi ! TODO : ez nem biztos, hogy eleg !!! Mi van, ha Cancelt nyomnak ???
    lokModOsszNevSzavazat = new OsszNevSzavazat( osszNevSzavazatTomb[0]);

    BorderPane borderPane = kontrolokLetrehoz( lokModOsszNevSzavazat);

    // A BorderPane hozzaadasa az ablakhoz
    root.getChildren().add( borderPane);
  }

  private void ablakInicializal( Modality modality, String title)
  {
    initModality( modality);

    setOpacity( 1.0);
    
    setTitle(title);
  }
  
  private BorderPane kontrolokLetrehoz( OsszNevSzavazat iniOsszNevSzavazat)
  {
    BorderPane borderPane = new BorderPane();
    
    // ----------------------------------------------------------------------------------------------------------------------
    // Kozepso resz a nevekkel es szavazo gombokkal
    GridPane nevSzavazoPane = new GridPane();

    nevSzavazoPane.setPadding( new Insets( 5)); // hely a kontrol korul new Insets( 0.1, 0.1, 0.1, 0.1)
    nevSzavazoPane.setHgap( 5);
    nevSzavazoPane.setVgap( 5);

    Label kerdesLabel = new Label( KERDES_S);
    
    // Kerdes label
    GridPane.setHalignment( kerdesLabel, HPos.RIGHT); // hatastalan
    nevSzavazoPane.add( kerdesLabel, 0, 0);

    // Baloldali nev label
    GridPane.setHalignment( baloNevLabel, HPos.CENTER);
    nevSzavazoPane.add( baloNevLabel, 0, 1);
    
    // Joboldali nev label
    GridPane.setHalignment( jobboNevLabel, HPos.CENTER);
    nevSzavazoPane.add( jobboNevLabel, 1, 1);

    szavazoGombListener = new SzavazoGombListener( osszNevSzavazatTomb, baloNevLabel.textProperty(), jobboNevLabel.textProperty(), baloNevButton, jobboNevButton);
    
    // Baloldali nev buton
    baloNevButton.setOnAction( szavazoGombListener);
    GridPane.setHalignment( baloNevButton, HPos.CENTER);
    nevSzavazoPane.add( baloNevButton, 0, 2);
    
    // Joboldali nev buton
    jobboNevButton.setOnAction( szavazoGombListener);
    GridPane.setHalignment( jobboNevButton, HPos.CENTER);
    nevSzavazoPane.add( jobboNevButton, 1, 2);
    
    borderPane.setCenter( nevSzavazoPane);
    // ----------------------------------------------------------------------------------------------------------------------

    // Az also VBox resz HBoxa
    HBox oKCancelHBoxa = new HBox( NEV_KEZ_KTRL_HBOX_SPACING);
    
    // OK Button
    Button oKButton = new Button( OK_BUTTON_S);
    oKButton.setOnAction( new OKListener( this));
    oKCancelHBoxa.getChildren().add( oKButton);
    
    // Cancel Button
    Button cancelButton = new Button( CANCEL_BUTTON_S);
    cancelButton.setOnAction( new CancelListener( this));
    oKCancelHBoxa.getChildren().add( cancelButton);
    
    borderPane.setBottom( oKCancelHBoxa);

    return borderPane;
  }
  
  @Override
  public void valtoztatasokVisszaad()
  {
System.out.println( "UjNevFelveszDialogBox::valtoztatasokVisszaad() : " + lokModOsszNevSzavazat.toString());
    osszNevSzavazatTomb[0] = lokModOsszNevSzavazat;
  }
}