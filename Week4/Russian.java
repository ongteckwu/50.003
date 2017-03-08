package Week4;

public class Russian {	
	public static int multiply (int m, int n) {
		int toReturn = 0;
		
		while (n > 0) {
			if (n%2 == 1) {
				toReturn += m;
			}
			
			m = m*2; 
			n = n/2;
		}
		
		return toReturn;
	}
}
