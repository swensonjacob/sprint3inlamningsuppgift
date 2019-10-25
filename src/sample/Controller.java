package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    Label youwon = new Label();
    @FXML
    Button b1 = new Button();
    @FXML
    Button b2 = new Button();
    @FXML
    Button b3 = new Button();
    @FXML
    Button b4 = new Button();
    @FXML
    Button b5 = new Button();
    @FXML
    Button b6 = new Button();
    @FXML
    Button b7 = new Button();
    @FXML
    Button b8 = new Button();
    @FXML
    Button b9 = new Button();
    @FXML
    Button b10 = new Button();
    @FXML
    Button b11 = new Button();
    @FXML
    Button b12 = new Button();
    @FXML
    Button b13 = new Button();
    @FXML
    Button b14 = new Button();
    @FXML
    Button b15 = new Button();
    @FXML
    Button nullButton = new Button();

    @FXML
    GridPane gameSpace = new GridPane();

    List<Button> buttons = new ArrayList<>();

    public void initialize() {

        addButtons(b1, b2, b3, b4, b5, b6, b7, b8);
        addButtons(b9, b10, b11, b12, b13, b14, b15, nullButton);

        placeButtons();

    }

    private void addButtons(Button b1, Button b2, Button b3, Button b4, Button b5, Button b6, Button b7, Button b8) {
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);

    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        for (Button button : buttons) {
            if (e.getSource().equals(button)) {
                if (buttonIsChangeable(button)) {
                    changeButtonPlace(button);
                    if (didYouWin()){
                        youwon.setVisible(true);
                    }
                }
            }
        }
    }

    @FXML
    public void newGame(ActionEvent e) {
        Collections.shuffle(buttons);
        placeButtons();
    }

    @FXML
    public void exit(ActionEvent e) {
        System.exit(0);
    }

    public void placeButtons() {
        List shuffledList = buttons;
        Collections.shuffle(shuffledList);
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                GridPane.setRowIndex(buttons.get(counter), i);
                GridPane.setColumnIndex(buttons.get(counter), j);
                counter++;
                youwon.setVisible(false);
            }
        }
    }

    public void changeButtonPlace(Button button) {
        int buttonColIndex = GridPane.getColumnIndex(button);
        int buttonRowIndex = GridPane.getRowIndex(button);
        int nullColIndex = GridPane.getColumnIndex(nullButton);
        int nullRowIndex = GridPane.getRowIndex(nullButton);
        GridPane.setColumnIndex(nullButton, buttonColIndex);
        GridPane.setRowIndex(nullButton, buttonRowIndex);
        GridPane.setColumnIndex(button, nullColIndex);
        GridPane.setRowIndex(button, nullRowIndex);
    }

    public boolean didYouWin() {
        int counter = 0;
        boolean winning = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((GridPane.getRowIndex(buttons.get(counter)) == i
                        && (GridPane.getColumnIndex(buttons.get(counter)) == j))){
                    counter++;
                }else{
                    counter++;
                    winning = false;
                }
            }
        }
        return winning;
    }

    public boolean buttonIsChangeable(Button button) {
        if ((GridPane.getRowIndex(button).equals(GridPane.getRowIndex(nullButton)))) {
            return GridPane.getColumnIndex(button) - 1 == GridPane.getColumnIndex(nullButton) ||
                    GridPane.getColumnIndex(button) + 1 == GridPane.getColumnIndex(nullButton);

        } else if (GridPane.getColumnIndex(button).equals(GridPane.getColumnIndex(nullButton))) {
            return GridPane.getRowIndex(button) - 1 == GridPane.getRowIndex(nullButton) ||
                    GridPane.getRowIndex(button) + 1 == GridPane.getRowIndex(nullButton);
        }
        return false;
    }
}
