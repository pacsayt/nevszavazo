package nevszavazo.listenerek;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nevszavazo.NevekKezeleseDialogBox;

/**
 *
 * @author tamas
 */
public class ModositListener implements EventHandler<ActionEvent>
{
  private final NevekKezeleseDialogBox ujNevFelveszDialogBox;
  
  public ModositListener( NevekKezeleseDialogBox iniUjNevFelveszDialogBox)
  {
    ujNevFelveszDialogBox = iniUjNevFelveszDialogBox;
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
    ujNevFelveszDialogBox.nevModosit();
  }
}
