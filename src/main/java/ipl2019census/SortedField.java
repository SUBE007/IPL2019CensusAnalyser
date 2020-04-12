package ipl2019census;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {
    public enum Field {
         AVERAGE,STRIKE_RATE,SIXES_AND_FOURS,CENTUARY, FOURS, HALFCENTUARY, HIGHSCORE, SIX, RUN;
    }
    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    SortedField() {}

    public static Comparator getField(SortedField.Field field)
    {
        Comparator<MostRunCSV> averageComparator = Comparator.comparing(census -> census.avg);
        Comparator<MostRunCSV> strikeRateComparator = Comparator.comparing(census -> census.strikeRate);
        sortFieldComparator.put(Field.AVERAGE, averageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, strikeRateComparator);
        sortFieldComparator.put(Field.SIXES_AND_FOURS, new FieldComparator());

        Comparator<MostRunCSV> csvComparator = sortFieldComparator.get(field);
        return csvComparator;
    }
}
