/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.connectapp.entities.Offer.Internships;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class InternshipOffersController implements Initializable {

     @FXML
    private TableView<Internships> ClaimsTable;
    @FXML
    private TableColumn<Internships, String> id= new TableColumn<>("id");
    @FXML
    private TableColumn<Internships, String> company= new TableColumn<>("company");
    @FXML
    private TableColumn<Internships, String> field= new TableColumn<>("field");
    @FXML
    private TableColumn<Internships, String> titre= new TableColumn<>("titre") ;
    @FXML
    private TableColumn<Internships, String> description =new TableColumn<>("description");
    @FXML
    private TableColumn<Internships, String> start_date =new TableColumn<>("start_date");
    @FXML
    private TableColumn<Internships, Date> date =new TableColumn<>("date");
    @FXML
    private TableColumn<Internships, String> supervisor = new TableColumn<>("supervisor");
    @FXML
    private TableColumn<Internships, String> statuss =new TableColumn<>("statuss");
  
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Internships internship = null ;
    
        private Connection cnx;

     ObservableList<Internships>  InternshipsList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    
   
    @FXML
    private void refreshTable() {
        try {
            InternshipsList.clear();
            
            query = "SELECT * FROM Internship";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
/* TO DO                InternshipsList.add(new Internships (
                resultSet.getString("company"),
                resultSet.getString("field"),
                resultSet.getString("titre"),
                resultSet.getString("description"),
                 resultSet.getString("start_date"),
                resultSet.getString("end_date"),
                resultSet.getString("end_date"),
                resultSet.getString("supervisor")));
          */      
                 ClaimsTable.setItems(InternshipsList);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InternshipOffersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDate() {
        
        connection = DBConnect.getConnect();
        refreshTable();
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        field.setCellValueFactory(new PropertyValueFactory<>("field"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_declaration"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
       
      
        //add cell of button edit 
         Callback<TableColumn<Internships, String>, TableCell<Internships, String>> cellFoctory = (TableColumn<Internships, String> param) -> {
            // make cell containing buttons
            final TableCell<Internships, String> cell = new TableCell<Internships, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    FontAwesomeIconView EyeIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        

                        EyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#B0E0E6;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#FDF1B8;"
                        );
                        EyeIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                          
                                internship = ClaimsTable.getSelectionModel().getSelectedItem();
                                 internship = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("readOnly.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ReadOnlyController readonlycontroller = loader.getController();
                            //repondrecontroller.setUpdate(true);
//                            readonlycontroller.ReadTextField(internship.getREF(), "opinion",internship.getCompany(), internship.getField(), internship.getTitle(), internship.getDescription(), internship.getSupervisor(), internship.getStatus());   
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });}
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              internship = ClaimsTable.getSelectionModel().getSelectedItem();
                                 internship = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("repondre.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            RepondreController repondrecontroller = loader.getController();
//                            repondrecontroller.setUpdate(true);
//                            repondrecontroller.setTextField(internship.getREF(), "opinion",internship.getCompany(), internship.getField(), internship.getTitle(), internship.getDescription(), internship.getStatus());   
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                           

                        });

                        HBox managebtn = new HBox(editIcon, EyeIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(EyeIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                

            };

            return cell;
        };
         statuss.setCellFactory(cellFoctory);
         ClaimsTable.setItems(InternshipsList);
         
         
    }   

    
}
