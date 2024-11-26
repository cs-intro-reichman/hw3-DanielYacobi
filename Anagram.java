
/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		
		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String s1 = preProcess(str1); //preprocessed version of str1
		String s2 = preProcess(str2); //preprocessed version of str2
		int l1 = s1.length();
		int l2 = s2.length();
		if (l1 != l2) //anagrams have the same length
			return false;
		String temp = "";
		boolean isSame = false;

		for (int i = 0; i < l1; i++) { //goes over the letters in s1
			for (int k = 0; k < l2; k++) { //goes over the letters in s2
				if ((s1.charAt(i) == s2.charAt(k)) && (!isSame)) //checks if two letters are the same
					isSame = true; 
				else 
					temp = temp + s2.charAt(k);
			}
			if (!isSame) //a letter exists in one string and doesn't in another
				return false; 
			s2 = temp;
			l2 = s2.length();
			temp = "";
			isSame = false;
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {    
		int l = str.length();
		String pro = "";
		for (int i = 0; i < l; i++) {
			if ((str.charAt(i) >= 97 && str.charAt(i) <= 122) || (str.charAt(i) >= 48 && str.charAt(i) <= 57))
				pro = pro + str.charAt(i); //ASCII range of lowercase letters or numbers
			if (str.charAt(i) >= 65 && str.charAt(i) <= 90) //ASCII range of uppercase letters
				pro = pro + ((char)(str.charAt(i) + 32)); //converts to lowercase
			if (str.charAt(i) == ' ');
				pro = pro + str.charAt(i);//preserves spaces
		}
		return pro;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String s = preProcess(str); //preprocessed version of str
		String rando = "";
		String temp = "";
		int num; //will be a random number
		int l = s.length();
		boolean isRemoved = false;
		char c;

		while (l > 0) { //goes over the characters in s until there's no more
			num = (int)(Math.random() * l); //random number 0-l
			c = s.charAt(num); //the letter in the place of the random number
			rando = rando + c;
			for (int i = 0; i < l; i++) { //will remove only one showing of this letter
				if ((s.charAt(i) == c) && !isRemoved) 
					isRemoved = true;
				else
					temp = temp + s.charAt(i);
			}
			s = temp;
			l = s.length();
			temp = "";
			isRemoved = false;
		}
		return rando;
	}
}