package com.cs213project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import java.lang.module.FindException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

//assuming database has to be created in this file
public class TransactionManagerController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField firstNameText, lastNameText, amountText, firstNameText2, lastNameText2, amountText2;
    @FXML
    private DatePicker datepicker, datepicker2;
    @FXML
    private RadioButton checkingButton,savingsButton,mmButton,ccButton,nbButton,camdenButton,newarkButton, checkingButton2,savingsButton2,mmButton2,ccButton2;
    @FXML
    private CheckBox loyalBox;
    @FXML
    private TextArea messageArea;
    private AccountDatabase database = new AccountDatabase();



    @FXML
    void ToggleAllCampusButtons(ActionEvent event, boolean toggle){
    if(!toggle) { //toggle = true enable them
        nbButton.setDisable(true);
        newarkButton.setDisable(true);
        camdenButton.setDisable(true);
        return;
    }
    nbButton.setDisable(false);
    newarkButton.setDisable(false);
    camdenButton.setDisable(false);

}
    @FXML
    void disableAccountButtons(ActionEvent event){
        if(checkingButton.isSelected()){
           ToggleAllCampusButtons(event,false);
            loyalBox.setDisable(true);
        }
        if(savingsButton.isSelected()){
            ToggleAllCampusButtons(event,false);
            loyalBox.setDisable(false);
        }
        if(mmButton.isSelected()){
            ToggleAllCampusButtons(event,false);
            loyalBox.setDisable(true);
        }
        if(ccButton.isSelected()){
            ToggleAllCampusButtons(event,true);
            loyalBox.setDisable(true);
        }
    }

    @FXML
    private boolean accountSelected(ActionEvent event){
        if(!mmButton.isSelected() && !ccButton.isSelected()){
            if(!checkingButton.isSelected() && !savingsButton.isSelected()){
                return false;
            }
        }
        return true;
    }
    @FXML
    private boolean campusSelected(ActionEvent event){
        if(!newarkButton.isSelected() && !camdenButton.isSelected() && !nbButton.isSelected()){
            return false;
        }
        return true;
    }

    private boolean containsSpecialChars(String input){
        int capitalLowerBound = 65; // based off ascii capital letters have val of 65-90
        int capitalUpperBound = 90;
        int lowerCaseLowerBound = 97; // ascii lower case letters have val of 97-122
        int lowerCaseUpperBound = 122;
        char[] array = input.toCharArray();
        for(int i = 0; i<array.length; i++){
            int temp = (int)array[i]; //getting ascii
            if((temp<65 || temp>90) && (temp<97 || temp >122)){ //if its in range theres no special characters
                return true;
            }
        }

        return false;

    }
    @FXML
    private String openExceptionFinder(ActionEvent event){
        String exception = "";
        /*if(firstNameText.getText().isEmpty()){
            exception = "Missing First Name\n";
        }
        if(lastNameText.getText().isEmpty()){
            exception+= "Missing Last Name\n";
        }
        if(containsSpecialChars(firstNameText.getText()) && !firstNameText.getText().isEmpty()){
            exception+= "First Name Cannot Contain Special Characters Or Spaces\n";
        }
        if(containsSpecialChars(lastNameText.getText()) && !lastNameText.getText().isEmpty()){
            exception+= "Last Name Cannot Contain Special Characters Or Spaces\n";
        }
        if(datepicker.getValue()==null){
            exception += "Missing Date of Birth\n";
        }
        if(amountText.getText().isEmpty()){
            exception+= "Missing Amount\n";
        }
        if(!accountSelected(event)){
            exception += "No Account Type Selected\n";
        }*/
        if(!campusSelected(event) && ccButton.isSelected()){
            exception+= "No Campus Selected\n";
        }
        /*try{
            double amount = Double.parseDouble(amountText.getText());
        }
        catch(NumberFormatException e){
            exception+= "Invalid Amount Type\n";
        }*/
        double amount = Double.parseDouble(amountText.getText());
        if (amount < 2000 && mmButton.isSelected()){
            exception+= "Minimum of $2000 to open a Money Market account.\n";
        }
        if (amount <= 0) {
            exception+= "Initial deposit cannot be 0 or negative.\n";
        }
        return exception;
    }

    @FXML
    private String depositExceptionFinder(ActionEvent event) {
        String exception = "";

        double amount = Double.parseDouble(amountText.getText());
        if (amount <= 0) {
            exception += "Deposit - amount cannot be 0 or negative.\n";
        }
        return exception;
    }

    @FXML
    private String textExceptionFinder(ActionEvent event) {
        String exception = "";
        if(firstNameText.getText().isEmpty()){
            exception = "Missing First Name\n";
        }
        if(lastNameText.getText().isEmpty()){
            exception+= "Missing Last Name\n";
        }
        if(containsSpecialChars(firstNameText.getText()) && !firstNameText.getText().isEmpty()){
            exception+= "First Name Cannot Contain Special Characters Or Spaces\n";
        }
        if(containsSpecialChars(lastNameText.getText()) && !lastNameText.getText().isEmpty()){
            exception+= "Last Name Cannot Contain Special Characters Or Spaces\n";
        }
        if(datepicker.getValue()==null){
            exception += "Missing Date of Birth\n";
        }
        if(amountText.getText().isEmpty()){
            exception+= "Missing Amount\n";
        }
        if(!accountSelected(event)){
            exception += "No Account Type Selected\n";
        }
        try{
            double amount = Double.parseDouble(amountText.getText());
        }
        catch(NumberFormatException e){
            exception+= "Invalid Amount Type\n";
        }
        return exception;
    }

    private String textExceptionFinder2(ActionEvent event) {
        String exception = "";
        if(firstNameText2.getText().isEmpty()){
            exception = "Missing First Name\n";
        }
        if(lastNameText2.getText().isEmpty()){
            exception+= "Missing Last Name\n";
        }
        if(containsSpecialChars(firstNameText2.getText()) && !firstNameText2.getText().isEmpty()){
            exception+= "First Name Cannot Contain Special Characters Or Spaces\n";
        }
        if(containsSpecialChars(lastNameText2.getText()) && !lastNameText2.getText().isEmpty()){
            exception+= "Last Name Cannot Contain Special Characters Or Spaces\n";
        }
        if(datepicker2.getValue() == null){
            exception += "Missing Date of Birth\n";
        }
        if(amountText2.getText().isEmpty()){
            exception+= "Missing Amount\n";
        }
        if(!accountSelected(event)){
            exception += "No Account Type Selected\n";
        }
        try{
            double amount = Double.parseDouble(amountText2.getText());
        }
        catch(NumberFormatException e){
            exception+= "Invalid Amount Type\n";
        }
        return exception;
    }
    @FXML
    private Account makeAccount(Profile holder, double balance){

        if(checkingButton.isSelected()){
            return new Checking(holder,balance);
        }
        if(savingsButton.isSelected()){
            boolean loyal = loyalBox.isSelected();
            return new Savings(holder, balance, loyal);
        }
        if(mmButton.isSelected()){
            return new MoneyMarket(holder,balance,0);
        }

        if(nbButton.isSelected()){
            return new CollegeChecking(holder,balance, Campus.NEW_BRUNSWICK);
        }
        if(newarkButton.isSelected()){
            return new CollegeChecking(holder,balance, Campus.NEWARK);
        }
            return new CollegeChecking(holder,balance, Campus.CAMDEN);
    }

    /**
     * make accounts for deposit and withdrawals
     * @param holder
     * @param balance
     * @return
     */
    @FXML
    private Account makeAccount2(Profile holder, double balance){

        if(checkingButton2.isSelected()){
            return new Checking(holder,balance);
        }
        if(savingsButton2.isSelected()){
            boolean loyal = loyalBox.isSelected();
            return new Savings(holder, balance, loyal);
        }
        if(mmButton2.isSelected()){
            return new MoneyMarket(holder,balance,0);
        }
        // campus code for temp account does not change it for original
        return new CollegeChecking(holder,balance, Campus.CAMDEN);
    }

    @FXML
    void openAccountButton(ActionEvent event){

        String exceptionA = textExceptionFinder(event);
        if(!exceptionA.equals("")){
            messageArea.setText(exceptionA);
            return;
        }
        String exceptionB = openExceptionFinder(event);
        if(!exceptionB.equals("")){
            messageArea.setText(exceptionB);
            return;
        }

        String fname = firstNameText.getText(), lname = lastNameText.getText();
        String[] dateinfo = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-");
        int month = Integer.parseInt(dateinfo[0]), day = Integer.parseInt(dateinfo[1]),year = Integer.parseInt(dateinfo[2]);
        Date dob = new Date(month,day,year);
        String dateS = dob.toString();
        Profile holder = new Profile(fname,lname,dob);
        double amount = Double.parseDouble(amountText.getText());

        Account newAcc = makeAccount(holder,amount);
        String accountType = newAcc.accountType();

        if (newAcc.getHolder().getDob().futureOrToday()) {
            messageArea.setText("DOB invalid: " + dateS  + " cannot be today or a future day.");
            return;
        }
        if (newAcc.getHolder().getDob().underSixteen()) {
            messageArea.setText("DOB invalid: " + dateS  + " under 16.");
            return;
        }
        if (newAcc.getHolder().getDob().overTwentyFour() && accountType.equals("College Checking")) {
            messageArea.setText ("DOB invalid: " + dateS  + " over 24.");
            return;
        }
        boolean didWeOpen = database.open(newAcc);
        String returnString = fname + " " + lname + " " + dateS + " " + "(" + accountType + ")";
        if(didWeOpen) {
            messageArea.setText(returnString + " " + "Opened."); // for testing purposes
            return;
        }
        messageArea.setText(returnString + " already exists in database");
    }

    @FXML
    void closeAccountButton(ActionEvent event){

        String exceptionA = textExceptionFinder(event);

        if(!exceptionA.equals("")){
            messageArea.setText(exceptionA);
            return;
        }

        String fname = firstNameText.getText(), lname = lastNameText.getText();
        String[] dateinfo = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-");

        int month = Integer.parseInt(dateinfo[0]), day = Integer.parseInt(dateinfo[1]), year = Integer.parseInt(dateinfo[2]);
        Date dob = new Date(month,day,year);
        String dateS = dob.toString();

        Profile holder = new Profile(fname,lname,dob);
        double amount = Double.parseDouble(amountText.getText());
        Account newAcc = makeAccount(holder,amount);
        String accountType = newAcc.accountType();
        boolean didWeClose = database.close(newAcc);

        String returnString = fname + " " + lname + " " + dateS + " " + "(" + accountType + ")";
        if(!didWeClose){
            messageArea.setText(returnString + " " + "is not in the database.");
            return;
        }
        messageArea.setText(returnString + " " + "has been closed."); // for testing purposes
        //will add exception handling later

    }

    @FXML
    void depositButton(ActionEvent event){
        String exceptionA = textExceptionFinder2(event);
        if(!exceptionA.equals("")){
            messageArea.setText(exceptionA);
            return;
        }
        String exceptionB = depositExceptionFinder(event);
        if(!exceptionB.equals("")) {
            messageArea.setText(exceptionB);
            return;
        }

        String fname = firstNameText2.getText(), lname = lastNameText2.getText();
        String[] dateinfo = datepicker2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-");
        int month = Integer.parseInt(dateinfo[0]), day = Integer.parseInt(dateinfo[1]),year = Integer.parseInt(dateinfo[2]);
        Date dob = new Date(month,day,year);
        String dateS = dob.toString();
        Profile holder = new Profile(fname,lname,dob);
        double depositAmount = Double.parseDouble(amountText2.getText());

        Account newAcc = makeAccount2(holder,0);
        String accountType = newAcc.accountType();

        String accountString = fname + " " + lname + " " + dob + "(" + accountType + ") ";

        if (database.contains(newAcc)) {
            double newBalance = database.getAccountBalance(newAcc) + depositAmount;
            newAcc.setBalance(newBalance);
            database.deposit(newAcc);
        }

        else {
            messageArea.setText(accountString + "is not in database.");
            return;
        }
        messageArea.setText(accountString + "Deposit - balance updated.");
    }

    @FXML
    void withdrawalButton(ActionEvent event){

        String fname = firstNameText2.getText(), lname = lastNameText2.getText();
        String[] dateinfo = datepicker2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-");
        int month = Integer.parseInt(dateinfo[0]), day = Integer.parseInt(dateinfo[1]),year = Integer.parseInt(dateinfo[2]);
        Date dob = new Date(month,day,year);
        String dateS = dob.toString();
        Profile holder = new Profile(fname,lname,dob);
        double withdrawalAmount = Double.parseDouble(amountText2.getText());

        Account newAcc = makeAccount2(holder,0);
        String accountType = newAcc.accountType();

        String accountString = fname + " " + lname + " " + dob + "(" + accountType + ") ";
        if(!database.contains(newAcc)){
            messageArea.setText(accountString + " " + "is not in the database.");
            return;
        }
        double currentBalance = database.getAccountBalance(newAcc);
        if(withdrawalAmount > currentBalance){
            messageArea.setText(accountString + " " + "Withdraw - insufficient fund.");
            return;
        }
        if(withdrawalAmount <= 0){
            messageArea.setText("Withdraw - amount cannot be 0 or negative.");
            return;
        }
        double newBalance = currentBalance - withdrawalAmount;
        newAcc.setBalance(newBalance);
        database.withdraw(newAcc);
        messageArea.setText(accountString + " Withdraw - balance updated.");
    }

    private double roundDouble(double amount) {
        double scale = Math.pow(10, 2);
        amount = Math.round(amount * scale) / scale;
        return amount;
    }

    @FXML
    void printFeesButton(ActionEvent event){
        //display sorted print fees
        if (database.getNumAcct() == 0) {
            messageArea.setText("Account Database is empty!");
        }
        else {
            database.sortAccounts();
            String display = "*list of accounts with fee and monthly interest.";
            for (int i = 0; i < database.getNumAcct(); i++) {
                Account account = database.getAccount(i);
                String formatFee = new DecimalFormat("#0.00").format(account.monthlyFee());
                String monthlyFee = "::fee $" + formatFee;

                double monthlyInterest = (account.getBalance() * account.monthlyInterest() / 12);
                monthlyInterest = roundDouble(monthlyInterest);
                String formatInterest = new DecimalFormat("#0.00").format(monthlyInterest);
                String interest = "::monthly interest $" + formatInterest;
                String temp = account + monthlyFee + interest;
                display += "\n" + temp;
            }
            display += "\n" + "* end of list.";
            messageArea.setText(display);
        }
    }

    @FXML
    void printSortedButton(ActionEvent event){
        //display sorted by accounts to textarea
        if (database.getNumAcct() == 0) {
            messageArea.setText("Account Database is empty!");
        }
        else {
            database.sortAccounts();
            String display = "*Accounts sorted by account type and profile.";
            for(int i =0; i< database.getNumAcct(); i++){
                String temp = database.getAccount(i).toString();
                display += "\n" + temp;
            }
            display += "\n" + "* end of list.";
            messageArea.setText(display);
        }
    }

    @FXML
    void printUpdatedBalanceButton(ActionEvent event){
        //display updated balance to textArea
        if (database.getNumAcct() == 0) {
            messageArea.setText("Account Database is empty!");
        }
        else {
            database.sortByUpdateBalances();
            String display = "* list of accounts with fees and interests applied.";
            for(int i = 0; i < database.getNumAcct(); i++){
                String temp = database.getAccount(i).toString();
                display += "\n" + temp;
            }
            display += "\n" + "* end of list.";
            messageArea.setText(display);
        }
    }
    private boolean validCommand(String input){
        String[] validCommands = {"MM","S","C","CC"};
        for(String command : validCommands){
            if(input.equals(command)){
                return true;
            }
        }
        return false;
    }

    private boolean validCode(String input1, String command){

        try{
            int test = Integer.parseInt(input1);
        } catch(NumberFormatException e){
            return false;
        }
        int x = Integer.parseInt(input1);
        if(command.equals("S") && (x != 1 && x != 0) ){
            return false;
        }
        if(command.equals("CC") && (x != 1 && x != 0 && x != 2) ){
            return false;
        }
        return true;
    }
    private boolean validInteger(String[] integers){
        for(String i : integers){
            try{
                int x = Integer.parseInt(i);
            }
            catch(NumberFormatException e){
                return false;
            }
        }
        return true;
    }
    private boolean validTextLine(String[] line){
        if(line.length > 6 || line.length <5){
            return false;
        }
        String command = line[0];
        if(!validCommand(command)) {return false;}

        String fname = line[1], lname = line[2];
        if(containsSpecialChars(fname) || containsSpecialChars(lname)){return false;}
        String[] date = line[3].split("/");

        if(date.length != 3 || !validInteger(date)){return false;}
        try{
            double balance = Double.parseDouble(line[4]);
        } catch(NumberFormatException e){
            return false;
        }
        if(line.length== 6){
            if(!validCode(line[5],command)){
                return false;
            }
        }
        return true;
   }

    private void readFile(Scanner scanner) {
        int counter = 1; // counter to indicate what line in text file is not correct format
        String message = "";
        while(scanner.hasNextLine()) {
            String accountString = scanner.nextLine();
            // prevent blank next line from being added
            if (accountString.length() <= 1) {
                continue; //changed from break to continue if for whatever theres a reason theres a gap between two command lines it will just move on to the next iteration
            }
            String[] accountAttributes = accountString.split(",");
            if(!validTextLine(accountAttributes)){ //if its not in the correct format throw exception and indicate the line
                messageArea.setText("Please Check Text File Formatting On Line: " + counter);
                return;
            }
            // will create separate method to do all this later
            String accountType = accountAttributes[0];
            String fname = accountAttributes[1];
            String lname = accountAttributes[2];
            Date dob = new Date(accountAttributes[3]);
            double balance = Double.parseDouble(accountAttributes[4]);


            Profile holder = new Profile(fname, lname, dob);

            Account account = new Checking(holder, balance);
            if (accountType.equals("CC")) {
                int campusCode = Integer.parseInt(accountAttributes[5]);
                if (campusCode == 0) {
                    account = new CollegeChecking(holder, balance, Campus.NEW_BRUNSWICK);
                }
                if (campusCode == 1) {
                    account = new CollegeChecking(holder, balance, Campus.NEWARK);
                }
                if (campusCode == 2){
                    account = new CollegeChecking(holder, balance, Campus.CAMDEN);
                }
            }
            if (accountType.equals("S")) {
                boolean isLoyal = false;
                if (Integer.parseInt(accountAttributes[5]) == 1) {
                    isLoyal = true;
                }
                account = new Savings(holder, balance, isLoyal);
            }
            if (accountType.equals("MM")) {
                account = new MoneyMarket(holder, balance, 0);
            }


            String returnString = fname + " " + lname + " " + dob + " " + "(" + account.accountType() + ")";
            Boolean opened = database.open(account);
            if (!opened) {
                message += returnString + " already exists in database \n";
            } else{
                message+= returnString + "Opened.\n";
            }
            counter++;
        }
        messageArea.setText(message);
    }

    @FXML
    void loadFromFileButton(ActionEvent event) throws FileNotFoundException {
        // get stage object from ActionEvent
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        // create file chooser in scene window
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Chooser");

        ExtensionFilter ex1 = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(ex1);
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile == null){ //if somebody just randomly closes file explorer without choosing a file
            messageArea.setText("No File Selected.");
            return;
        }

        Scanner scanner = new Scanner(selectedFile);
        readFile(scanner);
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
