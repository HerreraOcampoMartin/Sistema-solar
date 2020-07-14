//https://www.solarsystemscope.com/textures/

package sistema_solar;

import javafx.application.Application;
import javafx.stage.Stage;

public class Sistema_Solar extends Application {
    
    @Override
    public void start(Stage st) {
        PlanetMenuBuilder main = new PlanetMenuBuilder(st);
    }
    
    public static void main(String[] args) { launch(args); }
}
