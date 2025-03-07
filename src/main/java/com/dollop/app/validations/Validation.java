package com.dollop.app.validations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	static MessageDigest messageDigest = null;
	static Boolean result = false;
	
	public static Timestamp createDate() {
		return new Timestamp(System.currentTimeMillis());
	}
	 
		/**
		 * Encrypts a password using the MD5 hashing algorithm.
		 * 
		 * This method converts the provided password into a hashed format using MD5. It updates the message 
		 * digest with the password's bytes and then processes it to generate a hash. The hash is converted 
		 * into a hexadecimal string and returned. If an exception occurs (e.g., the MD5 algorithm is not 
		 * available), the stack trace is printed and the method returns null.
		 *
		 * @param password the password to be encrypted
		 * @return the encrypted password as a hexadecimal string; null if an exception occurs
		 */
		public static String encryptPassword(String password)
		{
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(password.getBytes());
				byte[] bytes = messageDigest.digest();
				StringBuffer sb = new StringBuffer();
				for (int i=0;i<bytes.length;i++) {
					sb.append(Integer.toString((bytes[i] & 0xFF)+0x100,16).substring(1)); 
				}
				return sb.toString();
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * Checks if two passwords match.
		 * 
		 * This method compares the provided password and repassword strings. 
		 * If both strings are null, the method returns false. If the two strings are 
		 * equal, the method returns true; otherwise, it returns false.
		 *
		 * @param password the first password to compare
		 * @param repassword the second password to compare
		 * @return true if the passwords match; false otherwise
		 */
		public static Boolean conformPassword(String password,String repassword)
		{
			if (password == null && repassword == null) {
	            return false;
	        }
			result = false;
			if(password.equals(repassword))
			{
				result = true;
			}
			return result;			
		}
		
		/**
		 * Validates if an password is in the correct format.
		 * 
		 * This method checks if the provided password is non-null and matches the following format:
		 * - Contains at least one digit
		 * - Contains at least one lowercase letter
		 * - Contains at least one uppercase letter
		 * - Does not contain any whitespace
		 * - Has a length between 8 and 20 characters
		 * 
		 * If the password matches all these format, the method returns true. Otherwise, it returns false.
		 *
		 * @param password the password to validate
		 * @return true if the password matches the format; false otherwise
		 */
		public static Boolean passwordValidation(String password) {
			if (password == null) {
	            return false;
	        }
	        String regex = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20})";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
		}
		
		/**
		 * Validates if an email address is in the correct format.
		 * 
		 * This method checks if the provided email is non-null and matches the regular expression 
		 * for a basic email format. The regular expression ensures that the email contains an '@' 
		 * symbol with characters before and after it.
		 * 
		 * If the email matches all these format, the method returns true. Otherwise, it returns false.
		 *
		 * @param email the email to validate
		 * @return true if the email matches the format; false otherwise
		 */
		public static Boolean emailValidation(String email)
		{
			 if (email == null) {
		            return false;
		        }

		        String regex = "^(.+)@(.+)$";
		        Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(email);
		        return matcher.matches();
		}
		
		/**
		 * Validates if an mobile number is in the correct format.
		 * 
		 * This method checks if the provided mobile number is non-null and matches the following format:
		 * - Length is 10 numbers
		 * - Contains only numbers.
		 * 
		 * If the email matches all these format, the method returns true. Otherwise, it returns false.
		 *
		 * @param email the email to validate
		 * @return true if the email matches the format; false otherwise
		 */
		public static Boolean mobileNumberValidation(String contact)
		{	    
		    if (contact == null) {
	            return false;
	        }

	        String regex = "^[6789]\\d{9}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(contact);
	    
	        System.out.println("CONTACT: "+matcher.matches());
	        return matcher.matches();
		}
		
		public static Boolean numberValidation(String number)
		{	    
			 return number != null && number.matches("^\\d+(\\.\\d+)?$");
		}

}
