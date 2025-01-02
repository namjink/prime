import java.util.*;
import java.util.stream.IntStream;

public class PrimeUtil {

    public int[] getPrimes(int size) {
        if (size < 1) throw new IllegalArgumentException("size must be greater than 0");
        int[] primes = new int[size];
        int idx = 1;
        primes[0] = 2;
        int now = 3;

        while (idx < size) {
            boolean isPrime = true;
            int limit = (int) Math.sqrt(now);
            for (int i = 0; i < idx && primes[i] <= limit; i++) {
                if (now % primes[i] == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes[idx++] = now;
            }
            now += 2;
            if (now % 3 == 0) now += 2;
        }
        return primes;
    }

    public int[] getPrimesBetween(int start, int end) {
        if (start > end) throw new IllegalArgumentException("start must be less than end");
        if (end < 2) throw new IllegalArgumentException("end must be greater than 1");

        boolean[] isPrime = new boolean[end + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int i = 2; i * i <= end; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= end; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return IntStream.range(start, end+1)
                .filter(i -> isPrime[i])
                .toArray();
    }

    public int[] getPrimesBelow(int maximum) {
        return getPrimesBetween(0, maximum);
    }

    public boolean isPrime(int n) {
        if (n < 1) throw new IllegalArgumentException("n must be greater than 0");
        if (n == 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        int limit = (int) Math.sqrt(n);
        for (int i = 5; i <= limit; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public int countPrime(int number) {
        return getPrimesBelow(number).length;
    }

    public int getNthPrime(int n) {
        return getPrimes(n)[n-1];
    }

    public int countTwins(int n) {
        if (n == 2) return 1;
        if (n == 3 || n == 5 || n == 7) return 3;
        if (!isPrime(n)) return 0;
        if (isPrime(n - 2) || isPrime(n + 2) ) return 2;
        return 1;
    }

    public List<Integer> getPrimeFactors(int n) {
        if (n < 2) return Collections.emptyList();
        if (n == 2) return List.of(n);

        List<Integer> primes = new ArrayList<>();

        while (n % 2 == 0) {
            primes.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            while (n % i == 0) {
                primes.add(i);
                n /= i;
            }
        }

        if (n > 2) {
            primes.add(n);
        }

        return primes;
    }

    public int getGCD(int a, int b) {
        while (b != a) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int getGCD_binary(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        if ((a & 1) == 0 && (b & 1) == 0) return getGCD_binary(a >> 1, b >> 1) << 1;
        else if ((a & 1) == 0) return getGCD_binary(a >> 1, b);
        else if ((b & 1) == 0) return getGCD_binary(a, b >> 1);
        else return getGCD_binary(Math.abs(a - b), Math.min(a,b));
    }

    public int getLCM(int a, int b) {
        if (a > Integer.MAX_VALUE / b) throw new IllegalArgumentException("Overflow");
        return ((a*b)/getGCD(a,b));
    }
}
