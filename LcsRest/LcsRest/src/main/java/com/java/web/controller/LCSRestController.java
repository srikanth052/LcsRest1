package com.java.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.model.LCSDto;
import com.java.web.model.LCSReq;
import com.java.web.model.LcsRes;
import com.java.web.service.LcsService;

@RestController
public class LCSRestController {

	@Autowired
	LcsService lcsService;

	@RequestMapping(value = "/lcs/", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody LCSReq lcs) {
		
		if (lcs.getSetOfStrings() == null || CollectionUtils.isEmpty(lcs.getSetOfStrings())) {
			return new ResponseEntity<Object>("setOfStrings should not be empty", HttpStatus.NO_CONTENT);
		}
		
		List<String> stringList = new ArrayList<>();
		
		for (LCSDto ls : lcs.getSetOfStrings()) {
			if(stringList.contains(ls.getValue())){
				return new ResponseEntity<Object>("setOfStrings should be unique",
						HttpStatus.NON_AUTHORITATIVE_INFORMATION);
			} else{
				stringList.add(ls.getValue());
			}
		}

		LcsRes lcsRes = lcsService.findLcs(stringList);

		return new ResponseEntity<Object>(lcsRes, HttpStatus.OK);
	}

}