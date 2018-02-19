/* Put your student number and name here
1640210
Ieva Zadeikyte
 *
 * Optionally, if you have any comments regarding your submission, put them here. 
 * For instance, specify here if your program does not generate the proper output or does not do it in the correct manner.
 */

import java.util.*;
import java.io.*;

// A class that represents a dense vector and allows you to read/write its elements
class DenseVector
{
	private int[] elements;
	
	public DenseVector(int n)
	{
		elements = new int[n];
	}
	
	public DenseVector(String filename)
	{
        File file = new File(filename);
        ArrayList<Integer> values = new ArrayList<Integer>();

        try {
            Scanner sc = new Scanner(file);
        
            while(sc.hasNextInt()){
            		values.add(sc.nextInt());
            }
            
            sc.close();
            
            elements = new int[ values.size() ];
            for(int i = 0; i < values.size(); ++ i)
            {
            		elements[i] = values.get(i);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// Read an element of the vector
	public int getElement(int idx) {
		return elements[idx];
	}
	
	// Modify an element of the vector
	public void setElement(int idx, int value)
	{
		elements[idx] = value;
	}
	
	// Return the number of elements
	public int size()
	{
		return (elements == null) ? 0 : (elements.length);
	}
	
	// Print all the elements 
	public void print()
	{
		if(elements == null) {
			return;
		}
		
		for(int i = 0; i < elements.length; ++ i)
		{
			System.out.println(elements[i]);
		}
	}
}


// A class that represents a sparse matrix
public class SparseMatrix 
{
	// Auxiliary function that prints out the command syntax
	public static void printCommandError()
	{
        System.err.println("ERROR: use one of the following commands");
        System.err.println(" - Read a matrix and print information: java SparseMatrix -i <MatrixFile>");
        System.err.println(" - Read a matrix and print elements: java SparseMatrix -r <MatrixFile>");
        System.err.println(" - Transpose a matrix: java SparseMatrix -t <MatrixFile>");
        System.err.println(" - Add two matrices: java SparseMatrix -a <MatrixFile1> <MatrixFile2>");
        System.err.println(" - Matrix-vector multiplication: java SparseMatrix -v <MatrixFile> <VectorFile>");
	}
	
	
    public static void main(String [] args) throws Exception
    {
        if (args.length < 2) 
        {
        		printCommandError();
        		System.exit(-1);
        }
        
        if(args[0].equals("-i")) 
        {
        		if(args.length != 2) {
            		printCommandError();
            		System.exit(-1);
        		}
        		
        		SparseMatrix mat = new SparseMatrix();
        		mat.loadEntries(args[1]);
        		System.out.println("Read matrix from " + args[1]);
        		System.out.println("The matrix has " + mat.numRows() + " rows and " + mat.numColumns() + " columns");
        		System.out.println("It has " + mat.numNonZeros() + " non-zeros");
        }
        else if(args[0].equals("-r")) 
        {
        		if(args.length != 2) {
            		printCommandError();
            		System.exit(-1);
        		}
        		
        		SparseMatrix mat = new SparseMatrix();
        		mat.loadEntries(args[1]);
        		System.out.println("Read matrix from " + args[1] + ":");
        		mat.print();
        }
        else if(args[0].equals("-t"))
        {
        		if(args.length != 2) {
            		printCommandError();
            		System.exit(-1);
        		}
        		
        		SparseMatrix mat = new SparseMatrix();
        		mat.loadEntries(args[1]);
        		System.out.println("Read matrix from " + args[1]);        		
        		SparseMatrix transpose_mat = mat.transpose();
        		System.out.println();
        		System.out.println("Matrix elements:");
        		mat.print();
        		System.out.println();
        		System.out.println("Transposed matrix elements:");
        		transpose_mat.print();
        }
        else if(args[0].equals("-a"))
        {
        		if(args.length != 3) {
            		printCommandError();
            		System.exit(-1);
        		}
        		
        		SparseMatrix mat1 = new SparseMatrix();
        		mat1.loadEntries(args[1]);
        		System.out.println("Read matrix 1 from " + args[1]);
        		System.out.println("Matrix elements:");
        		mat1.print();
        		
        		System.out.println();
        		SparseMatrix mat2 = new SparseMatrix();
        		mat2.loadEntries(args[2]);
        		System.out.println("Read matrix 2 from " + args[2]);
        		System.out.println("Matrix elements:");
        		mat2.print();
        		SparseMatrix mat_sum1 = mat1.add(mat2);
        		
        		System.out.println();
        		mat1.multiplyBy(2);
        		SparseMatrix mat_sum2 = mat1.add(mat2);
        		
        		mat1.multiplyBy(5);
        		SparseMatrix mat_sum3 = mat1.add(mat2);
        		
        		System.out.println("Matrix1 + Matrix2 =");
        		mat_sum1.print();
        		System.out.println();
        		
        		System.out.println("Matrix1 * 2 + Matrix2 =");
        		mat_sum2.print();
        		System.out.println();
        		
        		System.out.println("Matrix1 * 10 + Matrix2 =");
        		mat_sum3.print();
        }
        else if(args[0].equals("-v"))
        {
        		if(args.length != 3) {
            		printCommandError();
            		System.exit(-1);
        		}
        		
        		SparseMatrix mat = new SparseMatrix();
        		mat.loadEntries(args[1]);
        		DenseVector vec = new DenseVector(args[2]);
        		DenseVector mv = mat.multiply(vec);
        		
        		System.out.println("Read matrix from " + args[1] + ":");
        		mat.print();
        		System.out.println();
        		
        		System.out.println("Read vector from " + args[2] + ":");
        		vec.print();
        		System.out.println();
        		
        		System.out.println("Matrix-vector multiplication:");
        		mv.print();
        }
    }

    
    // Loading matrix entries from a text file
    // You need to complete this implementation
    public void loadEntries(String filename)
    {
        File file = new File(filename);

        try
        {
	        Scanner sc = new Scanner(file);
	        int numRows = sc.nextInt();
	        numCols = sc.nextInt();
	        entries = new ArrayList< ArrayList<Entry> >();
            ArrayList<Integer> allRow = new ArrayList();
            ArrayList<Integer> allCol = new ArrayList();
            ArrayList<Integer> allVal = new ArrayList();
            int step = 0;

	        
	        for(int i = 0; i < numRows; ++ i) {
	        		entries.add(null);
	        }
	        
	        while(sc.hasNextInt())
	        {
	        		// Read the row index, column index, and value of an element
	        		int row = sc.nextInt();
	        		int col = sc.nextInt();
	        		int val = sc.nextInt();
	        		
	        		// Add your code here to add the element into data member entries
                    //entries

                    allCol.add(col);
                    allRow.add(row);
                    allVal.add(val);
                    step++;


	        }
	        // Add your code here if approparite
            int lastRow = -1;
            int fromCol = 0;
            for (int loopstart = 0; loopstart < allCol.size(); loopstart++){
                fromCol = -1;
                int newCol = allCol.get(loopstart);
                ArrayList<Entry> cr = new ArrayList();
                if (lastRow == allRow.get(loopstart)){
                    cr = entries.get(lastRow);
                    fromCol = allCol.get(loopstart-1);

                }
            


                for (int i = fromCol+1; i <= newCol; i++){
                    Entry blank = new Entry(i,0);
                    if (i == newCol){
                        Entry member = new Entry(i,allVal.get(loopstart));
                        cr.add(member);
                    }
                    else{
                        cr.add(blank);
                    }
        
                }
                lastRow = allRow.get(loopstart);
                entries.set(lastRow,cr);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            numCols = 0;
            entries = null;
        }
    }
    
    // Default constructor
    public SparseMatrix()
    {
    		numCols = 0;
    		entries = null;
    }
    
    
    // A class representing a pair of column index and elements
    private class Entry
    {
    		private int column;	// Column index
    		private int value;	// Element value
    		
    		// Constructor using the column index and the element value
    		public Entry(int col, int val)
    		{
    			this.column = col;
    			this.value = val;
    		}
    		
    		// Copy constructor
    		public Entry(Entry entry)
    		{
    			this(entry.column, entry.value); 
    		}
    		
    		// Read column index
    		int getColumn() 
    		{
    			return column;
    		}
    		
    		// Set column index
    		void setColumn(int col)
    		{
    			this.column = col;
    		}
    		
    		// Read element value
    		int getValue()
    		{
    			return value;
    		}
    		
    		// Set element value
    		void setValue(int val)
    		{
    			this.value = val;
    		}
    }
    
    // Adding two matrices  
    public SparseMatrix add(SparseMatrix M)
    {   // print() prints the first array if you do M.print() prints second array.
        int numRows = entries.size();
        for(int i = 0; i < numRows; i++)
        {
            ArrayList<Entry> currentRow1 = entries.get(i);
            ArrayList<Entry> currentRow2 = M.entries.get(i);
            int currentCol = -1;
            if(currentRow1 != null && (!currentRow1.isEmpty())) {
                currentCol = currentRow1.get(i).getColumn();
            }
            
            for(int j = 0;  j < numCols; ++ j) {
                int value1 = 0;
                int value2 = 0;
                int added = 0;
                if (currentRow1.get(j) != null){
                     value1 = currentRow1.get(j).getValue();
                     System.out.println(j + " 1: " + value1);
                }
                if (currentRow2.get(j) != null){
                     value1 = currentRow2.get(j).getValue();
                     System.out.println(j + " 2: " + value2);
                }
                if (value1 != 0 || value2 != 0){
                    added = (value1) + (value2);
                    System.out.println(j + " 3: " + added);
                    Entry newValue = new Entry(j, (added));
                    currentRow2.set(j,newValue);
                }       
            }
        }


        return M;
    }
    
    // Transposing a matrix
    public SparseMatrix transpose()
    {
		// Add your code here

        return null;
    }
    
    // Matrix-vector multiplication
    public DenseVector multiply(DenseVector v)
    {
    		// Add your code here
        return v;
    }
    
    // Count the number of non-zeros
    public int numNonZeros()
    {
        int numNoZero = 0;
        int numRows = entries.size();
        for(int i = 0; i < numRows; i++)
        {
            ArrayList<Entry> currentRow = entries.get(i);
            int currentCol = -1, entryIdx = -1;
            if(currentRow != null && (!currentRow.isEmpty())) {
                entryIdx = 0;
                currentCol = currentRow.get(entryIdx).getColumn();
            }
            
            for(int j = 0;  j < numCols; ++ j) {
                if(j == currentCol) {
                    if (currentRow.get(entryIdx).getValue() != 0){
                        numNoZero++;
                    }
                    entryIdx++;
                    currentCol = (entryIdx < currentRow.size()) ? currentRow.get(entryIdx).getColumn() : (-1); 
                    
                }
            }

        }
        return numNoZero;
    }
    
    // Multiply the matrix by a scalar, and update the matrix elements
    public void multiplyBy(int scalar)
    {
    		// Add your code here
    }
    
    // Number of rows of the matrix
    public int numRows()
    {
    		if(this.entries != null) {
    			return this.entries.size();
    		}
    		else {
    			return 0;
    		}
    }
    
    // Number of columns of the matrix
    public int numColumns()
    {
    		return this.numCols;
    }
    
    // Output the elements of the matrix, including the zeros
    // Do not modify this method
	public void print()
	{
		int numRows = entries.size();
		for(int i = 0; i < numRows; ++ i)
		{
			ArrayList<Entry> currentRow = entries.get(i);
			int currentCol = -1, entryIdx = -1;
			if(currentRow != null && (!currentRow.isEmpty())) {
				entryIdx = 0;
				currentCol = currentRow.get(entryIdx).getColumn();
			}
			
			for(int j = 0;  j < numCols; ++ j) {
				if(j == currentCol) {
					System.out.print(currentRow.get(entryIdx).getValue());
					entryIdx++;
					currentCol = (entryIdx < currentRow.size()) ? currentRow.get(entryIdx).getColumn() : (-1); 
				}
				else {
					System.out.print(0);
				}
				
				System.out.print(" ");
			}
			
			System.out.println();
		}
	}
    
    private int numCols;		// Number of columns
    private ArrayList< ArrayList<Entry> > entries;	// Entries for each row
}
