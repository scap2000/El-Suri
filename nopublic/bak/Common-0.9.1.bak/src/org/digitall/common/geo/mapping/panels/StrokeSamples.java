package org.digitall.common.geo.mapping.panels;

import java.awt.BasicStroke;

import org.jfree.ui.StrokeSample;

public abstract class StrokeSamples {

     public static StrokeSample[] strokeSamples = new StrokeSample[] { 
		 new StrokeSample(new BasicStroke(1)),
		 new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			 new float[] {1f, 5f, 1f, 5f}, 0)),
		 new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			 new float[] {1f, 10f, 1f, 10f}, 0)),
		 new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			 new float[] {1f, 15f, 1f, 15f}, 0)),
		 new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			 new float[] {10f, 5f, 10f, 5f}, 0)),
		new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			new float[] {10f, 10f, 10f, 10f}, 0)),
		new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			new float[] {20f, 10f, 1f, 10f}, 0)),
		 new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			 new float[] {15f, 10f, 15f, 10f}, 0)),
		 new StrokeSample(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f,
			 new float[] {10f, 10f, 1f, 5f, 1f, 10f}, 0))
		 };

}
