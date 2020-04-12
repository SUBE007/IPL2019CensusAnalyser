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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class IPLCensusAnalyser {
    public int loadIPLMostRunsData(String csvFilePath) throws IPLCSVException {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            while (mostRunCSVIterator.hasNext()) {
                MostRunCSV mostRunCSV=mostRunCSVIterator.next();
                runCSVList.add(mostRunCSV);
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


    public static List<MostRunCSV> runCSVList = new ArrayList<MostRunCSV>();
    public String getAverageSortedRunOfIpl( ) {
            runCSVList.sort(Comparator.comparing(mostRunCSV -> mostRunCSV.strikeRate));
            String sortedJsonData = new Gson().toJson(runCSVList);
            return  sortedJsonData;
    }

}
