/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nevszavazo;

import nevszavazo.listenerek.SzavazoGombListener;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author tamas
 */
public class NevSzavazoPane extends GridPane
{
  private static final String KERDES_S = "Melyik nev a szimpatikusabb ?";
  private static final String EZ_S     = "Ez";

  private final Label baloNevLabel  = new Label( "balo nev"); //*.getProperty().bind( StringProperty -> SimpleStringProperty)
  private final Label jobboNevLabel = new Label( "jobbo nev");
  private final Button baloNevButton  = new Button( EZ_S);
  private final Button jobboNevButton = new Button( EZ_S);

  private final SzavazoGombListener szavazoGombListener;

  public NevSzavazoPane( OsszNevSzavazat[] osszNevSzavazatTomb)
  {
//    GridPane nevSzavazoPane = new GridPane();

    setPadding( new Insets( 5)); // hely a kontrol korul new Insets( 0.1, 0.1, 0.1, 0.1)
    setHgap( 5);
    setVgap( 5);

    Label kerdesLabel = new Label( KERDES_S);
    
    // Kerdes label
    GridPane.setHalignment( kerdesLabel, HPos.RIGHT); // hatastalan
    add( kerdesLabel, 0, 0);

    // Baloldali nev label
    GridPane.setHalignment( baloNevLabel, HPos.CENTER);
    add( baloNevLabel, 0, 1);
    
    // Joboldali nev label
    GridPane.setHalignment(jobboNevLabel, HPos.CENTER);
    add( jobboNevLabel, 1, 1);

    szavazoGombListener = new SzavazoGombListener( osszNevSzavazatTomb, baloNevLabel.textProperty(), jobboNevLabel.textProperty(), baloNevButton, jobboNevButton);
    
    // Baloldali nev buton
    baloNevButton.setOnAction( szavazoGombListener);
    GridPane.setHalignment( baloNevButton, HPos.CENTER);
    add( baloNevButton, 0, 2);
    
    // Joboldali nev buton
    jobboNevButton.setOnAction( szavazoGombListener);
    GridPane.setHalignment( jobboNevButton, HPos.CENTER);
    add( jobboNevButton, 1, 2);
  }
  
  public StringProperty getBaloNevProperty()
  {
    return baloNevLabel.textProperty();
  }
  
  public StringProperty getJobboNevProperty()
  {
    return jobboNevLabel.textProperty();
  }
  
  public Button getBaloNevButton()
  {
    return baloNevButton;
  }

  public Button getJobboNevButton()
  {
    return jobboNevButton;
  }
}