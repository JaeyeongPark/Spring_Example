package com.ssafy.guestbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Api("어드민 컨트롤러 V1")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private MemberService memberService;

	
	@ApiOperation(value = "회원목록", notes = "회원의 <big>전체 목록</big>을 리턴.")
	@ApiResponses({
		@ApiResponse(code = 404, message = "주소 오류!!"),
		@ApiResponse(code = 500, message = "서버 에러!!"),
		@ApiResponse(code = 200, message = "회원 목록 정상 처리"),
	})
	@GetMapping(value = "/user")
	public ResponseEntity<?> userList() {
		try {
			List<MemberDto> list = memberService.listMember();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@ApiOperation(value = "회원등록", notes = "회원의 정보를 받아 처리.")
	@PostMapping(value = "/user")
	public ResponseEntity<?> userRegister(@RequestBody MemberDto memberDto) {
		try {
			memberService.registerMember(memberDto);
			List<MemberDto> list = memberService.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@ApiOperation(value = "회원정보", notes = "회원한명에 대한 정보.")
	@GetMapping(value = "/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userid) {
		try {
			logger.debug("파라미터 : {}", userid);
			MemberDto memberDto = memberService.getMember(userid);
			if(memberDto != null)
				return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원정보수정", notes = "회원정보를 수정합니다.")
	@PutMapping(value = "/user")
	public ResponseEntity<?> userModify(@RequestBody MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			List<MemberDto> list = memberService.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원정보삭제", notes = "회원정보를 삭제합니다.")
	@DeleteMapping(value = "/user/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userid) {
		try {
			memberService.deleteMember(userid);
			List<MemberDto> list = memberService.listMember();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
