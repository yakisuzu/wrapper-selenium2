package support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProcessBuilderUtils {
	private static Logger LOG = LoggerFactory.getLogger(ProcessBuilderUtils.class);

	public static void killProcessIe() {
		ProcessBuilderUtils.killProcess("IEDriverServer.exe");
		ProcessBuilderUtils.killProcess("iexplore.exe");
	}

	public static void killProcess(String processName) {
		// TODO windows以外の対応
		if (!PlatformUtils.isWindows()) {
			return;
		}

		String[] comTasklist = {"tasklist", "/fi", "\"imagename eq " + processName + "\""};
		Process process = exeProcess(comTasklist);

		InputStream is = process.getInputStream();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.matches("^" + processName + ".+")) {
					continue;
				}

				String[] sp = line.split("\\s+");
				LOG.info("target is [" + Arrays.toString(sp) + "]");

				String[] comTaskkill = {"taskkill", "/pid", sp[1], "/f"};
				exeProcess(comTaskkill);
			}
		} catch (IOException e) {
			LOG.error("標準出力取得エラー", e);
		}
	}

	public static Process exeProcess(String[] com) {
		ProcessBuilder pb = new ProcessBuilder(com);
		pb.redirectErrorStream(true);
		LOG.info("process is [" + Arrays.toString(com) + "]");

		Process process = null;
		try {
			process = pb.start();
			process.getOutputStream().close();
		} catch (IOException e) {
			LOG.error("プロセス実行エラー", e);
		}
		return process;
	}
}
