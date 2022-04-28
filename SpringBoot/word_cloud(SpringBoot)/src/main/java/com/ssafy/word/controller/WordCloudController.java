package com.ssafy.word.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.word.model.WordDto;
import com.ssafy.word.model.service.WordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@Api("구름 컨트롤러 V1")
public class WordCloudController {

	private static final Logger logger = LoggerFactory.getLogger(WordCloudController.class);
	
	@Autowired
	private WordService wordService;
	
	@ApiOperation(value = "관심사 목록", notes = "관심사의 <big>전체 목록</big>을 리턴.")
	@ApiResponses({
		@ApiResponse(code = 404, message = "주소 오류!!"),
		@ApiResponse(code = 500, message = "서버 에러!!"),
		@ApiResponse(code = 200, message = "회원 목록 정상 처리"),
	})
	
	
	@GetMapping("/word")
	public ResponseEntity<List<WordDto>> listWord() {
		logger.debug("listWord - 호출");
		return new ResponseEntity<>(wordService.listWord(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심사 등록", notes = "관심사 입력")
	@PostMapping("/word")
	public ResponseEntity<List<WordDto>> registWord(@RequestParam(value = "concerns[]") List<String> concerns) {
		logger.debug("registWord - 호출");
		wordService.registWord(concerns);
		return new ResponseEntity<>(wordService.listWord(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심사 선택", notes = "관심사 선택 후 점수 올림")
	@PostMapping("/word/{word}")
	public ResponseEntity<List<WordDto>> updateWordCount(@PathVariable("word") String word) {
		logger.debug("updateWordCount - 호출");
		wordService.updateCount(word);
		return new ResponseEntity<>(wordService.listWord(), HttpStatus.OK);
	}
	
}
