package com.quince.rentingapp.service;
import org.xml.sax.InputSource;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import tr.gov.nvi.tckimlik.KPSPublic;
import tr.gov.nvi.tckimlik.KPSPublicSoap;

import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class TCValidationService {
    public boolean checkValidity(String name, String surname, long tcno, int birthyear) throws ParserConfigurationException {
        KPSPublicSoap tc = (KPSPublicSoap) new KPSPublic();

        return tc.tcKimlikNoDogrula(tcno,name,surname,birthyear);
    }


}
