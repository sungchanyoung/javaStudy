package day5.파일압축시스템;

import java.io.File;
import java.util.List;

public interface CompressionStrategy {
    void compressFile(List<File> files, String destination);
}
