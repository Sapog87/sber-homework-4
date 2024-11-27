package task2;

import java.io.IOException;

public interface SiteReader {
    String readContent(String url) throws IOException;
}
