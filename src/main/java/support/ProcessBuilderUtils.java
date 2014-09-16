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

	public static void killProcess(String processName) {
		// TODO windows以外の対応
		if (!PlatformUtils.isWindows()) {
			return;
		}

		String[] comTasklist = {"tasklist", "/fi", "\"imagename eq " + processName + "\""};
		Process process = exeProcess(comTasklist);

		InputStream is = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			while (true) {
				String line = null;
				try {
					line = br.readLine();
				} catch (IOException e) {
					LOG.error("標準出力取得エラー", e);
				}
				if (line == null) {
					break;
				}

				if (!line.matches("^" + processName + ".+")) {
					continue;
				}

				String[] sp = line.split("\\s+");
				LOG.info("target is [" + Arrays.toString(sp) + "]");

				String[] comTaskkill = {"taskkill", "/pid", sp[1], "/t"};
				exeProcess(comTaskkill);
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				LOG.error("標準出力取得クローズエラー", e);
			}
		}
	}

	public static Process exeProcess(String[] com) {
		ProcessBuilder pb = new ProcessBuilder(com);
		Process process = null;
		LOG.info("process is [" + Arrays.toString(com) + "]");

		try {
			process = pb.start();
		} catch (IOException e) {
			LOG.error("プロセス実行エラー", e);
		}
		wait(process);
		return process;
	}

	private static void wait(Process pro) {
		try {
			pro.waitFor();
		} catch (InterruptedException e) {
			LOG.error("プロセス待ちエラー", e);
		}
	}
}
