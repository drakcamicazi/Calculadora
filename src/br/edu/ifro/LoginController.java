/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author drakcamicazi
 */
public class LoginController implements Initializable {

    @FXML
    private Label lblErro;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();
        Query q;
        q = em.createQuery("SELECT u FROM Usuario as u WHERE nome = :usr");
        q.setParameter("usr", usuario);
        try {
            Usuario u = (Usuario) q.getSingleResult();
            if (u.getSenha().equals(senha)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Calculadora.fxml"));
                Stage menu = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                menu.setScene(scene);
                menu.show();
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.close();
            } else {
                lblErro.setVisible(true);
            }
        } catch (Exception e) {
            lblErro.setVisible(true);
        }

    }

}
