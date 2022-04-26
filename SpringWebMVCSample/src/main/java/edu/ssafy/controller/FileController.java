package edu.ssafy.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.dto.FileDTO;
import edu.ssafy.util.DBUtil;


@Controller
public class FileController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	DataSource source;
	
	@GetMapping("/filepage")
	public String filePage() {
		return "redirect:filelist";
	}
	
	@PostMapping("/fileupload")
	public String fileUpload(@RequestParam("upfile") MultipartFile[] files, Model m, RedirectAttributes ra) throws IllegalStateException, IOException, SQLException {
		
		System.out.println("파일 업로드 : "  );
		String path = servletContext.getRealPath("/upload");
		System.out.println(path);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String saveFolder = path+File.separator+today;
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		Connection conn = source.getConnection();
		String sql = "insert into files(path,name) value(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ArrayList<FileDTO> list = new ArrayList();
		
		for (MultipartFile mFile : files) {
			String oname = mFile.getOriginalFilename();
			if(!oname.isEmpty()) {
				pstmt.setString(1, saveFolder);
				pstmt.setString(2, oname);
				pstmt.executeUpdate();
				mFile.transferTo(new File(folder, oname));
			}
		}
		
		DBUtil.close(pstmt,conn);
		return "redirect:filelist";
	}
	
	@RequestMapping("/filelist")
	public String fileList(Model m) throws SQLException {
		Connection conn = source.getConnection();
		String sql = "select no, path, name from files";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList <FileDTO> list = new ArrayList<FileDTO>();
		while(rs.next()) {
			list.add(new FileDTO(rs.getString("no"),rs.getString("path"),rs.getString("name")));
		}
		DBUtil.close(rs,pstmt,conn);
		m.addAttribute("files", list);
		return "filepage";
	}
	
	@RequestMapping("/filedownload")
	public ModelAndView fileDownload(String no,ModelAndView mv) throws SQLException {
		Connection conn = source.getConnection();
		String sql = "select no, path, name from files where no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		FileDTO fdto=null;
		while(rs.next()) {
			fdto = new FileDTO(rs.getString("no"),rs.getString("path"),rs.getString("name"));
		}
		DBUtil.close(rs,pstmt,conn);
		System.out.println(fdto);
		File file = new File(fdto.getPath()+File.separator+fdto.getName());
		
		mv.addObject("downloadfile",file);
		mv.setViewName("filedownload");
		
		return mv;
	}
}


