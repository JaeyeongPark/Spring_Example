package edu.ssafy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownload extends AbstractView{
	
	public void Download() {
		setContentType("application/download charset=utf-8"); 
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String path = (String) model.get("downloadfile");
//		System.out.println(path);
		File file = (File) model.get("downloadfile");
		
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		
		String fileName = null;
		
		if(request.getHeader("User-Agent").indexOf("MSIE")>-1) {
			fileName = URLEncoder.encode(file.getName(),"utf-8");
		}else {
			fileName = new String(file.getName().getBytes("utf-8"),"ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
		response.setHeader("content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		fis = new FileInputStream(file);
		FileCopyUtils.copy(fis, out);
		out.flush();
	}

}
