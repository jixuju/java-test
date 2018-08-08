package com.superwind;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.superwind.util.CommonReq;
import com.superwind.util.HttpClientUtil;
import com.superwind.util.JsonUtil;
import com.superwind.util.PubReqInfo;
import com.superwind.util.StringUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import sun.nio.ch.IOUtil;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTestApplicationTests {

	@Test
	public void testOkHttpBlockRead1() throws IOException, InterruptedException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"pubInfo\":{\n    \"clientIp\":\"192.168.1.101\",\n    \"clientMac\":\"ER:23:34:RE:76\",\n    \"clientImei\":\"21312313\",\n    \"clientType\":\"00\",\n    \"callTime\":\"20160521094000\",\n    \"callMethod\":\"UM_QryUser\",\n    \"language\":\"en\",\n    \"country\":\"US\",\n    \"version\":\"1.0.0.alpha\"\n},\n\"phoneId\":\"18602040310\",\n\"reserv1\":\"\",\n\"reserv2\":\"\",\n\"reserv3\":\"\",\n\"reserv4\":\"\"\n}");
		Request request = new Request.Builder()
				.url("http://112.74.143.77:8880/api/user/qryUser")
				.post(body)
				.addHeader("ContentType", "application/json; charset=UTF-8")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept-Language", "en_US")
				.addHeader("version", "1.1.0")
				.addHeader("wenwenId", "10001")
				.addHeader("wenwenrpcauthkey", "g/+3OtTTd8g=")
				.addHeader("cache-control", "no-cache")
				.addHeader("Connection", "Keep-Alive")
				.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5")
//				.addHeader("postman-token", "15fb9874-eeb7-9c39-50f7-0e168193afc7")
				.build();
		for (int i = 0; i< 20; i ++) {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful())
			{
				Reader reader = response.body().charStream();
				StringBuilder sb = new StringBuilder();
				char cb[] = new char[25];
				int len = 0;
				while ((len = reader.read(cb)) == 25) {
					sb.append(cb);
				}
				sb.append(cb);
				JSONObject obj = new JSONObject(sb.toString());
				System.out.println("服务器响应为: " + obj.toString());
			}
			Thread.sleep(20L);
		}
	}

	@Test
	public void testOkHttpBlockRead2() throws IOException, InterruptedException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"pubInfo\":{\n" +
				"    \"clientIp\":\"192.168.31.113\",\n" +
				"    \"clientMac\":\"48:8A:D2:1E:1F:D1\",\n" +
				"    \"clientImei\":\"21312313\",\n" +
				"    \"clientType\":\"22\",\n" +
				"    \"callTime\":\"20160713121212\",\n" +
				"    \"callMethod\":\"qrySweetStatInfo\"\n" +
				"},\n" +
				"    \"wenwenId\":10001\n" +
				"}");
		Request request = new Request.Builder()
				.url("http://112.74.143.77:8880/api/user/index")
				.post(body)
				.addHeader("ContentType", "application/json; charset=UTF-8")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept-Language", "en_US")
				.addHeader("version", "1.1.0")
				.addHeader("wenwenId", "10001")
				.addHeader("wenwenrpcauthkey", "g/+3OtTTd8g=")
				.addHeader("cache-control", "no-cache")
				.addHeader("Connection", "Keep-Alive")
				.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5")
