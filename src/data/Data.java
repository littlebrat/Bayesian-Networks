package data;

import java.io.IOException;

public interface Data {
	public int[][] get();
	public String[] getNames(String url) throws IOException;
}
