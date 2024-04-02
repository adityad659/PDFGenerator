package com.example.pdfgenerator.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class APIResponse {

	public APIResponse() {
	}

	@JsonProperty("content")
	private Object data = null;

	@JsonProperty("page_size")
	private Integer pageSize;

	@JsonProperty("response")
	private CommonResponse response = new CommonResponse();

	@JsonProperty("page")
	@JsonInclude(value = Include.NON_NULL)
	private PageResponse pageResponse;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("timestamp")
	private Long timestamp;

	public APIResponse(Object obj) {
		data = obj;
	}

	public APIResponse(Object obj, Long timestamp) {
		data = obj;
		response.setTimestamp(timestamp);
	}

	public APIResponse(Object obj, String message) {
		data = obj;
		response.setMessage(message);
	}

	public static APIResponse ok(Object objects) {
		return new APIResponse(objects);
	}

	public static APIResponse ok(Object objects, Long timestamp) {
		return new APIResponse(objects, timestamp);
	}

	public static APIResponse ok() {
		return new APIResponse();
	}

	public static APIResponse ok(Object objects, String message) {
		return new APIResponse(objects, message);
	}

	public static APIResponse ok(String message) {
		APIResponse resp = new APIResponse();
		resp.setData(Arrays.asList());
		resp.getResponse().setMessage(message);
		return resp;
	}

	public static APIResponse submit(String message) {
		APIResponse resp = new APIResponse();
		resp.setData(null);
		resp.getResponse().setMessage(message);
		return resp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static APIResponse error(String message) {
		APIResponse resp = new APIResponse();
		resp.setData(Arrays.asList());
		resp.getResponse().setError(1);
		resp.getResponse().setMessage(message);
		return resp;
	}

	public static APIResponse error(int code, String message) {
		APIResponse resp = new APIResponse();
		resp.setData(Arrays.asList());
		resp.getResponse().setError(code);
		resp.getResponse().setMessage(message);
		return resp;
	}

	public static APIResponse error(Object code, String message) {
		APIResponse resp = new APIResponse();
		resp.setData(code);
		resp.getResponse().setError(1);
		resp.getResponse().setMessage(message);
		return resp;
	}

	public CommonResponse getResponse() {
		return response;
	}

	public void setResponse(CommonResponse response) {
		this.response = response;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public APIResponse(Object obj, PageResponse page) {
		data = obj;
		pageResponse = page;
	}

	public static APIResponse ok(Object objects, PageResponse page) {
		return new APIResponse(objects, page);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