//				.addHeader("postman-token", "15fb9874-eeb7-9c39-50f7-0e168193afc7")
				.build();
		for (int i = 0; i< 20; i ++) {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful())
			{
				Reader reader = response.body().charStream();
				StringBuilder sb = new StringBuilder();
				char cb[] = new char[1024];
				int len = 0;
				while ((len = reader.read(cb)) == 1024) {
					sb.append(cb);
				}
				sb.append(cb);
				JSONObject obj = new JSONObject(sb.toString());
				System.out.println("服务器响应为: " + obj.toString());
			}
			Thread.sleep(20L);
		}
	}

	@Test
	public void testOkHttpSingleRead() throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"pubInfo\":{\n    \"clientIp\":\"192.168.1.101\",\n    \"clientMac\":\"ER:23:34:RE:76\",\n    \"clientImei\":\"21312313\",\n    \"clientType\":\"00\",\n    \"callTime\":\"20160521094000\",\n    \"callMethod\":\"UM_QryUser\",\n    \"language\":\"en\",\n    \"country\":\"US\",\n    \"version\":\"1.0.0.alpha\"\n},\n\"phoneId\":\"18602040310\",\n\"reserv1\":\"\",\n\"reserv2\":\"\",\n\"reserv3\":\"\",\n\"reserv4\":\"\"\n}");
		Request request = new Request.Builder()
				.url("http://120.25.196.51:8880/api/user/qryUser")
				.post(body)
				.addHeader("ContentType", "application/json; charset=UTF-8")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept-Language", "en_US")
				.addHeader("version", "1.1.0")
				.addHeader("wenwenid", "10073")
				.addHeader("wenwenrpcauthkey", "g/+3OtTTd8g=")
				.addHeader("cache-control", "no-cache")
				.addHeader("Connection", "Keep-Alive")
				.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5")
//				.addHeader("postman-token", "15fb9874-eeb7-9c39-50f7-0e168193afc7")
				.build();
		Response response = client.newCall(request).execute();
		if(response.isSuccessful())
		{
			Reader reader = response.body().charStream();
			StringBuilder sb = new StringBuilder();
			int v=0;
			while((v=reader.read())!=-1)
			{
				if (942 == sb.toString().length()) {
					System.out.println(sb.toString());
				}
				sb.append((char)v);
			}

			JSONObject obj = new JSONObject(response.body().charStream().toString());
			System.out.println("服务器响应为: " + obj.toString());
		}
	}

	@Test
	public void testOkHttpString() throws IOException, InterruptedException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"pubInfo\":{\n    \"clientIp\":\"192.168.1.101\",\n    \"clientMac\":\"ER:23:34:RE:76\",\n    \"clientImei\":\"21312313\",\n    \"clientType\":\"00\",\n    \"callTime\":\"20160521094000\",\n    \"callMethod\":\"UM_QryUser\",\n    \"language\":\"en\",\n    \"country\":\"US\",\n    \"version\":\"1.0.0.alpha\"\n},\n\"phoneId\":\"18602040310\",\n\"reserv1\":\"\",\n\"reserv2\":\"\",\n\"reserv3\":\"\",\n\"reserv4\":\"\"\n}");
		Request request = new Request.Builder()
				.url("http://debug.wenwen8.com:8880/api/user/qryUser")
				.post(body)
				.addHeader("ContentType", "application/json; charset=UTF-8")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept-Language", "en_US")
				.addHeader("version", "1.1.0.alpha")
				.addHeader("wenwenid", "10073")
				.addHeader("wenwenrpcauthkey", "g/+3OtTTd8g=")
				.addHeader("cache-control", "no-cache")
//				.addHeader("Connection", "Keep-Alive")
				.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5")
//				.addHeader("postman-token", "15fb9874-eeb7-9c39-50f7-0e168193afc7")
				.build();
		for (int i = 0; i < 20; i ++) {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful())
			{
				String str = response.body().string();
				System.out.println("服务器响应为: " + str);
			}
			Thread.sleep(20L);
		}

	}

	@Test
	public void testOkHttpByteStream() throws IOException, InterruptedException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"pubInfo\":{\n    \"clientIp\":\"192.168.1.101\",\n    \"clientMac\":\"ER:23:34:RE:76\",\n    \"clientImei\":\"21312313\",\n    \"clientType\":\"00\",\n    \"callTime\":\"20160521094000\",\n    \"callMethod\":\"UM_QryUser\",\n    \"language\":\"en\",\n    \"country\":\"US\",\n    \"version\":\"1.0.0.alpha\"\n},\n\"phoneId\":\"18602040310\",\n\"reserv1\":\"\",\n\"reserv2\":\"\",\n\"reserv3\":\"\",\n\"reserv4\":\"\"\n}");
		Request request = new Request.Builder()
