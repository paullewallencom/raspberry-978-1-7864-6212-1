package com.packt.B05688.chapter3;

import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DigitalPhotoFrame {

    public static void main(String[] args) {

        try {

            URL resourceUrl = new URL("https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=9edadc37bf9d3661deea534c77effaf0&photoset_id=72157659233917324&user_id=128978031@N04&privacy_filter=2");
            HttpURLConnection conn = (HttpURLConnection) resourceUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            InputStream responseStream = conn.getInputStream();

            try {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(responseStream);
                NodeList nodeList = doc.getElementsByTagName("photo");

                for (int x = 0, size = nodeList.getLength(); x < size; x++) {
                    String s = "https://farm" + nodeList.item(x).getAttributes().getNamedItem("farm").getNodeValue() + ".staticflickr.com/" + nodeList.item(x).getAttributes().getNamedItem("server").getNodeValue() + "/" + nodeList.item(x).getAttributes().getNamedItem("id").getNodeValue() + "_" + nodeList.item(x).getAttributes().getNamedItem("secret").getNodeValue() + "_b.jpg";

                    System.out.println(s);

                    URL mediaUrl = new URL(s);
                    InputStream in = new BufferedInputStream(mediaUrl.openStream());
                    OutputStream out = new BufferedOutputStream(new FileOutputStream("/home/pi/RASPI3JAVA/DigitalPhotoFrame/media/" + nodeList.item(x).getAttributes().getNamedItem("id").getNodeValue() + ".jpg"));

                    for (int i; (i = in.read()) != -1;) {
                        out.write(i);
                    }
                    in.close();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
