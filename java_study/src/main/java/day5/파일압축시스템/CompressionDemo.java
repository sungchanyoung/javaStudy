package day5.파일압축시스템;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompressionDemo {
    public static void main(String[] args) {

        List<File> files = new ArrayList<>();
        files.add(new File("document.pdf"));
        files.add(new File("document.doc"));
        files.add(new File("document.txt"));

        CompressionStrategy zipCompression = new ZipCompression();
        zipCompression.compressFile(files, "C:/zip");

        CompressionStrategy rarCompression = new RarCompression();
        rarCompression.compressFile(files, "C:/rar");

    }
}
