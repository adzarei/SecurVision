package com.SecurVision.EntityManager;

import com.SecurVision.dataAccess.Accessors;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adrian on 02/11/2016.
 */
public class AutenticacionManager {

    private Accessors a;

    public AutenticacionManager(Accessors a){
        this.a = a;
    }


    public boolean recognize(InputStream fileInputStream) throws IOException {
        /*
        String base64 = Base64.encodeBase64URLSafeString(IOUtils.toByteArray(fileInputStream));
        */

        byte[] message = IOUtils.toByteArray(fileInputStream);
        String encoded = DatatypeConverter.printBase64Binary(message);
        String json = a.autenticationAccessor.recognize(encoded);

        JSONObject jo = new JSONObject(json);

        try{
            JSONObject aux = jo.getJSONArray("images").getJSONObject(0);
            Double ans = aux.getJSONObject("transaction").getDouble("confidence");
            if (ans > 0.5)
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public boolean uploadImagen(String jsonStr) throws Exception {
        JSONObject jo = new JSONObject(jsonStr);
        String dni;
        String imagen;
        try{
            dni = jo.getString("dni");
            imagen = jo.getString("imagen");
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }

        String res = a.autenticationAccessor.uploadImagen(dni, imagen);
        System.out.println(res);//Debug.
        boolean acomplished = false;
        try {
            jo = new JSONObject(res);
            JSONObject aux = jo.getJSONArray("images").getJSONObject(0);
            acomplished = aux.getJSONObject("transaction").getString("status").contains("success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return acomplished;

    }
}
