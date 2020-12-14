package ui.views.forms.users;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ui.views.forms.IForm;
import ui.controllers.users.AdminController;

/**
 * The {@code AdminForm} is the class used to register Admin
 * @see UserForm
 * @see IForm
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class AdminForm extends UserForm implements IForm<GridPane, AdminController> {
    private GridPane adminGrid;
    private  final Button REGISTER_BTN=new Button("Register Admin");
    public AdminForm(){super();}

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public GridPane getGrid(AdminController controller) {
        super.setTitle("Admin registration");
        this.adminGrid=super.getGrid();
        this.REGISTER_BTN.setOnAction(e->{ //
                    controller.register(super.getFields().NAME_INPUT.getText(),super.getFields().SURNAME_INPUT.getText(), super.getFields().EMAIL_INPUT.getText(),super.getFields().PSW_INPUT.getText());
        }
        );
        GridPane.setConstraints(this.REGISTER_BTN, 1, 4);
        this.adminGrid.getChildren().addAll(this.REGISTER_BTN);
        return this.adminGrid;

    }


}
