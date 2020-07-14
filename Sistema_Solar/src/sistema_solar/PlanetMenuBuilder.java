
package sistema_solar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PlanetMenuBuilder {
    private PlanetWindow window;
    private Scene scene;
    private Group group;
    private Sphere sphere;
    private Stage stageWindow;
    private Camera camera;
    private Cylinder cylinder = new Cylinder(300, 1);
    
    public PlanetMenuBuilder(Stage st){
        stageWindow = st;
        
        camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(10000);
        camera.translateZProperty().set(-1500);
        
        group = new Group();
        
        scene = new Scene(group, 800, 600, true);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        
        stageWindow.setMaximized(true);
        stageWindow.setScene(scene);
        stageWindow.setMinHeight(600);
        stageWindow.setMinWidth(800);
        stageWindow.setTitle("Sistema solar");
        stageWindow.show();
        stageWindow.getIcons().add(new Image("/files/logo.png"));
        stageWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        
        initComponents();
        
    }
    
    
    private void showPlanet(String planet){
        window = new PlanetWindow(planet);
        stageWindow.close();
        window.show();
    }
    
    private Sphere getSphere(){
        Sphere _sphere = new Sphere(150);
        
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/neptune_map.jpg")));
        
        _sphere.setMaterial(material);
        
        return _sphere;
    }
    
    private void prepareAnimation(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                sphere.rotateProperty().set(sphere.getRotate() - 0.2);
                sphere.setRotationAxis(Rotate.Y_AXIS);
            }
        };
        timer.start();
        Thread t = new Thread(new MaterialChange());
        t.start();
   }
    
    private class MaterialChange implements Runnable{
        PhongMaterial material = new PhongMaterial();
        PhongMaterial texture = new PhongMaterial();    
        private int con = 0;

        @Override
        public void run() {
            texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/saturn_ring.png")));
            cylinder.setMaterial(texture);
            while(true){
                if(con < 10){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/mercury_map.jpg"))); }
                else if(con < 20){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/venus_map.jpg"))); }
                else if(con < 30){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/venus_atmosphere.jpg"))); }
                else if(con < 40){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/earth.jpg"))); }
                else if(con < 50){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/mars_map.jpg"))); }
                else if(con < 60){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/jupiter_map.jpg"))); }
                else if(con < 70){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/saturn_map.jpg")));
                                 cylinder.setVisible(true);}
                else if(con < 80){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/uranus_map.jpg")));
                                cylinder.setVisible(false); }
                else if(con < 90){ material.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/neptune_map.jpg"))); }
                else if(con == 90){ con = 0; }
                
                con++;
                sphere.setMaterial(material);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sistema_Solar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void initComponents(){
        
        cylinder.setVisible(false);
        
        sphere = getSphere();
        group.getChildren().addAll(sphere, cylinder);
        prepareAnimation();
        cylinder.rotateProperty().set(cylinder.getRotate() + 30);
        cylinder.setRotationAxis(Rotate.X_AXIS);
        
        Label title = new Label("", new ImageView(new Image("/files/titulo.bmp")));
        title.setLayoutY(-300); title.setLayoutX(-100);
        title.setFont(new Font(40)); title.setTextFill(Color.WHITE);
        
        Button mercury = new Button();
        mercury.setLayoutY(200); mercury.setLayoutX(-425);
        mercury.setGraphic(new ImageView(new Image("/files/mercurio_btn.bmp")));
        mercury.setOnAction(e -> showPlanet("Mercurio"));
        
        Button venus_map = new Button();
        venus_map.setLayoutY(250); venus_map.setLayoutX(-425);
        venus_map.setGraphic(new ImageView(new Image("/files/venus_map_btn.bmp")));
        venus_map.setOnAction(e -> showPlanet("Mapa de Venus"));
        
        Button venus_at = new Button();
        venus_at.setLayoutY(300); venus_at.setLayoutX(-425);
        venus_at.setGraphic(new ImageView(new Image("/files/venus_at_btn.bmp")));
        venus_at.setOnAction(e -> showPlanet("Atmosfera de Venus"));
        
        Button earth = new Button();
        earth.setLayoutY(200); earth.setLayoutX(-100);
        earth.setGraphic(new ImageView(new Image("/files/earth_btn.bmp")));
        earth.setOnAction(e -> showPlanet("Tierra"));
        
        Button mars = new Button();
        mars.setLayoutY(250); mars.setLayoutX(-100);
        mars.setGraphic(new ImageView(new Image("/files/mars_btn.bmp")));
        mars.setOnAction(e -> showPlanet("Marte"));
        
        Button jupiter = new Button();
        jupiter.setLayoutY(300); jupiter.setLayoutX(-100);
        jupiter.setGraphic(new ImageView(new Image("/files/jupiter_btn.bmp")));
        jupiter.setOnAction(e -> showPlanet("Jupiter"));
        
        Button saturn = new Button();
        saturn.setLayoutY(200); saturn.setLayoutX(225);
        saturn.setGraphic(new ImageView(new Image("/files/saturn_btn.bmp")));
        saturn.setOnAction(e -> showPlanet("Saturno"));
        
        Button uranus = new Button();
        uranus.setLayoutY(250); uranus.setLayoutX(225);
        uranus.setGraphic(new ImageView(new Image("/files/uranus_btn.bmp")));
        uranus.setOnAction(e -> showPlanet("Urano"));
        
        Button neptune = new Button();
        neptune.setLayoutY(300); neptune.setLayoutX(225);
        neptune.setGraphic(new ImageView(new Image("/files/neptune_btn.bmp")));
        neptune.setOnAction(e -> showPlanet("Neptuno"));
        
        
        
        group.getChildren().addAll(mercury, venus_map, venus_at, earth, mars,
            jupiter, saturn, uranus, neptune, title);
        
    }
    
}
