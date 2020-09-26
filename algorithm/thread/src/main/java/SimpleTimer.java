public class SimpleTimer {
    private static int count;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        count = args.length > 0 ? Integer.valueOf(args[0]) : 60;

        while (count > 0) {
            System.out.print(String.format("\rremaining %d s", count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            count --;
        }
        System.out.println("\nDone!");
        System.out.println("duration: " + (System.currentTimeMillis() - start));
    }
}
