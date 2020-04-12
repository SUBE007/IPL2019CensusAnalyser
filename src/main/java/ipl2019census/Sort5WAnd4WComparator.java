package ipl2019census;

import dao.IPLRecordDAO;

import java.util.Comparator;

public class Sort5WAnd4WComparator implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO iplRecordDAO1, IPLRecordDAO iplRecordDAO2) {
        return ((iplRecordDAO1.fourWkts + iplRecordDAO1.fiveWkts) - (iplRecordDAO2.fourWkts + iplRecordDAO2.fiveWkts));
    }
}
