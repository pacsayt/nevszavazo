package nevszavazo;

import java.io.File;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author tamas
 */
public class CmdSzavazasTeszt
{
  private OsszNevSzavazat osszNevSzavazat = new OsszNevSzavazat();
  
  int baloIndex  = 0;
  int jobboIndex = 0;
  
  private boolean kilepes = false;
  
  public CmdSzavazasTeszt()
  {
  }

  public void szavazasFolytat( String fileNev)
  {
    kilepes = false;
    
    szavazasAllasBetolt( fileNev);

    szavazasKezd();
    
    szavazasAllasElment( fileNev);
  }
  
  public void szavazasKezd()
  {
    NevSzavazat baloNevSzavazat   = null;
    NevSzavazat jobboNevSzavazat  = null;
    int         szavazoListaMeret = 0;
    
    kilepes = false;
    szavazoListaMeret = osszNevSzavazat.szavazoListaMeret();
   
    for ( int baloIndex = osszNevSzavazat.getBaloldaliIndex() ; baloIndex < szavazoListaMeret && kilepes == false ; baloIndex++)
    {
      baloNevSzavazat = osszNevSzavazat.getNedikBaloldali(baloIndex);
      
      for ( int jobboIndex = osszNevSzavazat.getJobboldaliIndex() ; jobboIndex < szavazoListaMeret && kilepes == false ; jobboIndex++)
      {
        if ( baloIndex == jobboIndex )
        {
          continue;
        }
        
        jobboNevSzavazat = osszNevSzavazat.getNedikJobboldali(jobboIndex);
        szavazEgyParra( baloNevSzavazat, jobboNevSzavazat);
      }
    }
  }
  
  public void szavazEgyParra( NevSzavazat baloNevSzavazat, NevSzavazat jobboNevSzavazat)
  {
 		String valasz;

    System.out.println( baloNevSzavazat.getNev() + " / " + jobboNevSzavazat.getNev() + " (b[al]/j[obb]/k[ilep]) ?");

    valasz = valaszBeolvas();
    
    switch ( valasz )
    {
      case "b" :
        baloNevSzavazat.szavazatNovel();
        break;

      case "j" :
        jobboNevSzavazat.szavazatNovel();
        break;
        
      case "k" :
      default :
        kilepes = true;
    }
  }
  
  public String valaszBeolvas()
  {
 		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 
		String valasz;

    do
    {
      try
      {
        valasz = bufferedReader.readLine();
        System.out.println("valasz=" + valasz);
      }
      catch( IOException eIOException)
      {
		    eIOException.printStackTrace();
        valasz = "k";
	    }
    }
    while ( valasz == null || (!valasz.equals( "b") && !valasz.equals( "j") && !valasz.equals( "k")) );
    
    return valasz;
	}
  
  public boolean szavazasAllasBetolt( String fileNev)
  {
    ObjectMapper objectMapper = new ObjectMapper();
 
    try
    {
      // convert user object to json string, and save to a file
      osszNevSzavazat = objectMapper.readValue( new File( fileNev), OsszNevSzavazat.class);

      // display to console
      System.out.println( "szavazasAllasBetolt() : betoltve " + fileNev + "" + osszNevSzavazat);
    }
    catch (JsonGenerationException jsonGenerationException)
    {
      jsonGenerationException.printStackTrace();
      return false;
    }
    catch (JsonProcessingException jsonProcessingException)
    {
      jsonProcessingException.printStackTrace();
      return false;
    }
    catch (IOException iOException)
    {
      iOException.printStackTrace();
      return false;
    }

    return true;
  }

  public boolean szavazasAllasElment( String fileNev)
  {
    ObjectMapper objectMapper = new ObjectMapper();
 
    try
    {
      // convert user object to json string, and save to a file
      objectMapper.writeValue(new File( fileNev), osszNevSzavazat);

      // display to console
      System.out.println( "szavazasAllasElment()");
    }
    catch (JsonGenerationException jsonGenerationException)
    {
      jsonGenerationException.printStackTrace();
      return false;
    }
    catch (JsonProcessingException jsonProcessingException)
    {
      jsonProcessingException.printStackTrace();
      return false;
    }
    catch (IOException iOException)
    {
      iOException.printStackTrace();
      return false;
    }

    return true;
  }

  public static void main( String args[])
  {
//    CmdSzavazasTeszt szavazas = new CmdSzavazasTeszt();
    
//    szavazas.szavazasKezd();
//    szavazas.szavazasFolytat( "nevszavazas.json");
    
//    szavazas.valaszBeolvas();
//    NevSzavazat baloNevSzavazat  = new NevSzavazat( "baloldali nev", 0);
//    NevSzavazat jobboNevSzavazat = new NevSzavazat( "jobboldali nev", 0);
//    
//    System.out.println( "baloNevSzavazat=" + baloNevSzavazat);
//    System.out.println( "jobboNevSzavazat=" + jobboNevSzavazat);
//    
//    szavazas.szavazEgyParra( baloNevSzavazat, jobboNevSzavazat);
//    
//    System.out.println( "baloNevSzavazat=" + baloNevSzavazat);
//    System.out.println( "jobboNevSzavazat=" + jobboNevSzavazat);
  }
}
