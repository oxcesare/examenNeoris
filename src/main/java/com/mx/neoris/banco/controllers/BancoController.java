package com.mx.neoris.banco.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banco/v1.0")
public class BancoController {
	@GetMapping("/version")
	public ResponseEntity<String> inicial() {
		return new ResponseEntity<String>("Examen - César Ricardo Alducin Ruiz - Neoris Mexico" + "22 Noviembre 2022",
				HttpStatus.OK);
	}
}
