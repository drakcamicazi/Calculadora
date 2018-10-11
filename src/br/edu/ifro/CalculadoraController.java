/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author drakcamicazi
 */
public class CalculadoraController implements Initializable {

    @FXML
    private TableView tvHistorico;
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

    private String operando;
    private Double resultado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarTabela();
    }

    @FXML
    private void Somar(ActionEvent event) {
        operando = "+";
        resultado = Double.parseDouble(txtValor1.getText()) + Double.parseDouble(txtValor2.getText());
        txtResultado.setText(resultado.toString());
        adicionarRegistro();
        limpar();
        atualizarTabela();
    }

    @FXML
    private void Subtrair(ActionEvent event) {
        operando = "-";
        resultado = Double.parseDouble(txtValor1.getText()) - Double.parseDouble(txtValor2.getText());
        txtResultado.setText(resultado.toString());
        adicionarRegistro();
        limpar();
        atualizarTabela();
    }

    @FXML
    private void Multiplicar(ActionEvent event) {
        operando = "*";
        resultado = Double.parseDouble(txtValor1.getText()) * Double.parseDouble(txtValor2.getText());
        txtResultado.setText(resultado.toString());
        adicionarRegistro();
        limpar();
        atualizarTabela();
    }

    @FXML
    private void Dividir(ActionEvent event) {
        operando = "/";
        resultado = Double.parseDouble(txtValor1.getText()) / Double.parseDouble(txtValor2.getText());
        txtResultado.setText(resultado.toString());
        adicionarRegistro();
        limpar();
        atualizarTabela();
    }

    private void limpar() {
        txtValor1.setText("");
        txtValor2.setText("");
    }

    private void adicionarRegistro() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();

        Historico historico = new Historico();
        historico.setOperador(operando);
        historico.setPrimeiroValor(Double.parseDouble(txtValor1.getText()));
        historico.setSegundoValor(Double.parseDouble(txtValor2.getText()));
        historico.setResultado(resultado);

        em.getTransaction().begin();
        em.persist(historico);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private void atualizarTabela() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT h FROM Historico as h");
        List<Historico> h = query.getResultList();
        ObservableList oh = FXCollections.observableArrayList(h);
        tvHistorico.setItems(oh);
        em.close();
        emf.close();
    }
}
