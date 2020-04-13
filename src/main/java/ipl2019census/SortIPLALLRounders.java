package ipl2019census;

import dao.IPLRecordDAO;

import java.util.Comparator;

public class SortIPLALLRounders implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO iplRecordDAO1, IPLRecordDAO iplRecordDAO2) {
        return (iplRecordDAO1.batsmanRun * iplRecordDAO1.wkts) - (iplRecordDAO2.batsmanRun * iplRecordDAO2.wkts);
    }
}
