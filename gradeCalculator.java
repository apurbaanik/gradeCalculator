/**
 * @author Anik Barua
 * @since 10-12-2020
 * @version 1.0
 *
 * Description: Lab #6 - This java program is a grade calculator that calculates
 * my total points of this class and returns the letter grade based on the final
 * number. It has a dataExpect() method that takes the letter grade I seek (A,B
 * or C) and returns one possibility of the scores I need on the remaining
 * assignments and exams to get the grade.
 */
import java.io.File;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class gradeCalculator {  //class started

    public static void main(String[] args) throws Exception {
        PrintStream report = new PrintStream("report.txt");
        // Output file that will contain the print statements.

        String file = "grades.csv"; // file that contains my grades

        // Making arrays for each portion of the grade
        int[] practiceProblems = new int[8]; // for practice problems grades
        int[] lab = new int[7]; //for lab grades
        int[] midExam = new int[2]; // for 2 midterm exam
        int[] finalExam = new int[1]; // for the final

        //Using the data extract method I am filling up my array with the grades of each portion
        dataExtract(file, practiceProblems, lab, midExam, finalExam);

        //Using the total() mehtod, I am calculating the total grade for each portion
        double problems = total(practiceProblems);
        double lab1 = total(lab);
        double mid = total(midExam);
        double finale = total(finalExam);

        // total variable will have the total grade of adding all the portions
        double total = problems + lab1 + mid + finale;

        //fullGrade() method returns the letter grade based on my total points
        report.println("Current Letter Grade: " + fullGrade(problems, lab1, mid, finale)
                + " with " + total + " points.\n");

        //gradeExpect() method takes the letter grade you want and returns one possibility
        // of scores you need in the remaining assignments and exams
        report.println(gradeExpect("A", problems, lab1, mid));
        report.println(gradeExpect("B", problems, lab1, mid));
        report.println(gradeExpect("C", problems, lab1, mid));
    }

    /*
    The gradeExpect() method takes the letter grade you seek, current grades on practice
    problems, lab and midterms exams, and returns needed to score on the remaining 
    assignments and exams to get the letter grade. 
     */
    public static String gradeExpect(String x, double pp, double lab, double mid) {
        double remainpp = pp;
        double remainlab = lab;
        double finalExam = 0;
        double total = pp + lab + mid + finalExam;

        // For letter grade A
        if (x.equals("A")) {
            while (total != 90 && total < 90) {
                if (remainpp < 40) {
                    remainpp = remainpp + 6;
                }
                if (remainlab <= 16) {
                    remainlab = remainlab + 2;
                }
                if (finalExam <= 20) {
                    finalExam += 4;
                }
                total = remainpp + remainlab + mid + finalExam;
            }
        }

        // For letter grade C
        if (x.equals("B")) {
            while (total != 80 && total < 80) {
                if (remainpp < 40) {
                    remainpp += 6;
                }
                if (remainlab <= 16) {
                    remainlab += 2;
                }
                if (finalExam <= 20) {
                    finalExam += 4;
                }
                total = remainpp + remainlab + mid + finalExam;
            }
        }

        // For letter grade C
        if (x.equals("C")) {
            while (total != 70 && total < 70) {
                if (remainpp < 40) {
                    remainpp += 6;
                }
                if (remainlab <= 16) {
                    remainlab += 2;
                }
                if (finalExam <= 20) {
                    finalExam += 4;
                }
                total = remainpp + remainlab + mid + finalExam;
            }
        }

        //Remainning points 
        double needOnPP = remainpp - pp;
        double needOnLab = remainlab - lab;
        return "One possibility for " + x + " -> In Practice problems you need more :" + needOnPP
                + " points, in lab :" + needOnLab + " points, and in Final exam: " + finalExam + " points.";
    }

    /*
    The fullGrade() method takes your current grade and return the letter grade using the grade scale.
     */
    public static String fullGrade(double pp, double lab, double midExam, double finalExam) {
        double total = pp + lab + midExam + finalExam;
        if (total >= 90 && total <= 100) {
            return "A";
        } else if (total >= 80 && total <= 89) {
            return "B";
        } else if (total >= 70 && total <= 79) {
            return "C";
        } else if (total >= 60 && total <= 69) {
            return "D";
        } else {
            return "F";
        }
    }

    /*
    The total() methods adds upp all the numbers in the array
     */
    public static double total(int[] arr) {
        double x = 0;
        for (int i = 0; i < arr.length; i++) {
            x = x + arr[i];
        }
        return x;
    }

    /*
    The dataExtract() method reads the grade points from the csv file using a multidimensional array. 
     */
    public static void dataExtract(String x, int[] practiceProblems, int[] lab, int[] midExam, int[] finalExam) {
        //Row and Column number for multi-dimensinal array
        int row = 4;
        int column = 8;

        //Multi-Dimensinal array that will contain grades from the csv file.
        String[][] array = new String[row][column];

        //Read in the csv file part
        try {
            Scanner sc = new Scanner(new File(x));
            for (int i = 0; i < row; i++) {
                String[] line = sc.nextLine().split(",(?=([^\"]|\"[^\"]*\")*$)");
                // Splits words by "," from each line
                for (int j = 0; j < column; j++) {
                    array[i][j] = line[j];
                }
            }
            sc.close(); //Scanner closed
        } catch (FileNotFoundException e) {
            e.getMessage();
        } // End of try and catch block

        // Putting the practiceProblems grades in array
        for (int a = 0; a < practiceProblems.length; a++) {
            practiceProblems[a] = Integer.parseInt((array[0][a]));
        }

        // Putting the lab grades in array
        for (int b = 0; b < lab.length; b++) {
            lab[b] = Integer.parseInt((array[1][b]));
        }

        // Putting the midterm exam grades in array
        for (int c = 0; c < midExam.length; c++) {
            midExam[c] = Integer.parseInt((array[2][c]));
        }

        // Putting the final exam grade in array
        for (int c = 0; c < finalExam.length; c++) {
            finalExam[c] = Integer.parseInt((array[3][c]));
        }

    } //end of dataExtract method

} // end of class gradeCalculator