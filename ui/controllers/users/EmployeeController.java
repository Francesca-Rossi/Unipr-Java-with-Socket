package ui.controllers.users;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.utils.Type;
import javafx.scene.layout.BorderPane;
import ui.api.IController;
import ui.views.menu.EmployeeMenu;
import ui.views.menu.Menu;
import ui.views.component.panes.MainPane;
import ui.views.component.stage.BuilderStage;
import ui.views.forms.users.EmployeeForm;
import ui.views.response.Error;
import ui.views.response.Success;
import ui.views.views.ListEmployee;
import ui.views.views.UserProfile;

import java.util.List;

public class EmployeeController extends UserController implements IController<EmployeeController> {
    private EmployeeForm form;
    private UserProfile clientProfile;
    private BuilderStage adminStage;
    private ClientSocket clientSocket;
    private Menu menu;

    public EmployeeController(ClientSocket clientSocket){
        this.clientSocket=clientSocket;


    }
    public EmployeeController(ClientSocket clientSocket, Menu adminMenu){ ;
        this.clientSocket=clientSocket;
        this.menu=adminMenu;

    }
    public void register(String name, String surname, String email,  String password){
        String msgSuccess="Employee registration whit success";
        String msgError="Error to registration Employee";


        Employee user = (Employee) clientSocket.createUser(super.createUser(name, surname, email, password, Type.EMPLOYEE));
        System.out.println("ID:"+ user.getId()+"email:"+user.getEmail());
        super.getStage().close();
        if(user!=null)
        {
            Success success=new Success(form.getTitle(), msgSuccess );
            BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),success.getfilledGrid());
            super.setBorderStage(success.getTitle(), mainView);
            super.getStage().show();
        }
        else{
            Error error=new Error(form.getTitle(), msgSuccess );
            BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),error.getGrid());
            super.setBorderStage(error.getTitle(), mainView);
            super.getStage().show();
        }

    }
    public void getForm(){
        this.form =new EmployeeForm();
        BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),form.getGrid(this));
        super.setBorderStage(form.getTitle(),mainView );
        super.getStage().show();

    }
    public void getProfile(User userSignIn){
        if((menu==null)||(menu.getClass()!=EmployeeMenu.class)){
            this.menu=new EmployeeMenu(clientSocket, this, userSignIn);
        }
        this.clientProfile =new UserProfile(userSignIn);
        BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),clientProfile.getGrid());
        super.setBorderStage(clientProfile.getTitle(),mainView );
        super.getStage().show();
    }
    public void getAll(){
        UserSearchCriteria employeeSearch = new UserSearchCriteria().setSelectAll(true).setUserType(Type.EMPLOYEE);
        List<User> employees = clientSocket.searchUsers(employeeSearch);
        ListEmployee allEmployee=new ListEmployee(employees);
        BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),allEmployee.getTable());
        super.setBorderStage(allEmployee.getTitle(),mainView );
        super.getStage().show();
    }

    @Override
    public EmployeeController getController() {
        return this;
    }
    public Menu getMenu(){
        return menu;
    }
}
