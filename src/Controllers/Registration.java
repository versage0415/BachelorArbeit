package Controllers;

import connectivity.ConnectionClass;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration {
    public TabPane tabPaneLogin;
    public Tab tabAdmin;
    public TextField AdminUsername;
    public PasswordField AdminPass;
    public Button RegisterAdmin;
    public TextField AdminEmail;
    public PasswordField RepeatedPass;
    public Tab tabUser;
    public TextField UserNameRegister;
    public PasswordField UserPassRegister;
    public Button RegisterUser;
    public TextField UserEmailRegister;
    public PasswordField UserRepeatedPassRegister;
    public Pane MenuPane;
    public Label lbUser;
    public Label lbStatus;
    public Label BtnCreateAccount;
    Stage stage;
    ConnectionClass connectionClass=new ConnectionClass();
    ResultSet rs=null;
    PreparedStatement pst=null;



    public void RegisterAdminButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = connectionClass.getConnection();
//        String sql = "CREATE TABLE users(user_id int (10) primary key AUTO_INCREMENT,username varchar(200) NOT NULL ,password varchar(200) NOT NULL ,email varchar(200) NOT NULL ,level varchar(200) NOT NULL ) ";
        //String sql="insert into BENUTZER(username,password,email,level) values (?,?,?,?)";

        String sql="insert into BENUTZER values (?,?,?,?,?)";


        //System.out.println("The table user is created");

        pst = connection.prepareStatement(sql);
        pst.setString(1, null);
        pst.setString(2, AdminUsername.getText());

        pst.setString(3, AdminPass.getText());
        pst.setString(4, AdminEmail.getText());
        pst.setString(5, "ADMIN");
        String sql1="select * from BENUTZER where username="+"'"+AdminUsername.getText()+"'";
        String sql2="select * from BENUTZER where password="+"'"+AdminPass.getText()+"'";
        ResultSet rs = connection.createStatement().executeQuery(sql1);
        ResultSet rs1 = connection.createStatement().executeQuery(sql2);
        while (rs.next()){
            System.out.println("sql1="+rs.getString(2));
        }


        if(actionEvent.getSource()==RegisterAdmin){


               // if(!rs1.next()){
            if(AdminPass.getText().equals(RepeatedPass.getText())){
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "succesfull registration");
            }
            if(!AdminPass.getText().equals(RepeatedPass.getText())){
                JOptionPane.showMessageDialog(null, "the password don't macht together");
            }
            if(rs.next()){
                System.out.println("this username exist already");
                JOptionPane.showMessageDialog(null, "this username exist already");
            }
            if(rs1.next()){
                System.out.println("this password exist already");
                JOptionPane.showMessageDialog(null, "this password exist already");
            }

            stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    }

    public void RegisterUserButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = connectionClass.getConnection();
        String sql = "INSERT into users(username,password,email,level) values (?,?,?,?)";
        String sql1 = "INSERT into Rechte(Benutzername,Hostname,typ,Rechte,Grant) values (?,?,?,?,?)";
        pst = connection.prepareStatement(sql);
        pst.setString(1, UserNameRegister.getText());
        pst.setString(2, UserPassRegister.getText());
        pst.setString(3, UserEmailRegister.getText());
        pst.setString(4, "USER");




        if(actionEvent.getSource()==RegisterUser){
            pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Saved");
            stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void OpenUserTab(MouseEvent mouseEvent) {
        TranslateTransition toRightAnimation=new TranslateTransition(new Duration(500),lbStatus);
        toRightAnimation.setToX(MenuPane.getTranslateX()+(MenuPane.getPrefWidth()-lbStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event2)-> {

            // throw new UnsupportedOperationException("not supported yet");
            lbStatus.setText("USER");

        });
        tabPaneLogin.getSelectionModel().select(tabUser);
    }

    public void OpenAdminTab(MouseEvent mouseEvent) {
        TranslateTransition toFefttAnimation=new TranslateTransition(new Duration(500),lbStatus);
        toFefttAnimation.setToX(MenuPane.getTranslateX());
        toFefttAnimation.play();
        toFefttAnimation.setOnFinished((ActionEvent event1)->{
            lbStatus.setText("ADMINISTRATOR");
        });

        tabPaneLogin.getSelectionModel().select(tabAdmin);
    }

    public void GoToLoginScreen(MouseEvent mouseEvent) throws IOException {
        stage =(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
