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
package net.openchrom.xxd.converter.supplier.wasson.io.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.chemclipse.converter.io.support.AbstractArrayReader;

public class DataArrayReader extends AbstractArrayReader {

	public DataArrayReader(File file) throws FileNotFoundException, IOException {
		super(file);
	}
}
