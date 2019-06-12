package backend_internal;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;


/**
 * Simple parser based on regular expressions that matches program strings to
 * kinds of language features.
 * @author Robert C. Duvall, Jamie Palka, Jorge Raad
 */
public class Parser {
    public static final String LANGUAGE_FOLDER = "/languages/";
    public static final String SYNTAX_FILE = "Syntax";
    public static final String ENGLISH_FILE = "English";

    public static final String USER_COMMANDS_REGEX = "/user_command_regex";
    public static final String WHITESPACE = "\\s+";
    public static final String START_BRACKET = "[";
    public static final String END_BRACKET = "]";
    public static final String NEW_LINE = "$";

    public static String CURRENT_LANGUAGE = ENGLISH_FILE;

    private List<Entry<String, Pattern>> mySymbols; // "types" and the regular expression patterns that recognize those types
    private InputTypeFactory myFactory;

    /**
     * Creates a parser. Starts off with English as the default language. Also has access to the patterns from Syntax
     * and the patterns from the user-defined commands.
     */
    public Parser() {
        mySymbols = new ArrayList<>();
        myFactory = new InputTypeFactory();
        this.replacePatterns(ENGLISH_FILE); // default language is English
    }

    /**
     * Creates queue that represents the order of user-created input (if pre-defined, just one stack entry).
     * @param input
     * @return inputQueue
     */
    public Queue<Stack<InputType>> createQueue(String input) {
        // Each queue entry (represented by a stack) represents a different line/single input unit. Each "input unit" is comprised of an input type (a command, a variable command, etc.) and its respective parameter (a constant, unless nested). Both parts of an input unit will extend the InputType interface, which represents anything that can be typed by the user.

        Queue<Stack<InputType>> inputQueue = new LinkedList<>();
        String[] inputUnitArray = input.split(NEW_LINE);

        //Assumption - things that depend on each other are all on the same line

        for(int i = 0; i < inputUnitArray.length; i++) {
            String inputUnitString = inputUnitArray[i];
            Stack<InputType> inputUnitStack = createInputUnitStack(inputUnitString);
            inputQueue.add(inputUnitStack);
        }

        return inputQueue;
    }

    /**
     * Creates stack for each individual input unit (which could be a nested command). Converts strings to their respective InputType object.
     * @param inputUnit - a String for one input unit/line
     * @return unitStack - a stack that represents a line/single input unit
     */
    private Stack<InputType> createInputUnitStack(String inputUnit) {
//        System.out.println("Input: " + inputUnit);
        Stack<InputType> inputUnitStack = new Stack<>();
        String[] strings = inputUnit.trim().split(WHITESPACE);

        if(Arrays.asList(strings).contains(START_BRACKET)) {

            return createComplexInputUnitStack(strings);
        }

        for(int i = 0; i < strings.length; i++) {
            String type = getSymbol(strings[i]);
//            System.out.println("Creating: " + strings[i]);
            InputType inputObject = myFactory.createInputType(type, strings[i]);
            inputUnitStack.push(inputObject);
        }

        return inputUnitStack;
    }

    private Stack<InputType> createComplexInputUnitStack(String[] strings) {

        int start_count = 0;
        int end_count = 0;
        Stack<InputType> inputUnitStack = new Stack<InputType>();
        StringBuilder current = new StringBuilder();
        BracketList currentBracketList;

        for(int i = 0; i < strings.length; i++) {

            if(strings[i].equals(START_BRACKET)) {

                // Takes into account nesting
                if(start_count != 0) {
                    current.append(" ").append(strings[i]);
                }

                start_count ++;
            }

            else if(strings[i].equals(END_BRACKET)) {
                end_count ++;

                if(start_count == end_count) {
                    currentBracketList = new BracketList();
                    currentBracketList.setParameter(current.toString());
                    inputUnitStack.add(currentBracketList);
                    start_count = 0;
                    end_count = 0;
                    current = new StringBuilder();
                }

                // Accounts for potential nested bracketLists
                else {
                    current.append(" ").append(strings[i]);
                }
            }

            // Before a bracket list
            else if (start_count == 0) {
                String type = getSymbol(strings[i]);
                InputType inputObject = myFactory.createInputType(type, strings[i]);
                inputUnitStack.push(inputObject);
            }

            else {
                current.append(" ").append(strings[i]);
            }
        }
        return inputUnitStack;

    }

    /**
     * Adds the given resource file to this language's recognized types.
     * @param syntax
     */
    private void addPatterns (String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
            //System.out.println(key + " " + Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }

    /**
     * Replaces the language used to recognize commands with the properties from the given file. The parser would still
     * be able to recognize user-defined commands and all input types from the Syntax file.
     */
    public void replacePatterns(String language){
        mySymbols.clear();
        CURRENT_LANGUAGE = language;
        addPatterns(LANGUAGE_FOLDER + language);
        addPatterns(USER_COMMANDS_REGEX);
        addPatterns(LANGUAGE_FOLDER + SYNTAX_FILE);
    }

    /**
     * Returns language's type associated with the given text if one exists.
     * @param text String - user input
     * @return String input type (command, variable, etc.)
     */
    private String getSymbol (String text) {
        final var ERROR = "NO MATCH";
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }

        return ERROR;
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    // given some text, prints results of parsing it using the given language
    private void parseAndPrint (String[] text) {
        for (var s : text) {
            if (s.trim().length() > 0) {
//                System.out.println(String.format("%s : %s", s, this.getSymbol(s)));
            }
        }
    }

}