import java.io.File;

public class FileListener {
	private static final String warFilePath = "/yjdata/www/www/Fragrans.war";

	private static long lastModifyTime = 0;

	public static boolean needRestartTomcat() {
		long current = getCurrentLastModifyTime();
		long previous = getPreviousLastModifyTime();
		boolean needRestart = current != previous;
		if (needRestart) {
			lastModifyTime = current;
		}
		return needRestart;
	}

	private static long getPreviousLastModifyTime() {
		return lastModifyTime;
	}

	private static long getCurrentLastModifyTime() {
		File file = new File(warFilePath);
		if (file.exists() && file.isFile()) {
			return file.lastModified();
		} else {
			return -1;
		}
	}

}
