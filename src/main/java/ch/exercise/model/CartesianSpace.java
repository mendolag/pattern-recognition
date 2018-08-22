package ch.exercise.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CartesianSpace {
	/*
		Using a map in order to group segments by their slope
	*/
	private Map<String, List<Segment>> groupedSegments = new HashMap<>();
	private List<Point> points = new ArrayList<>();

	/*
		For each stored point a segment with the added point is created, for each of those segment the slope is calculate.
		The slope is need in order group every segment with the same slope.
	 */
	public void addPoint(Point newPoint) {
		if (points.contains(newPoint)) {
			return;
		}
		for (Point storedPoint : points) {
			createSegment(newPoint, storedPoint);
		}
		points.add(newPoint);
	}

	public List<Point> getPoints() {
		return points;
	}

	public Map<String, List<Segment>> getGroupedSegments() {
		return groupedSegments;
	}

	public void clearCartesianSpace() {
		points = new ArrayList<>();
		groupedSegments = new HashMap<>();
	}

	private void createSegment(Point storedPoint, Point newPoint) {
		String slope;
		//condition needed to manage vertical groupedSegments case
		if (storedPoint.getX() == newPoint.getX()) {
			slope = "V";
		} else {
			slope = Double.toString((storedPoint.getY() - newPoint.getY()) / (storedPoint.getX() - newPoint.getX()));
		}
		ArrayList<Point> segments = new ArrayList<>();
		segments.add(newPoint);
		segments.add(storedPoint);
		addSegment(slope, new Segment(segments));
	}

	private void addSegment(String slope, Segment segment) {
		if (!groupedSegments.containsKey(slope)) {
			ArrayList<Segment> segmentsList = new ArrayList<>();
			segmentsList.add(segment);
			groupedSegments.put(slope, segmentsList);
		} else {
			groupedSegments.get(slope).add(segment);
		}
	}

}
