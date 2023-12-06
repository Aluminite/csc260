package party.mrow.prioritytaskmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    // Tasks that are currently on the screen.
    // Needed for when one of the tasks gets modified.
    private Task[] visibleTasks = new Task[0];

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
        // Yes, this completely recreates the task grid every time it's called.
        // Yes, probably it could be much more efficient.
        // This is much easier, though.
        gridPane.getChildren().clear();
        Task[] tasks = storage.todoToArraySorted();
        for (int i = 0; i < tasks.length; i++) {
            TextField addedTaskInfo = new TextField(tasks[i].getInfo());
            // Add the listener for when the text box gets changed
            addedTaskInfo.textProperty().
                    addListener((observable, oldValue, newValue) -> taskTextFieldChanged(addedTaskInfo, newValue));

            ChoiceBox<TaskPriority> addedPriorityChooser = new ChoiceBox<>(priorities);
            addedPriorityChooser.setValue(tasks[i].getPriority());
            // Add the listener for when the priority ChoiceBox gets changed
            addedPriorityChooser.getSelectionModel().selectedItemProperty().
                    addListener((observable, oldValue, newValue) -> taskPriorityChanged(addedPriorityChooser, newValue));

            Button removeButton = new Button("Delete");
            // Add the listener for when the remove button is clicked
            removeButton.setOnAction(event -> removeButtonClicked(removeButton));

            gridPane.add(addedTaskInfo, 0, i);
            gridPane.add(addedPriorityChooser, 1, i);
            gridPane.add(removeButton, 2, i);
        }
        visibleTasks = tasks;
    }

    public void taskTextFieldChanged(TextField textField, String newValue) {
        visibleTasks[GridPane.getRowIndex(textField)].setInfo(newValue);
        // This won't change the order, so it doesn't need to be refreshed
    }

    public void taskPriorityChanged(ChoiceBox<TaskPriority> choiceBox, TaskPriority newValue) {
        Task currentTask = visibleTasks[GridPane.getRowIndex(choiceBox)];
        currentTask.setPriority(newValue);
        // Priority queues don't update order automatically, so the task must be removed and re-added
        storage.todo.remove(currentTask);
        storage.todo.add(currentTask);
        fillTaskGrid(); // Order may change so grid must be refreshed
    }

    public void removeButtonClicked(Button removeButton) {
        Task currentTask = visibleTasks[GridPane.getRowIndex(removeButton)];
        storage.todo.remove(currentTask);
        fillTaskGrid(); // Refresh the shown tasks
    }

}
