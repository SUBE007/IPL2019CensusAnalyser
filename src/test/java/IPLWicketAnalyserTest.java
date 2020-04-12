import com.google.gson.Gson;
import dao.MostWktCSV;
import exception.IPLCSVException;
import ipl2019census.IPLCensusAnalyser;
import ipl2019census.SortedField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLWicketAnalyserTest {
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH_WRONGDELIMETER="E:\\BridgrLabz\\IPL2019Census\\src\\test\\resources\\IPL2019FactsheetMostWktsWithWrongDelimiter.csv";


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
}
