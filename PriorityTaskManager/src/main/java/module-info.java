module party.mrow.prioritytaskmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens party.mrow.prioritytaskmanager to javafx.fxml;
    exports party.mrow.prioritytaskmanager;
}