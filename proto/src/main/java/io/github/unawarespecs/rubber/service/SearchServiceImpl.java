package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.service.SearchService;
import io.github.unawarespecs.appfx.model.SearchResult;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.appfx.enums.Vertex;
import io.github.unawarespecs.appfx.service.AppService;
import java.awt.*;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    private int s=5;
    AppService appService;
    public SearchServiceImpl(AppService appService){
        this.appService =appService;
    }

    private SearchResult search(Shape shape, Point point)  {
        SearchResult searchResult = new SearchResult();
        int locx = shape.getLocation().x;
        int locy = shape.getLocation().y;
        int endx = shape.getEnd().x;
        int endy = shape.getEnd().y;
        int midx = (locx + endx) /2;
        int midy = (locy + endy) /2;

        if (((point.x > (locx - s)) && (point.x < (endx + s)) && (point.y > (locy - s)) && (point.y < endy + s))) {
            //searchResult.setClone(shape.clone());
            searchResult.setShape(shape);
            searchResult.setPoint(point);

            if (((point.x > (locx - s)) && (point.x < (locx + s)) && (point.y > (locy - s)) && (point.y < locy + s))) {
                searchResult.setVertex(Vertex.UpperLeft);
            }
            else if (((point.x > (locx - s)) && (point.x < (locx + s)) && (point.y > (endy - s)) && (point.y < endy + s))) {
                searchResult.setVertex(Vertex.LowerLeft);
            }
            else if (((point.x > (endx - s)) && (point.x < (endx + s)) && (point.y > (endy - s)) && (point.y < endy + s))) {
                searchResult.setVertex(Vertex.LowerRight);
            }
            else if (((point.x > (endx - s)) && (point.x < (endx + s)) && (point.y > (locy - s)) && (point.y < locy + s))) {
                searchResult.setVertex(Vertex.UpperRight);
            }
            else if (((point.x > (locx - s)) && (point.x < (locx + s)) && (point.y > (midy - s)) && (point.y < midy + s))) {
                searchResult.setVertex(Vertex.LeftMiddle);
            }
            else if (((point.x > (midx - s)) && (point.x < (midx + s)) && (point.y > (endy - s)) && (point.y < endy + s))) {
                searchResult.setVertex(Vertex.LowerMiddle);
            }
            else if (((point.x > (endx - s)) && (point.x < (endx + s)) && (point.y > (midy - s)) && (point.y < midy + s))) {
                searchResult.setVertex(Vertex.RightMiddle);
            }
            else if (((point.x > (midx - s)) && (point.x < (midx + s)) && (point.y > (locy - s)) && (point.y < locy + s))) {
                searchResult.setVertex(Vertex.UpperMiddle);
            }
            else {
                searchResult.setVertex(Vertex.Whole);
            }
            return searchResult;
        }
        else {
            return null;
        }
    }
    @Override
    public SearchResult search(Point point) {
        List<Shape> shapes =appService.getShapes();
        for(Shape shape : shapes ) {
            SearchResult searchResult = search(shape, point);
            if(searchResult!=null)
                return searchResult;
        }
        return null;
    }
}
