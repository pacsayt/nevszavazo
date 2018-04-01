package nevszavazo;

/**
 *
 * @author tamas
 */
public class NevSzavazat
{
    private String nev;
    private int    szavazat;
    
    // Jackson hogy be tudja tolteni az osztalyt
    public NevSzavazat()
    {
    }

    public NevSzavazat( String iniNev, int iniSzavazat)
    {
      nev = iniNev;
      szavazat = iniSzavazat;
    }
    
    public String getNev()
    {
      return nev;
    }

    public void setNev( final String ujNev)
    {
      nev = ujNev;
    }
    
    public void szavazatNovel()
    {
      szavazat++;
    }
    
    public void szavazatTorol()
    {
      szavazat = 0;
    }
    
    public int getSzavazat()
    {
      return szavazat;
    }
    
    @Override
    public String toString()
    {
      StringBuilder nevSzavazatSztr = new StringBuilder();
      
      nevSzavazatSztr.append( "nev=").append(nev).append( " szavazat=").append(szavazat);
      
      return nevSzavazatSztr.toString();
    }
}
