import java.util.Scanner;

/**
 * Class for handling input from user.
 * Filters out unwanted responses from user.
 *
 * @author Ruben Natvik <r_bn-95@hotmail.com>
 * Marcus Olai Grindvik <marcusgrindvik@gmail.com>
 * Vebjørn Wille <vebjorn_wille@hotmail.com>
 * @version v1.0
 * @since v1.0
 */
public class UserInput {

    private String interruptWord;
    private Scanner scanner;

    public UserInput(String interruptWord) {
        this.scanner = new Scanner(System.in);
        this.interruptWord = interruptWord;
    }

    /**
     * Gets user input as integer
     *
     * @param messageToUser a message to the user about what information the system wants
     * @return user input as int
     */
    public int getInputInteger(String messageToUser) {
        boolean intFound = false;
        int integer = 0;
        System.out.println(messageToUser);
        while (!intFound) {
            if (this.scanner.hasNextInt()) {
                String value = this.scanner.nextLine().trim();
                while (value.equals("")) {
                    value = this.scanner.nextLine().trim();
                }
                integer = Integer.parseInt(value);
                intFound = true;
            } else {
                this.scanner.nextLine();
                System.out.println("Invalid input, must be Integer. Try again");
            }
        }
        return integer;
    }

    /**
     * Requests user input as string
     *
     * @param messageToUser a message to the user about what information the system wants
     * @return the user's input as string. Returns "" if interruptWord is typed
     */
    public String getInputString(String messageToUser) {
        String input = "";
        boolean validString = false;
        System.out.println(messageToUser);
        while (!validString) {
            validString = true;
            input = this.scanner.nextLine();
            if (input.equals(this.interruptWord)) {
                input = "";
                System.out.println("'canceled'");
            } else if (input.equals("")) {
                validString = false;
            }
        }
        return input;
    }

    /**
     * Requests user input as string
     *
     * @param messageToUser       a message to the user about what information the system wants
     * @param customInterruptWord a custom interrupt word to cancel input request
     * @return the user's input as string. Returns "" if interruptWord is typed
     */
    public String getInputString(String messageToUser, String customInterruptWord) {
        String input = "";
        boolean validString = false;
        System.out.print(messageToUser);
        if (customInterruptWord.equals("")) {
            System.out.println(" (leave blank to skip):");
        } else {
            System.out.println(" (type : " + customInterruptWord + " to skip):");
        }
        while (!validString) {
            validString = true;
            input = this.scanner.nextLine();
            if (input.equals(customInterruptWord)) {
                input = "";
                System.out.println("skipped " + messageToUser);
            } else if (input.equals("")) {
                validString = false;
            }
        }
        return input;
    }


    /**
     * Requests user confirmation
     *
     * @param messageToUser a message to the user about what information the system wants
     * @return true if user inputs "yes", false if "no" or this.interruptWord.
     */
    public boolean getUserConfirmation(String messageToUser) {
        boolean confirmation = false;
        boolean validInput = false;
        System.out.println(messageToUser + "  (yes || no)");
        while (!validInput) {
            String answer = this.getInputString("").trim().toLowerCase();
            if (answer.equals("yes")) {
                confirmation = true;
                validInput = true;
            } else if (answer.equals("no")) {
                confirmation = false;
                validInput = true;
            } else {
                System.out.println("Invalid input. Type yes or no");
            }
        }
        return confirmation;
    }
}
