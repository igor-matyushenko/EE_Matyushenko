package com.accenture.flowershop.be.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

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

    public Object convertFromXMLToObject(String xmlFile) throws IOException {
        FileInputStream is = null;
        is = new FileInputStream(xmlFile);
        return getUnmarshaller().unmarshal(new StreamSource(is));
    }

    public String convertFromObjectToString(Object object) throws IOException {
        StringWriter os = new StringWriter();
        getMarshaller().marshal(object, new StreamResult(os));
        return os.toString();
    }

    public Object convertStringXmlToObject(String stringXml) throws IOException {
        StringReader stringReader = new StringReader(stringXml);
        return getUnmarshaller().unmarshal(new StreamSource(stringReader));
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
