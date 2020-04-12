package ipl2019census;

import com.google.gson.Gson;
import csvbuilderfiles.CSVBuilderFactory;
import csvbuilderfiles.ICSVBuilder;
import exception.CSVBuilderException;
import exception.IPLCSVException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class IPLCensusAnalyser {
    public int loadIPLMostRunsData(String csvFilePath) throws IPLCSVException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            while (mostRunCSVIterator.hasNext()) {
                MostRunCSV mostRunCSV=mostRunCSVIterator.next();
                runCSVMap.put(mostRunCSV.player, mostRunCSV);
                count++;
            }
            return count;
        } catch (IOException e) {
           throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.ISSUE_IN_FILE);
        } catch (CSVBuilderException e) {
            throw new IPLCSVException(e.getMessage(), IPLCSVException.ExceptionType.INTERNAL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Map<String, MostRunCSV> runCSVMap = new HashMap<>();
    public String getSortedDataOfIpl(SortedField.Field sixesAndFours) throws IPLCSVException {
        Comparator<MostRunCSV> censusComparator = null;
        if (runCSVMap == null || runCSVMap.size() == 0) {
            throw new IPLCSVException("NO_CENSUS_DATA", IPLCSVException.ExceptionType.NO_CENSUS_DATA);
        }
        censusComparator =  SortedField.getField(sixesAndFours);
        ArrayList runCSVList = runCSVMap.values().stream().
                sorted(censusComparator).collect(Collectors.toCollection(ArrayList::new));
        String sortedJsonData = new Gson().toJson(runCSVList);
        return  sortedJsonData;
    }

}
