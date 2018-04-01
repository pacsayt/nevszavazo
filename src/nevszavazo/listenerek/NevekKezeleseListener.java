/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nevszavazo.listenerek;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import nevszavazo.NevekKezeleseDialogBox;
import nevszavazo.OsszNevSzavazat;

/**
 *
 * @author tamas
 */
public class NevekKezeleseListener implements EventHandler<ActionEvent>
{
  private OsszNevSzavazat[] osszNevSzavazatTomb;
  
  public NevekKezeleseListener( OsszNevSzavazat[] iniOsszNevSzavazatTomb)
  {
    osszNevSzavazatTomb = iniOsszNevSzavazatTomb;
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
    // Aszinkron hivas
    new NevekKezeleseDialogBox( /*Stage owner,*/ Modality.APPLICATION_MODAL, "Nevek kezelese", osszNevSzavazatTomb).show();
  }
}
