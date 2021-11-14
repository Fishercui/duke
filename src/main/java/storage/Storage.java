package storage;

import task.TaskList;
import exception.IllegalValueException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileNotFoundException;

public class Storage {

    public final Path path;



    public Storage(String filePath) throws InvalidStorageFilePathException{
        path= Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }


    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }


    public void save(TaskList taskList) throws StorageOperationException{
        try{
            List<String> encodedTaskList=TaskListEncorder.encodeTaskList(taskList);
            Files.write(path,encodedTaskList);
        }catch (IOException ioe)
        {
            throw new StorageOperationException("Saving went wrong");
        }

    }



    public TaskList load() throws StorageOperationException, IOException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return new TaskList();
        }
        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        }catch (IOException ioe){
            throw new StorageOperationException("Loading went wrong");
        }catch (IllegalValueException ive){
            throw new StorageOperationException("File contains incorrect format.");
        }

    }


    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }



    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }


}
