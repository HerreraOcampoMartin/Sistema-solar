
package sistema_solar;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import sistema_solar.PlanetWindow.SmartGroup;

public class PlanetConstructor {
    
    private Sphere sphere = new Sphere(150);
    private Cylinder cylinder = new Cylinder(300, 1);
    private SmartGroup smartGroup;
    
    public PlanetConstructor(SmartGroup smartGroup){
        this.smartGroup = smartGroup;
    }
    
    //PLANETS
    public SmartGroup prepareMercury(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/mercury_map.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/mercury.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareVenusMap(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/venus_map.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/venus.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareVenusAtmosphere(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/venus_atmosphere.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/venus.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareEarth(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/earth.jpg")));
        texture.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/files/night_earth.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/earth.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareMars(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/mars_map.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/mars.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareJupiter(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/jupiter_map.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/jupiter.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareSaturn(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/saturn_map.jpg")));
        sphere.setMaterial(texture);
        setSaturnRings();
        win.getIcons().set(0, new Image("/files/saturn.png"));
        
        smartGroup.getChildren().add(sphere);
        smartGroup.getChildren().add(cylinder);
        
        prepareAnimation();
        
        return smartGroup;
    }
     
    public SmartGroup prepareUranus(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/uranus_map.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/uranus.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareNeptune(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/neptune_map.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/neptune.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    public SmartGroup prepareSun(PlanetWindow win){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/earth.jpg")));
        sphere.setMaterial(texture);
        win.getIcons().set(0, new Image("/files/sun.png"));
        
        smartGroup.getChildren().add(sphere);
        
        prepareAnimation();
        
        return smartGroup;
    }
    
    
    //RINGS
    private void setSaturnRings(){
        PhongMaterial texture = new PhongMaterial();
        texture.setDiffuseMap(new Image(getClass().getResourceAsStream("/files/saturn_ring.png")));
        cylinder.setMaterial(texture);
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
    }
    
    
}
