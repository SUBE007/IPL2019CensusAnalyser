import com.google.gson.Gson;
import exception.IPLCSVException;
import ipl2019census.IPLCensusAnalyser;
import ipl2019census.MostRunCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
    public static String IPL_CENSUS_CSV_MOSTRUNS_FILEPATH="resources/IPL2019FactsheetMostRuns.csv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH="resources/IPL2019FactsheetMostWkts.csv";
    public static String  WRONG_CSV_FILE_PATH="E:\\BridgrLabz\\IPL2019Census\\src\\IPL2019FactsheetMostRuns.csv";
    public static String IPL_CENSUS_CSV_MOSTRUNS_FILEPATH_WRONGDELIMETER ="resources/IPL2019FactsheetMostRunsWithWrongDelimiter.csv";
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
        try{
            censusAnalyser.loadIPLMostRunsData(IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            String playersData =  censusAnalyser.getAverageSortedRunOfIpl();
            MostRunCSV[] AverageRuns = new Gson().fromJson(playersData, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", AverageRuns[AverageRuns.length-1].player);
        }catch (IPLCSVException e){
            e.printStackTrace();
       }
    }

    @Test
    public void givenIplCensusMostRunsData_WithWrongFile_ShouldThrowException() {
        try {

            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLCSVException.class);
            censusAnalyser.loadIPLMostRunsData(WRONG_CSV_FILE_PATH);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenIplCensusMostRunsData_WithWrongDelimeter_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLCSVException.class);
            censusAnalyser.loadIPLMostRunsData(IPL_CENSUS_CSV_MOSTRUNS_FILEPATH_WRONGDELIMETER);
        } catch (IPLCSVException e) {
             Assert.assertEquals(IPLCSVException.ExceptionType.ISSUE_IN_FILE,e.type);
        }
    }
}