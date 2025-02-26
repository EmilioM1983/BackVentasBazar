package com.ehmsoft.VentasBazar.responseDto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Configuration Metadata Response
 * @author Emilio Mayer
 */
public class ResponseRest {
    //Estructura de la cabecera del ResponseEntity
    private final ArrayList<HashMap<String, String>> metadata = new ArrayList();

    //Metodo Get de la cabecera del ResponseEntity
    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String type, String code, String data) {
        HashMap<String, String> map = new HashMap<>();
                
        map.put(type, type);
        map.put(code, code);
        map.put(data, data);
        
        metadata.add(map);
    }
    
    
}
