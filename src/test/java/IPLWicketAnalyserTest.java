import com.google.gson.Gson;
import dao.MostWktCSV;
import exception.IPLCSVException;
import ipl2019census.IPLCensusAnalyser;
import ipl2019census.SortedField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLWicketAnalyserTest {
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    public static String  WRONG_CSV_FILE_PATH="E:\\BridgrLabz\\IPL2019Census\\src\\IPL2019FactsheetMostWkts.csv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH_WRONGDELIMETER="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostWktsWithWrongDelimiter.csv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH_WITHOUTHEADER="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostWktsWithoutHeader.csv";

    IPLCensusAnalyser censusAnalyser;

    @Before
    public void getInstance_OfIPLCensusAnalyser(){
        censusAnalyser=new IPLCensusAnalyser();
    }

    @Test
    public void givenIPLMOstWktsCSVFile_ShouldReturnCorrectRecords() {
        try {

            int noOfIplRecords =censusAnalyser.loadIPLMostWktsData(IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH);
            Assert.assertEquals(99, noOfIplRecords);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            censusAnalyser.loadIPLMostWktsData(IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH);
            String playerRecord = censusAnalyser.getSortedDataOfIpl(SortedField.Field.AVERAGE);
            MostWktCSV[] mostRunCSVS = new Gson().fromJson(playerRecord, MostWktCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLCSVException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplCensusMostWicketsData_WithWrongFile_ShouldThrowException() {
        try {

            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLCSVException.class);
            censusAnalyser.loadIPLMostWktsData(WRONG_CSV_FILE_PATH);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenIplCensusMostWicketsData_WithWrongDelimeterFile_ShouldThrowException() {
        try {

            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLCSVException.class);
            censusAnalyser.loadIPLMostWktsData(IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH_WRONGDELIMETER);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.DELIMETER_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplCensusMostWicketsData_WithoutHeader_CSVFile_ShouldThrowException() {
        try {

            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLCSVException.class);
            censusAnalyser.loadIPLMostWktsData(IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH_WITHOUTHEADER);
        } catch (IPLCSVException e) {
            Assert.assertEquals(IPLCSVException.ExceptionType.HEADER_PROBLEM,e.type);
        }
    }

}
