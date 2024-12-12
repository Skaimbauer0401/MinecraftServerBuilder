package org.example.minecraftserverbuilder.vanilla;

import java.io.Serializable;

public class GameVersion implements Serializable {
    String version;
    String url;

    public GameVersion() {
    }

    public GameVersion(String version, String url) {
        this.version = version;
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return version;
    }
}
