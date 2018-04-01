package nevszavazo.listenerek;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nevszavazo.OKCancelDialogBox;

/**
 *
 * @author tamas
 */
public class OKListener implements EventHandler<ActionEvent>
{
  private final OKCancelDialogBox okCancelDialogBox;
  
  public OKListener( OKCancelDialogBox iniOKCancelDialogBox)
  {
    okCancelDialogBox = iniOKCancelDialogBox;
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
    okCancelDialogBox.valtoztatasokVisszaad();
    okCancelDialogBox.close();
  }
}