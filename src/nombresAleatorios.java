import java.util.Random;

public class nombresAleatorios {

    private static final Random RANDOM = new Random();

    public static String string(int size) {
        int leftLimit = 48;
        int rightLimit = 122;

        return RANDOM.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int number(int bound) {
        return (int)Math.floor(Math.random()*(bound-2)+2);
    }

}
