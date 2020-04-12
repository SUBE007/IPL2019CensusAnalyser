package csvbuilderfiles;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import exception.CSVBuilderException;
import exception.IPLCSVException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCsvBuilder implements ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return this.getCSVBean(reader, csvClass).iterator();
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return this.getCSVBean(reader, csvClass).parse();
    }

    private <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CSVBuilderException {

        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(), IPLCSVException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

}
