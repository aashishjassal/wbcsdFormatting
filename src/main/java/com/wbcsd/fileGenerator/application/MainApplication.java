package com.wbcsd.fileGenerator.application;

import java.util.List;
import java.util.Map;

import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.application.util.BlockCategarizationGenerator;
import com.wbcsd.fileGenerator.application.util.CommandLineParameters;
import com.wbcsd.fileGenerator.application.util.CommandLineReader;
import com.wbcsd.fileGenerator.application.util.DamDataGenerator;
import com.wbcsd.fileGenerator.application.util.GroundWaterOtherIndicatorsGenerator;
import com.wbcsd.fileGenerator.application.util.GroundWaterQualityGenerator;
import com.wbcsd.fileGenerator.application.util.RainfallDataGenerator;
import com.wbcsd.fileGenerator.application.util.SiteDataMapPopulator;
import com.wbcsd.fileGenerator.application.util.SiteDataMapPopulator_PrimaryInfoAsKey;
import com.wbcsd.fileGenerator.application.util.SiteInfoGenerator;
import com.wbcsd.fileGenerator.application.util.SurfaceWaterQualityGenerator;
import com.wbcsd.fileGenerator.application.util.WaterStatsGenerator;
import com.wbcsd.fileGenerator.application.util.WaterStatsGenerator_Pre2011;
import com.wbcsd.fileGenerator.excel.CSVDataReader;
import com.wbcsd.fileGenerator.excel.DamDataReader;
import com.wbcsd.fileGenerator.excel.GroundWaterQualityDataReader;
import com.wbcsd.fileGenerator.excel.RainfallDataReader;
import com.wbcsd.fileGenerator.excel.TextDataReader;

/**
 * The Class MainApplication.
 */
public class MainApplication {

    /** The reader. */
    private final CommandLineReader reader;

    private final CSVDataReader dataReader;

    private final TextDataReader textDataReader;

    private final SiteInfoGenerator siteInfoGenerator;

    private final WaterStatsGenerator waterStatsGenerator;

    private final WaterStatsGenerator_Pre2011 waterStatsGenerator_2007Onwards;

    private final SiteDataMapPopulator populator;

    private final SiteDataMapPopulator_PrimaryInfoAsKey populator_PrimaryInfoAsKey;

    private final BlockCategarizationGenerator blockCategarizationGenerator;

    private final GroundWaterOtherIndicatorsGenerator groundWaterOtherIndicatorsGenerator;

    private final DamDataReader damDataReader;

    private final DamDataGenerator damDataGenerator;

    private final RainfallDataReader rainfallDataReader;

    private final RainfallDataGenerator rainfallDataGenerator;

    private final SurfaceWaterQualityGenerator surfaceWaterQualityGenerator;

    private final GroundWaterQualityDataReader groundWaterQualityDataReader;

    private final GroundWaterQualityGenerator groundWaterQualityGenerator;

    /**
     * @param reader
     */
    public MainApplication() {
        this.reader = new CommandLineReader();
        dataReader = new CSVDataReader();
        textDataReader = new TextDataReader();
        siteInfoGenerator = new SiteInfoGenerator();
        waterStatsGenerator = new WaterStatsGenerator();
        waterStatsGenerator_2007Onwards = new WaterStatsGenerator_Pre2011();
        populator = new SiteDataMapPopulator();
        populator_PrimaryInfoAsKey = new SiteDataMapPopulator_PrimaryInfoAsKey();
        blockCategarizationGenerator = new BlockCategarizationGenerator();
        groundWaterOtherIndicatorsGenerator = new GroundWaterOtherIndicatorsGenerator();
        damDataReader = new DamDataReader(dataReader);
        damDataGenerator = new DamDataGenerator();
        rainfallDataReader = new RainfallDataReader(dataReader, textDataReader);
        rainfallDataGenerator = new RainfallDataGenerator();
        surfaceWaterQualityGenerator = new SurfaceWaterQualityGenerator();

        groundWaterQualityDataReader = new GroundWaterQualityDataReader(dataReader);
        groundWaterQualityGenerator = new GroundWaterQualityGenerator();
    }

