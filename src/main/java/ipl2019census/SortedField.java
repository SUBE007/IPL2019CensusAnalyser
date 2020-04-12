package ipl2019census;

import dao.IPLRecordDAO;
import dao.MostRunCSV;

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
        Comparator<IPLRecordDAO> averageComparator = Comparator.comparing(census -> census.average);
        Comparator<IPLRecordDAO> strikeRateComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLRecordDAO> maxrunComparator = Comparator.comparing(census -> census.runs);
        sortFieldComparator.put(Field.AVERAGE, averageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, strikeRateComparator);
        sortFieldComparator.put(Field.SIXES_AND_FOURS, new FieldComparator());
        sortFieldComparator.put(Field.MAXRUN_WITH_GREATAVG, maxrunComparator.thenComparing(averageComparator));
        Comparator<IPLRecordDAO> csvComparator = sortFieldComparator.get(field);
        return csvComparator;
    }
}
