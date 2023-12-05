package party.mrow.prioritytaskmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.Optional;

public class TaskManagerController {

    @FXML
    private GridPane gridPane;

    @FXML
    private ChoiceBox<TaskPriority> priorityChooser;

    @FXML
    private TextField taskInfoField;

    private final ObservableList<TaskPriority> priorities = FXCollections.observableArrayList();

    private TaskStorage storage = new TaskStorage();

    @FXML
    void addButtonClicked() {
        if (priorityChooser.getValue() != null) {
            Task newTask = new Task(taskInfoField.getText(), priorityChooser.getValue());
            storage.todo.add(newTask);
            fillTaskGrid();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "You have to choose a priority for the task.",
                    ButtonType.OK).showAndWait();
        }

        taskInfoField.clear();
        priorityChooser.setValue(null);
    }

    @FXML
    void getNextTask() {
        Task nextTask = storage.todo.poll();
        fillTaskGrid();
        if (nextTask == null) {
            new Alert(Alert.AlertType.ERROR, "There are no more tasks.", ButtonType.OK).showAndWait();
        } else {
            String taskString = String.format("%s%nwith priority %s", nextTask.getInfo(), nextTask.getPriority());
            Alert taskAlert = new Alert(Alert.AlertType.NONE, String.format("Your next task is:%n%s", taskString),
                    ButtonType.OK);
            taskAlert.showAndWait();
        }
    }

    @FXML
    void saveButtonClicked() {
        Window window = gridPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Task List", "*.tasks"));
        File saveFile = fileChooser.showSaveDialog(window);

        if (saveFile == null) return; // File will be null if the user cancels
        try (FileOutputStream fos = new FileOutputStream(saveFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(storage);
            oos.flush();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Unable to save to the specified file.",
                    ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void loadButtonClicked() {
        Window window = gridPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Task List", "*.tasks"));
        File loadFile = fileChooser.showOpenDialog(window);

        if (loadFile == null) return; // File will be null if the user cancels
        try (FileInputStream fis = new FileInputStream(loadFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object loaded = ois.readObject();
            try {
                storage = (TaskStorage) loaded;
                fillTaskGrid();
            } catch (ClassCastException e) {
                new Alert(Alert.AlertType.ERROR, "The specified file is of the wrong object type.",
                        ButtonType.OK).showAndWait();
            }
        } catch (IOException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Unable to load from the specified file.",
                    ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void clearButtonClicked() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to clear all tasks?", ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            storage = new TaskStorage();
            gridPane.getChildren().clear();
        }
    }

    @FXML
    public void initialize() {
        priorities.addAll(TaskPriority.values());
        priorityChooser.getItems().addAll(priorities);
    }

    public void fillTaskGrid() {
        gridPane.getChildren().clear();
        Task[] tasks = storage.todoToArraySorted();
        for (int i = 0; i < tasks.length; i++) {
            TextField addedTaskInfo = new TextField(tasks[i].getInfo());
            ChoiceBox<TaskPriority> addedPriorityChooser = new ChoiceBox<>(priorities);
            addedPriorityChooser.setValue(tasks[i].getPriority());

            gridPane.add(addedTaskInfo, 0, i);
            gridPane.add(addedPriorityChooser, 1, i);
        }
    }

}
