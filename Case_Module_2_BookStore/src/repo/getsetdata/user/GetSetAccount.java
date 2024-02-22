package repo.getsetdata.user;

import repo.getsetdata.GetSetData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class GetSetAccount implements GetSetData {
    File file = new File("src/data/accounts.csv");

    @Override
    public List<String> getData() {
        if (Files.exists(file.toPath())) {
            try {
                return Files.readAllLines(file.toPath(),
                        StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            try {
                Files.createFile(file.toPath());
                return getData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void setData(String entity) {
        try {
            Files.writeString(file.toPath(),
                    entity,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearData() {
        try {
            Files.writeString(file.toPath(),
                    "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
