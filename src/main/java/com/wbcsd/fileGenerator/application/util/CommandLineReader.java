package com.wbcsd.fileGenerator.application.util;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.wbcsd.fileGenerator.api.Operation;

/**
 * @author A.Jassal
 * @version $Id: $
 */
public class CommandLineReader {

	/** parser. */
	private final CommandLineParser parser;

	/** Options. */
	private final Options options;

	/** CommandLineParameters. */
	private CommandLineParameters commLineParam;

	/**
	 * Instantiates a new ran sys command line reader.
	 * 
	 * @param logger
	 *            the logger
	 */
	public CommandLineReader() {
		parser = new BasicParser();
		options = new Options();
	}

	/**
	 * Read command line info.
	 * 
	 * @param args
	 *            the args
	 * @return the command line parameters
	 */
	public CommandLineParameters readCommandLineInfo(final String[] args) {
		String operationStr = "";
		String filePath = "";
		String filePath2007 = "";
		String generationPath = "";
		String siteDataPath = "";
		String bc2004Path = "";
		String bc2009Path = "";
		String bc2011Path = "";
		String gWaterOtherIndicatorsPath = "";
		String damDataFolderPath = "";
		String rainfallDataFolderPath = "";
		String rainfallLatestDataFolderPath = "";
		String surfaceWaterQualityPath = "";
		String groundWaterQualityPath = "";

		options.addOption("o", "Operation", true, "Operation");
		options.addOption("f", "File Path", true, "File Path");
		options.addOption("r", "Site data Path", true, "Site data Path");
		options.addOption("s", "Generation path", true, "Generation path");
		options.addOption("b", "2007 file path", true, "2007 file path");
		options.addOption("bc4", "2004 file path", true, "2004 file path");
		options.addOption("bc9", "2009 file path", true, "2009 file path");
		options.addOption("bc11", "2011 file path", true, "2011 file path");
		options.addOption("gwoi", "Ground water other indicators", true, "Ground water other indicators");
		options.addOption("dd", "Dam Data", true, "Dam Data");
		options.addOption("rf", "Rainfall Data", true, "Rainfall Data");
		options.addOption("rfL", "Rainfall Latest Data", true, "Rainfall Latest Data");
		options.addOption("sw", "Surface water", true, "Surface water");
		options.addOption("gw", "Ground water", true, "Ground water");

		try {
			CommandLine commandLine = parser.parse(options, args);
			if (commandLine.hasOption('o')) {
				operationStr = commandLine.getOptionValue('o');
			}
			if (commandLine.hasOption('f')) {
				filePath = commandLine.getOptionValue('f');
			}
			if (commandLine.hasOption('s')) {
				generationPath = commandLine.getOptionValue('s');
			}
			if (commandLine.hasOption('r')) {
				siteDataPath = commandLine.getOptionValue('r');
			}
			if (commandLine.hasOption('b')) {
				filePath2007 = commandLine.getOptionValue('b');
			}
			if (commandLine.hasOption("bc4")) {
				bc2004Path = commandLine.getOptionValue("bc4");
			}
			if (commandLine.hasOption("bc9")) {
				bc2009Path = commandLine.getOptionValue("bc9");
			}
			if (commandLine.hasOption("bc11")) {
				bc2011Path = commandLine.getOptionValue("bc11");
			}
			if (commandLine.hasOption("gwoi")) {
				gWaterOtherIndicatorsPath = commandLine.getOptionValue("gwoi");
			}
			if (commandLine.hasOption("dd")) {
				damDataFolderPath = commandLine.getOptionValue("dd");
			}
			if (commandLine.hasOption("rf")) {
				rainfallDataFolderPath = commandLine.getOptionValue("rf");
			}
			if (commandLine.hasOption("rfL")) {
				rainfallLatestDataFolderPath = commandLine.getOptionValue("rfL");
			}
			if (commandLine.hasOption("sw")) {
				surfaceWaterQualityPath = commandLine.getOptionValue("sw");
			}
			if (commandLine.hasOption("gw")) {
				groundWaterQualityPath = commandLine.getOptionValue("gw");
			}
			Operation operation = Operation.value(operationStr);
			commLineParam = new CommandLineParameters(operation, filePath, generationPath, siteDataPath, filePath2007,
					bc2004Path, bc2009Path, bc2011Path, gWaterOtherIndicatorsPath, damDataFolderPath,
					rainfallDataFolderPath, rainfallLatestDataFolderPath, surfaceWaterQualityPath,
					groundWaterQualityPath);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return commLineParam;
	}

	public static void main(String[] args) {
		System.out.println("Dadra And Nagar Haveli".toUpperCase());
	}
}
