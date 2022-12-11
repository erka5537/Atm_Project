package atmproject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Options extends Account{

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    boolean flag = true;
    HashMap<Integer, Integer> data = new HashMap<>();
    public void login(){
        System.out.println("Techproed ATM' hoş geldiniz");

        do {
            data.put(12345,1234);
            data.put(23456,2345);
            data.put(34567,3456);
            data.put(45678,4567);
            data.put(56789,5678);

            try{
                System.out.println("Hesap numaranızı giriniz...");
                setAccountNumber(input.nextInt());
                System.out.println("Pin numaranızı giriniz");
                setPinNumber(input.nextInt());
            }catch (Exception e){
                System.out.println("Yanlış karakter girdiniz! lütfen sadece rakam giriniz veya Q ya basıp çıkabilirsiniz");
                input.nextLine();
                String exit = input.next();

                if(exit.equalsIgnoreCase("q")){
                    flag = false;
                }
            }

            int count = 0;

            for(Map.Entry<Integer, Integer> w : data.entrySet()){

                if(w.getKey().equals(getAccountNumber()) && w.getValue().equals(getPinNumber())){
                    getAccountTypes();//hesap işlemlerine gidiniz
                }else{
                    count++;
                }
            }
            if (count == data.size()) {
                System.out.println("Yanlış hesap numarası veya pin numarası girdiniz...");
                System.out.println("Tekrar deneyin ya da çıkmak için q ya basın");
                String exit = input.next().toLowerCase();
                if (exit.equals("q")) {
                    break;
                }
            }
        }while (flag);
    }

    //Checking hesap işlemleri =>
    public void checkingOperations(){
        do {
            displayMessage();

            int option = input.nextInt();

            if(option == 4){
                break;
            }

            switch (option){
                case 1:
                    System.out.println("Checking hesabınızda kalan bakiye: "+moneyFormat.format(getCheckingBalance()));
                    break;
                case 2:
                    getCheckingWithdraw();
                    break;
                case 3:
                    getCheckingDeposit();
                    break;
                default:
                    System.out.println("Yanlış seçenek! Lütfen 1, 2, 3 veya 4'ü kullanınız");
            }
        }while(true);
        getAccountTypes();
    }

    public void savingOperations(){

        do {
            displayMessage();

            int option = input.nextInt();

            if(option == 4){
                break;
            }

            switch (option){
                case 1:
                    System.out.println("Saving hesabınızda kalan bakiye: "+moneyFormat.format(getSavingBalance()));
                    break;
                case 2:
                    getSavingWithdraw();
                    break;
                case 3:
                    getSavingDopesit();
                    break;
                default:
                    System.out.println("Yanlış seçenek! Lütfen 1, 2, 3 veya 4'ü kullanınız");
            }

        }while (true);
        getAccountTypes();
    }
    //Ilgili hesabı seçiniz
    public void getAccountTypes(){

        System.out.println("İşlem yapmak istediğiniz hesabı seçiniz");
        System.out.println("1:Checking Account");
        System.out.println("2:Saving Account");
        System.out.println("3:Exit");

        int option = input.nextInt();

        switch (option){

            case 1:
                System.out.println("Checking/Vadesiz hesabınızdasınız");
                checkingOperations();
                break;
            case 2:
                System.out.println("Svaing/Vadeli hesabınızdasınız");
            case 3:
                System.out.println("ATM makinemizi kullandığınız için teşekkür ederiz");
                flag = false;
            default:
                System.out.println("Yanlış seçenek! Lütfen 1, 2 veya 3'ü kullanınız");
        }
    }
    public void displayMessage(){
        System.out.println("Option seçiniz...");
        System.out.println("1: View Balance");// / bakiyenizi kontrol ediniz
        System.out.println("2: Withdraw");// para cekme
        System.out.println("3: Deposit");// para yatırma
        System.out.println("4. Exit");// işlemi sonlandır
    }
}
