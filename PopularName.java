/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package popularname;

import java.io.File;    //imports the file class to perform file operations
import java.io.FileNotFoundException;   //imports this class to throw an error when the file is not found
import java.util.ArrayList;    //imports the util package to be able to use arraylist
import java.util.Scanner;     //imports the scanner class to read from files and console
/**
 *
 * @author melihkaanmetek
 */
public class PopularName {
    private String name;    //variable defined for the name information
    private int rank;   //variable defined for the rank information
    private double percentage;  //variable defined for the percentage information
    private int number; //variable defined for the count (amount) information
 
    public PopularName(int rank, String name, int number) {//parameterized constructor
        this.rank = rank; //assigns the incoming rank value to the class's rank field
        this.name = name; //assigns the incoming name value to the class's name field
        this.number = number; //assigns the incoming count value to the class's number field
    }
    public String getName() {//gets the name value
        return name;        //and returns it
    }
    public void setName(String name) {
        this.name = name;       //method that allows the name value to be set
    }
    public int getRank() {  //gets the rank value
        return rank;        //and returns it
    }
    public void setRank(int rank) {
        this.rank = rank;       //method that allows the rank value to be set
    }
    public double getPercentage() {//gets the percentage value
        return percentage;      //and returns it
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage; //method that allows the percentage value to be set
    }
    public int getNumber() {//gets the count value
        return number;      //and returns it
    }
    public void setNumber(int number) {
        this.number = number;//method that allows the count value to be set
    }
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //created a scanner object to read input from the user
        Scanner fileScanner = null; //assigned no value initially so it starts as empty
 
        while (fileScanner == null) {       //loops until a valid file is found
            System.out.print("Enter file name: ");  //asks the user for the file name during the loop
            String filename = input.nextLine(); //reads the file name from the user
            try {
                fileScanner = new Scanner(new File(filename));//tries to open the file
            } catch (FileNotFoundException e) {     //condition for when the file cannot be found
                System.out.println("File not found. Try again.");   //warning shown when the file is not found
            }
        }
 
        ArrayList<PopularName> males = new ArrayList<>();   //arraylist for male names
        ArrayList<PopularName> females = new ArrayList<>(); //arraylist for female names
        int total = 0;  //total variable created to be able to calculate the percentage value
                        //holds the sum of all babies
        while (fileScanner.hasNextLine()) {     //loops as long as there is a next line in the file
            String line = fileScanner.nextLine().trim(); //reads the line and trims whitespace
            if (line.isEmpty()) continue; //skips empty lines
            String[] parts = line.split(","); //splits each line by commas
            if (parts.length == 5) {        //processes only well-formed rows with 5 fields
                try {
                    int rank = Integer.parseInt(parts[0].trim());// parses the rank field as an integer
                    int male = Integer.parseInt(parts[2].trim());// parses the male count field as an integer
                    int female = Integer.parseInt(parts[4].trim());// parses the female count field as an integer
 
                    males.add(new PopularName(rank, parts[1].trim(), male));// adds the male name and count to the list
                    females.add(new PopularName(rank, parts[3].trim(), female));// adds the female name and count to the list
                    total += male + female; //adds both male and female counts to the total baby count
                } catch (NumberFormatException e) { //catches malformed numeric data
                    System.out.println("Skipping malformed line: " + line); //informs the user about the bad line
                }
            }
        }
        fileScanner.close(); //closes the file scanner since we are done reading
 
        if (males.isEmpty() || females.isEmpty() || total == 0) { //guards against empty or fully invalid files
            System.out.println("No valid data found in the file. Exiting."); //tells the user the file had no usable data
            input.close(); //closes the input scanner before exiting
            return; //terminates the program safely
        }
 
        for (PopularName p : males) {       //iterates over the male list using a for-each loop
            p.setPercentage((double) p.getNumber() * 100 / total);//computes the popularity percentage of the name
        }
        for (PopularName p : females) {     //iterates over the female list using a for-each loop
            p.setPercentage((double) p.getNumber() * 100 / total);//computes the popularity percentage of the name
        }
 
        bubbleSort(males);  //sorts the male names alphabetically
        bubbleSort(females);//sorts the female names alphabetically
        showSummary(males,females);//shows the summary of the most popular names from the sorted lists
 
