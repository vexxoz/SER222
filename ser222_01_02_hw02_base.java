/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * @author (your name), Ruben Acuna
 * @version (version)
 */
public class CaldwellMatrix implements Matrix {
    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        
        Matrix m1 = new CaldwellMatrix(data1);
        Matrix m2 = new CaldwellMatrix(data2);
        Matrix m3 = new CaldwellMatrix(data3);
        Matrix m4 = new CaldwellMatrix(data4);
        
        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
        
        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true
        
        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        
        //not tested... multiply(). you know what to do.
        System.out.println("m2*m3:\n" + m2.multiply(m3));
        
        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 - m2" + m1.minus(m2));
    }
    
    private final int[][] myMatrix;

    public CaldwellMatrix(int[][] matrix) {
    	if(matrix.length > 0) {
	    	myMatrix = new int[matrix.length][matrix[0].length];
	    	for(int x=0;x<matrix.length;x++) {
	    		for(int y=0;y<matrix[0].length;y++) {
	    			myMatrix[x][y]=matrix[x][y];
	    		}
	    	}
    	}else {
    		myMatrix = new int[0][0];
    	}
    }
    
    /**
     * Returns the element at particular point in the matrix.
     * @param x Which row
     * @param y Which Column
     * @return element
     */
    public int getElement(int x, int y) {
    	return myMatrix[x][y];
    }

    /**
     * Returns the number of rows in the matrix.
     * @return rows
     */
    public int getRows() {
    	return myMatrix.length;
    }
    
    /**
     * Returns the number of columns in the matrix.
     * @return columns
     */
    public int getColumns() {
    	if(getRows()>0) {
    		return myMatrix[0].length;
    	}
    	return 0;
    }
            
    /**
     * Returns this matrix scaled by a factor. That is, computes kA where k is a
     * constant and A is a matrix (this object).
     * 
     * @param scalar scalar
     * @return matrix
     */
    public Matrix scale(int scalar) {
    	int[][] returnMatrix = new int[this.getRows()][getColumns()];
    	for(int x=0;x<this.getRows();x++) {
    		for(int y=0;y<this.getColumns();y++) {
    			returnMatrix[x][y]=scalar*getElement(x,y);
    		}
    	}    	
    	return (new CaldwellMatrix(returnMatrix));
    }
    
    /**
     * Returns this matrix added with another matrix. That is, computes A+B 
     * where A and B are matrices (this object, and another respectively).
     * @param other addend
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    public Matrix plus(Matrix other) throws RuntimeException {
		int[][] returnMatrix = new int[this.getRows()][this.getColumns()];
		
		for(int x = 0; x<this.getRows();x++) {
			for(int y=0;y<this.getColumns();y++) {
				returnMatrix[x][y] = this.getElement(x, y)+other.getElement(x, y);
			}
		}
		return (new CaldwellMatrix(returnMatrix));
    }
    
    /**
     * Returns this matrix subtracted by another matrix. That is, computes A-B 
     * where A and B are matrices (this object, and another respectively).
     * @param other subtrahend
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */    
    public Matrix minus(Matrix other) throws RuntimeException{
		int[][] returnMatrix = new int[this.getRows()][this.getColumns()];
		if(this.getRows() == other.getRows() && this.getColumns() == other.getColumns()) {
			for(int x = 0; x<this.getRows();x++) {
				for(int y=0;y<this.getColumns();y++) {
					returnMatrix[x][y] = this.getElement(x, y)-other.getElement(x, y);
				}
			}
		}
		return new CaldwellMatrix(returnMatrix);
    }
    
    /**
     * Returns this matrix multiplied by another matrix. That is, computes AB 
     * where A and B are matrices (this object, and another respectively).
     * @param other multiplicand
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    public Matrix multiply(Matrix other) throws RuntimeException{
		int[][] returnMatrix = new int[this.getRows()][this.getColumns()];
		if(this.getRows() == other.getColumns() && this.getColumns() == other.getRows()) {
			for(int x = 0; x<this.getRows();x++) {
				for(int y=0;y<this.getColumns();y++) {
					for(int z = 0;z<this.getColumns();z++) {
						returnMatrix[x][y] += this.getElement(x,z)+other.getElement(z, y);
					}
					
				}
			}    	
		}
		return new CaldwellMatrix(returnMatrix);
    }
    
    
    /**
     * Returns true if this matrix matches another matrix.
     * @param other another matrix
     * @return equality
     */
    @Override
    public boolean equals(Object other) {
    	if(other != null && other instanceof Matrix) {
        	Matrix newMatrix = (Matrix)other;
        	if(this.getRows() == newMatrix.getRows() && this.getColumns() == newMatrix.getColumns()) {
               	for(int x=0;x<getRows();x++) {
            		for(int y=0;y<getColumns();y++) {
            			if(this.getElement(x, y) != newMatrix.getElement(x, y)) {
            				return false;
            			}
            		}
            	}
               	return true;        		
        	}
    	}
    	return false;

    }
    
    /**
     * Returns a string representation of this matrix. A new line character will
     * separate each row, while a space will separate each column.
     * @return string representation
     */
    @Override
    public String toString() {
    	String returnString = "";
       	for(int x=0;x<getRows();x++) {
    		for(int y=0;y<getColumns();y++) {
    			returnString += myMatrix[x][y] + " ";
    		}
    		returnString += "\n";
    	}
       	return returnString;
    }
}