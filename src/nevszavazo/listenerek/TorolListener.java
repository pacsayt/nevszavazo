package nevszavazo.listenerek;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nevszavazo.NevekKezeleseDialogBox;

/**
 *
 * @author tamas
 */
public class TorolListener implements EventHandler<ActionEvent>
{
  private final NevekKezeleseDialogBox ujNevFelveszDialogBox;
  
  public TorolListener( NevekKezeleseDialogBox iniUjNevFelveszDialogBox)
  {
    ujNevFelveszDialogBox = iniUjNevFelveszDialogBox;
  }
  
  @Override
  public void handle( ActionEvent actionEvent)
  {
    ujNevFelveszDialogBox.nevTorol();
  }
}

