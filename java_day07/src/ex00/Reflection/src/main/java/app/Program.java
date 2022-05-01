package app;

import classes.Car;
import classes.User;

public class Program {

    public static void  main(String[] args) {
        System.out.println("Classes:");

        Object[] classes = loadClasses();

        for (Object o : classes) {
            System.out.println("\t - " + o.getClass().getSimpleName());
        }
    }

    private static Object[] loadClasses() {
        Object[]    ret = new Object[2];
        ret[0] = new User();
        ret[1] = new Car();

        return ret;
    }
}
