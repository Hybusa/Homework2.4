import Exceptions.PasswordMismatchException;
import Exceptions.WrongLoginException;
import Exceptions.WrongPasswordException;

import java.util.Scanner;

public class Main {
    private final static int LENGTH = 20;
    public static void main(String[] args) {

        String login = getInput("Enter Login:");
        String password = getInput("Enter Password:");
        String passwordRepeat = getInput("Repeat Password:");

        System.out.println(loginAndPasswordCheck(login, password, passwordRepeat));

    }

    private static boolean loginAndPasswordCheck(String login, String password, String repeatPassword) {

        try {
            checkPassword(password);
            checkLogin(login);
            checkPasswordMatch(password,repeatPassword);
        }catch (RuntimeException e)
        {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        return true;
    }
    private static String getInput(String banner){
        Scanner userInput = new Scanner(System.in);
        System.out.println(banner);
        if (userInput.hasNextLine()) {
            return userInput.nextLine();
        }
        return null;
    }

    private static void checkPassword(String password)
    {
        if(!checkCriteria(password) || !checkLength(password))
            throw new WrongPasswordException("Password must contain only Letters Number and _" +
                    " or must be less then 20 symbols");

    }
    private static void checkLogin(String login)
    {
        if(checkCriteria(login) || !checkLength(login))
            throw new WrongLoginException("Login must contain only Letters Number and _ ," +
                    " or must be less then 20 symbols");

    }

    private static void checkPasswordMatch(String password, String repeatPassword) {
        if(!password.equals(repeatPassword))
        {
            throw new PasswordMismatchException("Passwords must match");
        }
    }
    private static boolean checkCriteria(String password)
    {
        return checkLength(password) && checkSyntax(password);
    }

    private static boolean checkSyntax(String input) {
        return input.matches("^[a-zA-Z0-9_.]*$");
    }

    private static boolean checkLength(String input) {
        return input.length() <= LENGTH;
    }

}