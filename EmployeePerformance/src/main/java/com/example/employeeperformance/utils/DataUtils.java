package com.example.employeeperformance.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    /**
     * Esse metodo vai retornar uma lista com data para os ultimos 6 meses
     * @param date
     * @return
     */
    public static List<LocalDate> getLastSemesterLocalDateList(LocalDate date){
        List<LocalDate> retorno = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            retorno.add(date.minusMonths(i));
        }

        return retorno;
    }
}
