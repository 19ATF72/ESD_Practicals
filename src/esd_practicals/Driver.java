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
public class Driver {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input the length of trinagle side A: ");
        int triangleSideA = scanner.nextInt();
        System.out.println("Please input the length of trinagle side B: ");
        int triangleSideB = scanner.nextInt();
        System.out.println("Please input the length of trinagle side C: ");
        int triangleSideC = scanner.nextInt();

        if(triangleSideA == 0 || triangleSideB == 0 || triangleSideC == 0 )
        {
            System.out.println("All sides must have a length.");
        }
           
        Triangle triangle = new Triangle(triangleSideA, triangleSideB, triangleSideC);

        String answer = triangle.getTriangleType();

        System.out.println(answer);

    }

}