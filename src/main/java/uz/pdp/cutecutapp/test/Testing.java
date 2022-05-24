package uz.pdp.cutecutapp.test;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Axmadjonov Eliboy on Mon 14:52. 23/05/22
 * @project cute-cut-app
 */
public class Testing {
    @SneakyThrows
    public static void main(String[] args) {


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String strDate = formatter.format(date);


        Date firstDate = formatter.parse(strDate);
        Date secondDate = formatter.parse("15:25");
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diff);

    }
}
