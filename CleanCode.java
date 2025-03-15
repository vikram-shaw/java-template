import java.io.*;

abstract class ReaderWriter {
    public BufferedReader in;
    public PrintStream out;
}

class OfflineReaderWriter extends ReaderWriter {
    OfflineReaderWriter(String inputFileName, String outputFileName) throws IOException {
        // Read from inputFileName and write to outputFileName
        in = new BufferedReader(new FileReader(inputFileName));
        out = new PrintStream(new File(outputFileName));
    }
}

class OnlineReaderWriter extends ReaderWriter {
    OnlineReaderWriter() throws IOException {
        // Read from / write to console / terminal 
        in = new BufferedReader(new InputStreamReader(System.in));
        out = System.out;
    }
}

interface IReaderWriterCreator {
    ReaderWriter Create() throws IOException;
}

class OfflineReaderWriterCreator implements IReaderWriterCreator {
    String inputFileName, outputFileName;
    OfflineReaderWriterCreator(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }
    public ReaderWriter Create() throws IOException {
        return new OfflineReaderWriter(inputFileName, outputFileName);
    }
}

class OnlineReaderWriterCreator implements IReaderWriterCreator {
    public ReaderWriter Create() throws IOException {
        return new OnlineReaderWriter();
    }
}

class ReaderWriterFactory {
    public static ReaderWriter get() throws IOException {
        ReaderWriter readerWriter;
        if(System.getenv("LOCAL_JAVA") == null) {
            readerWriter = new OnlineReaderWriterCreator().Create();
        } else {
            String inputFileName = "input.txt", outputFileName = "output.txt";
            readerWriter = new OfflineReaderWriterCreator(inputFileName, outputFileName).Create();
        }
        return readerWriter;
    }
}

class CleanCode {
    public static void main(String args[]) throws IOException{
        ReaderWriter reader = ReaderWriterFactory.get();
        String name = reader.in.readLine();
        reader.out.println("You name is " + name);
    }
}