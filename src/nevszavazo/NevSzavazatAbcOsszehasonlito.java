package nevszavazo;

import java.util.Comparator;

public class NevSzavazatAbcOsszehasonlito implements Comparator<NevSzavazat>
{
  public int compare( NevSzavazat baloldali, NevSzavazat jobboldali)
  {
    return baloldali.getNev().compareTo( jobboldali.getNev());
  }
}