    public void execute(String[] args) {
        CommandLineParameters parameters = reader.readCommandLineInfo(args);

        System.out.println(parameters);

        switch (parameters.getOperation()) {
            case SITE_INFO_GENERATION:
                System.out.println("site info generation");
                List<String[]> fileData = dataReader.readFileData(parameters.getFilePath());
                siteInfoGenerator.generate(fileData, parameters.getGenerationPath());
                break;

            case WATER_STATS_GENERATION_2012_ONWARDS:
                System.out.println("Water status generation for year 2012 onwards");
                List<String[]> siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                Map<String, SiteInfo> siteDataMap = populator.populate(siteDileData);
                List<String[]> waterFileData = dataReader.readFileData(parameters.getFilePath());
                waterStatsGenerator.generate(siteDataMap, waterFileData, parameters.getGenerationPath());
                break;
            case WATER_STATS_GENERATION_2007_ONWARDS:
                System.out
                        .println("Water status generation for year 2007 onwards, file does not contain lat long. Fetch it from SiteInfo.csv");
                siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                siteDataMap = populator_PrimaryInfoAsKey.populate(siteDileData);
                Map<String, SiteInfo> siteDataMap_IDBased = populator.populate(siteDileData);
                waterFileData = dataReader.readFileData(parameters.getFilePath());
                List<String[]> waterFileData2007 = dataReader.readFileData(parameters.getFilePath2007());
                waterStatsGenerator_2007Onwards.generate(siteDataMap_IDBased, siteDataMap, waterFileData,
                        waterFileData2007, parameters.getGenerationPath());
                break;
            case BLOCK_CATEGARIZATION:
                System.out.println("Block categarization, file does not contain lat long. Fetch it from SiteInfo.csv");
                siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                siteDataMap = populator_PrimaryInfoAsKey.populate(siteDileData);
                List<String[]> blockCategarization2004Data = dataReader.readFileData(parameters.getBc2004Path());
                List<String[]> blockCategarization2009Data = dataReader.readFileData(parameters.getBc2009Path());
                List<String[]> blockCategarization2011Data = dataReader.readFileData(parameters.getBc2011Path());
                blockCategarizationGenerator.generate(siteDataMap, blockCategarization2004Data,
                        blockCategarization2009Data, blockCategarization2011Data, parameters.getGenerationPath());
                break;

            case GROUND_WATER_OTHER_INDICATORS:
                System.out.println("Ground water other indicators.");
                siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                siteDataMap = populator_PrimaryInfoAsKey.populate(siteDileData);
                List<String[]> gWaterOtherIndicatorsData = dataReader.readFileData(parameters
                        .getgWaterOtherIndicatorsPath());
                groundWaterOtherIndicatorsGenerator.generate(siteDataMap, gWaterOtherIndicatorsData,
                        parameters.getGenerationPath());
                break;
            case DAM_DATA:
                System.out.println("Dam data indicators.");
                Map<State, List<String[]>> damData = damDataReader.readDamData(parameters.getDamDataFolderPath());
                damDataGenerator.generate(damData, parameters.getGenerationPath());
                break;
            case RAINFALL_DATA:
                System.out.println("Rainfall data indicators.");
                siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                siteDataMap = populator_PrimaryInfoAsKey.populate(siteDileData);
                Map<State, List<String[]>> rainfallData = rainfallDataReader.readRainfallData(parameters
                        .getRainfallDataFolderPath());
                Map<State, List<List<String>>> rainfallLatestData = rainfallDataReader
                        .readLatestRainfallData(parameters.getRainfallLatestDataFolderPath());
                rainfallDataGenerator.generate(siteDataMap, rainfallData, rainfallLatestData,
                        parameters.getGenerationPath());
                break;

            case SURFACE_WATER_QUALITY:
                System.out.println("Surface water quality.");
                siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                siteDataMap = populator_PrimaryInfoAsKey.populate(siteDileData);
                List<String[]> groundWaterDate = dataReader.readFileData(parameters.getSurfaceWaterQualityPath());
                surfaceWaterQualityGenerator.generate(siteDataMap, groundWaterDate, parameters.getGenerationPath());
                break;

            case GROUND_WATER_QUALITY:
                System.out.println("Ground water quality.");
                siteDileData = dataReader.readFileData(parameters.getSiteDataPath());
                siteDataMap = populator_PrimaryInfoAsKey.populate(siteDileData);
                Map<String, List<String[]>> groundWaterQualityMap = groundWaterQualityDataReader
                        .readGroundWaterQualityData(parameters.getGroundWaterQualityPath());
                groundWaterQualityGenerator
                        .generate(siteDataMap, groundWaterQualityMap, parameters.getGenerationPath());
                break;
            default:
                break;
        }

        System.out.println("ALL DONE");
    }

    /**
     * The main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting application..Reading command line arguments");
        MainApplication application = new MainApplication();
        application.execute(args);
    }

}
