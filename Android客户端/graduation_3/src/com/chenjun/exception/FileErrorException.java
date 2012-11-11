package com.chenjun.exception;

@SuppressWarnings("serial")
public class FileErrorException extends Exception {
	public FileErrorException(String message){
		super(message);
	}
}
