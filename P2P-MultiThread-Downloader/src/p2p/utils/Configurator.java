package p2p.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import p2p.environment.Environment;

public class Configurator {
	private static String host;
	private static String port;
	private static String serverHost;
	private static String serverPort;
	private static String subTaskSize;
	private static String coreThreadPoolSize;
	private static String maxThreadPoolSize;
	
	public static void init() {
		Pattern patternHost = Pattern.compile("host=([^\\s]+)");
		Pattern patternPort = Pattern.compile("port=([^\\s]+)");
		Pattern patternServerHost = Pattern.compile("serverHost=([^\\s]+)");
		Pattern patternServerPort = Pattern.compile("serverPort=([^\\s]+)");
		Pattern patternSubTaskSize = Pattern.compile("subTaskSize=([^\\s]+)");
		Pattern patternCoreThreadPoolSize = Pattern.compile("coreThreadPoolSize=([^\\s]+)");
		Pattern patternMaxThreadPoolSize = Pattern.compile("maxThreadPoolSize=([^\\s]+)");
		Matcher matcherHost;
		Matcher matcherPort;
		Matcher matcherServerHost;
		Matcher matcherServerPort;
		Matcher matcherSubTaskSize;
		Matcher matcherCoreThreadPoolSize;
		Matcher matcherMaxThreadPoolSize;
		
		try {
			//			打包时需修改
			File file = new File((new File("").getAbsolutePath()) + Environment.cfgPath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			while((tempString = reader.readLine()) != null) {
				matcherHost = patternHost.matcher(tempString);
				matcherPort = patternPort.matcher(tempString);
				matcherServerHost = patternServerHost.matcher(tempString);
				matcherServerPort = patternServerPort.matcher(tempString);
				matcherSubTaskSize = patternSubTaskSize.matcher(tempString);
				matcherCoreThreadPoolSize = patternCoreThreadPoolSize.matcher(tempString);
				matcherMaxThreadPoolSize = patternMaxThreadPoolSize.matcher(tempString);

				if(matcherHost.find()) {
					host = matcherHost.group(1);
				}
				if(matcherPort.find()) {
					port = matcherPort.group(1);
				}
				if(matcherServerHost.find()) {
					serverHost = matcherServerHost.group(1);
				}
				if(matcherServerPort.find()) {
					serverPort = matcherServerPort.group(1);
				}
				if(matcherSubTaskSize.find()) {
					subTaskSize = matcherSubTaskSize.group(1);
				}
				if(matcherCoreThreadPoolSize.find()) {
					coreThreadPoolSize = matcherCoreThreadPoolSize.group(1);
				}
				if(matcherMaxThreadPoolSize.find()) {
					maxThreadPoolSize = matcherMaxThreadPoolSize.group(1);
				}
			}

			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modify(String host, String port, String serverHost, String serverPort, 
			String subTaskSize, String coreThreadPoolSize, String maxThreadPoolSize) {
		try{
			File file = new File((new File("").getAbsolutePath()) + Environment.cfgPath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("host=" + host);
			writer.newLine();
			writer.write("port=" + port);
			writer.newLine();
			writer.write("serverHost=" + serverHost);
			writer.newLine();
			writer.write("serverPort=" + serverPort);
			writer.newLine();
			writer.write("subTaskSize=" + subTaskSize);
			writer.newLine();
			writer.write("coreThreadPoolSize=" + coreThreadPoolSize);
			writer.newLine();
			writer.write("maxThreadPoolSize=" + maxThreadPoolSize);
			writer.flush();
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		Configurator.init();
	}

	public static String getHost() {
		return host;
	}

	public static String getPort() {
		return port;
	}

	public static String getServerHost() {
		return serverHost;
	}

	public static String getServerPort() {
		return serverPort;
	}

	public static String getSubTaskSize() {
		return subTaskSize;
	}

	public static String getCoreThreadPoolSize() {
		return coreThreadPoolSize;
	}

	public static String getMaxThreadPoolSize() {
		return maxThreadPoolSize;
	}
}
