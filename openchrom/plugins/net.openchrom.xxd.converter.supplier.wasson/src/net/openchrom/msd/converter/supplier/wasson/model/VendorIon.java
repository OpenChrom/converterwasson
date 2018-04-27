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
package net.openchrom.msd.converter.supplier.wasson.model;

import org.eclipse.chemclipse.model.exceptions.AbundanceLimitExceededException;
import org.eclipse.chemclipse.msd.model.core.AbstractScanIon;
import org.eclipse.chemclipse.msd.model.exceptions.IonLimitExceededException;

public class VendorIon extends AbstractScanIon implements IVendorIon {

	private static final long serialVersionUID = 5403500392782803963L;
	//
	public static final float MIN_ABUNDANCE = Float.MIN_VALUE;
	public static final float MAX_ABUNDANCE = Float.MAX_VALUE;
	public static final double MIN_ION = 1.0d;
	public static final double MAX_ION = Double.MAX_VALUE;

	public VendorIon(double ion) throws IonLimitExceededException {
		super(ion);
	}

	public VendorIon(double ion, boolean ignoreAbundanceLimit) throws IonLimitExceededException {
		super(ion);
		setIgnoreAbundanceLimit(ignoreAbundanceLimit);
	}

	public VendorIon(double ion, float abundance) throws AbundanceLimitExceededException, IonLimitExceededException {
		super(ion, abundance);
	}

	@Override
	public float getMaxPossibleAbundanceValue() {

		return MAX_ABUNDANCE;
	}

	@Override
	public double getMaxPossibleIonValue() {

		return MAX_ION;
	}

	@Override
	public float getMinPossibleAbundanceValue() {

		return MIN_ABUNDANCE;
	}

	@Override
	public double getMinPossibleIonValue() {

		return MIN_ION;
	}
}
