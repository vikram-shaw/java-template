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

interface IReaderWriterCreator {
    ReaderWriter Create() throws IOException;
}

class OfflineReaderWriterCreator implements IReaderWriterCreator {
    public ReaderWriter Create() throws IOException {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";

        File file = new File(inputFileName);
        if(!file.exists()) {
            file.createNewFile();
        }

        file = new File(outputFileName);
        if(!file.exists()) {
            file.createNewFile();
        }
        
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
        if(System.getProperty("LOCAL") != null) {
            readerWriter = new OnlineReaderWriterCreator().Create();
        } else if(System.getProperty("ONLINE_JUDGE")!=null) {
            readerWriter = new OfflineReaderWriterCreator().Create();
        } else {
            readerWriter = new OfflineReaderWriterCreator().Create(); // Unnecessary, maybe removed later
        }
        return readerWriter;
    }
}

class CleanCode {
    public static void main(String str[]) throws IOException{
        ReaderWriter reader = ReaderWriterFactory.get();
        String name = reader.in.readLine();
        reader.out.println("You name is " + name);
    }
}