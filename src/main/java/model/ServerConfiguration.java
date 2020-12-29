package model;

import java.io.Serializable;

public class ServerConfiguration implements Serializable {
    private static final long serialVersionUID = 3L;
    private int stringServerPort;
    private int numericServerPort;
    private int objectServerPort;
    private int code;

    public ServerConfiguration(int stringServerPort, int numericServerPort, int objectServerPort, int code) {
        this.stringServerPort = stringServerPort;
        this.numericServerPort = numericServerPort;
        this.objectServerPort = objectServerPort;
        this.code = code;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStringServerPort() {
        return stringServerPort;
    }

    public void setStringServerPort(int stringServerPort) {
        this.stringServerPort = stringServerPort;
    }

    public int getNumericServerPort() {
        return numericServerPort;
    }

    public void setNumericServerPort(int numericServerPort) {
        this.numericServerPort = numericServerPort;
    }

    public int getObjectServerPort() {
        return objectServerPort;
    }

    public void setObjectServerPort(int objectServerPort) {
        this.objectServerPort = objectServerPort;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ServerConfiguration{" +
                "serialVersionUID=" + serialVersionUID +
                ", stringServerPort=" + stringServerPort +
                ", numericServerPort=" + numericServerPort +
                ", objectServerPort=" + objectServerPort +
                ", code=" + code +
                '}';
    }
}
