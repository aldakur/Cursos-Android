package service.webservice.facilito.codigo.com.ejemplocfwebservices.Parses;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import service.webservice.facilito.codigo.com.ejemplocfwebservices.POJO.Usuario;

/**
 * Created by aldakur on 18/7/16.
 */
public class UsuarioXMLParser {
    public static List<Usuario> parser(String content){
        boolean inDataItemTag = false;
        String currentTagName = "";
        Usuario usuario = null;
        List<Usuario> usuarioList = new ArrayList<>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG: // Cuando inicia la etiqueta
                        currentTagName = parser.getName();
                        if(currentTagName.equals("usuario")){
                            inDataItemTag = true; // Indicamos que hay información dentro de la etiqueta para leer
                            usuario = new Usuario();
                            usuarioList.add(usuario);
                        }
                        break;
                    case XmlPullParser.END_TAG: // Cuando acaba la etiqueta
                        if(parser.getName().equals("usuario")){
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT: // El contenido de la etiqueta. Aquí es donde empezanos a llenar los datos del objeto usuario
                        if(inDataItemTag && usuario != null){
                            switch (currentTagName){
                                case "usuarioid":
                                    usuario.setUsuarioId(Integer.parseInt(parser.getText())); // El dato viaja como texto pero sabemos que es un Integer, con lo cual lo parseamos como Text primero e Int después
                                    break;
                                case "nombre":
                                    usuario.setNombre(parser.getText());
                                    break;
                                case "twitter":
                                    usuario.setTwitter(parser.getText());
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next(); // leer el siguiente evento

            }
            return usuarioList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
