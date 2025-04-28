package day2.예제3;

public class DocumentService {
    public byte[] convertDocument(byte[] input, DocumentType sourceType, DocumentType targetType){
        ConverterFactory converterFactory = getConverterFactory(sourceType, targetType);
        return converterFactory.convertDocument(input);
    }
    private ConverterFactory getConverterFactory(DocumentType sourceType, DocumentType targetType){
        if(sourceType == DocumentType.PDF && targetType == DocumentType.WORD){
            return new PdfToWordConverterFactory();
        } else if (sourceType == DocumentType.PDF && targetType == DocumentType.WORD) {
            return new WordToPdfConverterFactory();
        }
        throw new IllegalArgumentException("sourceType or targetType is not valid");
    }
}
