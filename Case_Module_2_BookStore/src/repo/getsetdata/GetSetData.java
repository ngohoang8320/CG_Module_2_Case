package repo.getsetdata;

import java.util.List;

public interface GetSetData {
    List<String> getData();

    void setData(String entity);

    void clearData();
}
