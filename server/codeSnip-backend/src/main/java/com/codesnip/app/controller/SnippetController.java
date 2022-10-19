package com.codesnip.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesnip.app.dto.SnippetDto;
import com.codesnip.app.service.SnippetService;

@RestController
public class SnippetController {

	@Autowired
	private Environment environment;

	@Autowired
	private SnippetService snippetService;

	@GetMapping("/snippet")
	public ResponseEntity<List<SnippetDto>> getAllSnippets() throws Exception {
		List<SnippetDto> snippetDtos = snippetService.readAll();
		return new ResponseEntity<>(snippetDtos, HttpStatus.OK);
	}

}
