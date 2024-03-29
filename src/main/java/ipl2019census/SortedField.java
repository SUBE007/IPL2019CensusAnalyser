package ipl2019census;

import dao.IPLRecordDAO;
import dao.MostRunCSV;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {
    public enum Field {
        AVERAGE,STRIKE_RATE,SIXES_AND_FOURS, WKT_WITH_AVG,BATTING_BLOWING_AVG, WICKET_AVERAGE, AVGBOWL_WITH_STRIKERATE,
        SIX_AND_FOUR_WITH_STRIKERATE,GREATAVG_WITH_STRIKERATE,MAXRUN_WITH_GREATAVG,ECONOMY,FIVEWKT_FOURWKT_STRIKERATE,
        RUNBYBATSMAN, RUNBYBOWLER,IPL_BEST_ALLROUNDER,RUN_WITH_AVG;
    }

    public enum IPLEntity {
        BATING, BOWLING
    }

    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    SortedField() {}

    public static Comparator getField(SortedField.Field field)
    {
        Comparator<IPLRecordDAO> avgBatComparator = Comparator.comparing(census -> census.battingAverage);
        Comparator<IPLRecordDAO> avgBolComparator = Comparator.comparing(census -> census.bowlingAverage);
        Comparator<IPLRecordDAO> strikeRateComparator = Comparator.comparing(census -> census.strikeRate);
      //  Comparator<IPLRecordDAO> maxrunComparator = Comparator.comparing(census -> census.runs);
        Comparator<IPLRecordDAO> batRunComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.batsmanRun);
        Comparator<IPLRecordDAO> bowlRunComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.batsmanRun);
        Comparator<IPLRecordDAO> economyComparator = Comparator.comparing(census -> census.economy);
        Comparator<IPLRecordDAO> wktComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.wkts);
        sortFieldComparator.put(Field.AVERAGE, avgBatComparator);
        sortFieldComparator.put(Field.AVERAGE, avgBolComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, strikeRateComparator);
        sortFieldComparator.put(Field.ECONOMY, economyComparator);
        sortFieldComparator.put(Field.WKT_WITH_AVG, wktComparator.thenComparing(avgBolComparator));
        sortFieldComparator.put(Field.SIXES_AND_FOURS, new FieldComparator());
        sortFieldComparator.put(Field.RUN_WITH_AVG, batRunComparator.thenComparing(avgBatComparator));
        sortFieldComparator.put(Field.RUN_WITH_AVG, bowlRunComparator.thenComparing(avgBolComparator));        sortFieldComparator.put(Field.AVGBOWL_WITH_STRIKERATE, avgBolComparator.thenComparing(avgBolComparator));
        sortFieldComparator.put(Field.FIVEWKT_FOURWKT_STRIKERATE,new Sort5WAnd4WComparator().reversed().thenComparing(strikeRateComparator));
        sortFieldComparator.put(Field.WICKET_AVERAGE, wktComparator.thenComparing(avgBolComparator));
        sortFieldComparator.put(Field.RUNBYBATSMAN, batRunComparator);
        sortFieldComparator.put(Field.RUNBYBOWLER, bowlRunComparator);
        sortFieldComparator.put(Field.BATTING_BLOWING_AVG, new BatBolComparator());
        sortFieldComparator.put(Field.IPL_BEST_ALLROUNDER, new SortIPLALLRounders());

        Comparator<IPLRecordDAO> csvComparator = sortFieldComparator.get(field);
        return csvComparator;
    }
}
