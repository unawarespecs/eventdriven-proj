package io.github.unawarespecs.appfx.model;

import io.github.unawarespecs.appfx.enums.Vertex;
import lombok.Data;

import java.awt.*;
@Data
public class SearchResult {
    Vertex vertex;
    Point point;
    Shape shape;
    Shape clone;
}
