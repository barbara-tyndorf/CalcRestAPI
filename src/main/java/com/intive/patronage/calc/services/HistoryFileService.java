package com.intive.patronage.calc.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intive.patronage.calc.config.CalcConfig;
import com.intive.patronage.calc.errors.CreateFolderException;
import com.intive.patronage.calc.errors.FileStorageException;
import com.intive.patronage.calc.errors.FilesLoadException;
import com.intive.patronage.calc.model.CalcOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Slf4j
@Service
public class HistoryFileService implements HistoryService {

	private CalcConfig calcConfig;

	private final String root;

	private final Path rootHistory;

	private final String LOG_FILE_NAME = "historia_obliczeń.txt";

	private final List<CalcOperation> operationsHistory = new ArrayList<>();

	private final int MAX_ENTRIES;

	private ObjectMapper objectMapper;

	@Value("classpath:operations/operations.txt")
	private Resource operations;

	public HistoryFileService(CalcConfig calcConfig) {
		this.calcConfig = calcConfig;
		root = System.getProperty("java.io.tmpdir");
		rootHistory = Paths.get(root + "history");
		MAX_ENTRIES = calcConfig.getFileMaxLog();
		objectMapper = new ObjectMapper();
	}

	@Override
	public String send(){
		return "HistoryFileService";
	}

	private void createDirectory() {
		try {
			if (Files.notExists(rootHistory))
				Files.createDirectory(rootHistory);
		}
		catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new CreateFolderException();
		}
	}

	private int getLastFileNumber() {
		List<String> files = listFiles();
		return files.stream()
				.filter((name) -> name.startsWith(LOG_FILE_NAME))
				.map((currentFile) -> {
					if (currentFile.endsWith("txt")) {
						currentFile = LOG_FILE_NAME + ".0";
					}
					return currentFile;
				})
				.map((end) -> end.split("\\."))
				.map(number -> Integer.parseInt(number[2])).max(Comparator.naturalOrder()).get();
	}

	private File getHistoryFile() {
		return new File(rootHistory + File.separator + LOG_FILE_NAME);
	}

	private boolean historyFileExists() {
		return getHistoryFile().exists();
	}

	private List<CalcOperation> readFromHistoryFile() throws IOException {
		return objectMapper.readValue(getHistoryFile(), new TypeReference<List<CalcOperation>>() {

		});
	}

	private FileOutputStream getHistoryFileForWriting() throws FileNotFoundException {
		return new FileOutputStream(getHistoryFile());
	}

	@Override
	public void addOperation(CalcOperation calcOperation) {
		createDirectory();
		List<CalcOperation> list;
		try {
			if (historyFileExists()) {
				list = readFromHistoryFile();
			} else {
				list = new ArrayList<>();
			}
			list.add(calcOperation);

			FileOutputStream file = getHistoryFileForWriting();

			objectMapper.writeValue(file, list);
			file.close();
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new FileStorageException();
		}
		if (list.size() >= 50) {
			try {
				int lastFileNb = getLastFileNumber();
				int append = lastFileNb + 1;

				File currentFile = getHistoryFile();
				File archive = new File(rootHistory + File.separator + LOG_FILE_NAME + "." + append);

				Files.move(Paths.get(currentFile.getPath()), Paths.get(archive.getPath()));
				list.clear();

				FileOutputStream newFile = new FileOutputStream(getHistoryFile());
				objectMapper.writeValue(newFile, list);
				newFile.close();
			}
			catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new FileStorageException();
			}
		}
		operationsHistory.add(calcOperation);
	}

	@Override
	public void removeAll() {
		FileSystemUtils.deleteRecursively(rootHistory.toFile());
	}

	@Override
	public List<CalcOperation> gelAllOperations() {
		return operationsHistory;
	}

	public Resource getPossibleOperationsFile() {
		return operations;
	}

	@Override
	public List<CalcOperation> getOperationsFromRange(String filename, long start, long end) {
		try {
			File file = new File(rootHistory + File.separator + filename);
			if (file.exists() || file.canRead()) {
				return objectMapper.readValue(file, new TypeReference<List<CalcOperation>>() {

				});
			}
		}
		catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new FilesLoadException();
		}
		throw new FilesLoadException();
	}

	public List<String> listFiles() {
		try {
			return Files.walk(this.rootHistory, 1).filter(path -> !path.equals(this.rootHistory))
					.map(this.rootHistory::relativize).map(path -> path.getFileName().toString())
					.collect(Collectors.toList());
		}
		catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new FilesLoadException();
		}
	}

}