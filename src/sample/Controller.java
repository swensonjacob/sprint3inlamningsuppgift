package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.*;

public class Controller {

    @FXML private Button b1; @FXML private Button b2; @FXML private Button b3;
    @FXML private Button b4; @FXML private Button b5; @FXML private Button b6;
    @FXML private Button b7; @FXML private Button b8; @FXML private Button b9;
    @FXML private Button b10; @FXML private Button b11; @FXML private Button b12;
    @FXML private Button b13; @FXML private Button b14; @FXML private Button b15;
    @FXML
    private Button nullButton;
    @FXML
    private HBox youWon;
    @FXML
    private GridPane gameSpace;
    @FXML
    private Label startingDeathStar;
    @FXML
    private Label brokenDeathStar;

    private List<Button> buttons;

    public void initialize() {
        buttons = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8,
                b9, b10, b11, b12, b13, b14, b15, nullButton);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        for (Button button : buttons) {
            if (e.getSource().equals(button) && buttonIsChangeable(button)) {
                changeButtonPlace(button);
                if (didYouWin()) {
                    winMessage();
                }
            }
        }
    }

    @FXML
    public void newGame() {
        gameSpace.setVisible(true);
        winMessageGone();
        placeButtons(buttons);
    }

    @FXML
    public void showExitDialog() {
        Dialog<ButtonType> exitDialog = new Dialog<>();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addExitDialog.fxml"));
        try {
            exitDialog.getDialogPane().setContent(fxmlLoader.load());
            addDialogButtons(exitDialog);
        } catch (IOException e1) {
            System.out.println("Error");
            e1.printStackTrace();
        }

        Optional<ButtonType> result = exitDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }
    }

    public void addDialogButtons(Dialog<ButtonType> exitDialog) {
        exitDialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        ((Button) exitDialog.getDialogPane().lookupButton(ButtonType.YES)).setText("Yes");
        ((Button) exitDialog.getDialogPane().lookupButton(ButtonType.NO)).setText("No");
        exitDialog.getDialogPane().getStylesheets().add(getClass().getResource("exitDialogStyle.css").toExternalForm());
        exitDialog.getDialogPane().getStyleClass().add("exitDialogStyle");
    }

    public void placeButtons(List<Button> shuffledList) {
        //Collections.shuffle((shuffledList));
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                GridPane.setRowIndex(shuffledList.get(counter), i);
                GridPane.setColumnIndex(shuffledList.get(counter), j);
                counter++;
            }
        }
    }

    public void changeButtonPlace(Button button) {
        int nullColIndex = GridPane.getColumnIndex(nullButton);
        int nullRowIndex = GridPane.getRowIndex(nullButton);
        GridPane.setColumnIndex(nullButton, GridPane.getColumnIndex(button));
        GridPane.setRowIndex(nullButton, GridPane.getRowIndex(button));
        GridPane.setColumnIndex(button, nullColIndex);
        GridPane.setRowIndex(button, nullRowIndex);
    }

    public boolean didYouWin() {
        int counter = 0;
        boolean winning = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((GridPane.getRowIndex(buttons.get(counter)) == i
                        && (GridPane.getColumnIndex(buttons.get(counter)) == j))) {
                    counter++;
                } else {
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

    public void winMessage() {
        youWon.setVisible(true);
        brokenDeathStar.setVisible(true);
        startingDeathStar.setVisible(false);
        gameSpace.setVisible(false);
    }

    public void winMessageGone() {
        youWon.setVisible(false);
        brokenDeathStar.setVisible(false);
        startingDeathStar.setVisible(true);
        gameSpace.setVisible(true);
    }
}

