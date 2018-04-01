/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nevszavazo.listenerek;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nevszavazo.OKCancelDialogBox;

/**
 *
 * @author tamas
 */
public class CancelListener implements EventHandler<ActionEvent>
{
  private final OKCancelDialogBox okCancelDialogBox;
  
  public CancelListener( OKCancelDialogBox iniOKCancelDialogBox)
  {
    okCancelDialogBox = iniOKCancelDialogBox;
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
    okCancelDialogBox.close();
  }
}