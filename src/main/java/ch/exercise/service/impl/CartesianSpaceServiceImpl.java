package ch.exercise.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.exercise.model.CartesianSpace;
import ch.exercise.model.Point;
import ch.exercise.model.Segment;
import ch.exercise.service.CartesianSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartesianSpaceServiceImpl implements CartesianSpaceService {

	private final CartesianSpace cartesianSpace;

	@Autowired
	public CartesianSpaceServiceImpl(CartesianSpace cartesianSpace) {
		this.cartesianSpace = cartesianSpace;
	}

	@Override public void addPoint(Point point) {
		cartesianSpace.addPoint(point);
	}

	@Override public List<Point> getPoints() {
		return cartesianSpace.getPoints();
	}

	@Override public void clearSpace() {
		cartesianSpace.clearCartesianSpace();
	}

	@Override public Map<String,List<Segment>> getLines() {
		return cartesianSpace.getGroupedSegments();
	}

	@Override
	public List<List<Point>> getSegmentsWihtNColinearPoints(int numOfPoints) {
		List<List<Point>> responseList = new ArrayList<>();

		for (Map.Entry<String, List<Segment>> entry : cartesianSpace.getGroupedSegments().entrySet()) {
			List<Segment> segments = entry.getValue();
			ArrayList<Segment> segments1 = new ArrayList<>();
			segments1.addAll(segments);
			for (Segment segment : segments) {
				if (numOfPoints == 2) {
					responseList.add(segment.getPoints());
				}
				//Iterates over the original size of the list because there is no need to iterate over the newly added segments
				int size = segments1.size();
				for (int i = 0; i < size; i++) {
					Segment subsequentSegments = createSubsequentSegments(segment, segments1.get(i));
					if (subsequentSegments != null) {
						segments1.add(subsequentSegments);
						//collects only elements have a size equal or bigger than
						if (subsequentSegments.getPoints().size() >= numOfPoints) {
							responseList.add(subsequentSegments.getPoints());
						}
					}
				}
			}
		}
		return responseList;
	}

	//if endpoint and stating point of the segments are attached creates a new segment that is the union of the points of the new segment
	private Segment createSubsequentSegments(Segment segment1, Segment segment2) {
		if (segment1.getFirstPoint().equals(segment2.getLastPoint())) {
			ArrayList<Point> subsequentPoints = new ArrayList<>();
			List<Point> points = segment2.getPoints();
			subsequentPoints.addAll(points.subList(0, points.size()));
			points = segment1.getPoints();
			subsequentPoints.addAll(points.subList(1, points.size()));
			return new Segment(subsequentPoints);
		}
		return null;
	}
}
