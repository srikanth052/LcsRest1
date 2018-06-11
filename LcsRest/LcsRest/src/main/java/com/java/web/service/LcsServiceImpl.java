package com.java.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.web.model.LCSDto;
import com.java.web.model.LcsRes;

@Service("lcsService")
public class LcsServiceImpl implements LcsService {

	@Override
	public LcsRes findLcs(List<String> setOfStrings) {
		String lcs = lcs(setOfStrings);

		List<LCSDto> lcsResList = new ArrayList<>();
		LCSDto lcsDto = new LCSDto();
		lcsDto.setValue(lcs);
		lcsResList.add(lcsDto);
		LcsRes lcsRes=new LcsRes();
		lcsRes.setLcs(lcsResList);
		return lcsRes;
	}

	public String lcs(List<String> setOfStrings) {

		// Determine size of the array
		int n = setOfStrings.size();

		// Take first word from array as reference
		String s = setOfStrings.get(0);
		int len = s.length();

		String res = "";

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {

				// generating all possible substrings
				// of our reference string arr[0]
				String stem = s.substring(i, j);
				int k = 1;
				for (k = 1; k < n; k++)

					// Check if the generated stem is
					// common to to all words
					if (!setOfStrings.get(k).contains(stem))
						break;

				// If current substring is present in
				// all strings and its length is greater
				// than current result
				if (k == n && res.length() < stem.length())
					res = stem;
			}
		}

		return res;
	}

}
