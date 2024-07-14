package com.data.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.binding.InputDataBinding;
import com.data.binding.StateAndSSNNumber;
import com.data.service.MyService;

@RestController
public class SSNRestController {

	private MyService ser;

	public SSNRestController(MyService ser) {
		super();
		this.ser = ser;
	}

	@PostMapping(value = "/getstate")
	public ResponseEntity<StateAndSSNNumber> stateFinder(@RequestBody InputDataBinding bindingObj) {
		InputDataBinding inputDataBinding = new InputDataBinding(bindingObj.getName(), bindingObj.getDob(),
				bindingObj.getSsn());
		StateAndSSNNumber states = ser.getStates(inputDataBinding);
		if (states != null)
			return new ResponseEntity<>(states, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/get/{ssn}")
	public ResponseEntity<String> getStateBySSN(@PathVariable("ssn") String ssn) {
		String state = ser.getStateBySSN(ssn);
		if (state != null && !state.isEmpty())
			return new ResponseEntity<>(state, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
