package com.ssafy.guestbook.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.guestbook.model.FileInfoDto;
import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.GuestBookService;
import com.ssafy.util.PageNavigation;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	private static final Logger logger = LoggerFactory.getLogger(GuestBookController.class);
	
	@Autowired
	private GuestBookService guestBookService;
	
	@Autowired
	ResourceLoader resLoader;

	@GetMapping("/register")
	public String register() {
		return "guestbook/write";
	}

	@PostMapping("/register")
	public String register(GuestBookDto guestBookDto, @RequestParam("upfile") MultipartFile[] files, Model model,
			HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		guestBookDto.setUserId(memberDto.getUserId());

//		FileUpload 관련 설정.
//		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		if (!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
			// 파일을 저장할 폴더 지정
			Resource res = resLoader.getResource("classpath:static/upload");
			String canonicalPath = res.getFile().getCanonicalPath();
			logger.debug("file upload canonical path : {}", canonicalPath);
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = canonicalPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = System.nanoTime()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			guestBookDto.setFileInfos(fileInfos);
		}

		guestBookService.registerArticle(guestBookDto);
		redirectAttributes.addAttribute("pg", 1);
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		redirectAttributes.addFlashAttribute("msg", "글작성 성공!!!");
		return "redirect:/guestbook/list";
	}

	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();

		String spp = map.get("spp"); // size per page (페이지당 글갯수)
		map.put("spp", spp != null ? spp : "10");
		List<GuestBookDto> list = guestBookService.listArticle(map);
		PageNavigation pageNavigation = guestBookService.makePageNavigation(map);
		mav.addObject("articles", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("guestbook/list");
		return mav;
	}

	@GetMapping("/modify")
	public ModelAndView modify(@RequestParam("articleno") int articleNo) throws Exception {
		ModelAndView mav = new ModelAndView();
		GuestBookDto guestBookDto = guestBookService.getArticle(articleNo);
		mav.addObject("article", guestBookDto);
		mav.setViewName("guestbook/modify");
		return mav;
	}

	@PostMapping("/modify")
	public String modify(GuestBookDto guestBookDto, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		guestBookService.updateArticle(guestBookDto);
		redirectAttributes.addAttribute("pg", 1);
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		redirectAttributes.addFlashAttribute("msg", "글수정 성공!!!");
		return "redirect:/guestbook/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		Resource res = resLoader.getResource("classpath:static/upload");
		String canonicalPath = res.getFile().getCanonicalPath();
		guestBookService.deleteArticle(articleNo, canonicalPath);
		redirectAttributes.addAttribute("pg", 1);
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		redirectAttributes.addFlashAttribute("msg", "글삭제 성공!!!");
		return "redirect:/guestbook/list";
	}
	
//	@GetMapping(value = "/download")
//	public String downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
//			@RequestParam("sfile") String sfile, Model model, HttpSession session) {
//		System.out.println("다운로드 한다!!");
//		Map<String, Object> fileInfo = new HashMap<String, Object>();
//		fileInfo.put("sfolder", sfolder);
//		fileInfo.put("ofile", ofile);
//		fileInfo.put("sfile", sfile);
//		model.addAttribute("downloadFile", fileInfo);
//		return "fileDownLoadView";
//	}
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam Map<String, Object> param, HttpServletRequest request) throws IOException{
		Resource res = resLoader.getResource("classpath:static/upload");
		String canonicalPath = res.getFile().getCanonicalPath();
		String filePath = canonicalPath + File.separator + param.get("sfolder") + File.separator + param.get("sfile");
		File target = new File(filePath);
		HttpHeaders header = new HttpHeaders();
		Resource rs = null;
		if(target.exists()) {
			try {
				String mimeType = Files.probeContentType(Paths.get(target.getAbsolutePath()));
				if(mimeType == null) {
					mimeType = "apllication/download; charset=UTF-8";
				}
				rs = new UrlResource(target.toURI());
				String userAgent = request.getHeader("User-Agent");
		        boolean isIE = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1;
				String fileName = null;
				String originalFile = (String) param.get("ofile");
		        // IE는 다르게 처리
		        if (isIE) {
		        	fileName = URLEncoder.encode(originalFile, "UTF-8").replaceAll("\\+", "%20");
		        } else {
		            fileName = new String(originalFile.getBytes("UTF-8"), "ISO-8859-1");
		        }
				header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ fileName +"\"");
				header.setCacheControl("no-cache");
				header.setContentType(MediaType.parseMediaType(mimeType));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<Resource>(rs, header, HttpStatus.OK);
	}

}
