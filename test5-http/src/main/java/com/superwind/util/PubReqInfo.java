package com.superwind.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PubReqInfo {
	private String clientIp;
	private String clientMac;
	private String clientImei;
	private String clientType;
	private String callTime;
	private String callMethod;
	private String systemType;
	private String manufactor;
	private String resolution;
//	private String language;
//	private String country;
	private String version;
	private String reserv1;
	private String reserv2;
}
