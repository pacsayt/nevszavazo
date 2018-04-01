/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nevszavazo;

import nevszavazo.listenerek.CancelListener;
import nevszavazo.listenerek.OKListener;
import nevszavazo.listenerek.HozzaadListener;
import nevszavazo.listenerek.ModositListener;
import nevszavazo.listenerek.TorolListener;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author tamas
 */
public class NevekKezeleseDialogBox extends Stage implements OKCancelDialogBox
{
  private static final String UJ_NEV_FELVESZ_S = "Uj nev felvesz";
  
  private static final double ABLAK_SZELESSEG = 420;
  private static final double ABLAK_MAGASSAG  = 480;
  
  private static final double ALSO_RESZ_VBOX_SPACING = 8;
  private static final double NEV_KEZ_KTRL_HBOX_SPACING = 8;
  
  private static final String UJ_NEV_S = "Uj nev :";
  private static final String HOZZAAD_BUTTON_S = "Hozzaad";
  private static final String KIJ_NEV_MODOSIT_BUTTON_S = "Kijelolt nev modosit";
  private static final String KIJ_NEV_TOROL_BUTTON_S = "Kijelolt nev torol";
  
  private static final String OK_BUTTON_S = "OK";
  private static final String CANCEL_BUTTON_S = "Cancel";

  private OsszNevSzavazat[] osszNevSzavazatTomb;
  private OsszNevSzavazat   lokModOsszNevSzavazat;
  
  ListView<String> nevListView;
  ObservableList<String> nevLista = FXCollections.observableArrayList();
    
  TextField        ujNevTextField;

  
  // Modality.APPLICATION_MODAL : Modality.NONE
  public NevekKezeleseDialogBox( /*Stage owner,*/ Modality modality, String title, OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    super();

    ablakInicializal( modality, title);
    
    Group root  = new Group();
    Scene scene = new Scene(root, ABLAK_SZELESSEG, ABLAK_MAGASSAG);
    
    setScene( scene);

    osszNevSzavazatTomb = iniOsszNevSzavazatTomb;

    // A NevSzavazat-okat csak egyszeruen atveszi !
    lokModOsszNevSzavazat = new OsszNevSzavazat( osszNevSzavazatTomb[0]);

    BorderPane borderPane = kontrolokLetrehoz( lokModOsszNevSzavazat);

    // A BorderPane hozzaadasa az ablakhoz
    root.getChildren().add( borderPane);
  }

  private void ablakInicializal( Modality modality, String title)
  {
    //    initOwner(owner);

    initModality( modality);

    setOpacity( 1.0);
    
    setTitle(title);
  }
  
  private BorderPane kontrolokLetrehoz( OsszNevSzavazat iniOsszNevSzavazat)
  {
    BorderPane borderPane = new BorderPane();
    
    // A neveket tartalmazo lista Lehetett voolna direkt NevSzavazat tipussal is,
    // de annak a toString()-je a mezoneveket is tartalmazta, amit nem akartam megvaltoztatni
    nevListView = new ListView<>();
    borderPane.setCenter( nevListView);
    
    // Bottom : VBox-ban ket HBox
    VBox alsoReszVBoxa = new VBox( ALSO_RESZ_VBOX_SPACING);
    
    // A felso VBox resz HBoxa
    HBox nevKezeloKontrolokHBoxa = new HBox( NEV_KEZ_KTRL_HBOX_SPACING);
    
    // Uj nev label
    Label ujNevLabel = new Label( UJ_NEV_S);
    nevKezeloKontrolokHBoxa.getChildren().add( ujNevLabel);
    
    // Uj nev TextField
    ujNevTextField = new TextField();
    nevKezeloKontrolokHBoxa.getChildren().add( ujNevTextField);
    
    // Hozzaad Button
    Button hozzaadButton = new Button( HOZZAAD_BUTTON_S);
    hozzaadButton.setOnAction( new HozzaadListener( this));
    nevKezeloKontrolokHBoxa.getChildren().add( hozzaadButton);
    
    // Kijelolt nev modositasa Button
    Button kijeloltNevModositButton = new Button( KIJ_NEV_MODOSIT_BUTTON_S);
    kijeloltNevModositButton.setOnAction( new ModositListener( this));
    nevKezeloKontrolokHBoxa.getChildren().add( kijeloltNevModositButton);
    
    // Kijelolt nev torol Button
    Button kijeloltNevTorolButton = new Button( KIJ_NEV_TOROL_BUTTON_S);
    kijeloltNevTorolButton.setOnAction( new TorolListener( this));
    nevKezeloKontrolokHBoxa.getChildren().add( kijeloltNevTorolButton);
    
    alsoReszVBoxa.getChildren().add( nevKezeloKontrolokHBoxa);
    
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
    
    alsoReszVBoxa.getChildren().add( oKCancelHBoxa);

    borderPane.setBottom( alsoReszVBoxa);

    nevListViewFeltolt( nevListView, iniOsszNevSzavazat);

    return borderPane;
  }
  
