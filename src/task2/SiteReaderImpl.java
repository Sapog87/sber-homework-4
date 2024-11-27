package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SiteReaderImpl implements SiteReader {

    @Override
    public String readContent(String url) throws IOException {
        URL u = new URL(url);
        StringBuilder siteInfo = new StringBuilder();
        try (BufferedReader urlReader = new BufferedReader(new InputStreamReader(u.openStream()))) {
            String line;
            while ((line = urlReader.readLine()) != null) {
                siteInfo.append(line);
                siteInfo.append('\n');
            }
        }
        return siteInfo.toString();
    }
}
