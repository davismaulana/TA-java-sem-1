import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static boolean mainSection = false, loginSection = false;
    static String user = null, password = null, level = null;
    static int stock[] = {
            10,10,20,20,10,
            10,40,20,
            20,10,24,
            23,41,22

    };
    static String prescrip[] = {
            "acarbose","acetazolamide","acetylcysteine","albumin","allopurinol",
            "bacitracin","biotin","boron",
            "calcitriol", "calcium acatate", "captoprill",
            "dabigatran", "desloratadine", "dextrose"
    };

    static String member[][] = {
            {"Kamidi","ngopilur", "admin"},
            {"Sucipto","jalanjalan", "user"},
            {"Ahmad","alhamdulillah", "user"},
            {"Siti","bersyukur", "user"},
            {"Chiizu","kanojodesu", "user"},
    };

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
                            System.out.println("By " + user);
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

    static void login(){
        String username,pass;

        user = null;
        password = null;

        System.out.print("Username : ");
        username = sc.nextLine();
        System.out.print("Password : ");
        pass = sc.nextLine();

        for(int i = 0 ; i < member.length ; i++){
            if (member[i][0].equals(username)){
                user = member[i][0];
                if (member[i][1].equals(pass)){
                    password = member[i][1];
                    if (member[i][2] == "admin") {
                        level = "admin";
                    }else {
                        level = "user";
                    }
                }else {
                    password = null;
                }
            }
        }

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
            if (level == "admin"){
                System.out.println("Menu");
                System.out.println("1. Add Stock");
                System.out.println("2. Back to Menu");
                System.out.print("Choose (1/2) : ");
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                        addStock();
                        prescripData();
                        break;
                    case 2:
                        sectionDataPres = false;
                        mainSection = true;
                        break;

                    default:
                        System.out.println("Please choose between 1-2");
                        sectionDataPres = true;
                }
            }else{
                System.out.println("Menu");
                System.out.println("1. Back to Menu");
                System.out.print("Choose (1) : ");
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                        sectionDataPres = false;
                        mainSection = true;
                        break;

                    default:
                        System.out.println("Please choose 1 for exit");
                        sectionDataPres = true;
                }
            }

        }while (sectionDataPres == true);
    }

    static void memberData(){
        int choice;
        boolean sectionMember = false;

        line();
        System.out.println("Member Data");
        line();

        do {
            System.out.printf("|");
            System.out.printf("%-2s", "No");
            System.out.printf("%-2s", "|");
            System.out.printf("%-15s","Username");
            System.out.printf("%-2s","|");
            System.out.printf("%-15s","Password");
            System.out.printf("%-2s","|");
            System.out.printf("%-7s","level");
            System.out.println("|");

            if (member[0] == null){
                System.out.printf("|");
                System.out.printf("%-2s", "-");
                System.out.printf("%-2s", "|");
                System.out.printf("%-15s","null");
                System.out.printf("%-2s","|");
                System.out.printf("%-15s","null");
                System.out.printf("%-2s","|");
                System.out.printf("%-7s","null");
                System.out.println("|");
            }else {
                int num = 1;
                for (int i = 0;i< member.length;i++){
                    if (member[i] != null){
                        System.out.printf("|");
                        System.out.printf("%-2s", num);
                        System.out.printf("%-2s", "|");
                        System.out.printf("%-15s",member[i][0]);
                        System.out.printf("%-2s","|");
                        System.out.printf("%-15s",member[i][1]);
                        System.out.printf("%-2s","|");
                        System.out.printf("%-7s",member[i][2]);
                        System.out.println("|");
                        num++;
                    }
                }
            }
            System.out.println("Menu");
            System.out.println("1. Edit Member");
            System.out.println("2. Delete Member");
            System.out.println("3. Back to Menu");
            System.out.print("Choose (1-3) : ");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    editMember();
                    memberData();
                    break;
                case 2:
                    deleteMember();
                    memberData();
                    break;
                case 3:
                    sectionMember = false;
                    mainSection = true;
                    break;
                default:
                    System.out.println("Please choose between (1-3) ");
                    sectionMember = true;
            }
        }while (sectionMember == true);

    }

    static void editMember(){
        int arrLeng,a,choice,level;
        String username,pass;
        boolean chooseEdit = false,chooseLevel = false;
        arrLeng = member.length;
        System.out.print("Choose the number of Member (1-"+ arrLeng +") : ");
        a = sc.nextInt();

        int num = 1;

        if (a < arrLeng){
            System.out.println("Please choose between (1 - " + arrLeng + ") : ");

        }else {
            for (int i = 0 ; i < arrLeng ; i++){
                if (a == num){
                    System.out.printf("|");
                    System.out.printf("%-2s", "No");
                    System.out.printf("%-2s", "|");
                    System.out.printf("%-15s","Username");
                    System.out.printf("%-2s","|");
                    System.out.printf("%-15s","Password");
                    System.out.printf("%-2s","|");
                    System.out.printf("%-7s","level");
                    System.out.println("|");

                    System.out.printf("|");
                    System.out.printf("%-2s", a);
                    System.out.printf("%-2s", "|");
                    System.out.printf("%-15s",member[i][0]);
                    System.out.printf("%-2s","|");
                    System.out.printf("%-15s",member[i][1]);
                    System.out.printf("%-2s","|");
                    System.out.printf("%-7s", member[i][2]);
                    System.out.println("|");

                    do {
                        System.out.println("Menu");
                        System.out.println("1. Change Username");
                        System.out.println("2. Change Password");
                        System.out.println("3. Change Level");
                        System.out.print("Choose (1-3) : ");
                        choice = sc.nextInt();

                        switch (choice){
                            case 1:
                                System.out.print("Enter new Username : ");
                                username = sc.nextLine();
                                member[i][0] = username;
                                break;
                            case 2:
                                System.out.print("Enter new Password : ");
                                pass = sc.nextLine();
                                member[i][1] = pass;
                                break;
                            case 3:
                                do {
                                    System.out.println("Choose new Level");
                                    System.out.println("1. Admin");
                                    System.out.println("2. User");
                                    System.out.print("Choose (1-2) : ");
                                    level = sc.nextInt();
                                    if (level == 1){
                                        member[i][2] = "Admin";
                                    } else if (level == 2) {
                                        member[i][2] = "User";
                                    } else {
                                        System.out.println("Please choose between (1-2) : ");
                                        chooseLevel = true;
                                    }
                                }while (chooseLevel == true);
                                break;
                            default:
                                System.out.println("Please choose between (1-3)");
                                chooseEdit = true;
                        }
                    }while (chooseEdit == true);

                }
                num++;
            }
        }
    }

    static void deleteMember(){

    }

    static void addStock(){
        int a,addStock,arrLeng;
        arrLeng = prescrip.length;
        System.out.print("Choose the number of prescription (1-"+ arrLeng +") : ");
        a = sc.nextInt();

        int num = 1;
        if(a > arrLeng){
            System.out.println("Please choose between (1 - " + arrLeng + ") : ");
        } else {
            for (int i = 0;i < prescrip.length;i++){
                if (a == num){
                    System.out.print("Enter stock : ");
                    addStock = sc.nextInt();
                    stock[i] += addStock;
                }
                num++;
            }
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

        line();
        System.out.println("          WELCOME TO PHARMACY APK");
        line();

        do {
            System.out.println("--- Lhogin ---");
            login();

            if (user != null && password != null && level == "user"){
                do {
                    line();
                    System.out.println("Welcome " + user);
                    line();
                    System.out.println("Menu");
                    System.out.println("1. Prescription Data");
                    System.out.println("2. Cashier");
                    System.out.println("3. Logout");
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
                            loginSection = true;
                            mainSection = false;
                            break;
                        case 4:
                            mainSection = false;
                            loginSection = false;
                            break;
                        default:
                            System.out.println("Please choose between 1-4");
                            mainSection = true;
                    }

                }while (mainSection == true);

            }else if(user != null && password != null && level == "admin"){
                do {
                    line();
                    System.out.println("Welcome " + user + " (Admin)");
                    line();
                    System.out.println("Menu");
                    System.out.println("1. Prescription Data");
                    System.out.println("2. Member Data");
                    System.out.println("3. Logout");
                    System.out.println("4. Exit");
                    System.out.print("Choose (1/2/3/4) : ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice){
                        case 1:
                            prescripData();
                            break;
                        case 2:
                            memberData();
                            break;
                        case 3:
                            loginSection = true;
                            mainSection = false;
                            break;
                        case 4:
                            mainSection = false;
                            loginSection = false;
                            break;
                        default:
                            System.out.println("Please choose between 1-4");
                            mainSection = true;
                    }
                }while (mainSection == true);

            } else if (user == null) {
                System.out.println("| Username not found! |");
                loginSection = true;
            } else if (password == null && user != null) {
                System.out.println("| Password wrong! |");
                loginSection = true;
            } else {
                System.out.println("| Lhogin error! |");
                loginSection = true;
            }
        }while (loginSection == true);

    }
}