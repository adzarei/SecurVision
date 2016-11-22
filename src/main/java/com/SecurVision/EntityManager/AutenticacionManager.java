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
            String ans = aux.getJSONObject("transaction").getString("confidence");
            if (Double.parseDouble(ans) > 0.5)
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
