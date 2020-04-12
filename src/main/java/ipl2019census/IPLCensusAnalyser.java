package ipl2019census;

import com.google.gson.Gson;
import csvbuilderfiles.CSVBuilderFactory;
import csvbuilderfiles.ICSVBuilder;
import dao.IPLRecordDAO;
import dao.MostRunCSV;
import dao.MostWktCSV;
import exception.CSVBuilderException;
import exception.IPLCSVException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLCensusAnalyser {
    public <T> int loadIPLMostRunsData(String csvFilePath) throws IPLCSVException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            Iterable<T> mostRunCSVIterable = () -> mostRunCSVIterator;
            StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                    .map(MostRunCSV.class::cast)
                    .forEach(mostRunCSV ->runCSVMap.put(mostRunCSV.player, new IPLRecordDAO(mostRunCSV)));
            return  runCSVMap.size();
        } catch (IOException e) {
           throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.ISSUE_IN_FILE);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.INTERNAL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Map<String, IPLRecordDAO> runCSVMap = new HashMap<>();
    public String getSortedDataOfIpl(SortedField.Field field) throws IPLCSVException {
        Comparator<IPLRecordDAO> censusComparator = null;
        if (runCSVMap == null || runCSVMap.size() == 0) {
            throw new IPLCSVException("NO_CENSUS_DATA", IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        }
        censusComparator =  SortedField.getField(field);
        ArrayList runCSVList = runCSVMap.values().stream().
                sorted(censusComparator).collect(Collectors.toCollection(ArrayList::new));
        String sortedJsonData = new Gson().toJson(runCSVList);
        return  sortedJsonData;
    }

    public <T>int loadIPLMostWktsData(String csvFilePath) throws IPLCSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostWktCSV.class);
            Iterable<T> mostRunCSVIterable = () -> mostRunCSVIterator;
            StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                    .map(MostWktCSV.class::cast)
                    .forEach(mostWktsCSV -> runCSVMap.put(mostWktsCSV.player, new IPLRecordDAO(mostWktsCSV)));
            return runCSVMap.size();
        } catch (IOException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.NO_SUCH_FILE);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.HEADER_PROBLEM);
        }
    }
}
