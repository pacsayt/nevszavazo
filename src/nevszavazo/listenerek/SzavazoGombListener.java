/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nevszavazo.listenerek;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import nevszavazo.NevSzavazat;
import nevszavazo.OsszNevSzavazat;

/**
 *
 * @author tamas
 */
public class SzavazoGombListener implements EventHandler<ActionEvent>
{
  private StringProperty baloNevProperty  = new SimpleStringProperty();
  private StringProperty jobboNevProperty = new SimpleStringProperty();
  private Button         baloNevButton;
  private Button         jobboNevButton;
  
  private OsszNevSzavazat[] osszNevSzavazatTomb;
  
  public SzavazoGombListener( OsszNevSzavazat[] iniOsszNevSzavazat, StringProperty iniBaloNevProperty, StringProperty iniJobboNevProperty, Button iniBaloNevButton, Button iniJobboNevButton)
  {
    osszNevSzavazatTomb  = iniOsszNevSzavazat;
    baloNevProperty.bindBidirectional( iniBaloNevProperty);
    jobboNevProperty.bindBidirectional( iniJobboNevProperty);
    baloNevButton    = iniBaloNevButton;
    jobboNevButton   = iniJobboNevButton;
    
    // csak atmeneti jelleggel
    // java.lang.reflect.InvocationTargetException : aused by: java.lang.NullPointerException
    if ( osszNevSzavazatTomb[0].getSzavazoLista().size() != 0 )
    {
      baloNevProperty.setValue( osszNevSzavazatTomb[0].aktualisBaloldali().getNev());
      jobboNevProperty.setValue( osszNevSzavazatTomb[0].aktualisJobboldali().getNev());
    }
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
System.out.println( actionEvent.getSource().toString());
    if ( actionEvent.getSource() instanceof Button )
    {
      Button lenyomottButton = (Button) actionEvent.getSource();
      
      if ( lenyomottButton == baloNevButton )
      {
        NevSzavazat baloldaliNevSzavazat = osszNevSzavazatTomb[0].aktualisBaloldali();
        
        if ( baloldaliNevSzavazat != null )
        {
          baloldaliNevSzavazat.szavazatNovel();
System.out.println( "SzavazoGombListener::handle() : b noveles utan : " + baloldaliNevSzavazat.toString());
        }
      }
      else
      {
        if ( lenyomottButton == jobboNevButton )
        {
          NevSzavazat jobboldaliNevSzavazat = osszNevSzavazatTomb[0].aktualisJobboldali();

          if ( jobboldaliNevSzavazat != null )
          {
            jobboldaliNevSzavazat.szavazatNovel();
System.out.println( "SzavazoGombListener::handle() : j noveles utan : " + jobboldaliNevSzavazat.toString());
          }
        }
      }
      
      osszNevSzavazatTomb[0].indexekNovel();
      
      baloNevProperty.setValue( osszNevSzavazatTomb[0].aktualisBaloldali().getNev());
      jobboNevProperty.setValue( osszNevSzavazatTomb[0].aktualisJobboldali().getNev());
    }
  }

}
