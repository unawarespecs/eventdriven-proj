package io.github.unawarespecs.rubber.service;

public interface DocumentService {
    void save();
    void saveAs(String filename);
    void open(String filename);
}
