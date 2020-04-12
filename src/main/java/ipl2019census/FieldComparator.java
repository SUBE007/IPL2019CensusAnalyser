package ipl2019census;

import java.util.Comparator;

public class FieldComparator implements Comparator<MostRunCSV> {

    @Override
    public int compare(MostRunCSV  mostRunCSV1, MostRunCSV mostRunCSV2) {
        return (((mostRunCSV1.six * 6) + (mostRunCSV1.fours * 4)) - ((mostRunCSV2.six * 6) + (mostRunCSV2.fours * 4)));
    }


}
