package nevszavazo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * A szavazast lebonyolito osztaly.
 * A szavazas igy fest :
 * baloldali nev | jobboldali nev
 * a szimpatikusabbra kell szavazni, ami altal a szavazatainak szama novekszik egyel
 * A szavazas menete :
 * ket egymasba agyazott ciklus (szavazoLista-n) valasztja ki a szavazas alatt allo neveket
 * a "bal" a kulso ciklus
 * minden iteracios lepesben egy szavazat adhato
 * 
 * @author tamas
 */
public class OsszNevSzavazat
{
  private ArrayList<NevSzavazat> szavazoLista = new ArrayList<>();
  
  private int baloldaliIndex  = 0;
  private int jobboldaliIndex = 1;
  
  @JsonIgnore
  private boolean modositva = false;
  
  public OsszNevSzavazat()
  {
System.out.println( "OsszNevSzavazat()---------------------------------------");
/*
    szavazoLista.add( new NevSzavazat( "nev1", 0));
    szavazoLista.add( new NevSzavazat( "nev2", 0));
    szavazoLista.add( new NevSzavazat( "nev3", 0));
    szavazoLista.add( new NevSzavazat( "nev4", 0));
    szavazoLista.add( new NevSzavazat( "nev5", 0));
    szavazoLista.add( new NevSzavazat( "nev6", 0));
    szavazoLista.add( new NevSzavazat( "nev7", 0));    
*/
  }
  
  // A NevSzavazat osztalyokat csak atveszi, nem keszit sajat masolatot !!!
  public OsszNevSzavazat( final OsszNevSzavazat iniOsszNevSzavazat)
  {
System.out.println( "OsszNevSzavazat( final OsszNevSzavazat iniOsszNevSzavazat)---------------------------------------");

    if ( iniOsszNevSzavazat != null )
    {
      baloldaliIndex  = iniOsszNevSzavazat.getBaloldaliIndex();
      jobboldaliIndex = iniOsszNevSzavazat.getJobboldaliIndex();
      
      szavazoLista.addAll( iniOsszNevSzavazat.getSzavazoLista());
    }
  }
  
//  @JsonIgnore
  public boolean modositva()
  {
    return modositva;
  }
  
//   @JsonIgnore
  public void mentve()
  {
    modositva = false;
  }
  
//  @JsonIgnore
  public int szavazoListaMeret()
  {
    return szavazoLista.size();
  }

  public int getBaloldaliIndex()
  {
    return baloldaliIndex;
  }

  public int getJobboldaliIndex()
  {
    return jobboldaliIndex;
  }
  
  public ArrayList<NevSzavazat> getSzavazoLista()
  {
    return szavazoLista;
  }

  // Korabbi elofordulast nem vizsgaljuk egyenlore
  public void nEdikNevBeallit( int nEdik, String ujNev)
  {
    if ( ujNev != null )
    {
      if ( nEdik >= 0 && nEdik < szavazoLista.size() )
      {
        szavazoLista.get( nEdik).setNev( ujNev);
        
        modositva = true;
      }
    }
  }
  
   @JsonIgnore
  public NevSzavazat getNedikBaloldali( int nEdikBaloldali)
  {
    NevSzavazat nevSzavazat = null;
    
    if ( nEdikBaloldali >= 0 && nEdikBaloldali < szavazoLista.size() )
    {
      baloldaliIndex = nEdikBaloldali;
      nevSzavazat = szavazoLista.get( baloldaliIndex);
    }
    
    return nevSzavazat;
  }
  
  @JsonIgnore
  public NevSzavazat getNedikJobboldali( int nEdikJobboldali)
  {
    NevSzavazat nevSzavazat = null;
    
    if ( nEdikJobboldali >= 0 && nEdikJobboldali < szavazoLista.size() )
    {
      jobboldaliIndex = nEdikJobboldali;
      nevSzavazat = szavazoLista.get( jobboldaliIndex);
    }
    
    return nevSzavazat;
  }
  
  public void indexekNovel()
  {
    int szavazoListaMeret = szavazoLista.size();

    if ( jobboldaliIndex < szavazoListaMeret - 1 )
    {
      jobboldaliIndex++;
      
      // Ne legyen azonos a baloldali es jobboldali nev
      if ( jobboldaliIndex == baloldaliIndex  )
      {
        if ( jobboldaliIndex < szavazoListaMeret - 1 )
        {
          jobboldaliIndex++;
        }
      }

      modositva = true;
    }
    else
    {
      // A jobboldali index a vegere ert, noveljuk a baloldalit
      if ( baloldaliIndex < szavazoListaMeret - 1 )
      {
        baloldaliIndex++;
        
        if ( baloldaliIndex < szavazoListaMeret - 1 )
        {
          jobboldaliIndex = baloldaliIndex + 1;
        }
        
        modositva = true;
      }
    }
  }

  /* 
    getBaloldali()/getJobboldali() fuggvenynevek megletenek hatasara meg pluszban 
    "baloldali":{"nev":"nev1","szavazat":0},"jobboldali":{"nev":"nev2","szavazat":0}
    irodik a JSon fileba.
  */
  public NevSzavazat aktualisBaloldali()
  {
    NevSzavazat nevSzavazat = null;
    
    if ( baloldaliIndex < szavazoLista.size() )
    {
      nevSzavazat = szavazoLista.get( baloldaliIndex);
    }
    
    return nevSzavazat;
  }
    
