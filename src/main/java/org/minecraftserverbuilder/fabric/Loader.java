package org.minecraftserverbuilder.fabric;

public class Loader {
    public String separator;
    public int build;
    public String maven;
    public String version;
    public boolean stable;

    public Loader() {
    }

    public Loader(String separator, int build, String maven, String version, boolean stable) {
        this.separator = separator;
        this.build = build;
        this.maven = maven;
        this.version = version;
        this.stable = stable;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
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
