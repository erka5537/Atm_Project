package atmproject;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

    private int accountNumber; //hesap numarası
    private int pinNumber; //şifre

    private double checkingBalance; //vadesiz hesap bakiyesi
    private double savingBalance; //vadeli hesap bakiyesi

    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    Scanner input = new Scanner(System.in);

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    //para çekme ==> paraCekmeIslemindenSonraKalanMiktar amount:(miktar)
    private double calculateCheckingBalanceAfterWithdraw(double amount){
        checkingBalance = checkingBalance - amount;
        return checkingBalance;
    }

    //para yatırma: para yatırma işleminden sonra kalan bakiyeyi hesapla
    private double calculateCheckingBalanceAfterDeposit(double amount){
        checkingBalance = checkingBalance + amount;
        return checkingBalance;
    }

    //para çekme: saving hesabından para çekildikten sonra kalan bakiye
    private double calculateSavingBalanceAfterWithdraw(double amount){
        savingBalance = savingBalance - amount;
        return savingBalance;
    }

    //para yatırma: saving hesabına para yatırma işleminden sonra kalan bakiyeyi hesapla
    private double calculateSavingBalanceAfterDeposit(double amount){
        savingBalance = savingBalance + amount;
        return savingBalance;
    }

    //Müşteri ile para çekmek için etkileşime geçelim (Checking Hesap için)
    public void getCheckingWithdraw(){

        System.out.println("Chacking Hesabınızda bulunan bakiye: " + moneyFormat.format(checkingBalance));
        System.out.print("Lütfen çekmek istediğiniz tutarı girin: ");
        double amount = input.nextDouble();

        if(amount<=0){
            System.out.println("Tutar 0'dan küçük veya ona eşit olamaz");
            getCheckingWithdraw();//recursive call  //methodu tekrardan çağırma
        }else if(amount <= checkingBalance){
            calculateCheckingBalanceAfterWithdraw(amount);
            System.out.println("Çekilen tutar: " + moneyFormat.format(amount));
            System.out.println("Kalan bakiye: " + moneyFormat.format(checkingBalance));
        }else{
            System.out.println("Hesabınızda yeterli para yok");
            getCheckingWithdraw();
        }
    }

    //Müşteri ile para yatırmak için etkileşime geçelim (Checking Hesap için)
    public void getCheckingDeposit(){
        System.out.println("Chacking Hesabınızda bulunan bakiye: " + moneyFormat.format(checkingBalance));
        System.out.print("Lütfen yatırmak istediğiniz tutarı giriniz: ");
        double amount = input.nextDouble();
        if(amount<=0){
            System.out.println("Tutar 0'dan küçük veya ona eşit olamaz");
            getCheckingDeposit();
        }else {
            calculateCheckingBalanceAfterDeposit(amount);
            System.out.println("Yatırdığınız miktar: " + moneyFormat.format(amount));
            System.out.println("Güncel bakiye: " + moneyFormat.format(checkingBalance));
        }
    }

    //Müşteri ile para çekmek için etkileşime geçelim (Saving Hesap için)
    public void getSavingWithdraw(){
        System.out.println("Saving Hesabınızda bulunan bakiye " + moneyFormat.format(savingBalance));
        System.out.print("Lütfen çekmek istediğiniz tutarı girin: ");
        double amount = input.nextDouble();
        if(amount<=0){
            System.out.println("Tutar 0'dan küçük veya ona eşit olamaz");
            getSavingWithdraw();
        }else if(savingBalance>=amount){
            calculateSavingBalanceAfterWithdraw(amount);
            System.out.println("Çekilen tutar: " + moneyFormat.format(amount));
            System.out.println("Kalan bakiye: " + moneyFormat.format(savingBalance));
        }else{
            System.out.println("Hesabınızda yeterli para yok");
            getSavingWithdraw();
        }
    }

    //Müşteri ile para yatırmak için etkileşime geçelim (Saving Hesap için)
    public void getSavingDopesit(){
        System.out.println("Saving Hesabınızda bulunan bakiye " + moneyFormat.format(savingBalance));
        System.out.print("Lütfen yatırmak istediğiniz tutarı giriniz: ");
        double amount = input.nextDouble();
        if(amount<=0){
            System.out.println("Tutar 0'dan küçük veya ona eşit olamaz");
            getSavingDopesit();
        }else {
            calculateSavingBalanceAfterDeposit(amount);
            System.out.println("Yatırdığınız miktar: " + moneyFormat.format(amount));
            System.out.println("Güncel bakiye: " + moneyFormat.format(savingBalance));
        }
    }
}
