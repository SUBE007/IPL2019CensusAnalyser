import com.google.gson.Gson;
import exception.IPLCSVException;
import ipl2019census.IPLCensusAnalyser;
import ipl2019census.MostRunCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {
    public static String IPL_CENSUS_CSV_MOSTRUNS_FILEPATH="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";

    IPLCensusAnalyser censusAnalyser;

    @Before
    public void getInstance_OfIPLCensusAnalyser(){
        censusAnalyser=new IPLCensusAnalyser();
    }

    @Test
    public void givenIplCenusMostRunsCsvFile_ReturnCorrectNoOfRecords() {
        try {
             int numOfRecords = censusAnalyser.loadIPLMostRunsData(IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
             Assert.assertEquals(100,numOfRecords);
        }catch (IPLCSVException e) {
             e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopBattingAverages_WhenSortedOnIt() {
        try{ censusAnalyser.loadIPLMostRunsData(IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            String playersData =  censusAnalyser.getAverageSortedRunOfIpl();
            MostRunCSV[] AverageRuns = new Gson().fromJson(playersData, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", AverageRuns[AverageRuns.length-1].player);
        }catch (IPLCSVException e){
            e.printStackTrace();
       }
    }
}
