package nevszavazo;

import java.util.Comparator;

public class NevSzavazatSzavazatOsszehasonlito implements Comparator<NevSzavazat>
{
  @Override
  public int compare( NevSzavazat baloldali, NevSzavazat jobboldali)
  {
    int rc = 0;
    
    if ( baloldali.getSzavazat() == jobboldali.getSzavazat() )
    {
      rc = 0;
    }
    else
    {
      if ( baloldali.getSzavazat() < jobboldali.getSzavazat() )
      {
        rc = 1;
      }
      else
      {
        rc = -1;
      }
    }
    
    return rc;
  }
}
