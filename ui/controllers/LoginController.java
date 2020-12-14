package ui.controllers;


import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.utils.ModelRequestType;
import ui.controllers.users.AdminController;
import ui.controllers.users.ClientController;
import ui.controllers.users.EmployeeController;
import ui.models.utils.Size;
import ui.views.forms.LoginForm;
import ui.views.response.Error;
import ui.views.component.stage.BuilderStage;

public class LoginController {
    private LoginForm form;
    private BuilderStage loginStage;
    private ClientSocket clientSocket;
    private  Size.Field dim= new Size.Field();

    public LoginController(){
        this.form =new LoginForm();



    }
    public LoginController(ClientSocket clientSocket){
        this.form =new LoginForm();
        this.clientSocket=clientSocket;


    }
    public void register(String email, String password){
        try {
            UserLoginRequest userLoginRequest = new UserLoginRequest().asType(ModelRequestType.LOGIN).setEmail(email).setPassword(password);
            User userAuthenticate = clientSocket.loginUser(userLoginRequest);
            //this.loginStage.getStage().close();
            this.clientSocket=new ClientSocket();
            if (userAuthenticate==null)
            {
                Error error = new Error(form.getTitle(), "User is not present");
                this.loginStage = new BuilderStage(error.getTitle(), error.getGrid(), dim.WIDTH, dim.HEIGHT);
                this.loginStage.getStage().show();
            }
            else{
            switch(userAuthenticate.getUserType()) {
                case "ADMIN":
                    new AdminController(clientSocket).getProfile(userAuthenticate);
                    break;
                case "CLIENT":
                    new ClientController(clientSocket).getProfile(userAuthenticate);
                    break;
                case "EMPLOYEE":
                    new EmployeeController(clientSocket).getProfile(userAuthenticate);
                    break;

                default:
                    System.out.println("I'm the "+userAuthenticate.getUserType());
            }


        }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void getForm(){
        this.loginStage=new BuilderStage(form.getTitle(), form.getGrid(this), dim.WIDTH, dim.HEIGHT);
        this.loginStage.getStage().show();

    }

}