//				.url("http://112.74.143.77:8880/api/user/qryUser")
				.url("http://debug.wenwen8.com:8880/api/user/qryUser")
				.post(body)
				.addHeader("ContentType", "application/json; charset=UTF-8")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept-Language", "en_US")
				.addHeader("version", "1.1.0.alpha")
				.addHeader("wenwenid", "10073")
				.addHeader("wenwenrpcauthkey", "g/+3OtTTd8g=")
				.addHeader("cache-control", "no-cache")
				.addHeader("Connection", "close")
//				.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5")
//				.addHeader("postman-token", "15fb9874-eeb7-9c39-50f7-0e168193afc7")
				.build();
		for (int i = 0; i < 20; i++) {
			Response mResponse = client.newCall(request).execute();
			String n = mResponse.header("length");
//			if (mResponse.code() == 200) {
				InputStream inputStream = mResponse.body().byteStream();
			int nn = inputStream.available();

//				String result = StringUtils.istream2Str(inputStream);

//				String result = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));

//				String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

//				StringWriter writer = new StringWriter();
//				IOUtils.copy(inputStream, writer, "UTF-8");
//				String result = writer.toString();

//				String result = new BufferedReader(new InputStreamReader(inputStream))
//						.lines().collect(Collectors.joining("\n"));

//				String result = new BufferedReader(new InputStreamReader(inputStream))
//						.lines().parallel().collect(Collectors.joining("\n"));

				final int bufferSize = 32;
				final char[] buffer = new char[bufferSize];
				final StringBuilder out = new StringBuilder();
				Reader in = new InputStreamReader(inputStream, "UTF-8");
				while(true) {
					int len = in.read(buffer, 0, buffer.length);
					System.out.println(len);
					System.out.println(buffer);
					if (len < 0)
						break;
					out.append(buffer, 0, len);
				}
				String result = out.toString();

//				StringBuilder sb = new StringBuilder();
//				BufferedReader reader  = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//				reader.ready();
//				String input;
//				while ((input= reader.readLine()) != null) {
//					sb.append(new String(input.getBytes("utf-8")));
//				}
//				String result = sb.toString();

//				StringWriter writer = new StringWriter();
//				IOUtils.copy(inputStream, writer, "UTF-8");
//				String result = writer.toString();

//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1024];
//				int length;
//				while ((length = inputStream.read(buffer)) != -1) {
//					byteArrayOutputStream.write(buffer, 0, length);
//				}
//				String result = byteArrayOutputStream.toString("UTF-8");

			System.out.println("服务器响应为: " + result);
//			}
			Thread.sleep(20L);
		}
	}

	@Test
	public void testRestTemplate() throws IOException, InterruptedException {
		//设置请求的header
		HttpHeaders headers = new HttpHeaders();
		headers.set("ContentType", "application/json; charset=UTF-8");
		headers.set("Content-Type", "application/json");
		headers.set("Accept-Language", "en_US");
		headers.set("version", "1.1.0");
		headers.set("wenwenid", "10001");
		headers.set("wenwenrpcauthkey", "g/+3OtTTd8g=");
		headers.set("cache-control", "no-cache");
		headers.set("Connection", "Keep-Alive");
		headers.set("Accept-Encoding", "gzip;q=1.0, compress;q=0.5");

		//设置请求参数
		HttpEntity<String> request = new HttpEntity<String>("{\"pubInfo\":{\n" +
				"    \"clientIp\":\"192.168.31.113\",\n" +
				"    \"clientMac\":\"48:8A:D2:1E:1F:D1\",\n" +
				"    \"clientImei\":\"21312313\",\n" +
				"    \"clientType\":\"22\",\n" +
				"    \"callTime\":\"20160713121212\",\n" +
				"    \"callMethod\":\"qrySweetStatInfo\"\n" +
				"}\n" +
				"}", headers);

		//发送请求
		for (int i = 0; i < 20; i ++) {
			RestTemplate restTemplate = new RestTemplate();
			Object response = restTemplate.postForObject("http://112.74.143.77:8880/api/user/index", request, Object.class);
//			String response = restTemplate.postForObject("http://120.25.196.51:8880/api/user/index", request, String.class);

			String result = JsonUtil.toJSON(response);
			System.out.println("服务器响应为: " + result);
			Thread.sleep(20L);
		}
	}

	@Test
	public void testHttpClient() throws IOException, InterruptedException {
		//设置请求的header
		HttpPost httpPost = new HttpPost("http://112.74.143.77:8880/api/user/qryUser");
		httpPost.addHeader("ContentType", "application/json; charset=UTF-8");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Accept-Language", "en_US");
		httpPost.addHeader("version", "1.1.0");
		httpPost.addHeader("wenwenid", "10001");
		httpPost.addHeader("wenwenrpcauthkey", "g/+3OtTTd8g=");
		httpPost.addHeader("cache-control", "no-cache");
		httpPost.addHeader("Connection", "Keep-Alive");
		httpPost.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5");

		//设置请求参数
		StringEntity se = new StringEntity("{\"pubInfo\":{\n" +
				"    \"clientIp\":\"192.168.1.101\",\n" +
				"    \"clientMac\":\"ER:23:34:RE:76\",\n" +
				"    \"clientImei\":\"21312313\",\n" +
				"    \"clientType\":\"00\",\n" +
				"    \"callTime\":\"20160521094000\",\n" +
				"    \"callMethod\":\"UM_QryUser\",\n" +
				"    \"language\":\"en\",\n" +
				"    \"country\":\"US\",\n" +
				"    \"version\":\"1.0.0.alpha\"\n" +
				"},\n" +
				"\"phoneId\":\"18602040310\",\n" +
				"\"reserv1\":\"\",\n" +
				"\"reserv2\":\"\",\n" +
				"\"reserv3\":\"\",\n" +
				"\"reserv4\":\"\"\n" +
				"}");
		//发送请求
		for (int i = 0; i < 20; i ++) {
			httpPost.setEntity(se);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();
			httpClient.close();
			System.out.println("服务器响应为: " + response);
			Thread.sleep(20L);
		}
	}

	@Test
	public void testHttpUrlConn() throws IOException, InterruptedException {
		//创建连接
		URL urlObj = new URL("http://112.74.143.77:8880/api/user/qryUser");
		HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("ContentType", "application/json; charset=UTF-8");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept-Language", "en_US");
		connection.setRequestProperty("version", "1.1.0");
		connection.setRequestProperty("wenwenid", "10001");
		connection.setRequestProperty("wenwenrpcauthkey", "g/+3OtTTd8g=");
		connection.setRequestProperty("cache-control", "no-cache");
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Accept-Encoding", "gzip;q=1.0, compress;q=0.5");
		connection.connect();

		// POST请求
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes("{\"pubInfo\":{\n" +
				"    \"clientIp\":\"192.168.1.101\",\n" +
				"    \"clientMac\":\"ER:23:34:RE:76\",\n" +
				"    \"clientImei\":\"21312313\",\n" +
				"    \"clientType\":\"00\",\n" +
				"    \"callTime\":\"20160521094000\",\n" +
				"    \"callMethod\":\"UM_QryUser\",\n" +
				"    \"language\":\"en\",\n" +
				"    \"country\":\"US\",\n" +
				"    \"version\":\"1.0.0.alpha\"\n" +
				"},\n" +
				"\"phoneId\":\"18602040310\",\n" +
				"\"reserv1\":\"\",\n" +
				"\"reserv2\":\"\",\n" +
				"\"reserv3\":\"\",\n" +
				"\"reserv4\":\"\"\n" +
				"}");
		out.flush();
		out.close();

		// 读取响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		int n = connection.getContentLength();
		String lines;
		StringBuffer sb = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = URLDecoder.decode(lines, "utf-8");
			sb.append(lines);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		System.out.println("服务器响应为: " + sb);
	}
}
