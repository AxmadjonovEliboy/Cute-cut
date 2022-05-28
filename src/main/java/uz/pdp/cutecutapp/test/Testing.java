package uz.pdp.cutecutapp.test;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Axmadjonov Eliboy on Mon 14:52. 23/05/22
 * @project cute-cut-app
 */
public class Testing {
    @SneakyThrows
    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime2 = localDateTime.plusDays(1);
        LocalDateTime localDateTime1 = localDateTime.plusMinutes(3);
        System.out.println(localDateTime1);

        long between = ChronoUnit.MINUTES.between(localDateTime2,localDateTime1);
        System.out.println("between = " + between);
        //11 14

//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
//        String strDate = formatter.format(date);
//
//
//        Date firstDate = formatter.parse(strDate);
//        Date secondDate = formatter.parse("15:25");
//        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
//        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
//        System.out.println(diff);

    }
}
