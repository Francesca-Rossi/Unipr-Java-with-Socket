package ui.controllers.users;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.utils.Type;
import javafx.scene.layout.BorderPane;
import ui.api.IController;
import ui.views.menu.CustomerMenu;
import ui.views.component.panes.MainPane;
import ui.views.component.stage.BuilderStage;
import ui.views.forms.users.ClientForm;
import ui.views.menu.Menu;
import ui.views.response.Error;
import ui.views.response.Success;
import ui.views.views.ListCustomer;
import ui.views.views.UserProfile;

import java.util.List;

public class ClientController extends UserController implements IController<ClientController> {
    private ClientForm form;
    private UserProfile clientProfile;
    private BuilderStage adminStage;
    private ClientSocket clientSocket;
    private Menu menu;

    public ClientController(ClientSocket clientSocket){

        this.clientSocket=clientSocket;

    }
    public ClientController(ClientSocket clientSocket, Menu adminMenu){
        this.clientSocket=clientSocket;
        this.menu=adminMenu;

    }
    public void getProfile(User userSignIn){
        if((menu==null)||(menu.getClass()!= CustomerMenu.class)){
            this.menu=new CustomerMenu(clientSocket, this, userSignIn);
        }
        this.clientProfile =new UserProfile(userSignIn);
        BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),clientProfile.getGrid());
        super.setBorderStage(clientProfile.getTitle(),mainView );
        super.getStage().show();
    }
    public void register(String name, String surname, String email,  String password){
        String msgSuccess="Client registration whit success";
        String msgError="Error to registration client";


        Customer user = (Customer) clientSocket.createUser(super.createUser(name, surname, email, password, Type.CLIENT));
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
        this.form =new ClientForm();
        BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),form.getGrid(this));
        super.setBorderStage(form.getTitle(),mainView );
        super.getStage().show();

    }

    public void getAll(){
        UserSearchCriteria clientSearch = new UserSearchCriteria().setSelectAll(true).setUserType(Type.CLIENT);
        List<User> clients = clientSocket.searchUsers(clientSearch);
        ListCustomer allClients=new ListCustomer(clients);
        BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),allClients.getTable());
        super.setBorderStage(allClients.getTitle(),mainView );
        super.getStage().show();
    }
    @Override
    public ClientController getController() {
        return this;
    }
    public Menu getMenu(){
        return menu;
    }
}
