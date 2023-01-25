package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	
	String choice;
	int index = 0;
	Employee payableObjects[] = new Employee[100];
	File file = new File("data.txt");
	
	TextField tfName = new TextField();
	TextField tfLastName = new TextField();
	TextField tfSSN = new TextField();
	TextField tfSearchUpdateSSN = new TextField();
	TextField tfSALARY = new TextField();
	TextField tfGrossSales = new TextField();
	TextField tfCommissionRate = new TextField();
	TextField tfSalary = new TextField();
	TextField tfBaseSalary = new TextField();
	TextField tfWeeklySalary = new TextField();
	TextField tfWage = new TextField();
	TextField tfHours = new TextField();
	TextField tfEmployeeType = new TextField();
	
	
	Button btAdd = new Button("Add");
	Button btSearchBySSN = new Button("Search by SSN");
	Button btUpdateBySSN = new Button("Update by SSN");
	Button btClear = new Button("Clear text fields");
	

	Label lbName= new Label("Name");
	Label lbLastName= new Label("Last Name");
	Label lbSSN= new Label("SSN");
	Label lbSearchUpdateSSN = new Label("Search/Update SSN");
	Label lbSalary = new Label ("Salary");
	Label lbGrossSales = new Label ("GrossSales");
	Label lbCommissionRate = new Label ("Commission Rate");
	Label lbBaseSalary = new Label ("Base Salary");
	Label lbWeeklySalary = new Label ("Weekly Salary");
	Label lbWage = new Label ("Wage");
	Label lbHours = new Label ("Hours");
	Label lbEmployeeType = new Label ("Choose Employee Type");
	Label empty = new Label("");
	
	
	ChoiceBox<String> choiceBox = new ChoiceBox<>();
	ObservableList<String> list = choiceBox.getItems();
	
	
	public void start(Stage primaryStage) {
		try {
			
			list.addAll("Hourly Employee","Salaried Employee","Base Salaried Commission Employee","Commission Employee","None");
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			
			GridPane p1 = new GridPane();
			
			p1.setAlignment(Pos.CENTER);
			p1.setHgap(5);
			p1.setVgap(5);
			
			p1.add(lbName, 0, 2);
			p1.add(tfName, 1, 2);
			
			p1.add(lbGrossSales, 10, 2);
			p1.add(tfGrossSales, 13, 2);
			
			p1.add(lbLastName, 0, 3);
			p1.add(tfLastName, 1, 3);
			
			p1.add(lbCommissionRate, 10, 3);
			p1.add(tfCommissionRate, 13, 3);
			
			p1.add(lbSSN, 0, 4);
			p1.add(tfSSN, 1, 4);
			
			p1.add(lbBaseSalary, 10, 4);
			p1.add(tfBaseSalary, 13, 4);
			
			p1.add(lbSearchUpdateSSN, 0, 5);
			p1.add(tfSearchUpdateSSN, 1, 5);
			
			p1.add(lbWeeklySalary, 10, 5);
			p1.add(tfWeeklySalary, 13, 5);
			
			p1.add(lbSalary, 0, 6);
			p1.add(tfSalary, 1, 6);
			
			p1.add(lbWage, 10, 6);
			p1.add(tfWage, 13, 6);
			
			p1.add(lbHours, 10, 7);
			p1.add(tfHours, 13, 7);
		

			GridPane p2 = new GridPane();
			p2.setAlignment(Pos.TOP_CENTER);
			p2.setHgap(5);
			p2.setVgap(5);
			p2.add(lbEmployeeType,1,2);
			p2.add(choiceBox,5,2);
		
			
			GridPane p3 = new GridPane();
			p3.setAlignment(Pos.BOTTOM_CENTER);
			p3.setHgap(10);
			p3.setVgap(1);
			
			
			p3.add(btAdd,5,11);
			p3.add(btSearchBySSN, 6, 11);
			p3.add(btUpdateBySSN, 7, 11);
			p3.add(btClear, 8, 11);
			p3.add(empty, 1, 12);
			
			
			BorderPane borderPane= new BorderPane();
			
			Rectangle rect = new Rectangle();
			rect.setFill(Color.WHITESMOKE);
			rect.setStrokeWidth(5);
			rect.setStroke(Color.LIGHTBLUE);
	        rect.widthProperty().bind(borderPane.widthProperty().subtract(0));
	        rect.heightProperty().bind(borderPane.heightProperty().divide(1.8));
	        rect.xProperty().bind(borderPane.widthProperty().divide(1000));
	        rect.yProperty().bind(borderPane.heightProperty().divide(5));
			
			borderPane.getChildren().add(rect);
			borderPane.setCenter(p1);
			borderPane.setTop(p2);
			borderPane.setBottom(p3);
			
			
			Scene scene = new Scene(borderPane,700,400);
			primaryStage.setTitle("EMPLOYEE SALARY CALCULATOR");
			primaryStage.setScene(scene); primaryStage.show();
			
			
			btAdd.setOnAction(e -> {
				try {
					add(e,index,choice);
					alert.setContentText("Record is added successfully");
					alert.show();
					cleanTextFields();
					index++;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			});
			
			btSearchBySSN.setOnAction(e -> {
				searchSSN();
			});
			
			btUpdateBySSN.setOnAction(e -> {
				updateBySSN();
				updateSSN();
			});
			
			btClear.setOnAction(e -> {
				cleanTextFields();
			}); 
			
			choiceBox.setOnAction(e -> {
				choice = choiceBox.getValue();
				if(choiceBox.getValue().equals("Salaried Employee")) {
					tfBaseSalary.setDisable(true);
					tfWage.setDisable(true);
					tfHours.setDisable(true);
					tfCommissionRate.setDisable(true);
					tfGrossSales.setDisable(true);
					tfSSN.setDisable(true);
					tfSalary.setDisable(true);
					tfWeeklySalary.setDisable(false);
					
				}
				if(choiceBox.getValue().equals("Hourly Employee")) {
					tfBaseSalary.setDisable(true);
					tfCommissionRate.setDisable(true);
					tfGrossSales.setDisable(true);
					tfSSN.setDisable(true);
					tfWeeklySalary.setDisable(true);
					tfSalary.setDisable(true);
					tfWage.setDisable(false);
					tfHours.setDisable(false);
				
				}
				if(choiceBox.getValue().equals("Base Salaried Commission Employee")) {
					tfWage.setDisable(true);
					tfHours.setDisable(true);
					tfSSN.setDisable(true);
					tfWeeklySalary.setDisable(true);
					tfSalary.setDisable(true);
					tfGrossSales.setDisable(false);
					tfCommissionRate.setDisable(false);
					tfBaseSalary.setDisable(false);
				}
				if(choiceBox.getValue().equals("Commission Employee")) {
					tfWage.setDisable(true);
					tfHours.setDisable(true);
					tfSSN.setDisable(true);
					tfBaseSalary.setDisable(true);
					tfWeeklySalary.setDisable(true);
					tfSalary.setDisable(true);
					tfCommissionRate.setDisable(false);
					tfGrossSales.setDisable(false);
				}
				if(choiceBox.getValue().equals("None")) {
					tfBaseSalary.setDisable(false);
					tfWage.setDisable(false);
					tfHours.setDisable(false);
					tfCommissionRate.setDisable(false);
					tfGrossSales.setDisable(false);
					tfSSN.setDisable(false);
					tfSalary.setDisable(false);
					tfWeeklySalary.setDisable(false);
				}
				
				
			}); 
			
			try {
				readFile();
			} catch (IOException e) {
				alert.setContentText("Data file is empty!");
				alert.show();
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public void cleanTextFields()
	{
		tfBaseSalary.clear();
		tfName.clear();
		tfLastName.clear();
		tfSSN.clear();
		tfSearchUpdateSSN.clear();
		tfSALARY.clear();
		tfGrossSales.clear();
		tfCommissionRate.clear();
		tfSalary.clear();
		tfBaseSalary.clear();
		tfWeeklySalary.clear();
		tfWage.clear();
		tfHours.clear();
		tfEmployeeType.clear();
		tfName.clear();
		choiceBox.setValue("None");
	}
	
	public void searchSSN() {
		String SSN = tfSearchUpdateSSN.getText();
		
		for(int i = 0 ; i < index+1 ; i++) {
			
			if(payableObjects[i] != null) {
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof BasePlusCommissionEmployee) {
						String fields = payableObjects[i].fields();
						String[] values = fields.split(",");
						tfName.setText(values[0]);
						tfLastName.setText(values[1]);
						tfSSN.setText(values[2]);
						tfGrossSales.setText(values[3]);
						tfCommissionRate.setText(values[4]);
						tfBaseSalary.setText(values[5]);
						tfSalary.setText(String.valueOf(payableObjects[i].getPaymentAmount()));
						choiceBox.setValue("Base Salaried Commission Employee");
						return;
					}
				}
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof CommissionEmployee) {
						String fields = payableObjects[i].fields();
						String[] values = fields.split(",");
						tfName.setText(values[0]);
						tfLastName.setText(values[1]);
						tfSSN.setText(values[2]);
						tfGrossSales.setText(values[3]);
						tfCommissionRate.setText(values[4]);
						tfSalary.setText(String.valueOf(payableObjects[i].getPaymentAmount()));
						choiceBox.setValue("Commission Employee");
					}
				}
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof HourlyEmployee) {
						String fields = payableObjects[i].fields();
						String[] values = fields.split(",");
						tfName.setText(values[0]);
						tfLastName.setText(values[1]);
						tfSSN.setText(values[2]);
						tfWage.setText(values[3]);
						tfHours.setText(values[4]);
						tfSalary.setText(String.valueOf(payableObjects[i].getPaymentAmount()));
						choiceBox.setValue("Hourly Employee");
					}
				}
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof SalariedEmployee) {
						String fields = payableObjects[i].fields();
						String[] values = fields.split(",");
						tfName.setText(values[0]);
						tfLastName.setText(values[1]);
						tfSSN.setText(values[2]);
						tfWeeklySalary.setText(values[3]);
						tfSalary.setText(String.valueOf(payableObjects[i].getPaymentAmount()));
						choiceBox.setValue("Salaried Employee");
					}
				}
				
			}
		}
	}
	
	public void add(ActionEvent event, int order,String choice) throws IOException {
		
		FileWriter fw = new FileWriter(file,true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		
		if(choice.equals("Salaried Employee")) {
			String name = tfName.getText();
			String lastName = tfLastName.getText();
			Double salary = Double.parseDouble(tfWeeklySalary.getText());
			
			out.println(order + "," + name +"," +lastName +"," + "Salaried Employee" + ","+ salary);
			out.close();
			
			payableObjects[order] = new SalariedEmployee(name, lastName, salary);
			payableObjects[order].setSSN(order);
			
		}
		
		if(choice.equals("Hourly Employee")) {
			String name = tfName.getText();
			String lastName = tfLastName.getText();
			Double wage = Double.parseDouble(tfWage.getText());
			int hours = Integer.parseInt(tfHours.getText());
			
			payableObjects[order] = new HourlyEmployee(name, lastName, wage, hours);
			payableObjects[order].setSSN(order);
			
			out.println(order + "," + name +"," +lastName + "," +"Hourly Employee" +"," + wage + "," + hours +"," + payableObjects[order].getPaymentAmount() );
			out.close();
			
		}
		
		if(choice.equals("Base Salaried Commission Employee")) {
			String name = tfName.getText();
			String lastName = tfLastName.getText();
			Double commissionRate = Double.parseDouble(tfCommissionRate.getText());
			Double baseSalary = Double.parseDouble(tfBaseSalary.getText());
			int grossSales = Integer.parseInt(tfGrossSales.getText());
			
			payableObjects[order] = new BasePlusCommissionEmployee(name, lastName, grossSales, commissionRate, baseSalary);
			payableObjects[order].setSSN(order);
			
			out.println(order+ "," + name +"," +lastName + "," +"Base Salaried Commission Employee" +"," + commissionRate + "," + grossSales +","+baseSalary  +"," + payableObjects[order].getPaymentAmount() );
			out.close();
			
		}
		
		if(choice.equals("Commission Employee")) {
			String name = tfName.getText();
			String lastName = tfLastName.getText();
			Double commissionRate = Double.parseDouble(tfCommissionRate.getText());
			int grossSales = Integer.parseInt(tfGrossSales.getText());
			
			payableObjects[order] = new CommissionEmployee(name, lastName, grossSales, commissionRate);
			payableObjects[order].setSSN(order);
			
			out.println(order+ "," + name +"," +lastName + "," + "Commission Employee" +"," + commissionRate + "," + grossSales +"," + payableObjects[order].getPaymentAmount() );
			out.close();
			
		}
		
		
	}
	
	public void readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while((line = reader.readLine()) != null) {
			String[] values = line.split(",");
			if(values[3].equals("Salaried Employee")) {
				payableObjects[index] = new SalariedEmployee(values[1], values[2], Double.parseDouble(values[4]));
				payableObjects[index].setSSN(Integer.parseInt(values[0]));
				index++;
			}
			if(values[3].equals("Base Salaried Commission Employee")) {
				payableObjects[index] = new BasePlusCommissionEmployee(values[1], values[2], Integer.parseInt(values[5]), Double.parseDouble(values[4]), Double.parseDouble(values[6]));
				payableObjects[index].setSSN(Integer.parseInt(values[0]));
				index++;
			}
			if(values[3].equals("Commission Employee")) {
				payableObjects[index] = new CommissionEmployee(values[1], values[2], Integer.parseInt(values[5]), Double.parseDouble(values[4]));
				payableObjects[index].setSSN(Integer.parseInt(values[0]));
				index++;
			}
			if(values[3].equals("Hourly Employee")) {
				payableObjects[index] = new HourlyEmployee(values[1], values[2], Double.parseDouble(values[4]), Integer.parseInt(values[5]));
				payableObjects[index].setSSN(Integer.parseInt(values[0]));
				index++;
			}
			
		}
		reader.close();
	}
	
	public void updateBySSN() {
		String SSN = tfSearchUpdateSSN.getText();
		
		for(int i = 0 ; i < index ; i++) {
			
			if(payableObjects[i] != null) {
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof BasePlusCommissionEmployee) {
				
						String name = tfName.getText();
						String lastName = tfLastName.getText();
						Double commissionRate = Double.parseDouble(tfCommissionRate.getText());
						int grossSales = Integer.parseInt(tfGrossSales.getText());
						Double baseSalary = Double.parseDouble(tfBaseSalary.getText());
						
						BasePlusCommissionEmployee temp = new BasePlusCommissionEmployee(name, lastName, grossSales, commissionRate, baseSalary);
						temp.setSSN(payableObjects[i].getSSN());
						payableObjects[i] = temp;
						
						return;
					}
				}
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof CommissionEmployee) {
				
						String name = tfName.getText();
						String lastName = tfLastName.getText();
						Double commissionRate = Double.parseDouble(tfCommissionRate.getText());
						int grossSales = Integer.parseInt(tfGrossSales.getText());
						
						CommissionEmployee temp = new CommissionEmployee(name, lastName, grossSales, commissionRate);
						temp.setSSN(payableObjects[i].getSSN());
						payableObjects[i] = temp;
						
					}
				}
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof HourlyEmployee) {
						String name = tfName.getText();
						String lastName = tfLastName.getText();
						Double wage = Double.parseDouble(tfWage.getText());
						int hours = Integer.parseInt(tfHours.getText());
						
						HourlyEmployee temp = new HourlyEmployee(name, lastName, wage, hours);
						temp.setSSN(payableObjects[i].getSSN());
						payableObjects[i] = temp;
						
					}
				}
				
				if(String.valueOf(payableObjects[i].getSSN()).equals(SSN)) {
					
					if(payableObjects[i] instanceof SalariedEmployee) {
						String name = tfName.getText();
						String lastName = tfLastName.getText();
						Double salary = Double.parseDouble(tfWeeklySalary.getText());
						
						SalariedEmployee temp = new SalariedEmployee(name, lastName, salary);
						temp.setSSN(payableObjects[i].getSSN());
						payableObjects[i] = temp;
						
					}
				}
				
			}
		}
		
		
		
	}

	public void updateSSN() {
		String SSN = tfSearchUpdateSSN.getText();
		String name = tfName.getText();
		String lastName = tfLastName.getText();
		int tempSSN = Integer.parseInt(SSN);
		
		ArrayList<String> tempArray = new ArrayList<>();
		
		try {
			try (FileReader fr = new FileReader(file)){
				Scanner reader = new Scanner(fr);
				String line;
				String[] lineArr;
				
				while((line = reader.nextLine()) != null) {
					lineArr = line.split(",");
					if(lineArr[0].equals(SSN)) {
						if(lineArr[3].equals("Salaried Employee")) {
							String weeklySalary = tfWeeklySalary.getText();
							tempArray.add(
									lineArr[0] + ","+
									name + ","+
		                            lastName + ","+
									lineArr[3] + ","+
									weeklySalary + ","
									);
						}
						if(lineArr[3].equals("Base Salaried Commission Employee")) {
							String base = tfBaseSalary.getText();
							String rate = tfCommissionRate.getText();
							String sales = tfGrossSales.getText();
							
							tempArray.add(
									lineArr[0] + ","+
									name + ","+
		                            lastName + ","+
									lineArr[3] + ","+
									rate + ","+
									sales + ","+
									base + ","+
									payableObjects[tempSSN].getPaymentAmount()
									);
						}
						if(lineArr[3].equals("Commission Employee")) {
							String rate = tfCommissionRate.getText();
							String sales = tfGrossSales.getText();
							tempArray.add(
									lineArr[0] + ","+
									name + ","+
		                            lastName + ","+
									lineArr[3] + ","+
									rate + ","+
									sales + ","+
									payableObjects[tempSSN].getPaymentAmount()
									);
						}
						if(lineArr[3].equals("Hourly Employee")) {
							String wage = tfWage.getText();
							String hours = tfHours.getText();
							tempArray.add(
									lineArr[0] + ","+
									name + ","+
		                            lastName + ","+
									lineArr[3] + ","+
									wage + ","+
									hours + ","+
									payableObjects[tempSSN].getPaymentAmount()
									);
						}
						
					}
					else {
						tempArray.add(line);
					}
				}
				
				fr.close();
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			
		}
		
		try {
			try (PrintWriter pr = new PrintWriter(file)){
				
				for(String str: tempArray) {
					pr.println(str);
				}
				pr.close();
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
