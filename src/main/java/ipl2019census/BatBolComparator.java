package ipl2019census;

import dao.IPLRecordDAO;

import java.util.Comparator;

public class BatBolComparator implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO iplRecordDAO1, IPLRecordDAO iplRecordDAO2) {
        return (int) (iplRecordDAO1.battingAverage - (1d/iplRecordDAO1.bowlingAverage) -
                       (iplRecordDAO2.battingAverage - (1d/iplRecordDAO2.bowlingAverage)));
    }
}
