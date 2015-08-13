package edu.ucsd.mingprideconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.ac.ebi.pride.jmztab.model.MZTabFile;
import uk.ac.ebi.pride.jmztab.utils.MZTabFileConverter;

import uk.ac.ebi.pride.utilities.data.controller.DataAccessController;
import uk.ac.ebi.pride.utilities.data.controller.impl.ControllerImpl.PrideXmlControllerImpl;
import uk.ac.ebi.pride.utilities.data.exporters.AbstractMzTabConverter;
import uk.ac.ebi.pride.utilities.data.exporters.MGFConverter;
import uk.ac.ebi.pride.utilities.data.exporters.PRIDEMzTabConverter;



/**
 * Hello world!
 *
 */
public class MingPrideConverter extends MGFConverter
{

	public MingPrideConverter(DataAccessController controller, String outputFilePath) {
		super(controller, outputFilePath);
	}
	
	public void convertToMGF() throws Exception{
		this.convert();
	}

	public static void main( String[] args ) throws Exception
    {
        System.out.println(args[0]);
        String prideXMLFilename = args[0];
        writeMGF(prideXMLFilename, args[1]);
        writeMzTab(prideXMLFilename, args[2]);
    }
    
    public static int writeMzTab(String prideXMLFilename, String output_mzTab) throws IOException{
    	File inputFile = new File(prideXMLFilename);
    	PrideXmlControllerImpl prideController = new PrideXmlControllerImpl(inputFile);
    	AbstractMzTabConverter mzTabconverter = new PRIDEMzTabConverter(prideController);
    	MZTabFile mzTabFile = mzTabconverter.getMZTabFile();
    	
    	File file = new File(output_mzTab);
    	// if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
    	FileOutputStream fop = new FileOutputStream(file);
    	mzTabFile.printMZTab(fop);
		fop.close();
    	return 0;
    }
    
    public static int writeMGF(String prideXMLFilename, String output_MGF) throws Exception{
    	File inputFile = new File(prideXMLFilename);
    	PrideXmlControllerImpl prideController = new PrideXmlControllerImpl(inputFile);
    	MingPrideConverter converter = new MingPrideConverter(prideController, output_MGF);
    	converter.convertToMGF();
    	return 0;
    }
}
