import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author rob
 */
public class ESD_Practicals {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input the length of trinagle side A: ");
        double triangleSideA = scanner.nextDouble();
        System.out.println("Please input the length of trinagle side B: ");
        double triangleSideB = scanner.nextDouble();
        System.out.println("Please input the length of trinagle side C: ");
        double triangleSideC = scanner.nextDouble();

       Triangle triangle = new Triangle(triangleSideA, triangleSideB, triangleSideC);

       String answer = triangle.getTriangleType();

       System.out.println(answer);
    }

}