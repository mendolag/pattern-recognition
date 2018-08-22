package ch.exercise.controller;

import java.util.List;
import java.util.Map;

import ch.exercise.model.Point;
import ch.exercise.service.CartesianSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CartesianSpaceController {

	private final CartesianSpaceService cartesianSpaceService;

	@Autowired
	public CartesianSpaceController(CartesianSpaceService cartesianSpaceService) {
		this.cartesianSpaceService = cartesianSpaceService;
	}

	/*
	POST /point with body { "x": ..., "y": ... }
	 */
	@RequestMapping(value = "/point", method = RequestMethod.POST)
	public ResponseEntity addPoint(@RequestBody Point point) {
		cartesianSpaceService.addPoint(point);
		return new ResponseEntity(HttpStatus.OK);
	}

	/*
	Get all points in the space
	 */
	@RequestMapping(value = "/space", method = RequestMethod.GET)
	public ResponseEntity<List<Point>> getPointsInSpace() {
		return new ResponseEntity<List<Point>>(cartesianSpaceService.getPoints(), HttpStatus.OK);
	}

	/*
	Get all points in the space
	 */
	@RequestMapping(value = "/space", method = RequestMethod.DELETE)
	public ResponseEntity deletePointsInSpace() {
		cartesianSpaceService.clearSpace();
		return new ResponseEntity(HttpStatus.OK);
	}

	/*
	Get all line segments passing through at least N points. Note that a line segment should be a set of COLLINEAR points.
	 */
	@RequestMapping(value = "/lines/{collinearPoints}", method = RequestMethod.GET)
	public ResponseEntity<List<List<Point>>> getCollinearPoints(@PathVariable(value = "collinearPoints") int collinearPoints) {
		List<List<Point>> collinearSegmentsWith = cartesianSpaceService.getSegmentsWihtNColinearPoints(collinearPoints);
		return new ResponseEntity<>(collinearSegmentsWith, HttpStatus.OK);
	}

	@RequestMapping(value = "/lines", method = RequestMethod.GET)
	public ResponseEntity<Map> getLinesInSpace() {
		return new ResponseEntity<Map>(cartesianSpaceService.getLines(), HttpStatus.OK);
	}

}
