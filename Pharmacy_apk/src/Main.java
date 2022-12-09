import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static boolean mainSection = false;
    static int stock[] = {30,0,20};
    static String prescrip[] = {"alcohol","oxigen","paracetamol"};

    static void line(){
        System.out.println("--------------------------------------------------");
    }


    static void cashier(){
        String name;
        int count,index,calculate;
        String orderName[] = new String[99];
        int orderStock[] = new int[99];
        boolean repeatSec = false;

        line();
        System.out.println("Casier");
        line();

        do {
            System.out.print("Enter the name of prescription : ");
            name = sc.nextLine();
            index = search(name);
            if (index == -1){
                System.out.println("| The prescription isn't available |");
                repeatSec = true;
            }else if(stock[index] == 0){
                System.out.println("| Out of stock |");
                repeatSec = true;
            }else {
                System.out.print("Enter the number of prescription : ");
                count = sc.nextInt();
                sc.nextLine();
                calculate = stock[index] - count;
                if (calculate < 0){
                    System.out.println("| Not enough stock |");
                    repeatSec = true;
                }else {
                    stock[index] = calculate;
                    for (int i = 0; i < orderName.length;i++){
                        if (orderName[i] == null) {
                            orderName[i] = name;
                            orderStock[i] = count;
                            break;
                        }
                    }

                    System.out.print("Do you want input again? (y/n) : ");
                    String section = sc.nextLine();
                    switch (section){
                        case "y":
                        case "Y":
                            repeatSec = true;
                            break;
                        default:
                            System.out.println("--------------------");
                            System.out.println("     Order Data     ");
                            System.out.println("--------------------");
                            for (int i = 0;i < orderName.length;i++){
                                if (orderName[i] != null){
                                    System.out.println(orderName[i] + " = " + orderStock[i]);
                                }
                            }
                            System.out.println("--------------------");
                            System.out.println();
                            repeatSec = false;
                            mainSection = true;
                    }
                }
            }
        } while (repeatSec == true);


    }

    static void tableData(){
        System.out.printf("|");
        System.out.printf("%-2s", "No");
        System.out.printf("%-2s", "|");
        System.out.printf("%-20s","Name");
        System.out.printf("%-2s","|");
        System.out.printf("%-10s","Stock");
        System.out.println("|");

        if (prescrip[0] == null){
            System.out.printf("|");
            System.out.printf("%-2s", "-");
            System.out.printf("%-2s", "|");
            System.out.printf("%-20s","null");
            System.out.printf("%-2s","|");
            System.out.printf("%-10s","null");
            System.out.println("|");
        }else {
            int num = 1;
            for (int i = 0;i< prescrip.length;i++){
                if (prescrip[i] != null){
                    System.out.printf("|");
                    System.out.printf("%-2s", num);
                    System.out.printf("%-2s", "|");
                    System.out.printf("%-20s",prescrip[i]);
                    System.out.printf("%-2s","|");
                    System.out.printf("%-10s",stock[i]);
                    System.out.println("|");
                    num++;
                }
            }
        }
    }

    static void prescripData(){
        int choice;
        boolean sectionDataPres = false;

        line();
        System.out.println("Stock data");
        line();
        tableData();

        do {
            line();
            System.out.println("Menu");
            System.out.println("1. Add Prescription");
            System.out.println("2. Add Stock");
            System.out.println("3. Back to Menu");
            System.out.print("Choose (1/2/3) : ");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    addData();
                    prescripData();
                    break;
                case 2:
                    addStock();
                    prescripData();
                    break;
                case 3:
                    sectionDataPres = false;
                    mainSection = true;
                    break;

                default:
                    System.out.println("please choose between 1-4");
                    sectionDataPres = true;
            }
        }while (sectionDataPres == true);


    }

    static void addData(){
        String inPrescrip;
        int inStock;

        System.out.print("Enter the name of prescription : ");
        sc.nextLine();
        inPrescrip = sc.nextLine();
        System.out.print("Enter the stock of prescription : ");
        inStock = sc.nextInt();

        for (int i = 0; i < prescrip.length; i++){
            if (prescrip[i] == null){
                prescrip[i] = inPrescrip;
                stock[i] = inStock;
                break;
            }

        }

    }

    static void addStock(){
        int a,addStock;
        System.out.print("Choose the number of prescription : ");
        a = sc.nextInt();

        int num = 1;
        for (int i = 0;i < prescrip.length;i++){
            if (a == num){
                System.out.print("Enter stock : ");
                addStock = sc.nextInt();
                stock[i] += addStock;
            }
            num++;
        }
    }

     static int search(String key){
        for(int i = 0; i< prescrip.length; i++){
            if (prescrip[i] == null){
                return -1;
            }else {
                if(prescrip[i].equals(key)){
                    return i;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int choice;

        String again;

        do {
            line();
            System.out.println("          WELCOME TO PHARMACY APK");
            line();
            System.out.println("Menu");
            System.out.println("1. Prescription Data");
            System.out.println("2. Cashier");
            System.out.println("3. Member");
            System.out.println("4. Exit");
            System.out.print("Choose (1/2/3/4) : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    prescripData();
                    break;
                case 2:
                    cashier();
                    break;
                case 3:
//                    tableMember();
                    break;
                case 4:
                    mainSection = false;
                    break;
                default:
                    System.out.println("AAAAAAUUUUGGGGGHHHH");
                    mainSection = true;
            }

        }while (mainSection == true);
    }
}