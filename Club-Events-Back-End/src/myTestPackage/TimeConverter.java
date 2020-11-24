package myTestPackage;

import java.util.*;
import java.text.*;

public class TimeConverter {
	
    public String convertTime(long epoch_time) {
        Date date = new Date(epoch_time*1000);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("IST"));
        String formatted = format.format(date);
        return formatted;
    }
}