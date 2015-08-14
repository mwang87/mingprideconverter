package edu.ucsd.mingMZIDtoMzTab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.ac.ebi.pride.jmztab.model.MZTabFile;
import uk.ac.ebi.pride.utilities.data.controller.impl.ControllerImpl.MzIdentMLControllerImpl;
import uk.ac.ebi.pride.utilities.data.exporters.AbstractMzTabConverter;
import uk.ac.ebi.pride.utilities.data.exporters.MzIdentMLMzTabConverter;

public class MingMZIDToMZTab {

	public static void main( String[] args ) throws Exception
    {
		convertMZIdToMzTab(args[0], args[1]);
    }
	
	
	public static int convertMZIdToMzTab(String mzIDFilename, String output_mzTab) throws IOException{
		File inputFile = new File(mzIDFilename);
		MzIdentMLControllerImpl mzIdentMLController = new MzIdentMLControllerImpl(inputFile);
		AbstractMzTabConverter mzTabconverter = new MzIdentMLMzTabConverter(mzIdentMLController);
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
}
