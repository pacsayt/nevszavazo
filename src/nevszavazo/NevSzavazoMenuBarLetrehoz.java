/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nevszavazo;

import nevszavazo.listenerek.NevekKezeleseListener;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author tamas
 */
public class NevSzavazoMenuBarLetrehoz
{
  private static String SZAVAZAS_S          = "Szavazas";
  private static String UJ_S                = "Uj kezdese";
  private static String BETOLTOTT_FOLYTAT_S = "Betoltott folytatasa";
  private static String BEOLVASAS_S         = "Beolvasas";
  private static String MENTES_S            = "Mentes";
  private static String KILEPES_S           = "Kilepes";

  private static String NEVEK_S          = "Nevek";
  private static String NEVEK_KEZELESE_S = "Nevek kezelese";

  public static MenuBar menuBarLetrehoz( OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    MenuBar nevSzavazoMenuBar = new MenuBar();

    // Szavazas menu : Uj|Beolvasas|Mentes|Kilepes
    Menu szavazasMenu = new Menu( SZAVAZAS_S);
    
    MenuItem ujMenuItem = new MenuItem( UJ_S);
    ujMenuItem.setOnAction( new UjListener( iniOsszNevSzavazatTomb[0]));
    szavazasMenu.getItems().add( ujMenuItem);


    MenuItem betoltottFolytatMenuItem = new MenuItem( BETOLTOTT_FOLYTAT_S);
    betoltottFolytatMenuItem.setOnAction( new BetoltottFolytatListener( iniOsszNevSzavazatTomb));
    szavazasMenu.getItems().add( betoltottFolytatMenuItem);
    
    
    
    MenuItem beolvasasMenuItem = new MenuItem( BEOLVASAS_S);
    beolvasasMenuItem.setOnAction( new BeolvasasListener( iniOsszNevSzavazatTomb));
    szavazasMenu.getItems().add( beolvasasMenuItem);

    MenuItem mentesMenuItem = new MenuItem( MENTES_S);
    mentesMenuItem.setOnAction( new MentesListener( iniOsszNevSzavazatTomb));
    szavazasMenu.getItems().add( mentesMenuItem);
    
    szavazasMenu.getItems().add( new SeparatorMenuItem());

    MenuItem kilepesMenuItem = new MenuItem( KILEPES_S);
    kilepesMenuItem.setOnAction( new KilepesListener());
    szavazasMenu.getItems().add( kilepesMenuItem); //  Application::	exit()
    
    nevSzavazoMenuBar.getMenus().add( szavazasMenu);
    
    // Nevek menu : Uj nev felvesz|Mindet torol|Szavazas allasa torol
    Menu nevekMenu = new Menu( NEVEK_S);
    MenuItem nevekKezeleseMenuItem = new MenuItem( NEVEK_KEZELESE_S);
    nevekKezeleseMenuItem.setOnAction( new NevekKezeleseListener( iniOsszNevSzavazatTomb));
    nevekMenu.getItems().add( nevekKezeleseMenuItem);
    
    nevSzavazoMenuBar.getMenus().add( nevekMenu);
   
    return nevSzavazoMenuBar;
  }
}

class UjListener implements EventHandler<ActionEvent>
{
  OsszNevSzavazat osszNevSzavazat = null;
  
  public UjListener( final OsszNevSzavazat iniOsszNevSzavazat)
  {
    osszNevSzavazat = iniOsszNevSzavazat;
  }
  
  @Override
  public void handle(ActionEvent arg0)
  {
System.out.println( "UjListener::handle() : osszNevSzavazat.alaphelyzetbe()");
    osszNevSzavazat.alaphelyzetbe();
  }
}

class BetoltottFolytatListener implements EventHandler<ActionEvent>
{
  OsszNevSzavazat[] osszNevSzavazatTomb = null;
  
  public BetoltottFolytatListener( final OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    osszNevSzavazatTomb = iniOsszNevSzavazatTomb;
  }
  
  @Override
  public void handle(ActionEvent arg0)
  {
System.out.println( "BetoltottFolytatListener::handle()");
  }
}

class BeolvasasListener implements EventHandler<ActionEvent>
{
  OsszNevSzavazat[] osszNevSzavazatTomb = null;
  
  public BeolvasasListener( final OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    osszNevSzavazatTomb = iniOsszNevSzavazatTomb;
  }
  
  @Override
  public void handle(ActionEvent arg0)
  {
System.out.println( "BeolvasasListener::handle() : osszNevSzavazat.alaphelyzetbe()");
    boolean betoltesSikeres = szavazasAllasBetolt( "nevszavazas.json");
    
    if ( betoltesSikeres == false )
    {
System.out.println( "BeolvasasListener::handle() : betoltes nem sikerult. Ide lehetne valami MessageBoxot tenni");
    }
  }
  
  public boolean szavazasAllasBetolt( String fileNev)
  {
    ObjectMapper objectMapper = new ObjectMapper();

    try
    {
      // convert user object to json string, and save to a file
      osszNevSzavazatTomb[0] = objectMapper.readValue( new File( fileNev), OsszNevSzavazat.class);
      
      // A tomb rendezese ideiglenes jelleggel : a nev felvetelnel kellene TODO : biztositani, hogy felvetelnel rendezett legyen a tomb
      osszNevSzavazatTomb[0].nevekAbcRendezese(); // TODO : nincs frissitve a szavazo ablak
      
      // display to console
System.out.println( "BeolvasasListener::szavazasAllasBetolt() : betoltve " + fileNev + " " + osszNevSzavazatTomb[0]);
    }
    catch (JsonGenerationException jsonGenerationException)
    {
      jsonGenerationException.printStackTrace();
      return false;
    }
    catch (JsonProcessingException jsonProcessingException)
    {
      jsonProcessingException.printStackTrace();
      return false;
    }
    catch (IOException iOException)
    {
      iOException.printStackTrace();
      return false;
    }

    return true;
  }

}

class MentesListener implements EventHandler<ActionEvent>
{
  OsszNevSzavazat[] osszNevSzavazatTomb = null;
  
  public MentesListener( final OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    osszNevSzavazatTomb = iniOsszNevSzavazatTomb;
  }

  @Override
  public void handle(ActionEvent arg0)
  {
    boolean mentesSikeres = szavazasAllasElment( "nevszavazas.json");
    
    if ( mentesSikeres == false )
    {
System.out.println( "MentesListener::handle() : mentes nem sikerult. Ide lehetne valami MessageBoxot tenni");
    }
  }
  
  public boolean szavazasAllasElment( final String fileNev)
  {
    ObjectMapper objectMapper = new ObjectMapper();

    osszNevSzavazatTomb[0].nevekSzavazatSzerintiRendezese();
System.out.println( "MentesListener::szavazasAllasElment() : " + osszNevSzavazatTomb[0].toString());
    
    try
    {
      // convert user object to json string, and save to a file
      objectMapper.writeValue( new File( fileNev), osszNevSzavazatTomb[0]);

System.out.println( "MentesListener::szavazasAllasElment() OK");
    }
    catch (JsonGenerationException jsonGenerationException)
    {
      jsonGenerationException.printStackTrace();
      return false;
    }
    catch (JsonProcessingException jsonProcessingException)
    {
      jsonProcessingException.printStackTrace();
      return false;
    }
    catch (IOException iOException)
    {
      iOException.printStackTrace();
      return false;
    }

    return true;
  }
}

class KilepesListener implements EventHandler<ActionEvent>
{
  @Override
  public void handle(ActionEvent arg0)
  {
    Platform.exit();
  }
}