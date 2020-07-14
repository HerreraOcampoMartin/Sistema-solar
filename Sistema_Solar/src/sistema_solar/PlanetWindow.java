
package sistema_solar;

import javafx.animation.AnimationTimer;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PlanetWindow extends Stage{
    
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private String link = "";
    private SmartGroup smartGroup;
    private PlanetConstructor planetBuilder;
    
    public PlanetWindow(String planet){
        
        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(10000);
        camera.translateZProperty().set(-1500);
        camera.translateXProperty().set(300);
        
        smartGroup = new SmartGroup();
        planetBuilder = new PlanetConstructor(smartGroup);
        
        ObservableValue value = new ObservableValue() {
            @Override public void addListener(ChangeListener listener) { }
            @Override public void removeListener(ChangeListener listener) { }
            @Override public Object getValue() { return -250; } 
            @Override public void addListener(InvalidationListener listener) { } 
            @Override public void removeListener(InvalidationListener listener) { } };
        smartGroup.translateZProperty().bind(value);
        
        Group root = new Group();
        root.getChildren().add(smartGroup);
        root.getChildren().add(prepareImageView());
        
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        
        initMouseEvent(smartGroup, scene);
        
        this.setScene(scene);
        this.setTitle("Sistema solar");
        this.setMaximized(true);
        this.getIcons().add(new Image("/files/logo.png"));
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        
        checkPlanet(planet);
        
        initComponents(root, planet);
    }
    
    //METHODS
    private void initComponents(Group root, String planet){
        
        Label planet_name = new Label(planet);
        planet_name.setFont(new Font(30)); planet_name.setTextFill(Color.WHITE);
        planet_name.setLayoutY(-300); planet_name.setLayoutX(300);
        
        Button atras = new Button("<--");
        atras.setOnAction(e ->{
            this.close();
            PlanetMenuBuilder main = new PlanetMenuBuilder(new Stage());
        });
        atras.setLayoutY(-290); atras.setLayoutX(250);
        
        Browser browser = new Browser();
        browser.setLayoutY(-200); browser.setLayoutX(150);
        
        root.getChildren().addAll(atras, planet_name, browser);
        
    }
    
    private void initMouseEvent(SmartGroup box, Scene scene){
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        box.getTransforms().addAll(
                xRotate,
                yRotate
        );
        
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
        
    }
    
    private ImageView prepareImageView(){
        Image image = new Image(Sistema_Solar.class.getResourceAsStream("/files/background.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.getTransforms().add(new Translate(-image.getWidth() / 2, -image.getHeight()/ 2, 0));
        
        return imageView;
    }
    
    private void checkPlanet(String planet){
        switch (planet){
            case "Mercurio":
                planetBuilder.prepareMercury(this);
                link = "https://es.wikipedia.org/wiki/Mercurio_(planeta)";
                break;
            case "Mapa de Venus":
                planetBuilder.prepareVenusMap(this);
                link = "https://es.wikipedia.org/wiki/Venus_(planeta)";
                break;
            case "Atmosfera de Venus":
                planetBuilder.prepareVenusAtmosphere(this);
                link = "https://es.wikipedia.org/wiki/Venus_(planeta)";
                break;
            case "Tierra":
                planetBuilder.prepareEarth(this);
                link = "https://es.wikipedia.org/wiki/Tierra";
                break;
            case "Marte":
                planetBuilder.prepareMars(this);
                link = "https://es.wikipedia.org/wiki/Marte_(planeta)";
                break;
            case "Jupiter":
                planetBuilder.prepareJupiter(this);
                link = "https://es.wikipedia.org/wiki/J%C3%BApiter_(planeta)";
                break;
            case "Saturno":
                planetBuilder.prepareSaturn(this);
                link = "https://es.wikipedia.org/wiki/Saturno_(planeta)";
                break;
            case "Urano":
                planetBuilder.prepareUranus(this);
                link = "https://es.wikipedia.org/wiki/Urano_(planeta)";
                break;
            case "Neptuno":
                planetBuilder.prepareNeptune(this);
                link = "https://es.wikipedia.org/wiki/Neptuno_(planeta)";
                break;
        }
    }
    
    class SmartGroup extends Group{
        private Rotate r;
        private Transform t =  new Rotate();
        
        void rotateX(int ang){
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }
        void rotateY(int ang){
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }
        
    }
    
    class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load(link);
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}
    
}
