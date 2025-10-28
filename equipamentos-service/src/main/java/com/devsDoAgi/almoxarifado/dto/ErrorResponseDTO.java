package com.devsDoAgi.almoxarifado.dto;

public record ErrorResponseDTO(String message, int status, String timestamp, String path) {
}
