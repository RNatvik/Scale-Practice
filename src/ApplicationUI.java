import java.util.Random;

public class ApplicationUI {

    private UserInput userInput;

    private Random randomGenerator;

    private ScaleParser scaleParser;

    private String[] mainMenu = {
            "",
            "*** Main Menu ***",
            "1. List a scale",
            "2. List key",
            "3. List chord",
            "4. Practice",
            "99. Quit",
    };

    private String[] practiceMenu = {
            "",
            "*** Practice menu ***",
            "1. Intervals",
            "2. Notes",
            "3. Scale sequence",
            "99. Return to main menu",
    };

    private String[] intervalsMenu = {
            "",
            "1. Root / Octave",
            "2. Second",
            "3. Third",
            "4. Fourth",
            "5. Fifth",
            "6. Sixth",
            "7. Seventh",
            "99. Return to practice menu",
    };

    public ApplicationUI() {
        this.userInput = new UserInput(".stop");
        this.scaleParser = new ScaleParser();
        this.randomGenerator = new Random();
    }

    public void run() {
        System.out.println("Welcome.");
        System.out.println("This program was made to make practicing intervals and notes in a scale easier");
        this.startMainMenu();
    }

    private void startMainMenu() {
        boolean applicationFinished = false;
        String key = "";
        String mode = "";
        while (!applicationFinished) {
            this.printStringArray(this.mainMenu);
            int choice = this.userInput.getInputInteger(
                    "");
            switch (choice) {
                case 1: //List scale
                    key = requestKey();
                    mode = requestMode();
                    listScale(key, mode);
                    break;

                case 2: //List key
                    key = requestKey();
                    mode = requestMode();
                    this.listKey(key, mode);
                    break;

                case 3: //List chord
                    key = requestKey();
                    mode = requestMode();
                    this.listChord(key, mode);
                    break;

                case 4: //Practice
                    this.startPracticeMenu();
                    break;

                case 99: //Quit application
                    applicationFinished = true;
                    System.out.println("Goodbye");
                    break;

                default:
                    System.out.println(choice + " is not a valid option");
                    break;
            }
        }
    }

    private void startPracticeMenu() {
        boolean finished = false;
        String key;
        String mode;
        while (!finished) {
            this.printStringArray(this.practiceMenu);
            int choice = this.userInput.getInputInteger(
                    "");
            switch (choice) {
                case 1: //Intervals
                    key = requestKey();
                    mode = requestMode();
                    String[] scale = this.scaleParser.getScale(key, mode);
                    this.startIntervalPractice(scale);
                    break;

                case 2: //Notes
                    key = requestKey();
                    mode = requestMode();
                    this.startNotes(key, mode);
                    break;

                case 3: //Scale sequence
                    key = requestKey();
                    mode = requestMode();
                    this.startScaleSequencePractice(key, mode);
                    break;

                case 99: //Return to main menu
                    finished = true;
                    System.out.println("Returning to main menu");
                    break;

                default:
                    System.out.println(choice + " is not a valid option");
                    break;
            }
        }
    }

    private void listScale(String key, String mode) {
        String[] scale = this.scaleParser.getScale(key, mode);
        printStringArray(scale);
    }

    private void listKey(String key, String mode) {
        String[] scale = this.scaleParser.getScale(key, mode);
        String[] chordSequence = this.scaleParser.getChordSequence();
        for (int i = 0; i < chordSequence.length; i++) {
            System.out.println(scale[i] + " (" + chordSequence[i] + ")");
        }
    }

    private void listChord(String key, String mode){
        String[] scale = this.scaleParser.getScale(key, mode);
        System.out.println(scale[0]);
        System.out.println(scale[2]);
        System.out.println(scale[4]);
        System.out.println(scale[6]);
    }

    private void startNotes(String key, String mode) {

    }

    public void startIntervalPractice(String[] scale){
        boolean finished = false;
        int score = 0;
        while (!finished) {
            System.out.println("Your score: " + score);
            this.printStringArray(this.intervalsMenu);
            int randomNumber = randomGenerator.nextInt(scale.length-1);
            System.out.println(scale[0] + " --> " + scale[randomNumber]);
            int choice = this.userInput.getInputInteger("Which interval is this?");
            if ((choice > 0 && choice < 8) || choice == 99) {
                if (choice == randomNumber + 1) {
                    System.out.println("Correct");
                    score++;
                } else if (choice == 99) {
                    finished = true;
                    System.out.println("Returning to practice menu");
                    System.out.println("Your final score was: " + score);
                } else {
                    System.out.println("Wrong, correct answer was: " + (randomNumber + 1));
                    score--;
                }
            } else {
                System.out.println(choice + " is not a valid option");
            }
        }
    }

    private void startScaleSequencePractice(String key, String mode) {
        String[] scale = this.scaleParser.getScale(key, mode);
        int i = 1;
        for (String part : scale) {
            part = part.toLowerCase().trim();
            boolean correct = false;
            while (!correct) {
                String answer = userInput.getInputString("Type " + i + ". note:").toLowerCase().trim();
                if (answer.equals(part)) {
                    correct = true;
                    System.out.println("correct");
                    i++;
                } else {
                    System.out.println(answer + " is incorrect");
                }
            }
        }
    }

    private String requestKey() {
        boolean validKey = false;
        String key = "";
        System.out.println("Available keys:");
        for (String part : scaleParser.getNotes()) {
            System.out.print(part + ", ");
        }
        System.out.println();
        while (!validKey) {
            key = this.userInput.getInputString(
                    "Which key do you want the scale for?").toLowerCase().trim();
            validKey = this.scaleParser.verifyKey(key);
        }
        return key;
    }

    private String requestMode() {
        boolean validMode = false;
        String mode = "";
        while (!validMode) {
            mode = this.userInput.getInputString(
                    "Major or minor scale?").toLowerCase().trim();
            validMode = this.scaleParser.verifyMode(mode);
        }
        return mode;
    }

    private void printStringArray(String[] array) {
        for (String part : array) {
            System.out.println(part);
        }
    }
}
