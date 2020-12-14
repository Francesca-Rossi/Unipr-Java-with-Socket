package ui.controllers.users;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.utils.Type;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import ui.models.utils.Size;
import ui.views.menu.Menu;
import ui.views.component.stage.BuilderStage;

public class UserController {
    private BuilderStage stage;
    private Size.Field dim = new Size.Field();
    private Menu menu;


    public Stage getStage() {
        return stage.getStage();
    }

    public void setGridStage(String title, GridPane pane) {
        this.stage = new BuilderStage(title, pane, dim.WIDTH, dim.HEIGHT);

    }

    public void setBorderStage(String title, BorderPane pane) {
        this.stage = new BuilderStage(title, pane, dim.WIDTH, dim.HEIGHT);

    }

    public CreateUserCriteria createUser(String name, String surname, String email, String password, Type type) {
        CreateUserCriteria createUserCriteria = new CreateUserCriteria()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(password)
                .setUserType(type);
        return createUserCriteria;

    }
    public void closeStage(){
        if (this.stage.getStage()!=null)
        {
            this.stage.getStage().close();
        }
    }
    public void getForm(){}
    public void getAll(){}
    public void getProfile(User user){}
    public Menu getMenu(){
        return menu;
    }
}
