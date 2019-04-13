import Games.Game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class ArcExample extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing an arc
        Circle circle = new Circle();


        circle.setCenterX(300.0f);
        circle.setCenterY(10.0f);
        circle.setRadius(10.0f);
        circle.setTranslateY(100);

        //Setting the type of the arc


        //Creating a Group object
        Group root = new Group(circle);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Drawing an Arc");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}