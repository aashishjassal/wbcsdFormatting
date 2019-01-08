package com.wbcsd.fileGenerator.application.util;

import com.wbcsd.fileGenerator.api.Operation;

/**
 * The Class CommandLineParameters.
 * 
 * @author A.Jassal
 * @version $Id: $
 */
public class CommandLineParameters {

	/** Record type. **/
	private final Operation operation;

	/** File path. **/
	private final String filePath;

	/** The file path2007. */
	private final String filePath2007;

	/** The csv generation path. */
	private final String generationPath;

	/** The site data path. */
	private final String siteDataPath;

	/** The bc2004 path. */
	private final String bc2004Path;

	/** The bc2009 path. */
	private final String bc2009Path;

	/** The bc2011 path. */
	private final String bc2011Path;

	/** The g water other indicators path. */
	private final String gWaterOtherIndicatorsPath;

	/** The dam data folder path. */
	private final String damDataFolderPath;

	/** The rainfall data folder path. */
	private final String rainfallDataFolderPath;

	/** The rainfall latest data folder path. */
	private final String rainfallLatestDataFolderPath;

	/** The ground water quality path. */
	private final String surfaceWaterQualityPath;

	private final String groundWaterQualityPath;
	private final String monsoon2016_17Path;

	/**
	 * Constructor.
	 * 
	 * @param pOperation                   the record type
	 * @param pFilePath                    the file path
	 * @param csvGenerationPath            the csv generation path
	 * @param siteDataPath                 the site data path
	 * @param filePath2007                 the file path2007
	 * @param bc2004Path                   the bc2004 path
	 * @param bc2009Path                   the bc2009 path
	 * @param bc2011Path                   the bc2011 path
	 * @param gWaterOtherIndicatorsPath    the g water other indicators path
	 * @param damDataFolderPath            the dam data folder path
	 * @param rainfallDataFolderPath       the rainfall data folder path
	 * @param rainfallLatestDataFolderPath the rainfall latest data folder path
	 * @param surfaceWaterQualityPath      the ground water quality path
	 * @param groundWaterQualityPath       the ground water quality path
	 */
	public CommandLineParameters(final Operation pOperation, final String pFilePath, final String csvGenerationPath,
			final String siteDataPath, final String filePath2007, final String bc2004Path, final String bc2009Path,
			final String bc2011Path, final String gWaterOtherIndicatorsPath, String damDataFolderPath,
			final String rainfallDataFolderPath, final String rainfallLatestDataFolderPath,
			final String surfaceWaterQualityPath, final String groundWaterQualityPath,
			final String monsoon2016_17Path) {
		this.operation = pOperation;
		this.filePath = pFilePath;
		this.generationPath = csvGenerationPath;
		this.siteDataPath = siteDataPath;
		this.filePath2007 = filePath2007;
		this.bc2004Path = bc2004Path;
		this.bc2009Path = bc2009Path;
		this.bc2011Path = bc2011Path;
		this.gWaterOtherIndicatorsPath = gWaterOtherIndicatorsPath;
		this.damDataFolderPath = damDataFolderPath;
		this.rainfallDataFolderPath = rainfallDataFolderPath;
		this.rainfallLatestDataFolderPath = rainfallLatestDataFolderPath;
		this.surfaceWaterQualityPath = surfaceWaterQualityPath;
		this.groundWaterQualityPath = groundWaterQualityPath;
		this.monsoon2016_17Path = monsoon2016_17Path;
	}

	/**
	 * Gets the operation.
	 * 
	 * @return the operation
	 */
	public Operation getOperation() {
		return operation;
	}

	/**
	 * Gets the file path.
	 * 
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Gets the site info generation path.
	 * 
	 * @return the siteInfoGenerationPath
	 */
	public String getGenerationPath() {
		return generationPath;
	}

	/**
	 * Gets the site data path.
	 * 
	 * @return the siteDataPath
	 */
	public String getSiteDataPath() {
		return siteDataPath;
	}

	/**
	 * Gets the file path2007.
	 * 
	 * @return the filePath2007
	 */
	public String getFilePath2007() {
		return filePath2007;
	}

	/**
	 * Gets the bc2004 path.
	 * 
	 * @return the bc2004Path
	 */
	public String getBc2004Path() {
		return bc2004Path;
	}

	/**
	 * Gets the bc2009 path.
	 * 
	 * @return the bc2009Path
	 */
	public String getBc2009Path() {
		return bc2009Path;
	}

	/**
	 * Gets the bc2011 path.
	 * 
	 * @return the bc2011Path
	 */
	public String getBc2011Path() {
		return bc2011Path;
	}

	/**
	 * Gets the g water other indicators path.
	 * 
	 * @return the gWaterOtherIndicatorsPath
	 */
	public String getgWaterOtherIndicatorsPath() {
		return gWaterOtherIndicatorsPath;
	}

	/**
	 * Gets the dam data folder path.
	 * 
	 * @return the damDataFolderPath
	 */
	public String getDamDataFolderPath() {
		return damDataFolderPath;
	}

	/**
	 * Gets the rainfall data folder path.
	 * 
	 * @return the rainfallDataFolderPath
	 */
	public String getRainfallDataFolderPath() {
		return rainfallDataFolderPath;
	}

	/**
	 * Gets the rainfall latest data folder path.
	 * 
	 * @return the rainfallLatestDataFolderPath
	 */
	public String getRainfallLatestDataFolderPath() {
		return rainfallLatestDataFolderPath;
	}

	/**
	 * Gets the ground water quality path.
	 * 
	 * @return the groundWaterQualityPath
	 */
	public String getSurfaceWaterQualityPath() {
		return surfaceWaterQualityPath;
	}

	/**
	 * Gets the ground water quality path.
	 * 
	 * @return the groundWaterQualityPath
	 */
	public String getGroundWaterQualityPath() {
		return groundWaterQualityPath;
	}

	public String getMonsoon2016_17Path() {
		return monsoon2016_17Path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Operation: ").append(operation).append(" File Path: ").append(filePath)
				.append(" 2007 file path: ").append(filePath2007).append(" Generation path: ").append(generationPath);
		return builder.toString();
	}
}
