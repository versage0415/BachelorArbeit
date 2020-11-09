package Controllers;

import connectivity.ConnectionClass;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public Label lbStatus;
    public Label lbUser;
    public Label BtnCreateAccount;
    public Tab tabUser;
    public Tab tabAdmin;
    public TabPane tabPaneLogin;
    public Pane MenuPane;
    public Button login;
    public Button AdminLogin;
    public PasswordField AdminPassLogin;
    public TextField UserNameAdminLogin;
    public Button LoginUser;
    public PasswordField UserPassLogin;
    public TextField UserNameLogin;
    public StackPane Rootpane;
    public AnchorPane anchorPane;
    ConnectionClass connectionClass=new ConnectionClass();
    ResultSet rs=null;
    PreparedStatement pst=null;

    Stage stage;
    Stage stage1;

    public void OpenAdminTab(MouseEvent mouseEvent) {
        TranslateTransition toFefttAnimation=new TranslateTransition(new Duration(500),lbStatus);
        toFefttAnimation.setToX(MenuPane.getTranslateX());
        toFefttAnimation.play();
        toFefttAnimation.setOnFinished((ActionEvent event1)->{
                lbStatus.setText("ADMINISTRATOR");
                });

        tabPaneLogin.getSelectionModel().select(tabAdmin);
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

    public void LoginButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection connection=connectionClass.getConnection();
        String sql = "select * from users where username = ? and password = ?";
        pst = connection.prepareStatement(sql);
        pst.setString(1, UserNameAdminLogin.getText());
        pst.setString(2, UserPassLogin.getText());
        rs = pst.executeQuery();
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"Username And Password is correct");
        }
        else
            JOptionPane.showMessageDialog(null,"Invalide Username or Password");

    }

    public void AdminLoginButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = connectionClass.getConnection();
        String sql = "select * from benutzer where username = ? and password = ?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, UserNameAdminLogin.getText());
            pst.setString(2, AdminPassLogin.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                UserNameLogin.clear();
                UserPassLogin.clear();
                JOptionPane.showMessageDialog(null, "Username And Password is correct");
                 AdminLogin.getScene().getWindow().hide();
                stage1 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage1 = new Stage();
                AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/CentralAdminScreen.fxml"));
                Scene scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
            } else
                JOptionPane.showMessageDialog(null, "Invalide Username or Password");
            connection.close();

        }


    public void LoginUserButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = connectionClass.getConnection();
        String sql = "select * from BENUTZER where username = ? and password = ?";

        pst = connection.prepareStatement(sql);
        pst.setString(1, UserNameAdminLogin.getText());
        pst.setString(2, UserPassLogin.getText());
        rs = pst.executeQuery();
        if (!rs.next()) {
            UserNameAdminLogin.clear();
            UserPassLogin.clear();
            JOptionPane.showMessageDialog(null, "Username And Password is correct");
            LoginUser.getScene().getWindow().hide();
            //stage1 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage1 = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/CentralUserScreen.fxml"));
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
        } else
            JOptionPane.showMessageDialog(null, "Invalide Username or Password");
    }

    public void GoToRegisterScreen(MouseEvent mouseEvent) throws IOException {
        stage =(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Registration.fxml"));
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
