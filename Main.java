import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  private static int p; // prime number being tested
  private static int c; // value of c
  private static ArrayList<Integer> A = new ArrayList<Integer>(c); // arrays A being iterated
  private static ArrayList<Integer> B = new ArrayList<Integer>(c); // equal to 2A
  private static boolean found = false; // whether or not there is a counterexample

  private static boolean sumNotZp(ArrayList<Integer> A, ArrayList<Integer> B, int p) {
    // checks each element of Z_p to see if it is created by A+2A
    for (int i = 0; i < p; i++) {
      boolean isInSum = false;
      for (int j : A) {
        for (int k: B) {
          if ((j + k) % p == i) {
            isInSum = true;
            break;
          }
        }
        if (isInSum) {
          break;
        }
      }
      if (!isInSum) {
        return true;
      }
    }
    return false;
  }

  private static void recursive(ArrayList<Integer> A, ArrayList<Integer> B, int p, int r, int j) {
    // r is the recursive level
    for (int i = j + 1; i < p; i++) {
      A.add(r, i);
      B.add(r, (2 * i) % p);
      if (r + 1 < c) {
        recursive(A, B, p, r + 1, i);
      } else {
        if (sumNotZp(A, B, p)) {
          found = true;
          if (found) {
            System.out.print("A: ");
            for (int a : A) {
              System.out.print(a + " ");
            }
            System.out.println();
          }
        }
      }
      A.remove(r);
      B.remove(r);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    p = scanner.nextInt();
    scanner.close();
    c = (p - 1) / 2;
    recursive(A, B, p, 0, -1);
    if (!found) {
      System.out.println("No examples found");
    }
  }
}
