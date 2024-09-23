import java.util.Objects;
import java.util.Scanner;

public class Front {
    /* Global Declarations */
    /* Variables */

    static int charClass;
    static char[] lexeme = new char[100];
    static char[] inputArray; // Need to convert the input string to a character array
    static char nextChar;
    static int lexLen;
    static int i; // for iterating through inputArray
    static String input;
    //static String token;
    static String nextToken;

    static String border = "*".repeat(78);

    /* Character Classes */
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int UNKNOWN = 99;
    static final int EOI = -1;

    /* Tokens */
    static final String ADD_OP = "ADD_OP";
    static final String ASSIGN_OP = "ASSIGN_OP";
    static final String DIV_OP = "DIV_OP";
    //static final String END_KEYWORD = "END_KEYWORD";
    static final String END_OF_INPUT = "END_OF_INPUT";
    static final String IDENT = "IDENT";
    static final String INT_LIT = "INT_LIT";
    static final String LEFT_PAREN = "LEFT_PAREN";
    static final String MULT_OP = "MULT_OP";
    //static final String PROGRAM_KEYWORD = "PROGRAM_KEYWORD";
    static final String RIGHT_PAREN = "RIGHT_PAREN";
    static final String SEMICOLON = "SEMICOLON";
    static final String SUB_OP = "SUB_OP";

    /* Main Driver */
    public static void main(String[] args) {
        /* Input an expression using the keyboard */
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter an expression (Make sure there is no whitespace at the end): ");
        input = scnr.nextLine();

        /* Converts the input string to an array of characters */
        inputArray = input.toCharArray();

        /* Processes the input data */
        if (inputArray.length == 0) { // if nothing was typed
            System.out.println("Error - No input detected");
        } else {
            System.out.println(border);
            getChar();
            do {
                lex();
            } while (!Objects.equals(nextToken, END_OF_INPUT));
            System.out.println("Lexical analysis of the program is complete!");
            System.out.println(border);
        }

        scnr.close();
    }

    /* lookup(char ch) will look up operators, semicolons, and parenthesis and return their respective token. */
    public static String lookup(char ch) {
        addChar();
        switch (ch) {
            case '(':
                nextToken = LEFT_PAREN;
                break;
            case ')':
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                nextToken = ADD_OP;
                break;
            case '-':
                nextToken = SUB_OP;
                break;
            case '*':
                nextToken = MULT_OP;
                break;
            case '/':
                nextToken = DIV_OP;
                break;
            case ';':
                nextToken = SEMICOLON;
                break;
            case '=':
                nextToken = ASSIGN_OP;
                break;
            default:
                nextToken = END_OF_INPUT;
                break;
        }
        return nextToken;
    }

    /* addChar() will add nextChar to lexeme */
    public static void addChar() {
        if (lexLen <= 98) {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        } else {
            System.out.println("Error - lexeme is too long");
        }
    }

    public static void getChar() {
        if (i < inputArray.length) { //Checks if there is another character in the array
            nextChar = inputArray[i]; //Sets nextChar to the index where i currently points to.
            if (Character.isLetter(inputArray[i])) {
                charClass = LETTER;
                i++; //Points to the next character in the array if there is one
            } else if (Character.isDigit(inputArray[i])) {
                charClass = DIGIT;
                i++;
            } else {
                charClass = UNKNOWN;
                i++;
            }
        } else { //If i >= inputArray.length, there are no more characters available.
            charClass = EOI;
        }
    }

    /* getNonBlank() checks to see if the next character is a whitespace and continue to the next if it is. */
    public static void getNonBlank() {
        while (Character.isWhitespace(nextChar))
            getChar();
    }

    /* lex() is the lexical analyzer for a given expression */
    public static String lex() {
        lexLen = 0;
        getNonBlank();
        switch (charClass) {
            /* Parse identifiers */
            case LETTER:
                do {
                    addChar();
                    getChar();
                } while (charClass == LETTER || charClass == DIGIT);
                nextToken = IDENT;
                break;

            /*Parse numbers*/
            case DIGIT:
                do {
                    addChar();
                    getChar();
                } while (charClass == DIGIT);
                nextToken = INT_LIT;
                break;

                /* Parses operators, parenthesis and semicolons */
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;

            /*For the end of the expression*/
            case EOI:
                nextToken = END_OF_INPUT;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'I';
                lexeme[3] = 0;
        }
        System.out.println("Next token is: " + nextToken + ", Next lexeme is " + toString(lexeme));
        return nextToken;
    }

    /* toString(char[] chs) combines the indices of the lexeme array, up to the first empty index, to a string. */
    public static String toString(char[] chs) {
        String arrayToString = "";
        for (char ch : chs) {
            if (ch != 0) {
                arrayToString += ch;
            } else {
                break;
            }
        }
        return arrayToString;
    }
}