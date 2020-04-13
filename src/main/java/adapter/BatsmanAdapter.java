package adapter;

import csvbuilderfiles.CSVBuilderFactory;
import csvbuilderfiles.ICSVBuilder;
import dao.IPLRecordDAO;
import dao.MostRunCSV;
import dao.MostWktCSV;
import exception.CSVBuilderException;
import exception.IPLCSVException;
import ipl2019census.SortedField;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLRecordDAO> loadIPLData(SortedField.IPLEntity iplEntity, String... csvFilePath) throws IPLCSVException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(MostRunCSV.class, csvFilePath[0]);
        if (csvFilePath.length == 2)
            this.loadMostWKTSCSV(recordDAOMap, csvFilePath[1]);
        return recordDAOMap;
    }
    private int loadMostWKTSCSV(Map<String, IPLRecordDAO> recordDAOMap, String csvFilePath) throws IPLCSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostWktCSV> wktsCSVIterator = csvBuilder.getCSVFileIterator(reader, MostWktCSV.class);
            Iterable<MostWktCSV> wktsCSVS = () -> wktsCSVIterator;
            StreamSupport.stream(wktsCSVS.spliterator(), false)
                    .filter(csvIPL -> recordDAOMap.get(csvIPL.player) != null)
                    .forEach(mostWktsCSV -> {
                        recordDAOMap.get(mostWktsCSV.player).bowlingAverage = mostWktsCSV.average;
                        recordDAOMap.get(mostWktsCSV.player).wkts = mostWktsCSV.wkts;
                    });
             return recordDAOMap.size();
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(),
                    IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException | CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.INTERNAL_FILE_PROBLEM);
        }
    }
}
