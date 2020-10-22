/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rob
 */
public class Triangle {
    static int triangleSideA;
    static int triangleSideB;
    static int triangleSideC;
    
   
    public  Triangle(int tringle_side_a, int tringle_side_b, int tringle_side_c)
    {
        triangleSideA = tringle_side_a;
        triangleSideB = tringle_side_b;
        triangleSideC = tringle_side_c;
    }
    
    public String getTriangleType()
    {
        String triangleAnswer; 
        
        if(triangleSideA == triangleSideB 
                && triangleSideA == triangleSideC
                && triangleSideB == triangleSideC)
        {
            triangleAnswer = "The triangle is a equilateral";
        }
        else if(triangleSideA != triangleSideB 
                && triangleSideA != triangleSideC
                && triangleSideB != triangleSideC)
        {
            triangleAnswer = "The triangle is a scalene";
        }
        else
        {
            triangleAnswer = "The triangle is a isosceles";
        }
        
        return triangleAnswer;
    }
   
}
