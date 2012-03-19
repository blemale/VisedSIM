/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class processing the XML (de)serialization of an {@link Object}.
 * <p/>
 * @author bastien
 */
public final class XMLPersistanceTools {

    private XMLPersistanceTools() {
    }

    /**
     * XML Serialization of an object
     * <p/>
     * @param object object to serialize
     * @param filename path of the file
     */
    public static void encodeToFile(Object object, String fileName) throws
            FileNotFoundException, IOException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
        try {
            encoder.writeObject(object);
            encoder.flush();
        } finally {
            encoder.close();
        }
    }

    /**
     * XML Deserialization of an object from a file
     * <p/>
     * @param filename path of the file
     */
    public static Object decodeFromFile(String fileName) throws
            FileNotFoundException, IOException {
        Object object = null;
        // ouverture de decodeur
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        try {
            // deserialisation de l'objet
            object = decoder.readObject();
        } finally {
            // fermeture du decodeur
            decoder.close();
        }
        return object;
    }

    /**
     * XML Deserialization of an object from a file
     * <p/>
     * @param inputStream the input stream
     */
    public static Object decodeFromFile(InputStream inputStream) throws
            FileNotFoundException, IOException {
        Object object = null;
        // ouverture de decodeur
        XMLDecoder decoder = new XMLDecoder(inputStream);
        try {
            // deserialisation de l'objet
            object = decoder.readObject();
        } finally {
            // fermeture du decodeur
            decoder.close();
        }
        return object;
    }
}
