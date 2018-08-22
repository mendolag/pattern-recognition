package ch.exercise.service.impl;

import java.util.List;
import java.util.Map;

import ch.exercise.model.CartesianSpace;
import ch.exercise.model.Point;
import ch.exercise.model.Segment;
import ch.exercise.service.CartesianSpaceService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianSpaceServiceImplTest {
	private CartesianSpaceService cartesianSpaceService;

	@Before
	public void setUp() {
		CartesianSpace cartesianSpace = new CartesianSpace();
		cartesianSpaceService = new CartesianSpaceServiceImpl(cartesianSpace);
	}

	@Test
	public void addPoint() throws Exception {
		//given
		Point point = new Point(1, 1);
		//when
		cartesianSpaceService.addPoint(point);
		//then
		List<Point> points = cartesianSpaceService.getPoints();
		assertTrue("List should contain 1 point", points.size() == 1);
		assertEquals(point, points.get(0));
	}

	@Test
	public void addDuplicatePoint() throws Exception {
		//given
		Point point = new Point(1, 1);
		Point pointDup = new Point(1, 1);

		//when
		cartesianSpaceService.addPoint(point);
		cartesianSpaceService.addPoint(pointDup);

		//then
		List<Point> points = cartesianSpaceService.getPoints();
		assertTrue("List should contain 1 point", points.size() == 1);
		assertEquals(point, points.get(0));
	}

	@Test
	public void createSegmentVerticalSegment() throws Exception {
		//given
		Point pointA = new Point(1, 1);
		Point pointB = new Point(1, 2);
		//when
		cartesianSpaceService.addPoint(pointA);
		cartesianSpaceService.addPoint(pointB);
		//then
		List<Point> points = cartesianSpaceService.getPoints();

		assertTrue("List should contain 1 point", points.size() == 2);
		Map<String, List<Segment>> lines = cartesianSpaceService.getLines();
		assertTrue("should contain entry with key V", lines.containsKey("V"));
		List<Segment> segments = lines.get("V");
		assertFalse("Lines should have been created", segments.isEmpty());
		assertEquals(pointA, segments.get(0).getFirstPoint());
		assertEquals(pointB, segments.get(0).getLastPoint());
	}

	@Test
	public void clearSpace() throws Exception {
		//given
		Point pointA = new Point(1, 1);
		Point pointB = new Point(1, 2);
		cartesianSpaceService.addPoint(pointA);
		cartesianSpaceService.addPoint(pointB);
		//when
		cartesianSpaceService.clearSpace();
		//then
		List<Point> points = cartesianSpaceService.getPoints();
		assertTrue("Should be empty", points.isEmpty());
		assertTrue(cartesianSpaceService.getLines().isEmpty());
	}

	@Test
	public void getCollinearSegmentsWith2Points() throws Exception {
		//given
		Point pointA = new Point(1, 1);
		Point pointB = new Point(1, 2);
		cartesianSpaceService.addPoint(pointA);
		cartesianSpaceService.addPoint(pointB);
		//when
		List<List<Point>> underTest = cartesianSpaceService.getSegmentsWihtNColinearPoints(2);
		//then

		assertFalse("Should not be empty", underTest.isEmpty());
		assertEquals(1, underTest.size());
		assertEquals(pointA, underTest.get(0).get(0));
		assertEquals(pointB, underTest.get(0).get(1));
	}

	@Test
	public void getCollinearSegmentsWith3PointsHavingNoResult() throws Exception {
		//given
		Point pointA = new Point(1, 1);
		Point pointB = new Point(1, 2);
		cartesianSpaceService.addPoint(pointA);
		cartesianSpaceService.addPoint(pointB);
		//when
		List<List<Point>> underTest = cartesianSpaceService.getSegmentsWihtNColinearPoints(3);
		//then

		assertTrue("Should not be empty", underTest.isEmpty());
	}

	@Test
	public void getCollinearSegmentsWith3Points() throws Exception {
		//given
		Point pointA = new Point(1, 1);
		Point pointB = new Point(1, 2);
		Point pointC = new Point(1, 3);

		cartesianSpaceService.addPoint(pointA);
		cartesianSpaceService.addPoint(pointB);
		cartesianSpaceService.addPoint(pointC);
		//when
		List<List<Point>> underTest = cartesianSpaceService.getSegmentsWihtNColinearPoints(3);
		//then

		assertFalse("Should not be empty", underTest.isEmpty());
		assertEquals(1, underTest.size());
		assertEquals(underTest.get(0).size(), 3);
		assertEquals(pointA, underTest.get(0).get(0));
		assertEquals(pointB, underTest.get(0).get(1));
		assertEquals(pointC, underTest.get(0).get(2));

	}

	@Test
	public void getCollinearSegmentsWith3PointsAndMoreSlopes() throws Exception {
		//given
		Point pointA = new Point(1, 1);
		Point pointB = new Point(1, 2);
		Point pointC = new Point(1, 3);
		Point pointD = new Point(2, 2);
		Point pointE = new Point(3, 3);
		Point pointF = new Point(4, 4);

		cartesianSpaceService.addPoint(pointA);
		cartesianSpaceService.addPoint(pointB);
		cartesianSpaceService.addPoint(pointC);
		cartesianSpaceService.addPoint(pointD);
		cartesianSpaceService.addPoint(pointE);
		cartesianSpaceService.addPoint(pointF);

		//when
		List<List<Point>> underTest1 = cartesianSpaceService.getSegmentsWihtNColinearPoints(3);
		List<List<Point>> underTest2 = cartesianSpaceService.getSegmentsWihtNColinearPoints(4);

		//then

		assertFalse("Should not be empty", underTest1.isEmpty());
		assertEquals(6, underTest1.size());
		assertFalse("Should not be empty", underTest2.isEmpty());
		assertEquals(1, underTest2.size());

	}

}