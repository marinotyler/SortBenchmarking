/**UMGC CMSC 451
 * Description: Heapsort vs Bubblesort Benchmarking- Produces reports for output file in JTable format. 
 * @author Ty Marino
 * Date: August 13th, 2024
 * Java 11
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Report {
    
    public static void main(String[] args) throws Exception {
        CreateReport();
    }
    protected static void CreateReport() throws Exception {
        File dataFile = null;
        final String[] columnNames = {"Data Set Size", "Avg Count", "Coef Count", "Avg Time", "Coef Time"};
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION){
                dataFile = fileChooser.getSelectedFile();
                System.out.println("Opening File " + dataFile.getName()+ "...");
            }else{
                System.out.println("Selection Cancelled by user.");

            }

            //window setup
            ArrayList<double[]> data= processData(dataFile);
            // TableModel model = new BenchmarkModel(data);
            DefaultTableModel model = new DefaultTableModel(columnNames,0);
            for(double[] run :data){
                Object[] row = new Object[5];
                row[0] = run[0];
                row[1] = run[1];
                row[2] = run[2];
                row[3] = run[3];
                row[4] = run[4];
                model.addRow(row);
            }
            JTable reportOutput = new JTable(model);
            
            JScrollPane scrollPane = new JScrollPane(reportOutput);
            
            JFrame frame = new JFrame();
            frame.setTitle("Benchmark Results for " + dataFile.getName());
            frame.add(scrollPane);
            frame.setSize(1000, 1000);
            frame.setVisible(true);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<double[]> processData(File file) throws IOException {
        String line;
        BufferedReader reader;
        ArrayList<double[]> runs = new ArrayList<>();
        ArrayList<DataPoint> data = new ArrayList<>();

        //process the line of data into tokens and then convert tokens into individual datapoints to calculate avgs
        try{
            reader = new BufferedReader(new FileReader(file)); 
            reader.readLine();
            while ((line = reader.readLine())!= null ) {
                String [] tokens = line.split(" ");
                int size = Integer.parseInt(tokens[0]);
                for(String dataPoint : tokens){
                    DataPoint dp = new DataPoint();
                        if (!dataPoint.contains(",")){
                            continue;
                        } 
                        String newData = dataPoint.replaceAll("[()]", "");
                        dp.setCount(Integer.parseInt(newData.split(",")[0]));
                        dp.setTime(Integer.parseInt(newData.split(",")[1]));
                        dp.setSize(size);
                        data.add(dp); 
                }
                runs.add(calcStats(data));
                data.clear();  
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return runs;
    }
    private static double[] calcStats(ArrayList<DataPoint> data){
        double[] stats = new double[5];
        double timeVar = 0.0;
        double countVar = 0.0;
        double avgTime = 0.0;
        double avgCount = 0.0;
        stats[0] = data.get(0).size;
        for(DataPoint d : data){
            avgTime+=d.time;
            avgCount+=d.count;
        }
        avgCount/=40;
        avgTime /=40;
        stats[1] = avgCount;
        stats[3] = avgTime;
        for(DataPoint d : data){
            timeVar=Math.pow((d.time-avgTime),2);
            countVar=Math.pow((d.count-avgCount),2);
        }
        timeVar = Math.sqrt(timeVar/39);
        stats[4] = (timeVar/avgTime)*100;
        countVar = Math.sqrt(countVar/39);
        stats[2] = (countVar/avgCount)*100;
        return stats;
    }  
}
    

