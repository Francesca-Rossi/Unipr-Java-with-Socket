package ui.views.response;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ui.controllers.LoginController;
import ui.controllers.users.AdminController;
/**
 * The {@code RegistrationAdminSuccess} is a javaFX class to output the positive response after send a form of registration admin
 * this class extends {@code Success} class
 * @see Success
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RegistrationAdminSuccess  extends  Success{
    private Button successBtn;
    private AdminController myController;

    public RegistrationAdminSuccess(String title, String message, String nameBtn, AdminController admin){
        super(title, message);
        this.successBtn=new Button(nameBtn);
        this.myController=admin;
    }
    public String getTitle()
    {
        return super.getTitle();
    }

    public GridPane getGrid(LoginController futureController)
    {
        GridPane.setConstraints(super.getFields().getMessage(),  1, 2);
        GridPane.setConstraints(this.successBtn,  1, 3);
        this.successBtn.setOnAction(e->{
            myController.getStage().close();
            futureController.getForm();
        });
        super.getGrid().getChildren().addAll(super.getFields().getMessage(), this.successBtn);
        return  super.getGrid();
    }
}
