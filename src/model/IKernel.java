package model;

import java.util.Observer;

public interface IKernel extends Observer {
    abstract void connect();
    abstract void disconnect();
}
