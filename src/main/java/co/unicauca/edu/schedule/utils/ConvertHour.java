package co.unicauca.edu.schedule.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertHour {

    public Date stringToDateH(String hora) throws ParseException {
        Date inicial = new SimpleDateFormat("HH:mm").parse(hora.trim());

        return inicial;
    }
    public Date stringToDateD(String fecha) throws ParseException {
        Date inicial = new SimpleDateFormat("MM-dd-yyyy").parse(fecha);

        return inicial;
    }
}
