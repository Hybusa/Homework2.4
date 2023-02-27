import Enums.InputTypes;
import Exceptions.PasswordMismatchException;
import Exceptions.WrongLoginException;
import Exceptions.WrongPasswordException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String login = getInput(InputTypes.LOGIN);
        String password = getInput(InputTypes.PASSWORD);
        String passwordRepeat = getInput(InputTypes.PASSWORD_REPEAT);

        System.out.println(loginAndPasswordInputAndCheck(login, password, passwordRepeat));

    }


    private static boolean loginAndPasswordInputAndCheck(String login, String password, String repeatPassword) {

        try {
            checkSyntax(login, InputTypes.LOGIN);
            checkSyntax(password, InputTypes.PASSWORD);
            checkSyntax(repeatPassword, InputTypes.PASSWORD);
            checkPasswordMatch(password,repeatPassword);
        }catch (RuntimeException e)
        {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        return true;
    }

    private static String getInput(InputTypes inputType) {
        Scanner userInput = new Scanner(System.in);
        String returnString = null;

        switch (inputType) {
            case LOGIN:
                System.out.println("Enter Login:");
                if (userInput.hasNextLine()) {
                    returnString = userInput.nextLine();
                }
                break;
            case PASSWORD:
                System.out.println("Enter Password:");
                if (userInput.hasNextLine()) {
                    returnString =  userInput.nextLine();
                }
                break;
            case PASSWORD_REPEAT:
                System.out.println("Repeat Password:");
                if (userInput.hasNextLine()) {
                    returnString =  userInput.nextLine();
                }
                break;
        }
        return returnString;
    }

    private static void checkPasswordMatch(String password, String repeatPassword) {
        if(!password.equals(repeatPassword))
        {
            throw new PasswordMismatchException("Passwords must match");
        }
    }

    private static void checkSyntax(String input, InputTypes inputType) {
        if (!input.matches("^[a-zA-Z0-9_.]*$") || input.length() > 20) {
            switch (inputType) {
                case LOGIN:
                    throw new WrongLoginException("Login must contain only Letters Number and _ ," +
                            " or must be less then 20 symbols");
                case PASSWORD:
                    throw new WrongPasswordException("Password must contain only Letters Number and _" +
                            " or must be less then 20 symbols");
            }
        }
    }

}