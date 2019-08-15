package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSerller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

	//synchronized serve para garatir que o processamento multi thread não seja interrompida
	private synchronized void loadView(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); //vai abrir o que vier de caminho nesse parametro
			VBox newVBox = loader.load();
		
			//pegar a referencia para cena
			Scene mainScene = Main.getMainScene();
			
			//referencia do VBox da MainView
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //(ROOT) primeiro elemento da minha view principal (ScrollPane), depois acessa o content, faço um casting referencia do VBox 
		
			//precisa excluir tudo dentro do <content> para colocar todos os <children> do About
			//referencia para o menu
			Node mainMenu = mainVBox.getChildren().get(0); //primeiro children da janela principal
			mainVBox.getChildren().clear(); //limpa todos children do MainView
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
