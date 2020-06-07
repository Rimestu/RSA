package RSA;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your plaintext!");
        BigInteger msg = BigInteger.valueOf(scanner.nextInt());
        int p = new RandomPrimeInt().getRandom();
        int q = new RandomPrimeInt().getRandom();
        BigInteger n = BigInteger.valueOf(p * q);
        int fn = (p-1)*(q-1);
        int e = 0;
        int d = 0;
        for (int i = 3; i < fn; i++){
            e = i;
            if (GCD.gcd(e,fn)==1)
                break;
        }
        for(int i=0;i<=9;i++)
        {
            int x = 1+(i*fn);
            if(x%e==0)
            {
                d=x/e;
                break;
            }
        }
        System.out.println("p = " + p + " and q = " + q + " n = " + n + " fn = " + fn + " e = " + e + " d = " + d);
        System.out.println("Do you want to encrypt(E) or decrypt(D) " + msg + "?");
        String path = scanner.next();
        if (path.equals("E")){
            BigInteger c = msg.modPow(BigInteger.valueOf(e),n);
            System.out.println("Encrypted message (c) is " + c);
            BigInteger m = c.modPow(BigInteger.valueOf(d),n);
            System.out.println("Decrypted message of (c = " + c + ") is " + m);
        }else if (path.equals("D")){
            System.out.println("Enter value for d");
            d = scanner.nextInt();
            System.out.println("Enter value for n");
            n = BigInteger.valueOf(scanner.nextInt());
            System.out.println("Enter value for e");
            e = scanner.nextInt();
            BigInteger m = msg.modPow(BigInteger.valueOf(d),n);
            System.out.println("Decrypted message with public key d(" + d + ") and n(" + n + ") is equal to " + m);
            BigInteger c = m.modPow(BigInteger.valueOf(e),n);
            System.out.println("Encrypted message of (m = " + m + ") is " + c);
        }else {
            System.out.println("Wrong input");
        }
    }
}
