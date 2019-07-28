package com.freenow.test.helper;

/**
 * Created by stevevarsanis on 28/07/19.
 */
public class Core {

    private Management management;

    public Management suiteManager() {
        if(management == null) {
            management = new Management();
        }
        return management;
    }
}
