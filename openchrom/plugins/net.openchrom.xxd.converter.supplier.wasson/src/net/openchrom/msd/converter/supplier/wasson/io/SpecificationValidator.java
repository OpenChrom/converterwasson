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

public class SpecificationValidator {

	private static final String MSD_EXTENSION = ".wfm";

	/**
	 * Use only static methods.
	 */
	private SpecificationValidator() {
	}

	public static File validateSpecification(File file) {

		if(file == null) {
			return null;
		}
		/*
		 * Validate
		 */
		File validFile = null;
		String path = file.getAbsolutePath().toLowerCase();
		if(file.isFile() && !path.endsWith(MSD_EXTENSION)) {
			validFile = new File(path + MSD_EXTENSION);
		}
		return validFile;
	}
}
