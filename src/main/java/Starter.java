import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Starter {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("PuzzleValidator [inputFile] [outputFile] [resultFile]");
            System.exit(-1);
        }

        // args[0]: input
        // args[1]: output
        // args[2]: result
        String inputFileName = args[0];
        String outputFileName = args[1];
        String resultFileName = args[2];

        String[] input = readLines(inputFileName);
        String[] output = readLines(outputFileName);

        ValidationResult result = new NPuzzleValidator().validate(input, output);

        BufferedWriter w = new BufferedWriter(new FileWriter(resultFileName));
        w.write(result.toPC2Result(resultFileName));
        w.close();

        System.out.println(result.getMessage());
    }

    public static String[] readLines(String fileName) throws Exception {
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            lines.add(line);
        }
        reader.close();

        return lines.toArray(new String[lines.size()]);
    }

}
