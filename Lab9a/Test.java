import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ForkJoinPool;

public class Test {
  public static void print(String label, long time) {
    System.out.println(label + " Time:\t" + time + "ns\t" + (time / 1000000000.0) + "s");
  }
  public static void main(String[] args) {
    final ThreadLocalRandom random = ThreadLocalRandom.current();
    // max is 2048
    int size = 512;
    // ForkJoinPool fjp = new ForkJoinPool();
    System.out.println("Testing with dimension " + size);
    Matrix m1 = Matrix.generate(size, () -> random.nextDouble(0, 1025));
    Matrix m2 = Matrix.generate(size, () -> random.nextDouble(0, 1025));
    // Matrix m1 = Matrix.generate(size, () -> 2.0);
    // Matrix m2 = Matrix.generate(size, () -> 3.0);
    // System.out.println(m1);
    long start = System.nanoTime();
    Matrix result1 = Matrix.recursiveMultiply(m1, m2);
    long test1time = System.nanoTime() - start;
    // System.out.println("Sequential Time:\t" + test1time);
    print("Seq", test1time);
    // System.out.println(result1);

    start = System.nanoTime();
    // Matrix result2 = Matrix.parallelMultiply(m1, m2);
    Matrix result2 = ForkJoinPool.commonPool().invoke(new MatrixMultiplication(m1, m2, 0, 0, 0, 0, m1.dimension));
    long test2time = System.nanoTime() - start;

    // System.out.println("Parallel Time:\t\t" + test2time + "ns " + (test2time / 1000000000) + "s");
    print("Parallel", test2time);
    // System.out.println(result2);
    long diff = test1time - test2time;
    print("Diff", diff);
    System.out.println("Speed up:\t" + (test1time / (double) test2time));
    System.out.println(Matrix.equals(result1, result2));
    // System.out.println(result1.equals(result2));
    // m1 = Matrix.generate(4, () -> 2.0);
    // m2 = Matrix.generate(4, () -> 3.0);
    // // System.out.println(m1);
    // result = Matrix.parallelMultiply(m1, m2);
    // System.out.println(result);
  }
}
