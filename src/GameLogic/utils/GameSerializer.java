package Logic.utils;

import Logic.Game;
import Logic.GameDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class GameSerializer {
    public static GameDescriptor serializeXML(String path) throws Exception{
        GameDescriptor gameDescriptor = null;
        try {
            InputStream inputStream = new FileInputStream(path);
            JAXBContext jc = JAXBContext.newInstance("Logic");
            Unmarshaller u = jc.createUnmarshaller();
            gameDescriptor = (GameDescriptor) u.unmarshal(inputStream);
            gameDescriptor.init();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new Exception("Cant create an instance out of the XML file");
        } catch (FileNotFoundException e){
            throw new Exception("File not found");
        }

        return gameDescriptor;
    }

}
