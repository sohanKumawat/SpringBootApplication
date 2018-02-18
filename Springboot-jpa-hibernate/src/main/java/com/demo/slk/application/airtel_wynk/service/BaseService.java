package com.demo.slk.application.airtel_wynk.service;

import com.demo.slk.application.airtel_wynk.pojo.BasePojo;

public interface BaseService {
	 <T extends BasePojo> String save(BasePojo pojo) throws Throwable;
	 String delete(String id) throws Throwable;
}
