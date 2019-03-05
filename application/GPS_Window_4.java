/*
 * This version adds Thomas's code part and then work with PE's code part
 * Pay attention to the part with Slider, it isn't finished !!!!
 */
package application;

import java.io.FileInputStream;

import fr.ensma.a3.ia.InfoWifi.ReseauOverTime;
import fr.ensma.a3.ia.InfoWifi.Routeur;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class GPS_Window_4 extends Application{
	int countClickedPoints = 0;
	int countTextField = 0;
	
	// Circle points
	Circle firstCircle = new Circle();
	Circle secondCircle = new Circle();
	Circle assignedCircle = new Circle();
	
	// Parameters help to get coordinates
	String firstLongGPSText, secondLongGPSText, firstLatGPSText, secondLatGPSText;
    
    // Parameters to calculate our X Point
    PointPixel pixelPoint1 = new PointPixel();
    PointPixel pixelPoint2 = new PointPixel();
    PointPixel pixelPointX = new PointPixel();
    PointGPS gpsPoint1 = new PointGPS();
    PointGPS gpsPoint2 = new PointGPS();
    PointGPS gpsPointX = new PointGPS();
	
    public static void main(String[] args) {
        launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		primaryStage.setTitle("GPS Map Viewer");
		
		// Instruction text
		String instructTextString = "Choose the first point";
		
		Text instructionText = new Text(instructTextString);
		instructionText.setTextAlignment(TextAlignment.CENTER);
		instructionText.setStyle("-fx-font : 16 Arial;");
		
		/*or Label instructionText = new Label(instructTextString);*/
		
		// The Map will be put into a scrollable pane
		ScrollPane scrollMap = new ScrollPane();
		
		// Create our Map image
		String imageLink = "/home/tranv/Images/ENSMA1.png"; // Create a textbox to insert image's link later !!!!!!!
		Image image = new Image(new FileInputStream(imageLink));
		ImageView imageView = new ImageView(image);
		Pane paneMap = new Pane(imageView);
		
		scrollMap.setPrefSize(1000, 1000);
		scrollMap.setContent(paneMap);
		
		// Create a HBox to type our parameters and to show results
		HBox pointsBox = new HBox();
		// Text Column (Left Side)
			// Text for first point part
		Text click1 = new Text("Clicked First Point :");
		click1.setFill(javafx.scene.paint.Color.ORANGE);
		Text latitudeGPS1 = new Text("First Point's Latitude :");
		latitudeGPS1.setFill(javafx.scene.paint.Color.ORANGE);
		Text longitudeGPS1 = new Text("First Point's Longitude :");
		longitudeGPS1.setFill(javafx.scene.paint.Color.ORANGE);
			// Text for second point part
		Text click2 = new Text("Clicked Second Point :");
		click2.setFill(javafx.scene.paint.Color.BLUE);
		Text latitudeGPS2 = new Text("Second Point's Latitude:");
		latitudeGPS2.setFill(javafx.scene.paint.Color.BLUE);
		Text longitudeGPS2 = new Text("Second Point's Longitude:");
		longitudeGPS2.setFill(javafx.scene.paint.Color.BLUE);
			// Text for unknown point part
		Text clickUnk = new Text("Clicked Unknown Point :");		
		clickUnk.setFill(javafx.scene.paint.Color.RED);
		Text latitudeResultText = new Text("Latitude Result :");
		latitudeResultText.setFill(javafx.scene.paint.Color.RED);
		Text longitudeResultText = new Text("Longitude Result :");
		longitudeResultText.setFill(javafx.scene.paint.Color.RED);		
			// Put all the Texts above into a VBox
		VBox vboxPointsText = new VBox(click1, latitudeGPS1, longitudeGPS1, click2, latitudeGPS2, longitudeGPS2, clickUnk, latitudeResultText, longitudeResultText);
		vboxPointsText.setSpacing(20);
		
		// Text Field Column (Right Side)
			// Text Field for first point part
		TextField textField1 = new TextField("[x , y]");
		textField1.setDisable(true);
		TextField gpsLatField1 = new TextField();
		gpsLatField1.setPromptText("Insert latitude");
		gpsLatField1.setDisable(false);
		TextField gpsLongField1 = new TextField();
		gpsLongField1.setPromptText("Insert longitude");
		gpsLongField1.setDisable(false);
			// Text Field for second point part
		TextField textField2 = new TextField("[x , y]");
		textField2.setDisable(true);
		TextField gpsLatField2 = new TextField();
		gpsLatField2.setPromptText("Insert latitude");
		gpsLatField2.setDisable(false);
		TextField gpsLongField2 = new TextField();
		gpsLongField2.setPromptText("Insert longitude");
		gpsLongField2.setDisable(false);
			// Text Field for unknown point part
		TextField textFieldUnk = new TextField("[x , y]");
		textFieldUnk.setDisable(true);
		TextField resultLatTextField = new TextField("Latitude result");
		resultLatTextField.setDisable(true);
		TextField resultLongTextField = new TextField("Longitude result");
		resultLongTextField.setDisable(true);
			// Put all the TextFields above into a VBox
		VBox vboxPointsTextField = new VBox(textField1, gpsLatField1, gpsLongField1, textField2, gpsLatField2, gpsLongField2, textFieldUnk, resultLatTextField, resultLongTextField);
		vboxPointsTextField.setSpacing(10);
		
		pointsBox.getChildren().addAll(vboxPointsText, vboxPointsTextField);
		pointsBox.setAlignment(Pos.BASELINE_CENTER);
		
		// Buttons Part
		Button showResultBtn = new Button("Show result");
		Button changeUnkPointBtn = new Button("Change Unkown Point");
		Button resetAllBtn = new Button("Reset All");
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(showResultBtn, changeUnkPointBtn, resetAllBtn);
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.BASELINE_CENTER);
		
		// Slider Part
        String cheminCSV = "/home/tranv/Documents/BE/simulation3.csv"; // Where we store css file
		ReseauOverTime r = new ReseauOverTime(cheminCSV);
		r.update();
		
		Label sliderLabel = new Label("Select Time");
		Slider timeSlider = new Slider();
        timeSlider.setMin(0);
        timeSlider.setMax(r.getNbReseau()-1);
        timeSlider.setValue(0);
 
        timeSlider.setMajorTickUnit(1);
        timeSlider.setMinorTickCount(0);
        timeSlider.setSnapToTicks(true); // timeSlider should always be aligned with the tick marks.
        timeSlider.setShowTickLabels(true);
        timeSlider.setShowTickMarks(true);
        timeSlider.setBlockIncrement(1);
		VBox sliderBox = new VBox(sliderLabel, timeSlider);
		
		// Create a VBox to contain all other nodes
		VBox root = new VBox(instructionText,scrollMap, pointsBox, buttonBox, sliderBox);
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		
		// CREATE EVENT HANDLERS PART
		
		// EVENT FOR ImageView
		
        // The following lines allows detection of clicks on transparent parts of the image:
        imageView.setPickOnBounds(true);
        imageView.setOnMouseClicked(e -> {
        	countClickedPoints++;
        	
        	if(countClickedPoints == 1) {
        		System.out.println("First point's pixel : ["+e.getX()+", "+e.getY()+"]");
        		// Create first circle point on paneMap    		
        		firstCircle.setCenterX(e.getX());
        		firstCircle.setCenterY(e.getY());
        		firstCircle.setRadius(2);
        		firstCircle.setFill(javafx.scene.paint.Color.ORANGE);
        		paneMap.getChildren().add(firstCircle);
        		 
        		// Modify click1 textfield
                textField1.setDisable(false);
                textField1.setText("["+e.getX()+", "+e.getY()+"]");
                textField1.setDisable(true);
                
                // Set parameters for First Point Pixel
                pixelPoint1.setXPixel(e.getX());
                pixelPoint1.setYPixel(e.getY());
                
                instructionText.setText("Choose Second Point");
        	} else if(countClickedPoints == 2) {
        		System.out.println("Second point's pixel : ["+e.getX()+", "+e.getY()+"]");
        		// Create second circle point on paneMap
        		secondCircle.setCenterX(e.getX());
        		secondCircle.setCenterY(e.getY());
        		secondCircle.setRadius(2);
        		secondCircle.setFill(javafx.scene.paint.Color.BLUE);
        		paneMap.getChildren().add(secondCircle);
        		
        		// Modify click2 textfield
        		textField2.setDisable(false);
                textField2.setText("["+e.getX()+", "+e.getY()+"]");
                textField2.setDisable(true);
                // Set parameters for First Point Pixel
                pixelPoint2.setXPixel(e.getX());
                pixelPoint2.setYPixel(e.getY());
                
                instructionText.setText("Choose Point which needs to be Determinated");
        	} else if (countClickedPoints == 3) {
        		System.out.println("Determination point's pixel : ["+e.getX()+", "+e.getY()+"]");
        		// Create assigned circle point on paneMap
        		assignedCircle.setCenterX(e.getX());
        		assignedCircle.setCenterY(e.getY());
        		assignedCircle.setRadius(2);
        		assignedCircle.setFill(javafx.scene.paint.Color.RED);
        		paneMap.getChildren().add(assignedCircle);
        		
        		
        		// Modify clickUnk textfield
        		textFieldUnk.setDisable(false);
                textFieldUnk.setText("["+e.getX()+", "+e.getY()+"]");
                textFieldUnk.setDisable(true);
                // Set parameters for X Point Pixel
                pixelPointX.setXPixel(e.getX());
                pixelPointX.setYPixel(e.getY());
                
                instructionText.setText("All Points are set \n"
                		+ "Fill all other parameters and wait to see your Result below!");
                
        	} else return;
            
            // This method must be use one time (use a counter), then change to another method.
        });
        
        // EVENT FOR TextField
       
        // Event for GPS latitude TextField 1
        // Get all Pressed Keys after the ENTER key        
        gpsLatField1.setOnKeyPressed(e -> {
        	if (e.getCode().equals(KeyCode.ENTER)) {
        		countTextField++;
        		
        		System.out.println("First Point's Latitude : "+ gpsLatField1.getText());
        		// Set parameters for the First Point GPS
        		firstLatGPSText = gpsLatField1.getText();
        		gpsPoint1.setYGPS(Double.parseDouble(firstLatGPSText));       		
        		gpsLatField1.setDisable(true);
        	}
        });
        
        // Event for GPS longitude TextField 1
        // Get all Pressed Keys after the ENTER key        
        gpsLongField1.setOnKeyPressed(e -> {
        	if (e.getCode().equals(KeyCode.ENTER)) {
        		countTextField++;
        		
        		System.out.println("First Point's Longitude : "+ gpsLongField1.getText());
        		// Set parameters for the First Point GPS
        		firstLongGPSText = gpsLongField1.getText();
        		gpsPoint1.setXGPS(Double.parseDouble(firstLongGPSText));       		
        		gpsLongField1.setDisable(true);
        	}
        });        
        
        // Event for GPS latitude TextField 2
        // Get all Pressed Keys after the ENTER key        
        gpsLatField2.setOnKeyPressed(e -> {
        	if (e.getCode().equals(KeyCode.ENTER)) {
        		countTextField++;
        		
        		System.out.println("Second Point's Latitude : "+ gpsLatField2.getText());
        		// Set parameters for the Second Point GPS
        		secondLatGPSText = gpsLatField2.getText();
        		gpsPoint2.setYGPS(Double.parseDouble(secondLatGPSText));       		
        		gpsLatField2.setDisable(true);
        	}
        });
        
        // Event for GPS longitude TextField 2
        // Get all Pressed Keys after the ENTER key        
        gpsLongField2.setOnKeyPressed(e -> {
        	if (e.getCode().equals(KeyCode.ENTER)) {
        		countTextField++;
        		
        		System.out.println("Second Point's Longitude : "+ gpsLongField2.getText());
        		// Set parameters for the Second Point GPS
        		secondLongGPSText = gpsLongField2.getText();
        		gpsPoint2.setXGPS(Double.parseDouble(secondLongGPSText));       		
        		gpsLongField2.setDisable(true);
        	}
        });       
        // EVENT FOR Buttons
        
        // Event for Show Result Button
        showResultBtn.setOnAction(e -> {
        	if(countClickedPoints == 3 && countTextField == 4) {
        		// This button helps to show results only when all the TextField is filled and all points are set.
            	DeterminerGPS gpsPointX = new DeterminerGPS(gpsPoint1, gpsPoint2, pixelPoint1, pixelPoint2, pixelPointX);
                resultLatTextField.setDisable(false);
                resultLatTextField.setText(gpsPointX.getY_GPS() + "");
                resultLatTextField.setDisable(true);
                resultLongTextField.setDisable(false);
                resultLongTextField.setText(gpsPointX.getX_GPS() + "");
                resultLongTextField.setDisable(true);
                instructionText.setText("See Results below");
                
                
        		//ajouter cercles de presence
                /*
                System.out.println("entre E9 dans les cercles");
                /*
                PointGPS centre1 = new PointGPS(0.361753f, 46.660738f);
                PointGPS centre2 = new PointGPS(0.361332f, 46.660651f);
                PointGPS centre3 = new PointGPS(0.361521f, 46.660817f);
        		
                Routeur router1 = r.getReseau(0).getRouteur(0);
                PointGPS centre1 = new PointGPS(router1.getLatitude(), router1.getLongitude());
                Routeur router2 = r.getReseau(0).getRouteur(1);
                PointGPS centre2 = new PointGPS(router2.getLatitude(), router2.getLongitude());
                PointGPS centre3 = new PointGPS(0.361521f, 46.660817f);
                /* We haven't had information about the third router 
                Routeur router3 = r.getReseau(0).getRouteur(2);
                PointGPS centre3 = new PointGPS(router3.getLatitude(), router3.getLongitude());
                
                
        		Circle minB460 = new CercleEnM(centre1, 12f, gpsPointX,Color.rgb(255, 0, 0, 0.1));
        		Circle maxB460 = new CercleEnM(centre1, 23f, gpsPointX,Color.rgb(255, 0, 0, 0.1));
        		Shape zoneB460 = Shape.subtract(maxB460,minB460);
        		zoneB460.setFill(Color.rgb(255, 0, 0, 0.2));
        		Circle maxB414 = new CercleEnM(centre2, 15f, gpsPointX,Color.rgb(0, 255, 0, 0.1));
        		Circle minB414 = new CercleEnM(centre2, 5f, gpsPointX,Color.rgb(0, 255, 0, 0.1));
        		Shape zoneB414 = Shape.subtract(maxB414,minB414);
        		zoneB414.setFill(Color.rgb(0, 255, 0, 0.2));
        		Circle maxB405 = new CercleEnM(centre3, 23f, gpsPointX,Color.rgb(0, 0, 255, 0.1));
        		Circle minB405 = new CercleEnM(centre3, 12f, gpsPointX,Color.rgb(0, 0, 255, 0.1));
        		Shape zoneB405 = Shape.subtract(maxB405,minB405);
        		zoneB405.setFill(Color.rgb(0, 0, 255, 0.2));
        		Shape zonepresence =Shape.intersect(zoneB460, zoneB414);
        		zonepresence =Shape.intersect(zonepresence, zoneB405);    
        		zonepresence.setFill(Color.rgb(0, 255, 255, 0.4));
                paneMap.getChildren().addAll(zoneB405,zoneB414,zoneB460,zonepresence);
                */
                
                System.out.println("Worked");
        	} else {
        		// If not, it shows instruction text.
				instructionText.setText("Missing Information! "
						+ " Reset All please!");
			}
        		
        });
        
        // Event for Change Unknown Point Button
        changeUnkPointBtn.setOnAction(e -> {
        	countClickedPoints = 2;
        	instructionText.setText("Choose Point which needs to be Determinated");
        	textFieldUnk.setText("[x , y]");
        	resultLatTextField.setText("Latitude result");
        	resultLongTextField.setText("Longitude result");       	
        	paneMap.getChildren().removeAll(assignedCircle);
        });
        
        // Event for Reset All Button
        resetAllBtn.setOnAction(e -> {
        	countClickedPoints = 0;
        	countTextField = 0;
        	
        	// Reset all text field layouts
        	instructionText.setText("Choose First Point");
        	textField1.setText("[x , y]");        	
        	gpsLatField1.setDisable(false);
        	gpsLatField1.clear();
        	gpsLongField1.setDisable(false);
        	gpsLongField1.clear();
        	textField2.setText("[x , y]");       	
        	gpsLatField2.setDisable(false);
        	gpsLatField2.clear();
        	gpsLongField2.setDisable(false);
        	gpsLongField2.clear();
        	textFieldUnk.setText("[x , y]");
        	resultLatTextField.setText("Latitude result");
        	resultLongTextField.setText("Longitude result");        	
        	
        	// Remove all circle points
        	paneMap.getChildren().removeAll(firstCircle, secondCircle, assignedCircle);
  
        	//more....
        });
        
        // EVENT FOR Slider
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
        	 
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
            	
            	// Delete old Circles when timeSlider slides
            	paneMap.getChildren().clear();
            	paneMap.getChildren().add(imageView);
            	
            	// Create new Circles when timeSlider slides
            	Routeur router1 = r.getReseau(newValue.intValue()).getRouteur(0);
            	float minRouter1 = router1.getDistanceMin();
            	float maxRouter1 = router1.getDistanceMax();
                PointGPS centre1 = new PointGPS(router1.getLatitude(), router1.getLongitude());
                Routeur router2 = r.getReseau(newValue.intValue()).getRouteur(1);
                float minRouter2 = router2.getDistanceMin();
            	float maxRouter2 = router2.getDistanceMax();
                PointGPS centre2 = new PointGPS(router2.getLatitude(), router2.getLongitude());
                
                // We have to call out gpsPointX cause it'll be use in Cercle class. Then call functions getX and getY cause if not, we will catch NullPointException.
                DeterminerGPS gpsPointX = new DeterminerGPS(gpsPoint1, gpsPoint2, pixelPoint1, pixelPoint2, pixelPointX);
                gpsPointX.getX_GPS();
                gpsPointX.getY_GPS();
                
                Circle minB460 = new CercleEnM(centre1, minRouter1, gpsPointX,Color.rgb(255, 0, 0, 0.1));
        		Circle maxB460 = new CercleEnM(centre1, maxRouter1, gpsPointX,Color.rgb(255, 0, 0, 0.1));
        		Shape zoneB460 = Shape.subtract(maxB460,minB460);
        		zoneB460.setFill(Color.rgb(255, 0, 0, 0.2));
        		Circle maxB414 = new CercleEnM(centre2, maxRouter2, gpsPointX,Color.rgb(0, 255, 0, 0.1));
        		Circle minB414 = new CercleEnM(centre2, minRouter2, gpsPointX,Color.rgb(0, 255, 0, 0.1));
        		Shape zoneB414 = Shape.subtract(maxB414,minB414);
        		zoneB414.setFill(Color.rgb(0, 255, 0, 0.2));
        		Shape zonepresence =Shape.intersect(zoneB460, zoneB414);
        		zonepresence.setFill(Color.rgb(0, 255, 255, 0.4));
                paneMap.getChildren().addAll(zoneB414,zoneB460,zonepresence);
            }
        });
		
		Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}

}
