package com.superwind.test7qrcode;

import com.superwind.test7qrcode.common.QRCodeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.IOException;

import com.google.zxing.WriterException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test7QrcodeApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testQrcode() {
		String content="http://release-img-server.oss-cn-shenzhen.aliyuncs.com/fozhu.png";
		String logUri="D:\\java-test\\test7-qrcode\\logo.jpg";
		String outFileUri="D:\\java-test\\test7-qrcode\\qrcode.jpg";
		int[]  size=new int[]{430,430};
		String format = "jpg";

		try {
			new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri, size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
