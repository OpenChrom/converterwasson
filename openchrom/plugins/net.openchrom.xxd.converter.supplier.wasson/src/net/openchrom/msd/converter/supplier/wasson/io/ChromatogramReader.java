/*******************************************************************************
 * Copyright (c) 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *
 * Dr. Philip Wenig - initial API and implementation
 * R. Chip Wasson - initial API and implementation
 *******************************************************************************/
package net.openchrom.msd.converter.supplier.wasson.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.chemclipse.converter.exceptions.FileIsEmptyException;
import org.eclipse.chemclipse.converter.exceptions.FileIsNotReadableException;
import org.eclipse.chemclipse.logging.core.Logger;
import org.eclipse.chemclipse.model.core.IChromatogramOverview;
import org.eclipse.chemclipse.model.exceptions.AbundanceLimitExceededException;
import org.eclipse.chemclipse.msd.converter.io.AbstractChromatogramMSDReader;
import org.eclipse.chemclipse.msd.model.core.AbstractIon;
import org.eclipse.chemclipse.msd.model.core.IChromatogramMSD;
import org.eclipse.chemclipse.msd.model.exceptions.IonLimitExceededException;
import org.eclipse.core.runtime.IProgressMonitor;

import net.openchrom.msd.converter.supplier.wasson.model.IVendorChromatogram;
import net.openchrom.msd.converter.supplier.wasson.model.IVendorScan;
import net.openchrom.msd.converter.supplier.wasson.model.VendorChromatogram;
import net.openchrom.msd.converter.supplier.wasson.model.VendorIon;
import net.openchrom.msd.converter.supplier.wasson.model.VendorScan;

public class ChromatogramReader extends AbstractChromatogramMSDReader {

	private static final Logger logger = Logger.getLogger(ChromatogramReader.class);

	@Override
	public IChromatogramMSD read(File file, IProgressMonitor monitor) throws FileNotFoundException, FileIsNotReadableException, FileIsEmptyException, IOException {

		return parseChromatogram(file, false, monitor);
	}

	@Override
	public IChromatogramOverview readOverview(File file, IProgressMonitor monitor) throws FileNotFoundException, FileIsNotReadableException, FileIsEmptyException, IOException {

		return parseChromatogram(file, true, monitor);
	}

	private IChromatogramMSD parseChromatogram(File file, boolean overview, IProgressMonitor monitor) throws FileNotFoundException, FileIsNotReadableException, FileIsEmptyException, IOException {

		// DataArrayReader dataArrayReader = new DataArrayReader(file);
		// float value1 = dataArrayReader.read4BFloatLE();
		// double value2 = Double.longBitsToDouble(dataArrayReader.read8BLongLE());
		//
		int scanInterval = 500;
		IVendorChromatogram chromatogram = new VendorChromatogram();
		chromatogram.setScanDelay(0);
		chromatogram.setScanInterval(scanInterval);
		//
		int retentionTime = 0; // Milliseconds
		if(overview) {
			/*
			 * Overview
			 */
			for(int i = 0; i < 100; i++) {
				try {
					IVendorScan vendorScan = new VendorScan();
					vendorScan.setRetentionTime(retentionTime);
					vendorScan.addIon(new VendorIon(AbstractIon.TIC_ION, getRandomIntensity()));
					chromatogram.addScan(vendorScan);
					retentionTime += scanInterval;
				} catch(AbundanceLimitExceededException
						| IonLimitExceededException e) {
					logger.warn(e);
				}
			}
		} else {
			/*
			 * Complete
			 */
			for(int i = 0; i < 100; i++) {
				try {
					IVendorScan vendorScan = new VendorScan();
					vendorScan.setRetentionTime(retentionTime);
					for(int mz = 25; mz <= 45; mz++) {
						vendorScan.addIon(new VendorIon(mz, getRandomIntensity()));
					}
					chromatogram.addScan(vendorScan);
					retentionTime += scanInterval;
				} catch(AbundanceLimitExceededException
						| IonLimitExceededException e) {
					logger.warn(e);
				}
			}
		}
		//
		return chromatogram;
	}

	private float getRandomIntensity() {

		float value = 0.0f;
		while(value == 0.0f) {
			value = (float)Math.random() * 1000;
		}
		return value;
	}
}
