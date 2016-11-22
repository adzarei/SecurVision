package com.SecurVision.EntityManager;

import com.SecurVision.dataAccess.Accessors;

/**
 * Created by adrian on 26/10/2016.
 */
public class Managers {
    private static Managers ourInstance = new Managers();

    private Accessors accessors;

    public PersonaManager       personaManager;
    public CheckeoManager       checkeoManager ;
    public UsuarioManager       usuarioManager;
    public ZonaManager          zonaManager;
    public AutenticacionManager autenticacionManager;




    private Managers() {
        this.accessors = Accessors.getInstance();
        this.personaManager = new PersonaManager(accessors);
        this.checkeoManager = new CheckeoManager(accessors);
        this.usuarioManager = new UsuarioManager(accessors);
        this.zonaManager    = new ZonaManager(accessors);
        this.autenticacionManager = new AutenticacionManager(accessors);
    }

    public static Managers getInstance() {
        return ourInstance;
    }
}
