package adapter;

import dao.IPLRecordDAO;
import dao.MostWktCSV;
import exception.IPLCSVException;
import ipl2019census.SortedField;

import java.util.Map;

public class BowlerAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLRecordDAO> loadIPLData(SortedField.IPLEntity iplEntity, String... csvFilePath) throws IPLCSVException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(MostWktCSV.class, csvFilePath);
        return recordDAOMap;

    }
}
