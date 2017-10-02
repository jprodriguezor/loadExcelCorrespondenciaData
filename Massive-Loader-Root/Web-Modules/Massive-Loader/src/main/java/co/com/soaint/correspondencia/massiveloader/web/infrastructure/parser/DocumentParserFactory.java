package co.com.soaint.correspondencia.massiveloader.web.infrastructure.parser;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by g2o on 31-Jul-17.
 */
public class DocumentParserFactory<O> {
    public DocumentParser getDocumentParser(MultipartFile file){
        if(file == null){
            return null;
        }
        try {
            BufferedInputStream inp = new BufferedInputStream(file.getInputStream());
            if(POIXMLDocument.hasOOXMLHeader(inp) || POIFSFileSystem.hasPOIFSHeader(inp))
                return new ExcelParser<O>();
            else
                return new CSVParser<O>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
