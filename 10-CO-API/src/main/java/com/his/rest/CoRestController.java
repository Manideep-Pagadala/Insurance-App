package com.his.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.his.entity.COEntity;
import com.his.ser.CoService;

@RestController
@CrossOrigin
public class CoRestController {

	private CoService service;

	public CoRestController(CoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/notices/{appNumber}/{coStatus}")
	public ResponseEntity<List<COEntity>> getNotices(@PathVariable("appNumber") Integer appNumber,
			@PathVariable("coStatus") String coStatus) {
		List<COEntity> notices = service.getNotices(appNumber, coStatus);
		notices.forEach(System.out::println);
		return new ResponseEntity<>(notices, HttpStatus.OK);
	}

	@GetMapping("/notice/{coNoticeId}")
	public ResponseEntity<String> printNotice(@PathVariable("coNoticeId") Integer coNoticeId) {
		ResponseEntity<String> rs = null;
		try {
			boolean status = service.printNotice(coNoticeId);
			rs = status ? new ResponseEntity<>("Notice Sent", HttpStatus.OK)
					: new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@GetMapping("/sendremainder")
	public ResponseEntity<String> getNotices() {
		service.sendRemainderNotice();
		return new ResponseEntity<>("Notice sent..", HttpStatus.CREATED);
	}

}
