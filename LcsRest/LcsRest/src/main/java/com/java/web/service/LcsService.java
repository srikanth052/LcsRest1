package com.java.web.service;

import java.util.List;
import java.util.Set;

import com.java.web.model.LCSDto;
import com.java.web.model.LcsRes;

public interface LcsService {

public LcsRes findLcs(List<String> lcsDto);

}
