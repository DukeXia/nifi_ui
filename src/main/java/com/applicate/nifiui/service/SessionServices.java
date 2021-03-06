package com.applicate.nifiui.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.microsoft.sqlserver.jdbc.StringUtils;

@Service
public class SessionServices {
	
	@Value("${default.lob}")
	private String defaultLob;

	public Object get(String key) {
		HttpSession session = getRequest().getSession();
		return session.getAttribute(key);
	}
	
	public String getLob(){
		String lob = (String) get("lob");
		return StringUtils.isEmpty(lob)?defaultLob:lob;
	}

	private HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(requestAttributes!=null) {
			return requestAttributes.getRequest();
		}
		return null;
	}
	
	
}