  public NevSzavazat aktualisJobboldali()
  {
    NevSzavazat nevSzavazat = null;
    
    if ( jobboldaliIndex < szavazoLista.size() )
    {
      nevSzavazat = szavazoLista.get( jobboldaliIndex);
    }
    
    return nevSzavazat;
  }

  public boolean nevHozzaad( String ujNev)
  {
    if ( ! vanIlyenNev( ujNev) )
    {
      szavazoLista.add( new NevSzavazat( ujNev, 0));
         
      modositva = true;

      return true;
    }
    
    return false;
  }
  
  public boolean nevHozzaad( NevSzavazat ujNevSzavazat)
  {
    if ( ! vanIlyenNev( ujNevSzavazat.getNev()) )
    {
      szavazoLista.add( ujNevSzavazat);
         
      modositva = true;

      return true;
    }
    
    return false;
  }
  
  public void nevTorol( String torlendoNev)
  {
    if ( torlendoNev == null )
    {
      return;
    }

    ListIterator<NevSzavazat> szavazoListaIterator = szavazoLista.listIterator();
    
    while ( szavazoListaIterator.hasNext() )
    {
      if ( torlendoNev.equals( szavazoListaIterator.next().getNev()) )
      {
        szavazoListaIterator.remove();
        
        modositva = true;
        
        return;
      }
    }
  }
  
  public boolean vanIlyenNev( String nev)
  {
    for ( NevSzavazat aktNevSzavazat : szavazoLista )
    {
      String aktNev = aktNevSzavazat.getNev();
      
      if ( aktNev == null )
      {
        if ( nev == null )
        {
          return true;
        }
        
        return false;
      }
      else
      {
        if ( nev == null )
        {
          return false;
        }
        
        return aktNev.equals( nev);
      }
    }
    
    return false;
  }
  
  public void alaphelyzetbe()
  {
    baloldaliIndex  = 0;
    jobboldaliIndex = 1;

    for ( NevSzavazat aktNevSzavazat : szavazoLista )
    {
      aktNevSzavazat.szavazatTorol();
    }
  }
  
  @Override
  public String toString()
  {
    StringBuilder osszNevSzavazatSB = new StringBuilder();
    
    for ( NevSzavazat aktNevSzavazat : szavazoLista)
    {
      osszNevSzavazatSB.append( aktNevSzavazat.getNev()).append( "(").append( aktNevSzavazat.getSzavazat()).append( ")");
    }

    osszNevSzavazatSB.append( "baloldaliIndex=").append( baloldaliIndex);
    osszNevSzavazatSB.append( "jobboldaliIndex=").append( jobboldaliIndex);
    
    return osszNevSzavazatSB.toString();
  }
  
  public void nevekAbcRendezese()
  {
    nevekRendezese( new NevSzavazatAbcOsszehasonlito());
  }
  
  public void nevekSzavazatSzerintiRendezese()
  {
    nevekRendezese( new NevSzavazatSzavazatOsszehasonlito());
  }
  
  private void nevekRendezese( Comparator<NevSzavazat> osszehasonlitoAlgoritmus)
  {
    NevSzavazat[] nevSzavazatTomb = szavazoLista.toArray(new NevSzavazat[szavazoLista.size()]);
    
    Arrays.sort( nevSzavazatTomb, osszehasonlitoAlgoritmus);
    
    szavazoLista.clear();
    
    szavazoLista.addAll( Arrays.asList( nevSzavazatTomb));

  }
  
  public static void main( String[] args)
  {
    OsszNevSzavazat osszNevSzavazat = new OsszNevSzavazat();
/* rendezes
    osszNevSzavazat.nevHozzaad( new NevSzavazat( "nev2", 3));
    osszNevSzavazat.nevHozzaad( new NevSzavazat( "nev4", 5));
    osszNevSzavazat.nevHozzaad( new NevSzavazat( "nev3", 2));
    osszNevSzavazat.nevHozzaad( new NevSzavazat( "nev1", 4));
    osszNevSzavazat.nevHozzaad( new NevSzavazat( "nev5", 1));

    osszNevSzavazat.nevekAbcRendezese();
    
    System.out.println("nevekAbcRendezese() utan : osszNevSzavazat=" + osszNevSzavazat.toString());
    
    osszNevSzavazat.nevekSzavazatSzerintiRendezese();
    
    System.out.println("nevekSzavazatSzerintiRendezese() utan : osszNevSzavazat=" + osszNevSzavazat.toString());
*/
/*
    osszNevSzavazat.nevTorol( "nev1");
    osszNevSzavazat.nevTorol( "nincsIlyen");
*/
    
//    NevSzavazat baloldaliNevSzavazat  = null;
//    NevSzavazat jobboldaliNevSzavazat = null;

/*
    int szavazoListaMeret = osszNevSzavazat.szavazoListaMeret();
    int baloldaliIndex  = osszNevSzavazat.getBaloldaliIndex();
    int jobboldaliIndex = osszNevSzavazat.getJobboldaliIndex();
    
    while ( !(baloldaliIndex == szavazoListaMeret - 1 && jobboldaliIndex == szavazoListaMeret - 1) )
    {
      baloldaliIndex  = osszNevSzavazat.getBaloldaliIndex();
      jobboldaliIndex = osszNevSzavazat.getJobboldaliIndex();
      
      System.out.println( "baloldaliIndex : " + baloldaliIndex);
      System.out.println( "jobboldaliIndex : " + jobboldaliIndex);
//      NevSzavazat baloldaliNevSzavazat  = null;
//      NevSzavazat jobboldaliNevSzavazat = null;
      osszNevSzavazat.indexekNovel();
    }
*/
  }
}