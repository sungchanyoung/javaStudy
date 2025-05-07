package day5.파일압축시스템;

import java.io.File;
import java.util.List;

public class RarCompression implements CompressionStrategy{

    @Override
    public void compressFile(List<File> files, String destination) {
        System.out.println("Rar Compression");
        System.out.println("압축할 파일의 수" + files.size());
        System.out.println("압축 파일 저장 경로" + destination);
    }
}
