import java.io.*;

abstract class ReaderWriter {
    public BufferedReader in;
    public PrintStream out;
    protected ReaderWriter(BufferedReader br, PrintStream ps) throws IOException {
        in = br;
        out = ps;
    }
}

class OfflineReaderWriter extends ReaderWriter {
    OfflineReaderWriter(String inputFileName, String outputFileName) throws IOException {
        // Read from inputFileName and write to outputFileName
        super(new BufferedReader(new FileReader(inputFileName)), new PrintStream(new File(outputFileName)));
    }
}

class OnlineReaderWriter extends ReaderWriter {
    OnlineReaderWriter() throws IOException {
        // Read from / write to console / terminal 
        super(new BufferedReader(new InputStreamReader(System.in)), new PrintStream(System.out));
    }
}

class CleanCode {
    public static void main(String str[]) throws IOException{
        ReaderWriter reader = new OfflineReaderWriter("input.txt", "output.txt");
        // ReaderWriter reader = new OnlineReaderWriter();
        String name = reader.in.readLine();
        reader.out.println("You name is " + name);
    }
}