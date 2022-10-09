package org.coody.reduce.controller;

import org.coody.framework.core.annotation.AutoBuild;
import org.coody.framework.core.util.CommonUtil;
import org.coody.framework.minicat.http.iface.MinicatServletResponse;
import org.coody.framework.minicat.web.annotation.PathBinding;
import org.coody.reduce.common.util.PECode;
import org.coody.reduce.controller.base.BaseController;
import org.coody.reduce.domain.ShortInfo;
import org.coody.reduce.queue.FrequencyQueue;
import org.coody.reduce.service.ShortService;
import org.coody.reduce.service.UserService;

@PathBinding("/")
public class ReduceController extends BaseController {

	@AutoBuild
	UserService userService;
	@AutoBuild
	ShortService shortService;
	@AutoBuild
	FrequencyQueue frequencyQueue;

	@PathBinding("/{code}")
	public void trigger(String code) {
		if (CommonUtil.isNullOrEmpty(code)) {
			return;
		}
		System.out.println(code);
		Long id = PECode.decode(code);
		if (id == null) {
			return;
		}
		ShortInfo info = shortService.fromId(id);
		if (info == null || info.getStatus() != 1) {
			return;
		}
		frequencyQueue.addFrequency(info.getId());

		response.sendRedirect(info.getUrl());
	}

	
	@PathBinding("/index.do")
	public void index(String code, MinicatServletResponse response) {
		response.sendRedirect("/html/login.html");
		;
	}
}
