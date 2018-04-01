package nevszavazo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author tamas
 */
public class NevSzavazo extends Application
{
  public static final String ALKALMAZAS_CIM_S = "Nev Szavazo";
    
  private final OsszNevSzavazat[] osszNevSzavazatTomb = {new OsszNevSzavazat()};

  @Override
  public void start( Stage primaryStage)
  {
    BorderPane borderPane = new BorderPane();
    Group rootGroup = new Group(); // The root node of the scene graph
    Scene scene     = new Scene( rootGroup, 300, 250);

    // Menu
    borderPane.setTop( NevSzavazoMenuBarLetrehoz.menuBarLetrehoz( osszNevSzavazatTomb));

    // Kozepso parbeszed panel
    NevSzavazoPane nevSzavazoPane = new NevSzavazoPane( osszNevSzavazatTomb);
    
    borderPane.setCenter( nevSzavazoPane);

    // bind to take available space
    borderPane.prefHeightProperty().bind( scene.heightProperty());
    borderPane.prefWidthProperty().bind( scene.widthProperty());

    // BorderPane -> Group -> Stage -> Scene
    rootGroup.getChildren().add( borderPane);
    // l. fent : new Scene( rootGroup, 300, 250);
    primaryStage.setTitle( ALKALMAZAS_CIM_S);
    primaryStage.setScene( scene);
    primaryStage.show();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application.
   * main() serves only as fallback in case the application can not be
   * launched through deployment artifacts, e.g., in IDEs with limited FX
   * support. NetBeans ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
System.out.println( "NevSzavazo::main()");
    launch(args);
  }
}