        while (true) {      //continues looping until the user chooses no
            System.out.print("\nContinue for searching? (yes/no): ");//asks the user whether they want to continue
            String choice = input.nextLine();   //reads the user's answer
 
            if (choice.equalsIgnoreCase("yes")) { //if the user typed yes (case-insensitive)
                System.out.print("Gender (male/female): ");
                String gender = input.nextLine(); //asks the user for the gender
 
                if (gender.equalsIgnoreCase("male")) {//if the user typed male (case-insensitive)
                    displayStats(males, input); //passes the male list and the entered name to the method
                } else if (gender.equalsIgnoreCase("female")) {//if the user typed female (case-insensitive)
                    displayStats(females, input);//passes the female list and the entered name to the method
                } else {
                    System.out.println("Invalid gender. Try again.");//returns to the start if input is neither male nor female
                }
            } else if (choice.equalsIgnoreCase("no")) {//if the user typed no (case-insensitive)
                break;  //exits the loop
            } else {
                System.out.println("Invalid response. Try again.");//warns the user about an invalid response
            }
        }
        input.close(); //closes the input scanner before the program ends
      }
    public static void bubbleSort(ArrayList<PopularName> babieslist) { // bubble sort algorithm to sort names alphabetically
    for (int i = 0; i < babieslist.size() - 1; i++) {   //outer loop traverses the whole list
        for (int j = 0; j < babieslist.size() - i - 1; j++) { //inner loop performs the comparisons
            if (babieslist.get(j).getName().compareToIgnoreCase(babieslist.get(j + 1).getName()) > 0) {
                //if the current name comes after the next one alphabetically, swap them
                PopularName temp = babieslist.get(j); //assigns the element to a temporary variable
                babieslist.set(j, babieslist.get(j + 1));   //performs the swap
                babieslist.set(j + 1, temp);
                }
            }
        }
    }
    public static void displayStats(ArrayList<PopularName> list, Scanner input) { //method that displays info about the entered name
        System.out.print("\nEnter a name: ");   //asks the user for a name
        String inputName = input.nextLine();    //reads the name from the user
        for (PopularName p : list) {     //scans the list
            if (p.getName().equalsIgnoreCase(inputName)) {  //if the name is found (case-insensitive)
                System.out.println("\n" + p.getName()
                        + ":\nPosition in sorted list: " + (list.indexOf(p) + 1)
                        + "\nPopularity rank: " + p.getRank()
                        + "\nNumber of babies: " + p.getNumber()
                        + "\nPercentage: " + String.format("%.2f", p.getPercentage()) + "%");   //prints the info in order
                return; //exits the method
                }
            }
        System.out.println("\nName couldn't be found: " + inputName);  //if the name is not found, shows the entered name with a not-found message
        }
    public static void showSummary(ArrayList<PopularName> males, ArrayList<PopularName> females) {
        System.out.println("\n--- GENERAL STATISTICS ---"); //heading shown for the general statistics
        int totalBoys = 0;      //sum of male babies
        int totalGirls = 0;     //sum of female babies
        for (PopularName boy : males) {     //iterates over the male list
            totalBoys += boy.getNumber();   //accumulates the total
        }
        for (PopularName girl : females) {  //iterates over the female list
         totalGirls += girl.getNumber();    //accumulates the total
        }
        System.out.println("Total babies: " + (totalBoys + totalGirls));//prints the total baby count
 
       PopularName mostPopularBoy = males.get(0); //takes the first male name as a starting point for finding the most popular one
            for (PopularName boy : males) {     //iterates over the male list
                if (boy.getNumber() > mostPopularBoy.getNumber()) { //finds the one with the highest count
                mostPopularBoy = boy;   //marks it as the most popular
            }
        }
        PopularName mostPopularGirl = females.get(0); //takes the first female name as a starting point for finding the most popular one
            for (PopularName girl : females) {          //iterates over the female list
                if (girl.getNumber() > mostPopularGirl.getNumber()) {//finds the one with the highest count
                mostPopularGirl = girl; //marks it as the most popular
            }
        }
        System.out.println("\nMost popular male name: " + mostPopularBoy.getName() +
                         " (Rank: " + mostPopularBoy.getRank() +
                         ", Count: " + mostPopularBoy.getNumber() + ")");
        //section that prints the result
        System.out.println("\nMost popular female name: " + mostPopularGirl.getName() +
                         " (Rank: " + mostPopularGirl.getRank() +
                         ", Count: " + mostPopularGirl.getNumber() + ")");
    }
}
