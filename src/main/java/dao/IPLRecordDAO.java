package dao;

public class IPLRecordDAO {
    public IPLRecordDAO() {
    }
    public String player;
    //public int runs;
    public int batsmanRun;
    public int bowlerRun;
    public double strikeRate;
    public double average;
    public double battingAverage;
    public double bowlingAverage;
    public double economy;
    public int fours;
    public int six;
    public int wkts;
    public int fourWkts;
    public int fiveWkts;

    public IPLRecordDAO(MostRunCSV mostRunCSV) {
        player = mostRunCSV.player;
        batsmanRun = mostRunCSV.run;
        strikeRate = mostRunCSV.strikeRate;
        battingAverage = mostRunCSV.avg;
        fours = mostRunCSV.fours;
        six = mostRunCSV.six;
     }

    public IPLRecordDAO(MostWktCSV mostWktsCSV) {
        player = mostWktsCSV.player;
        bowlerRun = mostWktsCSV.runs;
        strikeRate = mostWktsCSV.strikeRate;
        bowlingAverage = mostWktsCSV.average;
        this.economy=mostWktsCSV.economy;
        wkts = mostWktsCSV.wkts;
        fourWkts = mostWktsCSV.fourWkts;
        fiveWkts = mostWktsCSV.fiveWkts;

    }

    public IPLRecordDAO(String player, int runs, double strikeRate, double battingAverage, int fours, int six, int wkts, int fourWkts, int fiveWkts, double bowlingAverage) {
        this.player = player;
        //this.runs = runs;
        this.strikeRate = strikeRate;
        this.battingAverage = battingAverage;
        this.bowlingAverage = bowlingAverage;
        this.fours = fours;
        this.six = six;
        this.wkts = wkts;
        this.fourWkts = fourWkts;
        this.fiveWkts = fiveWkts;
    }

}
