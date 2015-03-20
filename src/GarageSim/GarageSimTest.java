package GarageSim;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * I certify that this whole program is completely a work of mine and of no
 * other person(s).
 *
 * @author Jahkell Lazarre
 *
 * COP 3530 - Data Structures (Section U01) 
 * Assignment #2 - FIU Garage Simulation 
 * Completed: February 7th, 2015
 */
public class GarageSimTest {

    public static void main(String[] args) throws Exception {

        GarageSimulation sim = new GarageSimulation();

        String input = JOptionPane.showInputDialog("Welcome to FIU GarageSim!\n"
                + "Type the directory path or filename of your input file here.");

        Scanner scan = new Scanner(new File(input));

        String status;
        String licensePlate;

        while (scan.hasNext()) {

            status = scan.next();
            licensePlate = scan.next();

            sim.interpretCar(status, licensePlate);

            System.out.println(sim.toString());

            sim.checkWaitingLine();

        }

    }

}
