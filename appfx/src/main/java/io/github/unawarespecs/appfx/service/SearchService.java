package io.github.unawarespecs.appfx.service;

import io.github.unawarespecs.appfx.model.SearchResult;

import java.awt.Point;


public interface SearchService {
    SearchResult search(Point point);
}
