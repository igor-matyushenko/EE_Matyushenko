package com.accenture.flowershop.be.business;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UserMarshallingServiceImpl {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Value("${filepath}")
    private String filepath;

    public UserMarshallingServiceImpl() {
    }

    public void convertFromObjectToXML(Object object, String fileName)
            throws IOException {
        FileOutputStream os = null;
        filepath = filepath + fileName + ".xml";
        os = new FileOutputStream(filepath);
        getMarshaller().marshal(object, new StreamResult(os));
        os.close();
    }

    public Object convertFromXMLToObject(String xmlfile) throws IOException {
        FileInputStream is = null;
        is = new FileInputStream(xmlfile);
        return getUnmarshaller().unmarshal(new StreamSource(is));
    }

    private Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    private Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
}
