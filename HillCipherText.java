import java.util.Scanner;

public class HillCipherText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String plain_text;
		String key;
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Plain Text to be Encrypt");
		plain_text=scan.nextLine();
		System.out.println("Enter key to Encrypt");
		key=scan.nextLine();
		int key_key_matrix[][]=new int[plain_text.length()][plain_text.length()];
		int plain_text_key_matrix[][]=new int[1][plain_text.length()];
		int encrypted_key_matrix[][]=new int[1][plain_text.length()];
		int k=0;
		for(int i=0;i<plain_text.length();i++)
		{
			for(int j=0;j<plain_text.length();j++)
			{
				key_key_matrix[i][j]=key.charAt(k)-97;
				k++;
				
			}
		}
		
		System.out.println("key key_matrix");
		for(int i=0;i<plain_text.length();i++)
		{
			for(int j=0;j<plain_text.length();j++)
			{
				System.out.print(key_key_matrix[i][j]+" ");
				
			}
			System.out.println("");
		}
		
		for(int i=0;i<plain_text.length();i++)
		{
			plain_text_key_matrix[0][i]=plain_text.charAt(i)-97;
		}
		
		System.out.println("plaintext key_matrix");
		for(int i=0;i<plain_text.length();i++)
		{
			System.out.print(plain_text_key_matrix[0][i]+" ");
		}
		System.out.println("");
		
		/*
		 * perform key_matrix multiplication to get resultant key_matrix
		 */
		for(int i=0;i<plain_text.length();i++)
		{
			int sum=0;
			for(int j=0;j<plain_text.length();j++)
			{
				sum=sum+plain_text_key_matrix[0][j]*key_key_matrix[j][i];
				
			}
			encrypted_key_matrix[0][i]=sum%26;
		}
		
		System.out.println("resultant key_matrix");
		for(int i=0;i<plain_text.length();i++)
		{
			System.out.print(encrypted_key_matrix[0][i]+" ");
		}
		
		System.out.println("");
		System.out.print("encrypted text : ");
		
		for(int i=0;i<plain_text.length();i++)
		{
			System.out.print((char)(encrypted_key_matrix[0][i]+96));
		}
		
		/*
		 * find inverse of key key_matrix
		 */
		int inverse_key_matrix[][]=new int[plain_text.length()][plain_text.length()];
		inverse_key_matrix=invert(key_key_matrix,plain_text.length());
		
		System.out.println("inverse_key_matrix");
		for(int i=0;i<plain_text.length();i++)
		{
			for(int j=0;j<plain_text.length();j++)
			{
				System.out.print(inverse_key_matrix[i][j]);
			}
			System.out.println("");
		}
		System.out.println("hey dude");
		/*
		 * perform mod 26 on inverse_key_matrix
		 */
		
		for(int i=0;i<plain_text.length();i++)
		{
			for(int j=0;j<plain_text.length();j++)
			{
				inverse_key_matrix[i][j]=inverse_key_matrix[i][j]%26;
			}
		}
		
		/*
		 * get final multiplication of inverse of key key_matrix and the resultant key_matrix 
		 */
		
		int decrypted_key_matrix[][]=new int[0][plain_text.length()];
		for(int i=0;i<plain_text.length();i++)
		{
			int sum=0;
			for(int j=0;j<plain_text.length();j++)
			{
				sum=sum+encrypted_key_matrix[0][j]*inverse_key_matrix[j][i];
				
			}
			decrypted_key_matrix[0][i]=sum%26;
		}
		
		System.out.println("decrypted key_matrix");
		for(int i=0;i<plain_text.length();i++)
		{
			System.out.print(decrypted_key_matrix[0][i]+" ");
		}
		
		System.out.println("");
		
		System.out.print("decrypted text : ");
		
		for(int i=0;i<plain_text.length();i++)
		{
			System.out.print((char)(decrypted_key_matrix[0][i]+96));
		}
		
		
		
	}
	
	
	public static int[][] invert(int a[][], int n) 
    {
		 
        
        int x[][] = new int[n][n];
        int b[][] = new int[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the key_matrix into an upper triangle
        gaussian(a, index);
       
 // Update the key_matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
       
 // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        
        System.out.println("inside invert function");
        for(int i=0;i<n;i++)
        {
        	for(int j=0;j<n;j++)
        	{
        		System.out.print(x[i][j]);
        	}
        	System.out.println("");
        }
        
        
       
        return x;
        
    }
 
// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
 
    public static int[][] gaussian(int[][] a, int index[]) 
    {
        int n = index.length;
        int c[] = new int[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            int c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                int c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            int pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                int pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                int pj = a[index[i]][j]/a[index[j]][j];
 
 // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
 // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
        return a;
    }

}
