package nevszavazo.listenerek;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nevszavazo.NevekKezeleseDialogBox;

/**
 *
 * @author tamas
 */
public class HozzaadListener implements EventHandler<ActionEvent>
{
  private final NevekKezeleseDialogBox ujNevFelveszDialogBox;
  
  public HozzaadListener( NevekKezeleseDialogBox iniUjNevFelveszDialogBox)
  {
    ujNevFelveszDialogBox = iniUjNevFelveszDialogBox;
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
    ujNevFelveszDialogBox.ujNevHozzaad();
  }
}
