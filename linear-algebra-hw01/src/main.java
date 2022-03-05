import java.util.Scanner;

public class main {
	 
	// print the matrix
	static void PrintMatrix(double a[][], int n)
	{
		// round to two decimal places
	    for (int i = 0; i < n; i++)
	    {
	        for (int j = 0; j <= n; j++) 
	            System.out.print(Math.round(a[i][j] * 100.0) / 100.0 + " ");
	        System.out.println();
	    }
	}
	 
	// function to reduce matrix to reduced
	// row echelon form
	// use flag to record the answer type
	static int PerformOperation(double a[][], int n)
	{
	    int k = 0, c, flag = 0;
	     
	    // Performing elementary operations
	    for (int i = 0; i < n; i++)
	    {
	    	// if diagonal = 0
	        if (a[i][i] == 0)
	        {
	            c = 1;
	            // check if a[i,i+1,...i+n][i] are 0
	            while ((i + c) < n && a[i + c][i] == 0)
	                c++;        
	            // if it is the last row
	            if ((i + c) == n)
	            {
	            	// then there is an unique answer
	                flag = 1;
	                break;
	            }
	            //swap with down
	            for (int j = i; k <= n; k++)
	            {
	            	double temp =a[j][k];
	                a[j][k] = a[j+c][k];
	                a[j+c][k] = temp;
	            }
	        }
	 
	        for (int j = 0; j < n; j++)
	        { 
	            // excluding all i == j
	            if (i != j)
	            {
	                // converting matrix to reduce row
	                // echelon form (diagonal matrix)
	            	double p = a[j][i] / a[i][i];
	 
	                for (k = 0; k <= n; k++)                
	                    a[j][k] = a[j][k] - (a[i][k]) * p;            
	            }
	        }
	    }
	    return flag;
	}
	 
	// To check whether infinite solutions
	// exists or no solution exists
	static int CheckConsistency(double a[][], int n, int flag)
	{
	    int i, j;
	    double sum;
	     
	    // flag == 3 for No solution
	    flag = 3;
	    for (i = 0; i < n; i++)
	    {
	        sum = 0;
	        for (j = 0; j < n; j++)    
	            sum = sum + a[i][j];
		    // flag == 2 for infinite solution
	        if (sum == a[i][j])
	            flag = 2;    
	    }
	    return flag;
	}
	
	// print the result
	// print if unique solutions exists, no solution or infinite solutions
	static void PrintResult(double a[][], int n, int flag)
	{
	    System.out.print("Answer : ");
	 
	    if (flag == 2)    
		    System.out.println("Infinite Solutions Exists");
	    else if (flag == 3)    
	    	System.out.println("No Solution Exists");
	    else 
	        for (int i = 0; i < n; i++)      
	        {
	            System.out.print( Math.round((a[i][n] / a[i][i]) * 100.0) / 100.0  +" ");    
	        }
	}
	 
	// main class
	// let user enter their matrix
	public static void main(String[] args)
	{
		//user input the row and column

		System.out.println("Enter the row and column for the matrix: ");
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		int row = Integer.valueOf(scanner1.next());
		int column = Integer.valueOf(scanner2.next());
		
		double a[][] = new double [row][column+1];
		
		//user input matrix
		System.out.println("Enter the "+row+"x"+column+" matrix: ");
		
		Scanner scanner3 = new Scanner(System.in);
		
		for(int i=0; i<a.length; i++) 
		{
			for(int j=0; j<a[i].length; j++) 
				a[i][j] = Double.valueOf(scanner3.next());
			System.out.print("");
		}
		
		System.out.println();
	    
	    // Order of Matrix(n)
	    int n = row, flag = 0;
	     
	    // Performing Matrix transformation
	    flag = PerformOperation(a, n);
	     
	    if (flag == 1)
	        flag = CheckConsistency(a, n, flag);
	 
	    // print final matrix
	    System.out.println("The final augmented matrix : ");
	    PrintMatrix(a, n);
	    System.out.println("");
	     
	    // print solutions(if exist)
	    PrintResult(a, n, flag);
	}
	
}
