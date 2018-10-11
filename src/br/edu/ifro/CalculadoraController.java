/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author drakcamicazi
 */
public class CalculadoraController implements Initializable {

    @FXML
    private TextField txtValor1;
    @FXML
    private TextField txtValor2;
    @FXML
    private Button btnSomar;
    @FXML
    private Button btnSubtrair;
    @FXML
    private Button btnMultiplicar;
    @FXML
    private Button btnDividir;
    @FXML
    private Text txtResultado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Somar(ActionEvent event) {
    }

    @FXML
    private void Subtrair(ActionEvent event) {
    }

    @FXML
    private void Multiplicar(ActionEvent event) {
    }

    @FXML
    private void Dividir(ActionEvent event) {
    }
    
    private void limpar(){
        txtValor1.setText("");
        txtValor2.setText("");
        txtResultado.setText("");
    }
}
