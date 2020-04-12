package ipl2019census;

import com.opencsv.bean.CsvBindByName;

public class MostRunCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int run;

    @CsvBindByName(column = "HS", required = true)
    public String highScore;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int centuary;

    @CsvBindByName(column = "50", required = true)
    public int HalfCentuary;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    public MostRunCSV(MostRunCSV mostRunCSV) {
    }

    public MostRunCSV() {
    }

    public MostRunCSV(String player, int match, int innings,int notOut, int run, String highScore, double avg, double strikeRate, int centuary, int halfCentuary, int fours, int six) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.notOut=notOut;
        this.run = run;
        this.highScore = highScore;
        this.avg = avg;
        this.strikeRate = strikeRate;
        this.centuary = centuary;
        this.HalfCentuary = halfCentuary;
        this.fours = fours;
        this.six = six;
     }
}
