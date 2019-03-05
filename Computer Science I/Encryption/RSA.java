/**
 * @version 1.1
 * @author Bailey Erickson and Domenico Galati
 */
import java.util.*;
import java.math.BigInteger;
/**
* This is the main class that has the RSA encrypter and decrypter
*
*/
public class RSA {
    BigInteger d;
    BigInteger e;
    BigInteger n;
    BigInteger m;
     /**
     * The GenerateKeys method creates the algorithim to decrypt and encrypt
     * @param Len length for BigInteger's length parameter
     */
    void GenerateKeys(int Len){ //The method GenerateKeys should implement steps 1 to 5 from the Wikipedia page
        Random ran = new Random();
    	BigInteger p = new BigInteger(Len, 100, ran);
    	BigInteger q = new BigInteger(Len, 100, ran);
        n=p.multiply(q);
        BigInteger One = new BigInteger("1");
    	m = (p.subtract(One)).gcd(q.subtract(One));                     //Finds Greatest Common Divisor
    	m = (p.subtract(One)).multiply(q.subtract(One)).divide(m);      //Finds Least Common Multiple from GCD
        do{
        e = new BigInteger(Len, 100, ran);
   	}while((e.compareTo(m)<0) && (e.compareTo(One)>0) && (m.mod(e).equals(0)));
        d = e.modInverse(m);   
    }
     /**
     * The Encrypt method executes the algorithim to encrypt
     * @param number the message number to encrypt
     * @return the encrypted method
     */
    BigInteger Encrypt(BigInteger number){
        return number.modPow(e, n);
    }
     /**
     * The Decrypt method executes the algorithim to decrypt
     * @param number the message number to decrypt
     * @return the decryted message
     */
    BigInteger Decrypt(BigInteger number){
        return number.modPow(d, n);
    }
    
}
