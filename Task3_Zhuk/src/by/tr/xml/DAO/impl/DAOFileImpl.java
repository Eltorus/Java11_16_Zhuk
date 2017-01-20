package by.tr.xml.DAO.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import by.tr.xml.DAO.DAOFile;
import by.tr.xml.DAO.exception.DAOException;

public class DAOFileImpl implements DAOFile {
	private String url;
	private BufferedReader br;
	private String currentLine;

	public String getLexeme() throws DAOException {
		int i = 0;
		StringBuilder output = new StringBuilder();
		String lexeme = null;
		try {
			if (currentLine == null || currentLine.equals("")) {
				currentLine = br.readLine();
			}
			if (currentLine != null) {
				currentLine = currentLine.trim();
				char[] line = currentLine.toCharArray();
				if (line[0] == '<') {
					for (i = 0; i < line.length; i++) {
						output.append(line[i]);
						if (line[i] == '>') {
							i++; //инкрементируем значение i, чтобы обрезанная в дальнейшем строка начиналась с символа
								 //следующего за '>'
							break;
						}
					}
				} else {
					for (i = 0; i < line.length; i++) {
						if (line[i] != '<') {
							output.append(line[i]);
						} else {
							break;
						}
					}
				}
				currentLine = currentLine.substring(i);
				return lexeme = output.toString();
			}
		} catch (IOException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return lexeme;
	}

	@Override
	public void setFile(String filename) throws DAOException {
		this.url = filename;
		try {
			FileInputStream fis = new FileInputStream(url);
			br = new BufferedReader(new InputStreamReader(fis));
		} catch (FileNotFoundException e) {
			throw new DAOException("Couldnt find file...");
		}
	}

	@Override
	public void close() throws DAOException {
		try {
			br.close();
		} catch (IOException e) {
			try {
				br.close();
			} catch (IOException e1) {
				throw new DAOException(e);
			}
		}
	}

}
