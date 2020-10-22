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
    private double triangleSideA;
    private double triangleSideB;
    private double triangleSideC;
    
   
    public  Triangle(double tringle_side_a, double tringle_side_b, double tringle_side_c)
    {
        triangleSideA = tringle_side_a;
        triangleSideB = tringle_side_b;
        triangleSideC = tringle_side_c;
    }
    
    public String getTriangleType()
    {
        if(triangleSideA == triangleSideB 
                && triangleSideA == triangleSideC
                && triangleSideB == triangleSideC
                && triangleSideB == triangleSideA)
        {
            return "The triangle is a equilateral";
        }
        else if(triangleSideA != triangleSideB 
                && triangleSideA != triangleSideC
                && triangleSideB != triangleSideC
                && triangleSideB != triangleSideA)
        {
            return "The triangle is a scalene";
        }
        else
        {
            return "The triangle is a isosceles";
        }
    }
    
    
}
