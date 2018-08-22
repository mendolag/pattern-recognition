package ch.exercise.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Segment {

	private final List<Point> pointList;

	public Segment(List<Point> pointList) {
		if (pointList.size() > 1) {
			this.pointList = pointList;
		} else {
			throw new UnsupportedOperationException("Segment must have at least two points");
		}
	}

	@JsonProperty(value = "")
	public List<Point> getPoints() {
		return pointList;
	}

	@JsonIgnore
	public Point getFirstPoint() {
		return pointList.get(0);
	}

	@JsonIgnore
	public Point getLastPoint() {
		return pointList.get(pointList.size() - 1);
	}
}
