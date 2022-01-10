import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public class MyClass1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> surName = new ArrayList<>();
        ArrayList<String> dateOfBirth = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        String filePath = "/home/cmdq9/Documents/MyClass2/userdata.txt";

        while(true){
            int menuOption = getOption();
            if(menuOption == 1){
                (name, surName, emailmail, dateOfBirth, filePath);
            }else if(menuOption == 2){
                updateUser(name, surName, emailmail, dateOfBirth, filePath);
            }else if(menuOption == 3){
                (name, surName, emailmail, dateOfBirth, filePath);
            }
            else if(menuOption == 4){
                listUsers(name, surName, emailmail, dateOfBirth, filePath);
            }else if(menuOption == 5){
                break;
            }
        }

    }
    public static int getOption(){
        Scanner sc = new Scanner(System.in);
        int option;
        while(true){

            System.out.println("1: AddUser");
            System.out.println("2: UpdateUser");
            System.out.println("3: DeleteUser");
            System.out.println("4: ListUser");
            System.out.println("5: Exit");
            System.out.print("Select an option: ");
            try{
                option = sc.nextInt();
                break;
            }catch(Exception e){
                System.out.println("invalid option");
                sc.next();
            }
        }
        return option;
    }
    public static int update(){
        Scanner sc = new Scanner(System.in);

        int option;
        while(true){

            System.out.println("1: Name");
            System.out.println("2: Surname");
            System.out.println("3: Email");
            System.out.println("4: Date of birth");
            System.out.println("5: All Details");

            System.out.print("Select an option : ");
            try{
                option = sc.nextInt();
                if(option >= 1 && option <= 5){
                    break;
                }

            }catch(Exception e){
                System.out.println("Invalid Option");
                sc.next();
            }
        }
        return option;
    }
    public static void addUser(ArrayList<String> name, ArrayList<String> surname, ArrayList<String> emails,
                               ArrayList<String> dateOfBirth, String filePath) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<String>> userDatabase = new ArrayList<ArrayList<String>>();
        try {
            userDatabase = JSONSerializationHelper.deserializeFromFile(filePath, ArrayList.class);
        }catch (Exception e){
            System.out.print("");
        }finally {
            if (userDatabase.size() == 0) {
                userDatabase.add(name);
                userDatabase.add(surname);
                userDatabase.add(emails);
                userDatabase.add(dateOfBirth);
            } else {
                name = userDatabase.get(0);
                surname = userDatabase.get(1);
                emails = userDatabase.get(2);
                dateOfBirth = userDatabase.get(3);
            }
            String userName = getUserName();
            name.add(userName);
            String userSurname = getUserSurname();
            surname.add(userSurname);
            String userEmail = getUserEmail();
            emails.add(userEmail);
            String dob = getUserDOB();
            dateOfBirth.add(dob);
            JSONSerializationHelper.serialiseToFile(userDatabase, filePath);
            int year = Integer.parseInt(dob.split("/")[2]);
            int age = 2021 - year;
            System.out.println("Hello " + userName + " " + userSurname +"you are "+age +"years old"+"your details have been saved.");

        }
    }
    public static void updateUser(ArrayList<String> name, ArrayList<String> surname, ArrayList<String> emails,
                                  ArrayList<String> dateOfBirth, String filePath) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<String> > userDatabase;
        Scanner sc = new Scanner(System.in);
        int updateOption;
        String userName;
        String userSurname;
        String userEmail;
        String dob;
        try{
            userDatabase = JSONSerializationHelper.deserializeFromFile(filePath, ArrayList.class);
            name = userDatabase.get(0);
            surname = userDatabase.get(1);
            emails = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
            lengthOfList(emails);
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            int check = emails.size();
            for(int i = 0; i < emails.size(); i++){
                if(email.equalsIgnoreCase(emails.get(i))){
                    System.out.println("Name: "+ name.get(i) + " Surname: "
                            + surname.get(i) + " Email: " +
                            emails.get(i) + " DOB " +
                            dateOfBirth.get(i));
                    check--;
                    updateOption = update();
                    if(updateOption == 1){
                        userName = getUserName();
                        name.set(i, userName);
                    }else if(updateOption == 2){
                        userSurname = getUserSurname();
                        surname.set(i, userSurname);
                    }else if(updateOption == 3){
                        userEmail = getUserEmail();
                        emails.set(i, userEmail);
                    }else if(updateOption == 4){
                        dob = getUserDOB();
                        dateOfBirth.set(i, dob);
                    }else if(updateOption == 5){
                        userName = getUserName();
                        name.set(i, userName);
                        userSurname = getUserSurname();
                        surname.set(i, userSurname);
                        userEmail = getUserEmail();
                        emails.set(i, userEmail);
                        dob = getUserDOB();
                        dateOfBirth.set(i, dob);
                    }
                    JSONSerializationHelper.serialiseToFile(userDatabase, filePath);
                }
            }
            if(check == emails.size()){
                System.out.println("User not found");
            }
        }catch(Exception e){
            System.out.println("No users available");
        }
    }
    public static void deleteUser(ArrayList<String> name, ArrayList<String> surname, ArrayList<String> emails,
                                  ArrayList<String> dateOfBirth, String filePath) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<String> > userDatabase;
        Scanner sc = new Scanner(System.in);
        try{
            userDatabase = JSONSerializationHelper.deserializeFromFile(filePath, ArrayList.class);
            name = userDatabase.get(0);
            surname = userDatabase.get(1);
            emails = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
            lengthOfList(emails);
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            int temp = emails.size();
            for(int i = 0; i < emails.size(); i++){
                if(email.equalsIgnoreCase(emails.get(i))){
                    String tempName = name.get(i);
                    String tempsurname = surname.get(i);
                    name.remove(i);
                    surname.remove(i);
                    emails.remove(i);
                    dateOfBirth.remove(i);
                    System.out.println("User "+ tempName + " " + tempsurname+ " has been deleted");
                    i--;
                }
                JSONSerializationHelper.serialiseToFile(userDatabase, filePath);
            }if(temp == emails.size()){
                System.out.println("User not found!");
            }
        }catch(Exception e){
            System.out.println("No users available");
        }
    }
    public static void listUsers(ArrayList<String> name, ArrayList<String> surname, ArrayList<String> emails,
                                 ArrayList<String> dateOfBirth, String filePath) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<String> > userDatabase;
        try{
            userDatabase = JSONSerializationHelper.deserializeFromFile(filePath, ArrayList.class);
            name = userDatabase.get(0);
            surname = userDatabase.get(1);
            emails = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
            lengthOfList(emails);
            for(int i = 0; i < name.size(); i++){
                System.out.println(name.get(i) + " " + surname.get(i) + " " + emails.get(i) + " " + dateOfBirth.get(i));
            }

        }catch (Exception e){
            System.out.println("No users available");
        }
    }
    public static String getUserName(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            try{
                validateName(name);
                return name;
            }catch(Exception e){
                System.out.println("Name Invalid!");
            }
        }
    }
    public static String getUserSurname(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Enter your surname: ");
            String surname = sc.nextLine();
            try{
                validateSurname(surname);
                return surname;
            }catch(Exception e){
                System.out.println("Surname Invalid!");
            }
        }
    }
    public static String getUserEmail(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            try{
                validateEmail(email);
                return email;
            }catch(Exception e){
                System.out.println("Email Invalid!");
            }
        }
    }
    public static String getUserDOB(){
        Scanner sc = new Scanner(System.in);
        String dob;
        int yearCheck;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        while(true){
            System.out.print("Enter your date of birth(dd/MM/yyyy): ");
            dob = sc.nextLine();
            try{
                Date date = dateFormat.parse(dob);
                if(dob.length() == 10){
                    yearCheck = Integer.parseInt(dob.split("/")[2]);
                    if(yearCheck < 2021){
                        return dob;
                    }else{
                        System.out.println("Cannot be born in years to come");
                    }
                }else{
                    System.out.println("Date format not valid");
                }
            }catch(Exception e){
                System.out.println("Date of birth is not valid");
            }
        }
    }

    public static void validateSurname(String surnameCheck) throws ThrowException{
        if(!Pattern.matches("^[a-zA-Z]+$",surnameCheck)){
            throw new ThrowException("");
        }
    }
    public static void lengthOfList(ArrayList<String> arrayList) throws ThrowException{
        ArrayList<String> userNames = arrayList;
        if(userNames.size() == 0){
            throw new ThrowException("");


        }
    }
    public static void validateEmail(String emailCheck) throws ThrowException{
        if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
                emailCheck)){
            throw new ThrowException("");
        }
    }
    public static void validateName(String nameCheck) throws ThrowException{
        if(!Pattern.matches("^[a-zA-Z]+$",nameCheck)){
            throw new ThrowException("");

        }
    }
}
