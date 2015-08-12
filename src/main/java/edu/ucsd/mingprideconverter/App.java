package edu.ucsd.mingprideconverter;

import java.io.File;

import uk.ac.ebi.pride.jmztab.model.MZTabFile;
import uk.ac.ebi.pride.jmztab.utils.MZTabFileConverter;

import uk.ac.ebi.pride.utilities.data.controller.impl.ControllerImpl.PrideXmlControllerImpl;
import uk.ac.ebi.pride.utilities.data.exporters.AbstractMzTabConverter;
import uk.ac.ebi.pride.utilities.data.exporters.MGFConverter;
import uk.ac.ebi.pride.utilities.data.exporters.PRIDEMzTabConverter;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(args[1]);
        String prideXMLFilename = args[1];
        //writeMGF(prideXMLFilename, "output.mgf");
        writeMzTab(prideXMLFilename, "output.mzTab");
    }
    
    public static int writeMzTab(String prideXMLFilename, String output_mzTab){
    	File inputFile = new File(prideXMLFilename);
    	PrideXmlControllerImpl prideController = new PrideXmlControllerImpl(inputFile);
    	AbstractMzTabConverter mzTabconverter = new PRIDEMzTabConverter(prideController);
    	MZTabFile mzTabFile = mzTabconverter.getMZTabFile();
        MZTabFileConverter checker = new MZTabFileConverter();
        checker.check(mzTabFile);
        System.out.println(mzTabFile.toString());
    	return 0;
    }
    
    public static int writeMGF(String prideXMLFilename, String output_MGF){
    	File inputFile = new File(prideXMLFilename);
    	PrideXmlControllerImpl prideController = new PrideXmlControllerImpl(inputFile);
    	MGFConverter converter = new MGFConverter(prideController, output_MGF);
    	return 0;
    }
}
