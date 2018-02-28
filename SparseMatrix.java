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
        // Remove !!!
        else if(args[0].equals("-add"))
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
                System.out.println("Matrix1 + Matrix2 =");
                mat_sum1.print();
                SparseMatrix mat_sum2 = mat1.add(mat2);

            }
            //End remove
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
                    Entry member = new Entry(col,val);
                    ArrayList<Entry> cr = new ArrayList();

                    ArrayList<Entry> currentRow = entries.get(row);
                    if (currentRow != null && (!currentRow.isEmpty())) {
                        cr = currentRow;
                    }

                    cr.add(member);
                    Collections.sort(cr, SparseMatrix.entryCheck);
                    
                    entries.set(row,cr);
                    }  
            

        }
        catch (Exception e) {
            e.printStackTrace();
            numCols = 0;
            entries = null;
        }
    }
    // Creates a new blank matrix of speicified number of rows and columns
    public void loadBlank(int row, int col)
    {
        try
        {
            numCols = col;
            entries = new ArrayList< ArrayList<Entry> >();
            
            for(int i = 0; i < row; ++ i) {
                    entries.add(null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            numCols = 0;
            entries = null;
        }
    }

    // Sorts entries in a row in case of entries being added in a random order
    public static Comparator<Entry> entryCheck = new Comparator<Entry>() {

        public int compare(Entry e1, Entry e2) {

           int entry1 = e1.getColumn();
           int entry2 = e2.getColumn();

           return entry1-entry2;
       }
    };
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
        SparseMatrix tempMatrix = new SparseMatrix();
        int numRows = entries.size();
        tempMatrix.loadBlank(numRows, numCols);
        // Gets each row
        for(int i = 0; i < numRows; i++)
        {   
            int size1 = 0;
            int size2 = 0;

            ArrayList<Entry> tempRow = new ArrayList();
            // Saves row for each matrix
            ArrayList<Entry> currentRow1 = entries.get(i);
            ArrayList<Entry> currentRow2 = M.entries.get(i);
            int currentCol = -1;

            if(currentRow1 != null && (!currentRow1.isEmpty())) {
                size1 = currentRow1.size();
            }
            if(currentRow2 != null && (!currentRow2.isEmpty())) {
                size2 = currentRow2.size();
            }
            
            for(int j = 0;  j < size1; j++) {
                currentCol = currentRow1.get(j).getColumn();
                int added = currentRow1.get(j).getValue();
                Entry newValue = new Entry(currentCol, added);
                tempRow.add(newValue);
            }
            tempMatrix.entries.set(i,tempRow);

            for(int j = 0;  j < size2; j++) {
                currentCol = currentRow2.get(j).getColumn();
                int added = currentRow2.get(j).getValue();
                boolean found = false;
                for(int k = 0;  k < size1; k++) {
                    int currentCol2 = currentRow1.get(k).getColumn();
                    int added2 = currentRow1.get(k).getValue();

                    if (currentCol == currentCol2) {
                        added = added + added2;    
                        Entry newValue = new Entry(currentCol, added);
                        tempRow.set(k, newValue);   
                        found = true;                 
                    }
                    
                }
                if (found == false) {
                    Entry newValue = new Entry(currentCol, added);
                    tempRow.add(newValue);
                }
                
            }
            Collections.sort(tempRow, SparseMatrix.entryCheck);
            tempMatrix.entries.set(i,tempRow);
  
            // Only goes through selected rows
                    
                //Collections.sort(cr, SparseMatrix.entryCheck);
                
            }    



        return tempMatrix;
    }
    
    // Transposing a matrix
    public SparseMatrix transpose()
    {
		// Add your code here
        SparseMatrix tempMatrix = new SparseMatrix();
        int numRows = numCols;
        int cols = entries.size();
        // Creates a blank matrix of correct size
        tempMatrix.loadBlank(numRows, cols);
        
        // Loops through old matrix rows
        for (int j = 0; j < cols; j++){
            
            ArrayList<Entry> cr = entries.get(j);

            if(cr != null && (!cr.isEmpty())) {
                int size = cr.size();

                for (int k = 0;k < size ; k++ ) {
                    int newCol = cr.get(k).getColumn();
                    int newValue = cr.get(k).getValue();
                    ArrayList<Entry> tempRow = new ArrayList<Entry>();
                    // If transposed matrix already has a value, reads it into tempRow
                    if (tempMatrix.entries.get(newCol)!= null) {
                        tempRow = tempMatrix.entries.get(newCol);
                        
                    }
                    // Puts the value from the old matrix to the correct place in new matrix
                    Entry transVal = new Entry (j, newValue);
                    tempRow.add(transVal);
                    tempMatrix.entries.set(newCol,tempRow);

                }
            }       
        }
        return tempMatrix;
    }
    
    // Matrix-vector multiplication
    public DenseVector multiply(DenseVector v)
    {   
        //v.size() gives vector size
        int numRows = entries.size();
        // Output vectore
        DenseVector multiplied = new DenseVector(numRows);
        // loop through each row.
        for(int i = 0; i < numRows; i++){
            // sum all entries multiplied by vector v of same index as column
            ArrayList<Entry> cr = entries.get(i);
            int size = 0;

            if(cr != null && (!cr.isEmpty())) {
               size = cr.size();
            }
            int rowSum = 0;

            for(int j = 0;  j < size; ++ j) {
                int colIndex = cr.get(j).getColumn();
                int valueIn = cr.get(j).getValue();
                rowSum = rowSum + (valueIn * v.getElement(colIndex));
                
            }
            multiplied.setElement(i,rowSum);

        }
        return multiplied;
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
        // Gets each row
        for(int i = 0; i <  entries.size(); i++)
        {   
            int size1 = 0;
            int size2 = 0;

            ArrayList<Entry> tempRow = new ArrayList();
            // Saves row for each matrix
            ArrayList<Entry> currentRow1 = entries.get(i);

            int currentCol = -1;

            if(currentRow1 != null && (!currentRow1.isEmpty())) {
                size1 = currentRow1.size();
            }
                

            // Loads matrix 1 values into temp matrix
            for(int j = 0;  j < size1; j++) {
                int value1 = 0;
                int colValue1 = 0;
                colValue1 = currentRow1.get(j).getColumn();
                value1 = currentRow1.get(j).getValue() * scalar;
                //System.out.println(value1);
                Entry newValue = new Entry(colValue1, value1);
                tempRow.add(newValue);
            }
            entries.set(i,tempRow);
            // end
        }

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
