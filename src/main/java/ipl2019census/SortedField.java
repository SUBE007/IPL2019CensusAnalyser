package ipl2019census;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {
    public enum Field {
        AVERAGE,STRIKE_RATE,SIXES_AND_FOURS,CENTUARY, FOURS, HALFCENTUARY, HIGHSCORE, SIX, RUN,
        SIX_AND_FOUR_WITH_STRIKERATE,GREATAVG_WITH_STRIKERATE,MAXRUN_WITH_GREATAVG;
    }
    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    SortedField() {}

    public static Comparator getField(SortedField.Field field)
    {
        Comparator<MostRunCSV> averageComparator = Comparator.comparing(census -> census.avg);
        Comparator<MostRunCSV> strikeRateComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<MostRunCSV> maxrunComparator = Comparator.comparing(census -> census.run);
        sortFieldComparator.put(Field.AVERAGE, averageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, strikeRateComparator);
        sortFieldComparator.put(Field.SIXES_AND_FOURS, new FieldComparator());
        sortFieldComparator.put(Field.MAXRUN_WITH_GREATAVG, maxrunComparator.thenComparing(averageComparator));
        Comparator<MostRunCSV> csvComparator = sortFieldComparator.get(field);
        return csvComparator;
    }
}
