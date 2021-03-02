
package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Game extends Application{ // This is our main class.
    static int levelNumber=1; // this variable stores current level.
    static int winCounter =0; // this variable stores number of win for new levels. This is incremented when level is completed for the first time
    static int moveCounter=0; // this variable stores number of moves that player does for each level.
    static int totalCounter=0;// this variable stores number of moves that player does for all game.
    static boolean winChecker=false; // this variable checks whether current level is completed or not.
    static Text counter = new Text(); // this text shows user how many moves that player does with taking moveCounter.
    static Text warning = new Text(); // this texts tells user that he/she should complete previous levels to play selected level.
    static ComboBox levels = new ComboBox(); // this ComboBox object is for passing between levels.
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        ArrayList blockList = new ArrayList<Blocks>(); // this ArrayList holds Blocks objects in the GridPane.
        BorderPane mainPane = new BorderPane(); // this pane takes everything in our game to show them on the scene.
        GridPane pane = new GridPane(); // this pane holds Blocks objects taken from blockList.
        Pane menuPane = new Pane(); // this pane is for menu.
        Pane howToPane = new Pane(); // this pane is for showing How To Play scene.
        Pane close = new Pane(); // this pane is for closing the game.
        Pane restartPane = new Pane(); // this pane is for asking user to play the game again or not in the end of the game.

        Stage closeProgram = new Stage(); // this stages takes close Pane.
        Stage restartStage = new Stage(); // this stage takes restartPane Pane
        // We set pane's properties for our games in the below lines.
        pane.setPrefSize(270,270);
        pane.setHgap(2);
        pane.setVgap(2);
        mainPane.setBackground(new Background(new BackgroundFill(Color.INDIGO,null,null)));



        Button play = new Button("Start Game"); // this button is for start to the game
        Button howToPlay = new Button("How To Play"); // this button is for open howToPane
        Button exitGame = new Button("Exit Game"); // this button is for show closeProgram Stage.
        //these yes and no buttons for Exit game and restart Game.
        Button yes = new Button("Yes"); 
        Button no = new Button("No!");
        Button yes1 = new Button("Yes");
        Button no1 = new Button("No");
        Text sureToExit = new Text("Are you afraid to continue?"); // this text appears in the closeProgram Pane.
        Text endGame = new Text(); //This text appears in the restartPane Pane.
        endGame.setFont(Font.font("Times New Roman" , FontWeight.BLACK, FontPosture.REGULAR ,13));
        //In these lines above we set properties of restartPane,close and menuPane Panes.
        restartPane.setStyle("-fx-background-color : Mintcream"); 
        restartPane.getChildren().addAll(endGame,yes1,no1);
        restartPane.getChildren().get(0).setLayoutX(30);
        restartPane.getChildren().get(0).setLayoutY(40);
        restartPane.getChildren().get(1).setLayoutX(90);
        restartPane.getChildren().get(1).setLayoutY(80);
        restartPane.getChildren().get(2).setLayoutX(140);
        restartPane.getChildren().get(2).setLayoutY(80);

        close.getChildren().addAll(sureToExit,yes,no);
        close.getChildren().get(0).setLayoutX(20);
        close.getChildren().get(0).setLayoutY(20);
        close.getChildren().get(1).setLayoutX(20);
        close.getChildren().get(1).setLayoutY(40);
        close.getChildren().get(2).setLayoutX(100);
        close.getChildren().get(2).setLayoutY(40);

        menuPane.getChildren().addAll(play,howToPlay,exitGame);
        menuPane.setStyle("-fx-background-color : DarkBlue");
        menuPane.getChildren().get(0).setLayoutY(70);
        menuPane.getChildren().get(1).setLayoutY(110);
        menuPane.getChildren().get(2).setLayoutY(150);
        menuPane.getChildren().get(0).setLayoutX(100);
        menuPane.getChildren().get(1).setLayoutX(96);
        menuPane.getChildren().get(2).setLayoutX(103);

        Text description = new Text(); // this is for howToPlay Scene.
        description.setText("\n**********  Welcome to our brand new game. *************\n\nYou should complete 5 different levels to win this game.\n\n"
                + "Rules: \n-You should complete current level to see next level.\n-"
                + "You should set the pipes in the correct order.\n-"
                + "You can't change the position of all items.\n  Some items are static and cannot be moved.\n-"
                + "The difficulty of the game will increase in each new level.");
        Button backMenu = new Button("Go to Main Menu");// this is for back to menuPane from howToPane pane. 

        //we set the properties of howToPane in these lines.
        howToPane.getChildren().add(description);
        howToPane.getChildren().add(backMenu);
        howToPane.getChildren().get(1).setLayoutY(200);
        howToPane.getChildren().get(1).setLayoutX(100);
        description.setFont(Font.font("Times New Roman" , FontWeight.BLACK, FontPosture.REGULAR ,12));

        Scene oppening = new Scene(menuPane,270,270); // this scene takes menuPane pane.
        Scene tutorialScene = new Scene(howToPane,320,320); // this scene takes howToPane pane.
        Scene scene=new Scene(mainPane,262,300); // this scene takes mainPane pane.
        Scene closeScene = new Scene(close,250,80); // this scene takes close pane.
        Scene restartScene = new Scene(restartPane,300,150); // this scene takes restartPane pane.
        closeProgram.setScene(closeScene); // we set the scene for closeProgram Stage
        restartStage.setScene(restartScene); // we set the scene for restartStage Stage
        play.setOnAction(e -> primaryStage.setScene(scene)); //we set setOnAction event for play button. this event for setting primaryStage's scene as scene
        
        howToPlay.setOnAction(e -> { //we set setOnAction event for howToPlay button. this event for setting primaryStage's scene as tutorialScene
            primaryStage.setScene(tutorialScene);
        });
        backMenu.setOnAction(e -> primaryStage.setScene(oppening));//we set setOnAction event for backMenu button. this event for setting primaryStage's scene as oppening

        exitGame.setOnAction(e -> closeProgram.show());// we set exitGame button with setOnAction event for showing closeProgram stage. 
        primaryStage.setOnCloseRequest(e ->{// this event is for when player attempt to close game.
            e.consume();
            closeProgram.show(); // when player attempt to close game, program shows closeProgram stage.
        });

        no.setOnAction(e ->closeProgram.close()); // we set no button with setOnAction event for closing closeProgram stage.
        yes.setOnAction(e ->{ // we set yes button with setOnAction event for closing closeProgram and primaryStage stages.
            closeProgram.close();
            primaryStage.close();
        });

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null))); //we set properties of pane.
        //we set properties of primaryStage below the lines.
        primaryStage.setResizable(false);  
        primaryStage.setScene(oppening);
        primaryStage.setTitle("Roll the Ball");
        restartStage.setTitle("Roll the Ball");
        primaryStage.show();

        //we set properties of counter object below the lines.
        counter.setText("Move : "+moveCounter);
        counter.setLayoutX(190);
        counter.setLayoutY(18);
        counter.setFont(Font.font("Times New Roman" , FontWeight.BOLD, FontPosture.ITALIC ,15));
        counter.setStyle("-fx-text-fill: green");
        mainPane.getChildren().add(counter);
        
        levels.getItems().addAll( // we set levels into the ComboBox.
                "level1",
                "level2",
                "level3",
                "level4",
                "level5"
        );

        levels.setValue("level1");// level1 is the default level for levels ComboBox

        mainPane.setTop(levels);
        levelGenerator(1,blockList,pane,mainPane); // we invoke levelGenerator method for starting the first level.
        levels.setOnAction(e->{ // this event is for choosing levels into levels ComboBox 
            moveCounter=0; // we reset moveCounter value for every new level
            counter.setText("Move : "+moveCounter); // we set text for counter object.
            try {
            	//these if statements controls the index of levels ComboBox and set levelNumber due to that and invoke levelGenerator.
            	//we control winCounter variable to prevent user from playing following levels without completing previous ones.
                if(levels.getValue()=="level1") {
                    levelNumber=1;
                    levelGenerator(1,blockList,pane,mainPane);
                }
                else if(levels.getValue()=="level2"  && winCounter>=1){
                    levelNumber=2;
                    levelGenerator(2,blockList,pane,mainPane);
                }
                else if(levels.getValue()=="level3"  && winCounter>=2) {
                    levelNumber=3;
                    levelGenerator(3,blockList,pane,mainPane);
                }
                else if(levels.getValue()=="level4"  && winCounter>=3) {
                    levelNumber=4;
                    levelGenerator(4,blockList,pane,mainPane);
                }
                else if(levels.getValue()=="level5" && winCounter>=4) {
                    levelNumber=5;
                    levelGenerator(5,blockList,pane,mainPane);
                }
                else {// this else block is executed when user attempt to play following levels without completing previous ones.
                    mainPane.getChildren().remove(pane); //we remove pane from our mainPane
                    if(mainPane.getChildren().size()>1) // we remove ball that we created in levelGenerator method. we use if statement due to possible index out of bound exceptions
                        mainPane.getChildren().remove(2);
                    //we set warning object's index in these lines below
                    warning.setText("You should complete previous level first!");
                    warning.setFont(Font.font("Times New Roman" , FontWeight.BOLD, FontPosture.ITALIC ,15));
                    warning.setLayoutX(5);
                    warning.setLayoutY(150);

                    mainPane.getChildren().add(warning); // adding warning message into mainPane
                }
            }
            catch(Exception e1) {
                System.out.println("There is a problem");
            }

        }) ;
        pane.setOnMousePressed(e -> { //we set setOnMousePressed event into pane GridPane.
        	//Due to the pixel equivalent of the dimensions of the block objects, we divide it by 64 and keep it as an integer.
            int c = (int) (e.getX()/64);// we take event's x value and divide it with 64 to get column equivalent of the dimensions that mouse pressed
            int r = (int) (e.getY()/64);// we take event's y value and divide it with 64 to get row equivalent of the dimensions that mouse pressed
            int i = (r*4)+c; // i is the index of Blocks object that pressed.
            ((Blocks) blockList.get(i)).getImg().setOnMouseDragged(event -> { // we set setOnMouseDragged event into pressed Blocks object in the blockList arrayList.
                if(winChecker==false) { // with this if statement user cannot drag any Blocks object when level is completed.
                	//we substitute pressed event's index from dragged event's index and check the result. Than we do the swap operation due to this result. 
                    if(event.getX()-((Blocks)blockList.get(i)).getImg().getX()>64){// if substitution of pressed event's index from dragged event's index is bigger than 64 we invoke moveToRight method 
                        moveToRight(i,c,blockList,pane); 
                        Win(blockList);//we invoke win method after every swap operation to check whether level is completed or not 
                    }
                    else if(event.getY()-((Blocks)blockList.get(i)).getImg().getY()>64){// if substitution of pressed event's index from dragged event's index is bigger than 64 we invoke moveToRight method
                        moveToDown(i,r,blockList,pane);
                        Win(blockList);
                    }
                    else if(event.getX()-((Blocks)blockList.get(i)).getImg().getX()<0){// if substitution of pressed event's index from dragged event's index is smaller than 0 we invoke moveToRight method
                        moveToLeft(i,c,blockList,pane);
                        Win(blockList);
                    }
                    else if(event.getY()-((Blocks)blockList.get(i)).getImg().getY()<0){// if substitution of pressed event's index from dragged event's index is smaller than 0 we invoke moveToRight method
                        moveToUp(i,r,blockList,pane);
                        Win(blockList);
                    }
                    if( Win(blockList)) { // if level is completed:
                        winChecker=true; // we change the value of winChecker variable as true 
                        ((Circle) mainPane.getChildren().get(3)).setFill(Color.GREENYELLOW); // we change the ball's color when level is completed
                        PathTransition pathTransition = new PathTransition(); // we create pathTransition object for animation
                        pathTransition.setDuration(Duration.millis(4000)); // we set duration as 4000 millis.
                        pathTransition.setNode(mainPane.getChildren().get(3)); // we setNode as circle object.
                        //there are 2 different paths for every level in our game 
                        if(levelNumber==1||levelNumber==2||levelNumber==3) { //level1, level2 and level3 has the same path 
                            Path path = new Path(); // we create path object
                            //we make the right path due to our scene in the game
                            path.getElements().add(new MoveTo(32,50));
                            path.getElements().add(new LineTo(32,220));
                            path.getElements().add(new CubicCurveTo(32, 220, 32, 252, 64,260 ));
                            path.getElements().add(new LineTo(245,260));
                            pathTransition.setPath(path);// we set pathTransition as our path
                            pathTransition.setAutoReverse(false); // AutoReverse must be false 
                            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        }
                        else if(levelNumber==4||levelNumber==5) { // level4,level5 has the same path
                        	//we make the right path due to our scene in the game
                            Path path1 = new Path();
                            path1.getElements().add(new MoveTo(32,50));
                            path1.getElements().add(new LineTo(32,166));
                            path1.getElements().add(new CubicCurveTo(32, 156, 32, 188, 64,194 ));
                            path1.getElements().add(new LineTo(226,194));
                            path1.getElements().add(new CubicCurveTo(226, 194, 230, 194, 234,162));
                            path1.getElements().add(new LineTo(234,123));
                            pathTransition.setPath(path1);// we set pathTransition as our path
                            pathTransition.setAutoReverse(false);// AutoReverse must be false 
                            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        }
                        pathTransition.play(); // play the animation
                        //if the player completed these levels below for the first time, value of winCounter is incremented. 
                        if(levels.getValue().equals("level1")&&winCounter==0) {
                            winCounter++;
                        }
                        else if(levels.getValue().equals("level2")&&winCounter==1){
                            winCounter++;
                        }
                        else if(levels.getValue().equals("level3")&&winCounter==2)
                            winCounter++;
                        else if(levels.getValue().equals("level4")&&winCounter==3)
                            winCounter++;
                        else if(levels.getValue().equals("level5")&&winCounter==4)
                            winCounter++;

                        if(levels.getValue().equals("level5")) { // if current level is 5 and player completed it, game is finished.
                        //We are setting endGame message due to totalCounter variable.
                            if(totalCounter<=27) {
                                endGame.setText("                       You are insane!! "
                                        + "\nYou complete all the levels just with " + totalCounter +" move"
                                        + "\n          Do you want to play again?");
                            }
                            else if(totalCounter<=37) {
                                endGame.setText("                      Not bad! "
                                        + "\nYou complete all the levels with " + totalCounter +" move"
                                        + "\n          Do you want to play again?");
                            }
                            else if(totalCounter>37) {
                                endGame.setText(" You should do brain training dude :(     "
                                        + "\nYou complete all the levels with " + totalCounter +" move"
                                        + "\n          Do you want to play again?");
                            }
                            restartStage.show();// restartStage displays.
                            yes1.setOnAction(restartEvent->{ // if user press yes button it should close the restartStage stage and resets and generates game again. 
                                restartStage.close();
                                try {
                                    levelGenerator(1,blockList,pane,mainPane);
                                    totalCounter=0;
                                    winCounter=0;
                                    levels.setValue("level1");

                                } catch (Exception e1) {
                                }

                            });
                            no1.setOnAction(restartEvent1 ->restartStage.close());// if player pressed no button, player can play current game or exit from game.
                        }
                    }
                }
            });
        });

    }
    public void moveToRight(int i, int c , ArrayList<Blocks> blockList, GridPane pane){// moveToRight method takes index value i ,column value c, ArrayList blockList and GridPane pane
        if(((Blocks) blockList.get(i+1)).getProperty().equals("Free") && (((Blocks) blockList.get(i)).getType().equals("Pipe")
                || ((Blocks) blockList.get(i)).getProperty().equals("none"))){ // this if statement controls the swapping is able to do or not.
            moveCounter++; // we increment moveCounter and totalCounter inside these methods
            totalCounter++;
            counter.setText("Move : "+moveCounter); // and we update the value of counter Text.
            pane.setColumnIndex(((Blocks) blockList.get(i)).getImg(), c+1); // we swap these blocks in gridpane.
            pane.setColumnIndex(((Blocks) blockList.get(i+1)).getImg(), c); 
            
            Blocks blok = blockList.get(i); // this is a temporary Blocks object for swapping
            blockList.set(i,blockList.get(i+1)); //also we swap these blocks in blokcList ArrayList.
            blockList.set(i+1,blok);        
        }
    }
    public void moveToDown(int i, int r, ArrayList<Blocks> blockList , GridPane pane){// moveToRight method takes index value i ,column value c, ArrayList blockList and GridPane pane
        if(i<12&&((Blocks) blockList.get(i+4)).getProperty().equals("Free") && (((Blocks) blockList.get(i)).getType().equals("Pipe")
                || ((Blocks) blockList.get(i)).getProperty().equals("none"))){
            moveCounter++;// we increment moveCounter and totalCounter inside these methods
            totalCounter++;
            counter.setText("Move : "+moveCounter);// and we update the value of counter Text.
            pane.setRowIndex(((Blocks) blockList.get(i)).getImg(), r+1);// we swap these blocks in gridpane.
            pane.setRowIndex(((Blocks) blockList.get(i+4)).getImg(), r);

            Blocks blok = blockList.get(i);// this is a temporary Blocks object for swapping
            blockList.set(i,blockList.get(i+4));//also we swap these blocks in blokcList ArrayList
            blockList.set(i+4,blok);        
        }
    }
    public void moveToLeft(int i, int c, ArrayList<Blocks> blockList , GridPane pane){// moveToRight method takes index value i ,column value c, ArrayList blockList and GridPane pane
        if(((Blocks) blockList.get(i-1)).getProperty().equals("Free") && (((Blocks) blockList.get(i)).getType().equals("Pipe")
                || ((Blocks) blockList.get(i)).getProperty().equals("none"))){
            moveCounter++;// we increment moveCounter and totalCounter inside these methods
            totalCounter++;
            counter.setText("Move : "+moveCounter);// and we update the value of counter Text.
            pane.setColumnIndex(((Blocks) blockList.get(i)).getImg(), c-1);// we swap these blocks in gridpane.
            pane.setColumnIndex(((Blocks) blockList.get(i-1)).getImg(), c);

            Blocks blok = blockList.get(i);// this is a temporary Blocks object for swapping
            blockList.set(i,blockList.get(i-1));//also we swap these blocks in blokcList ArrayList
            blockList.set(i-1,blok);         
        }
    }
    public void moveToUp(int i, int r, ArrayList<Blocks> blockList, GridPane pane){// moveToRight method takes index value i ,column value c, ArrayList blockList and GridPane pane
        if(i>4&&((Blocks) blockList.get(i-4)).getProperty().equals("Free") && (((Blocks) blockList.get(i)).getType().equals("Pipe")
                || ((Blocks) blockList.get(i)).getProperty().equals("none"))){
            moveCounter++;// we increment moveCounter and totalCounter inside these methods
            totalCounter++;
            counter.setText("Move : "+moveCounter);// and we update the value of counter Text.
            pane.setRowIndex(((Blocks) blockList.get(i)).getImg(), r-1);// we swap these blocks in gridpane.
            pane.setRowIndex(((Blocks) blockList.get(i-4)).getImg(), r);
            
            Blocks blok = blockList.get(i);// this is a temporary Blocks object for swapping
            blockList.set(i,blockList.get(i-4));//also we swap these blocks in blokcList ArrayList
            blockList.set(i-4,blok);         
        }
    }
    public boolean Win(ArrayList<Blocks> blockList) { //we are checking whether current level is completed or not with this method
        String didWin = "No";//method will return a boolean value according to this variable
        for (int i = 0; i < blockList.size(); i++) {
        	//we control every blocks object in the blockList with this loop.Firstly it checks the property and type of current block object.
            //than it checks the blocks object which placed right,left,below or above the current blocks object
            //if all blocks are placed correctly, didWin variable's value will be "Yes" and the program will exit from loop
            //if all blocks are not placed correctly, didWin variable's value will be "No" and the program will exit from loop
            if (blockList.get(i).getProperty().equals("Vertical") && !blockList.get(i).getType().equals("End")) {
                if (i<12 && !blockList.get(i + 4).getProperty().equals("Vertical") && !blockList.get(i + 4).getProperty().equals("00")
                        && !blockList.get(i + 4).getProperty().equals("01") ){
                    didWin = "No";
                    break;}
                else if(i>3 && !blockList.get(i - 4).getProperty().equals("Vertical") && !blockList.get(i - 4).getType().equals("End")
                        && !blockList.get(i - 4).getProperty().equals("10")  && !blockList.get(i - 4).getProperty().equals("11")){
                    didWin = "No";
                    break;}
                else if(i>3 && blockList.get(i - 4).getType().equals("End") && blockList.get(i - 4).getProperty().equals("Vertical")) {
                    didWin = "Yes";
                    break; }
            } else if (blockList.get(i).getProperty().equals("Horizontal") && !blockList.get(i).getType().equals("End")) {
                if (i<15 && !blockList.get(i + 1).getProperty().equals("Horizontal") && !blockList.get(i + 1).getProperty().equals("00")
                        && !blockList.get(i + 1).getProperty().equals("10") && !blockList.get(i + 1).getType().equals("End") ) {
                    didWin = "No";
                    break;}
                else if (i>0 && !blockList.get(i - 1).getProperty().equals("Horizontal")
                        && !blockList.get(i - 1).getProperty().equals("01") && !blockList.get(i - 1).getProperty().equals("11")) {
                    didWin = "No";
                    break;}
                else if(i<15 && blockList.get(i + 1).getType().equals("End") && blockList.get(i + 1).getProperty().equals("Horizontal")) {
                    didWin = "Yes";
                    break; }
            } else if (blockList.get(i).getProperty().equals("00")){//10.png
                if (i>0 && !blockList.get(i - 1).getProperty().equals("Horizontal")
                        && !blockList.get(i - 1).getProperty().equals("01") && !blockList.get(i - 1).getProperty().equals("11")) {
                    didWin = "No";
                    break;}
                else if(i>3 && !blockList.get(i - 4).getProperty().equals("Vertical") && !blockList.get(i - 4).getType().equals("End")
                        && !blockList.get(i - 4).getProperty().equals("10")  && !blockList.get(i - 4).getProperty().equals("11")){
                    didWin = "No";
                    break;}
                else if(i>3 && blockList.get(i - 4).getType().equals("End") && blockList.get(i - 4).getProperty().equals("Vertical")) {
                    didWin = "Yes";
                    break; }
            } else if (blockList.get(i).getProperty().equals("01")){ //12.png
                if (i<15 && !blockList.get(i + 1).getProperty().equals("Horizontal") && !blockList.get(i + 1).getType().equals("End")
                        && !blockList.get(i + 1).getProperty().equals("00") && !blockList.get(i + 1).getProperty().equals("10")) {
                    didWin = "No";
                    break;}
                else if(i>3 && !blockList.get(i - 4).getProperty().equals("Vertical") && !blockList.get(i - 4).getType().equals("End")
                        && !blockList.get(i - 4).getProperty().equals("10")  && !blockList.get(i - 4).getProperty().equals("11")){
                    didWin = "No";
                    break;}
                else if(i>3 && blockList.get(i - 4).getType().equals("End") && blockList.get(i - 4).getProperty().equals("Vertical")) {
                    didWin = "Yes";
                    break; }
                else if(i<15 && blockList.get(i + 1).getType().equals("End") && blockList.get(i + 1).getProperty().equals("Horizontal")) {
                    didWin = "Yes";
                    break; }
            } else if (blockList.get(i).getProperty().equals("10")) {//13.png
                if (i<12 && !blockList.get(i + 4).getProperty().equals("Vertical")
                        && !blockList.get(i + 4).getProperty().equals("00") && !blockList.get(i + 4).getProperty().equals("01")) {
                    didWin = "No";
                    break;}
                else if (i>0 && !blockList.get(i - 1).getProperty().equals("Horizontal")
                        && !blockList.get(i - 1).getProperty().equals("01") && !blockList.get(i - 1).getProperty().equals("11")) {
                    didWin = "No";
                    break;}
            } else if(blockList.get(i).getProperty().equals("11")){//14.png
                if (i<15 && !blockList.get(i + 1).getProperty().equals("Horizontal") && !blockList.get(i + 1).getProperty().equals("10")
                        && !blockList.get(i + 1).getProperty().equals("00") && !blockList.get(i + 1).getType().equals("End") ) {
                    didWin = "No";
                    break;}
                else if(i<12 && !blockList.get(i + 4).getProperty().equals("Vertical")
                        && !blockList.get(i + 4).getProperty().equals("00") && !blockList.get(i + 4).getProperty().equals("01")){
                    didWin = "No";
                    break;}
                else if(i<15 && blockList.get(i + 1).getType().equals("End") && blockList.get(i + 1).getProperty().equals("Horizontal")) {
                    didWin = "Yes";
                    break;
                }
            } }
        if (didWin == "Yes") { //if didWin's value is "Yes" , this method returns true
            return true;
        }
        else //else, this method returns false
            return false;
    }
    public void levelGenerator (int levelNumber , ArrayList<Blocks> blockList,GridPane pane, BorderPane mainPane) throws Exception { // This method invoked at the beginning of the game and when level is changed.
        moveCounter=0; // we reset the value of moveCounter when this method invoked.
        winChecker=false; // we set winChecker's value as false.
        //we remove warning(if it is created) from mainPane and remove ball and GridPane pane from mainPane.
        mainPane.getChildren().remove(warning); 
        if(mainPane.getChildren().size()>2)
            mainPane.getChildren().remove(3);
        mainPane.getChildren().remove(pane);
        blockList.clear(); // we reset blockList ArrayList.
        pane.getChildren().clear(); // we clear GirdPane pane.
        String levelName = "level" + levelNumber +".txt"; //we create levelName according to levelNumber for taking input.
        //we read input data from file in these lines below
        File input1 = new File(levelName); 
        Scanner  inputLvl = new Scanner(input1);
        String [] inputArray; // this String array is for seperating taken input datas.
        String a = ""; // this is for taking all the input datas from a file.
        // we take all the input inside of an array a with this while loop
        while(inputLvl.hasNext()) { 
            a +=",";
            a += inputLvl.next(); 
        }
        inputArray = a.split(","); // we seperate String a and put it into inputArray String type array.
        // we are creating Blocks objects and put them into blockList with that loop below
        for(int i=1;i<inputArray.length;i+=3) { // we are adding 3 to i variable because of format of input file.
            int j = Integer.parseInt(inputArray[i]);// this is for id value. we are reading this from string so we use parseInt. 
            int c = (j%4)-1;  // this integer variable is for taking column index of the object in the pane due to its id.
            int r = j/4;      // this integer variable is for taking row index of the object in the pane due to its id.
            if(c<0 && j!=8 && j!=12 && j!=16) // we should control some cases and change the value of c for correct result.
                c=j-1;
            else if(j==8 ||j==12 ||j==16 ) 
                c=3;
            if(j==4||j==8||j==12||j==16) // we should control some cases and change the value of r for correct result.
                r--;

            Blocks block = new Blocks(j,inputArray[i+1],inputArray[i+2]); // we are creating blocks in this line
            blockList.add(block);//than we add them into the blockList.
            block.Assign();// we should call Blocks classes assign method of the objects for getting the images for that object.
            pane.add(block.getImg(),c,r); // than we should add them into our pane.
        }
        mainPane.setBottom(pane); // we should set the position of our GridPane into mainPane
        mainPane.getChildren().add(new Circle(32,50,10,Color.DARKRED)); // and we are creating and adding circle into our mainPane.
    }
    public class Blocks { // this is Blocks class. We have various variables for Blocks class objects. 
        private int id; // id is the numerical value of the object. We are putting our Blocks objects into pane due to that value.
        private String type; //Type attribute may be equal to Starter, End, Empty, Pipe or PipeStatic
        private String property;//Property attribute may be equal to Vertical, Horizontal, none, Free or (00, 01, 10, 11).
        private ImageView img; // every block object has an imageView.
        public Blocks(int id,String type,String property) { // this is constructor method of our Blocks class.
            this.id=id;
            this.type=type;
            this.property=property;
        }
        public void Assign() throws FileNotFoundException { // Assign method is for setting images due to their type and properties.
            switch(getType()) {
                case "Starter":
                    if(getProperty().equals("Horizontal")) {
                        FileInputStream input = new FileInputStream("2.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    if(getProperty().equals("Vertical")) {
                        FileInputStream input = new FileInputStream("1.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    break;
                case "Empty":
                    if(getProperty().equals("none")) {
                        FileInputStream input = new FileInputStream("6.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("Free")) {
                        FileInputStream input = new FileInputStream("5.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    break;
                case "Pipe":
                    if(getProperty().equals("Vertical")) {
                        FileInputStream input = new FileInputStream("9.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("Horizontal")) {
                        FileInputStream input = new FileInputStream("11.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("00")) {
                        FileInputStream input = new FileInputStream("10.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("01")) {
                        FileInputStream input = new FileInputStream("12.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("10")) {
                        FileInputStream input = new FileInputStream("13.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("11")) {
                        FileInputStream input = new FileInputStream("14.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    break;
                case "PipeStatic":
                    if(getProperty().equals("Vertical")) {
                        FileInputStream input = new FileInputStream("8.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    if(getProperty().equals("Horizontal")) {
                        FileInputStream input = new FileInputStream("7.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);}
                    else if(getProperty().equals("00")) {
                        FileInputStream input = new FileInputStream("18.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("01")) {
                        FileInputStream input = new FileInputStream("17.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("10")) {
                        FileInputStream input = new FileInputStream("15.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    else if(getProperty().equals("11")) {
                        FileInputStream input = new FileInputStream("16.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    break;
                case "End":
                    if(getProperty().equals("Horizontal")) {
                        FileInputStream input = new FileInputStream("4.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    if(getProperty().equals("Vertical")) {
                        FileInputStream input = new FileInputStream("3.png");
                        Image image = new Image(input);
                        ImageView imgview = new ImageView(image);
                        setImg(imgview);
                    }
                    break;
                default:
                    System.out.println("There is a problem with taking inputs");
            }
        }
        // There are setters/getters method of our Blocks class.
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getProperty() {
            return property;
        }
        public void setProperty(String property) {
            this.property = property;
        }
        public ImageView getImg() {
            return img;
        }
        public void setImg(ImageView img) {
            this.img = img;
        }
    }
}
