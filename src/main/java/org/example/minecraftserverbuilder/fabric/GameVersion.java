package org.example.minecraftserverbuilder.fabric;

import java.io.Serializable;

public class GameVersion implements Serializable {

    public String version;
    public boolean stable;

    public GameVersion() {
    }

    public GameVersion(String version, boolean stable) {
        this.version = version;
        this.stable = stable;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isStable() {
        return stable;
    }

    public void setStable(boolean stable) {
        this.stable = stable;
    }

    @Override
    public String toString() {
        return version + "\t\t" +"stable: "+ stable;
    }
}
