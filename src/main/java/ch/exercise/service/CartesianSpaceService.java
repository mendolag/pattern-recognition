package ch.exercise.service;

import java.util.List;
import java.util.Map;

import ch.exercise.model.Point;
import ch.exercise.model.Segment;

public interface CartesianSpaceService {

	void addPoint(Point point);

	List<Point> getPoints();

	void clearSpace();

	Map<String,List<Segment>> getLines();

	List<List<Point>> getSegmentsWihtNColinearPoints(int numOfPoints);
}
