package org.minecraftserverbuilder.fabric;

public class Installer {
    public String url;
    public String maven;
    public String version;
    public boolean stable;

    public Installer() {
    }

    public Installer(String url, String maven, String version, boolean stable) {
        this.url = url;
        this.maven = maven;
        this.version = version;
        this.stable = stable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMaven() {
        return maven;
    }

    public void setMaven(String maven) {
        this.maven = maven;
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
        return version;
    }
}
