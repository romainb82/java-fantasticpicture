package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import service.IFileEdi;

public class FileEdi implements IFileEdi {

	@Override
	public void writeNote(String texte, String currentImage, String user) {
		try {
			String pathFile = "src/note.txt";

			File fichier = new File(pathFile);

			List<String> lines = Files.readAllLines(Paths.get(fichier.toURI()));

			int index = -1;

			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).contains(currentImage) & lines.get(i).contains(user)) {
					index = i;
					break;
				}
			}

			if (index != -1) {
				lines.set(index, texte);
			} else {
				lines.add(texte);
			}

			Files.write(Paths.get(fichier.toURI()), lines);
		} catch (IOException err) {
			System.err.println(err.getMessage());
		}
	}

	@Override
	public String readNote(String person, String picture) {
		Path filePath = Paths.get("src/note.txt");

		if (!Files.exists(filePath)) {
			return "nul";
		}

		try {
			List<String> lines = Files.readAllLines(filePath);

			for (String line : lines) {
				if (line.startsWith(person + ";" + picture + ";")) {
					String[] parts = line.split(";");
					if (parts.length >= 3) {
						return parts[2];
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "nul";
	}
}
