import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* This program calculates amount of Energy released when mass is converted to
* energy.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class StringStuff {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private StringStuff() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    */

    public static void main(String[] args) {
        try {
            // Creating I/O files; Reader and writer objects
            final File input = new File("input.txt");
            final Scanner reader = new Scanner(input);
            final FileWriter writer = new FileWriter("output.txt");

            // Initializing variables
            String line = "";
            int counter = 0;
            final ArrayList<String> lines = new ArrayList<>();

            while (reader.hasNextLine()) {
                // Getting the next line in the file
                line = reader.nextLine();
                // adding line to lines Arraylist
                lines.add(line);
            }
            // creating an array of strings
            final String[] lineArr = new String[lines.size()];
            // putting the lines into the new array
            for (String place : lines) {
                lineArr[counter] = place;
                counter++;
            }
            // Calling functions
            final String[] blowUps = blowUp(lineArr);
            // now we use the result from blowups to test the max runs in each
            // line.
            final String[] maxRuns = maxRun(blowUps);
            // Writing the blowups to the output file
            for (String result : blowUps) {
                writer.write(result + "\n");
            }
            // Writing the runs to the output file
            for (String run : maxRuns) {
                writer.write(run + " \n");
            }
            writer.close();
        } catch (IOException error) {
            System.out.println("ERROR: " + error);
        }
    }
    /**
    * This function calculates the degrees in fahrenheit given a celsius value.
    *
    * @author  Alex De Meo
    * @version 1.0
    * @param lines Necessary for string manipulation
    * @return The temperature in fahrenheit that was calculated
    * @since   2023/02/08
    */

    public static String[] maxRun(String[] lines) {
        // Creating the array that will be returned to main
        final String[] newLines = new String[lines.length];
        newLines[0] = "\n\nMax Run program output:";
        // variable initialization
        int counter1 = 1;
        int lineCounter = 0;
        // Looping through every line
        for (String line : lines) {
            if (lineCounter != 0) {
                // placeholders and variables
                char lastChar;
                int currRun = 0;
                int topRun = 1;
                int counter2 = 0;
                // Creating a char array to break the line into individual chars
                final char[] characters = line.toCharArray();

                // Setting last char for comparison
                lastChar = characters[0];
                // looping through the current String
                for (char character : characters) {
                    // Checking to ensure that the last place is not
                    // being indexed
                    if (counter2 != characters.length - 1) {
                        // Checking to see if a run is there
                        if (character == lastChar) {
                            currRun++;
                        } else {
                            if (currRun >= topRun) {
                                // Setting the maximum run
                                topRun = currRun;
                            }
                            // Resetting the run
                            currRun = 1;
                        }
                        counter2++;
                        // Setting the last character
                        lastChar = character;
                    } else {
                        // Looking to see if a run exists
                        if (character == lastChar) {
                            currRun++;
                            // Setting the top run
                            if (currRun > topRun) {
                                topRun = currRun;
                            }
                        } else {
                            // Setting the top Run
                            if (currRun > topRun) {
                                topRun = currRun;
                            }
                        }
                    }
                }
                // Adding to the the array that will later be returned to main
                newLines[counter1] = Integer.toString(topRun);
                counter1++;
            }
            lineCounter++;
        }
        return newLines;
    }
    /**
    * This function calculates the degrees in fahrenheit given a celsius value.
    *
    * @author  Alex De Meo
    * @version 1.0
    * @param lines Necessary for string manipulation
    * @return The temperature in fahrenheit that was calculated
    * @since   2023/02/08
    */

    public static String[] blowUp(String[] lines) {
        // The array that will be sent back to main
        final String[] newLines = new String[lines.length + 1];
        newLines[0] = "Blow Up program output:";
        // initialization of more variables
        int counter1 = 1;
        int charNum = 0;
        boolean isNum = false;
        // Looping through the array of lines that was passed to this function
        for (String line : lines) {
            // Variable initialization
            int counter2 = 0;
            String newSentence = "";
            // Breaking the String into a character array, this way it can be
            // more easily manipulated
            final char[] characters = line.toCharArray();
            // ArrayList will be used to hold the new lines
            final ArrayList<String> newLine = new ArrayList<>();
            // Looping through the char array of this line
            for (char character : characters) {
                // Checking to see if its the last place in the array. If It's
                // the last, then you print the character instead of checking
                // for a number value
                if (counter2 != characters.length - 1) {
                    // Checking to see if the character has a numeric value
                    try {
                        // Parsing to int
                        charNum = Integer.parseInt(String.valueOf(character));
                        // If the above parsing works, than this boolean will
                        // help decide what to do with it
                        isNum = true;
                    } catch (NumberFormatException error) {
                        // If an error is caught, then the bool stays as false
                        isNum = false;
                    }
                    if (isNum) {
                        // If it has a numerical value, then we need to loop
                        // through and add the next character that many times
                        for (int i = 0; i < charNum; i++) {
                            // Adding the character to a newLine list
                            newLine.add(
                                String.valueOf(characters[counter2 + 1])
                            );
                        }
                    } else {
                        // adding the character to a newLine list
                        newLine.add(String.valueOf(character));
                    }
                } else {
                    // Adding the character to a newLine list
                    newLine.add(String.valueOf(character));
                }
                counter2++;
            }
            // Iterating through the list
            for (String piece : newLine) {
                // Creating a new String from the places in the List
                newSentence += piece;
            }
            // Adding the new Sentence to the array we are returning to main
            newLines[counter1] = newSentence;
            counter1++;
        }
        // Returning to main
        return newLines;
    }
}