  private void nevListViewFeltolt( ListView<String> iniNevListView, OsszNevSzavazat iniOsszNevSzavazat)
  {
    ArrayList<NevSzavazat> szavazoLista = iniOsszNevSzavazat.getSzavazoLista();
    
    for ( NevSzavazat aktNevSzavazat : szavazoLista )
    {
      nevLista.add( aktNevSzavazat.getNev());
    }
    
    iniNevListView.setItems( nevLista);
  }
  
  @Override
  public void valtoztatasokVisszaad()
  {
System.out.println( "UjNevFelveszDialogBox::valtoztatasokVisszaad() : " + lokModOsszNevSzavazat.toString());
    osszNevSzavazatTomb[0] = lokModOsszNevSzavazat;
  }
  
  public void ujNevHozzaad()
  {
    String ujNev;
    
    ujNev = ujNevTextField.getText();
    ujNev = ujNev.trim();
    
    if ( ujNev.isEmpty() )
    {
      return;
    }
    
    if ( lokModOsszNevSzavazat.nevHozzaad( ujNev) )
    {
// System.out.println( "UjNevFelveszDialogBox::ujNevHozzaad() : " + lokModOsszNevSzavazat.toString());

      nevLista.add( ujNev);
      
      ujNevTextField.clear();

      nevListView.setItems( nevLista); // kell-e ez minden egyes hozzaadasnal ???
    }
  }
  
  public void nevTorol()
  {
    final int selectedIdx = nevListView.getSelectionModel().getSelectedIndex();
    
    if ( selectedIdx != -1 )
    {
      String torlendoNev = nevListView.getSelectionModel().getSelectedItem();
      
      lokModOsszNevSzavazat.nevTorol( torlendoNev);
System.out.println( "UjNevFelveszDialogBox::nevTorol() : " + lokModOsszNevSzavazat.toString());
      
      final int newSelectedIdx = (selectedIdx == nevListView.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;
      
      nevListView.getItems().remove( selectedIdx);
      
      nevListView.getSelectionModel().select(newSelectedIdx);
    }
  }
  
  public void nevModosit()
  {
    String ujNev;
    final int selectedIdx = nevListView.getSelectionModel().getSelectedIndex();
System.out.println( "UjNevFelveszDialogBox::nevModosit() : " + lokModOsszNevSzavazat.toString());
    
    if ( selectedIdx != -1 )
    {
      ujNev = ujNevTextField.getText();
      ujNev = ujNev.trim();

      if ( ujNev.isEmpty() == false )
      {
        nevLista.remove( selectedIdx);
        nevLista.add( selectedIdx, ujNev);

        ujNevTextField.clear();
        
        lokModOsszNevSzavazat.nEdikNevBeallit( selectedIdx, ujNev);
      }
    }
  }
}